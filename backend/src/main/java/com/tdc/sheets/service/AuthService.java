package com.tdc.sheets.service;

import com.tdc.sheets.dto.auth.LoginRequest;
import com.tdc.sheets.dto.auth.LoginResponse;
import com.tdc.sheets.dto.auth.RegisterRequest;
import com.tdc.sheets.entity.User;
import com.tdc.sheets.repository.UserRepository;
import com.tdc.sheets.security.JwtTokenProvider;
import com.tdc.sheets.security.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço para operações de autenticação
 * 
 * @author Victor Gabriel Soares
 * @since 2025-06-29
 */
@Service
@Transactional
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Value("${app.jwt.expiration}")
    private long jwtExpirationMs;

    /**
     * Autentica um usuário e retorna os tokens JWT
     */
    public LoginResponse authenticate(LoginRequest loginRequest) {
        logger.info("Tentativa de login para: {}", loginRequest.getUsernameOrEmail());

        try {
            // Autentica o usuário
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsernameOrEmail(),
                    loginRequest.getPassword()
                )
            );

            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

            // Gera os tokens
            String accessToken = tokenProvider.generateToken(authentication);
            String refreshToken = tokenProvider.generateRefreshToken(authentication);

            // Cria informações do usuário para resposta
            List<String> authorities = userPrincipal.getAuthorities().stream()
                    .map(grantedAuthority -> grantedAuthority.getAuthority())
                    .collect(Collectors.toList());

            LoginResponse.UserInfo userInfo = new LoginResponse.UserInfo(
                userPrincipal.getId(),
                userPrincipal.getUsername(),
                userPrincipal.getNomeCompleto(),
                userPrincipal.getEmail(),
                authorities
            );

            logger.info("Login bem-sucedido para usuário: {}", userPrincipal.getUsername());

            return new LoginResponse(accessToken, refreshToken, jwtExpirationMs / 1000, userInfo);

        } catch (AuthenticationException e) {
            logger.warn("Falha na autenticação para: {} - Motivo: {}", 
                       loginRequest.getUsernameOrEmail(), e.getMessage());
            throw new RuntimeException("Credenciais inválidas");
        }
    }

    /**
     * Registra um novo usuário
     */
    public LoginResponse register(RegisterRequest registerRequest) {
        logger.info("Tentativa de registro para: {}", registerRequest.getUsername());

        // Validações básicas
        if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
            throw new RuntimeException("As senhas não coincidem");
        }

        // Verifica se username já existe
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new RuntimeException("Username já está em uso");
        }

        // Verifica se email já existe
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Email já está em uso");
        }

        try {
            // Cria o novo usuário
            User user = new User();
            user.setNomeCompleto(registerRequest.getNomeCompleto());
            user.setUsername(registerRequest.getUsername());
            user.setEmail(registerRequest.getEmail());
            user.setSenha(passwordEncoder.encode(registerRequest.getPassword()));
            user.setAtivo(true);

            // TODO: Adicionar authority padrão (USER)
            // Isso será implementado quando tivermos os dados iniciais carregados
            
            // Salva o usuário
            user = userRepository.save(user);

            logger.info("Usuário registrado com sucesso: {}", user.getUsername());

            // Autentica automaticamente após registro
            LoginRequest loginRequest = new LoginRequest(registerRequest.getUsername(), registerRequest.getPassword());
            return authenticate(loginRequest);

        } catch (DataIntegrityViolationException e) {
            logger.error("Erro de integridade ao registrar usuário: {}", e.getMessage());
            throw new RuntimeException("Erro ao registrar usuário: dados duplicados");
        } catch (Exception e) {
            logger.error("Erro inesperado ao registrar usuário: {}", e.getMessage(), e);
            throw new RuntimeException("Erro interno do servidor");
        }
    }

    /**
     * Renova um token de acesso usando o refresh token
     */
    public LoginResponse refreshToken(String refreshToken) {
        logger.debug("Tentativa de renovação de token");

        try {
            if (!tokenProvider.validateToken(refreshToken)) {
                throw new RuntimeException("Refresh token inválido ou expirado");
            }

            String username = tokenProvider.getUsernameFromToken(refreshToken);
            Long userId = tokenProvider.getUserIdFromToken(refreshToken);
            List<String> authorities = tokenProvider.getAuthoritiesFromToken(refreshToken);

            // Verifica se o usuário ainda está ativo
            User user = userRepository.findByUsernameAndIsActiveTrue(username)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado ou inativo"));

            // Gera novos tokens
            String newAccessToken = tokenProvider.generateTokenFromUsername(
                username, userId, user.getNomeCompleto(), user.getEmail(), authorities);
            String newRefreshToken = tokenProvider.generateTokenFromUsername(
                username, userId, user.getNomeCompleto(), user.getEmail(), authorities);

            LoginResponse.UserInfo userInfo = new LoginResponse.UserInfo(
                userId, username, user.getNomeCompleto(), user.getEmail(), authorities);

            logger.debug("Token renovado com sucesso para usuário: {}", username);

            return new LoginResponse(newAccessToken, newRefreshToken, jwtExpirationMs / 1000, userInfo);

        } catch (Exception e) {
            logger.error("Erro ao renovar token: {}", e.getMessage());
            throw new RuntimeException("Erro ao renovar token de acesso");
        }
    }

    /**
     * Invalida um refresh token (logout)
     */
    public void logout(String refreshToken) {
        logger.debug("Logout solicitado");
        
        // TODO: Implementar blacklist de tokens ou armazenamento de refresh tokens
        // Por enquanto, apenas log do logout
        
        try {
            if (tokenProvider.validateToken(refreshToken)) {
                String username = tokenProvider.getUsernameFromToken(refreshToken);
                logger.info("Logout realizado para usuário: {}", username);
            }
        } catch (Exception e) {
            logger.warn("Erro ao processar logout: {}", e.getMessage());
        }
    }

    /**
     * Verifica se um token é válido
     */
    public boolean validateToken(String token) {
        return tokenProvider.validateToken(token);
    }
}

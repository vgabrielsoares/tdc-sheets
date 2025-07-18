package com.tdc.sheets.controller;

import com.tdc.sheets.dto.auth.LoginRequest;
import com.tdc.sheets.dto.auth.LoginResponse;
import com.tdc.sheets.dto.auth.RegisterRequest;
import com.tdc.sheets.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller para endpoints de autenticação
 * 
 * @author Victor Gabriel Soares
 * @since 2025-06-29
 */
@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Endpoints para autenticação de usuários")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    /**
     * Endpoint para login de usuário
     */
    @PostMapping("/login")
    @Operation(summary = "Login de usuário", description = "Autentica um usuário e retorna tokens de acesso")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            logger.info("Tentativa de login para: {}", loginRequest.getUsernameOrEmail());
            
            LoginResponse loginResponse = authService.authenticate(loginRequest);
            
            return ResponseEntity.ok(loginResponse);
            
        } catch (Exception e) {
            logger.error("Erro no login: {}", e.getMessage());
            
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Credenciais inválidas");
            errorResponse.put("message", e.getMessage());
            errorResponse.put("status", HttpStatus.UNAUTHORIZED.value());
            
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }

    /**
     * Endpoint para registro de novo usuário
     */
    @PostMapping("/register")
    @Operation(summary = "Registro de usuário", description = "Registra um novo usuário no sistema")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            logger.info("Tentativa de registro para: {}", registerRequest.getUsername());
            
            LoginResponse loginResponse = authService.register(registerRequest);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(loginResponse);
            
        } catch (Exception e) {
            logger.error("Erro no registro: {}", e.getMessage());
            
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erro no registro");
            errorResponse.put("message", e.getMessage());
            errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    /**
     * Endpoint para renovação de token
     */
    @PostMapping("/refresh")
    @Operation(summary = "Renovar token", description = "Renova o token de acesso usando refresh token")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> request) {
        try {
            String refreshToken = request.get("refreshToken");
            
            if (refreshToken == null || refreshToken.trim().isEmpty()) {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("error", "Refresh token é obrigatório");
                errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            }
            
            LoginResponse loginResponse = authService.refreshToken(refreshToken);
            
            return ResponseEntity.ok(loginResponse);
            
        } catch (Exception e) {
            logger.error("Erro ao renovar token: {}", e.getMessage());
            
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Token inválido");
            errorResponse.put("message", e.getMessage());
            errorResponse.put("status", HttpStatus.UNAUTHORIZED.value());
            
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }

    /**
     * Endpoint para logout
     */
    @PostMapping("/logout")
    @Operation(summary = "Logout", description = "Invalida o refresh token do usuário")
    public ResponseEntity<?> logout(@RequestBody Map<String, String> request) {
        try {
            String refreshToken = request.get("refreshToken");
            
            authService.logout(refreshToken);
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Logout realizado com sucesso");
            response.put("status", HttpStatus.OK.value());
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            logger.error("Erro no logout: {}", e.getMessage());
            
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erro no logout");
            errorResponse.put("message", e.getMessage());
            errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    /**
     * Endpoint para validação de token
     */
    @PostMapping("/validate")
    @Operation(summary = "Validar token", description = "Valida se um token de acesso é válido")
    public ResponseEntity<?> validateToken(@RequestBody Map<String, String> request) {
        try {
            String token = request.get("token");
            
            if (token == null || token.trim().isEmpty()) {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("error", "Token é obrigatório");
                errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            }
            
            boolean isValid = authService.validateToken(token);
            
            Map<String, Object> response = new HashMap<>();
            response.put("valid", isValid);
            response.put("status", HttpStatus.OK.value());
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            logger.error("Erro ao validar token: {}", e.getMessage());
            
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erro na validação");
            errorResponse.put("message", e.getMessage());
            errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    /**
     * Endpoint público para verificar saúde da API de autenticação
     */
    @GetMapping("/health")
    @Operation(summary = "Health check", description = "Verifica se a API de autenticação está funcionando")
    public ResponseEntity<?> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "TDC Sheets Authentication API");
        response.put("timestamp", System.currentTimeMillis());
        
        return ResponseEntity.ok(response);
    }
}

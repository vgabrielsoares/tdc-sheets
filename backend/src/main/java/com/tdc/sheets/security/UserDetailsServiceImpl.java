package com.tdc.sheets.security;

import com.tdc.sheets.entity.User;
import com.tdc.sheets.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementação customizada do UserDetailsService para Spring Security
 * 
 * @author Victor Gabriel Soares
 * @since 2025-06-29
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("Tentativa de autenticação para usuário: {}", username);

        User user = userRepository.findByUsernameAndIsActiveTrue(username)
                .orElseThrow(() -> {
                    logger.warn("Usuário não encontrado ou inativo: {}", username);
                    return new UsernameNotFoundException("Usuário não encontrado: " + username);
                });

        logger.debug("Usuário encontrado: {} - Authorities: {}", user.getUsername(), 
                     user.getAuthorities().size());

        return UserPrincipal.create(user);
    }

    /**
     * Carrega usuário por ID (útil para validação de token)
     */
    @Transactional(readOnly = true)
    public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
        logger.debug("Carregando usuário por ID: {}", id);

        User user = userRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> {
                    logger.warn("Usuário não encontrado ou inativo com ID: {}", id);
                    return new UsernameNotFoundException("Usuário não encontrado com ID: " + id);
                });

        logger.debug("Usuário carregado por ID: {} - Username: {}", id, user.getUsername());

        return UserPrincipal.create(user);
    }

    /**
     * Carrega usuário por email (útil para recuperação de senha)
     */
    @Transactional(readOnly = true)
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        logger.debug("Carregando usuário por email: {}", email);

        User user = userRepository.findByEmailAndIsActiveTrue(email)
                .orElseThrow(() -> {
                    logger.warn("Usuário não encontrado ou inativo com email: {}", email);
                    return new UsernameNotFoundException("Usuário não encontrado com email: " + email);
                });

        logger.debug("Usuário carregado por email: {} - Username: {}", email, user.getUsername());

        return UserPrincipal.create(user);
    }
}

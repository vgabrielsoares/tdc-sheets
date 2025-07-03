package com.tdc.sheets.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;

/**
 * Configuração JPA para auditoria, repositórios e transações
 * 
 * @author Victor Gabriel Soares
 * @since 2025-06-28
 */
@Configuration
@EnableJpaRepositories(
    basePackages = "com.tdc.sheets.repository",
    enableDefaultTransactions = true
)
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@EnableTransactionManagement
public class JpaConfig {
    
    /**
     * Provedor de auditor para auditoria automática de entidades
     * Obtém o usuário atual do contexto de segurança
     */
    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            
            if (authentication == null || !authentication.isAuthenticated() 
                || "anonymousUser".equals(authentication.getPrincipal())) {
                return Optional.of("system");
            }
            
            return Optional.of(authentication.getName());
        };
    }
}

package com.tdc.sheets.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
@EnableJpaAuditing
@EnableTransactionManagement
public class JpaConfig {
    
    // Configurações específicas de JPA podem ser adicionadas aqui
    // Como customizações de naming strategy, etc.
}

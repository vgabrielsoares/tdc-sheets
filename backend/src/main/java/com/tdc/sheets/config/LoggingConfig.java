package com.tdc.sheets.config;

import org.springframework.context.annotation.Configuration;

/**
 * Configuração de Logging para a aplicação
 * 
 * Logging de requisições é configurado no WebConfig.java via FilterRegistrationBean
 * Esta classe pode ser usada para configurações adicionais de logging como:
 * - Appenders customizados
 * - Formatação de logs estruturados 
 * - Configurações específicas de loggers
 * 
 * @author Victor Gabriel Soares
 * @since 2025-06-28
 */
@Configuration
public class LoggingConfig {
    
    // Configurações adicionais de logging podem ser adicionadas aqui
    // Por exemplo:
    // - @Bean para configurar appenders customizados
    // - @Bean para MDC (Mapped Diagnostic Context) configuration
    // - @Bean para configurações de logger específicos
    
}

package com.tdc.sheets.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

/**
 * Configuração de Logging para a aplicação
 * 
 * @author Victor Gabriel Soares
 * @since 2025-06-28
 */
@Configuration
public class LoggingConfig {
    
    /**
     * Configuração adicional de logging de requests
     * Complementa o filtro já configurado no WebConfig
     */
    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(false); // Por segurança
        filter.setMaxPayloadLength(10000);
        filter.setIncludeHeaders(false);
        filter.setAfterMessagePrefix("REQUEST DATA: ");
        return filter;
    }
}

package com.tdc.sheets.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Configuração Web para CORS, interceptors e outras configurações gerais da aplicação
 * 
 * @author Victor Gabriel Soares
 * @since 2025-06-28
 */
@Configuration
@ConfigurationProperties(prefix = "app.cors")
public class WebConfig implements WebMvcConfigurer {

    private String[] allowedOrigins;
    private String[] allowedMethods;
    private String allowedHeaders;
    private boolean allowCredentials;
    private long maxAge;
    
    // Getters and setters for ConfigurationProperties binding
    
    public String[] getAllowedOrigins() {
        return allowedOrigins;
    }
    
    public void setAllowedOrigins(String[] allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }
    
    public String[] getAllowedMethods() {
        return allowedMethods;
    }
    
    public void setAllowedMethods(String[] allowedMethods) {
        this.allowedMethods = allowedMethods;
    }
    
    public String getAllowedHeaders() {
        return allowedHeaders;
    }
    
    public void setAllowedHeaders(String allowedHeaders) {
        this.allowedHeaders = allowedHeaders;
    }
    
    public boolean isAllowCredentials() {
        return allowCredentials;
    }
    
    public void setAllowCredentials(boolean allowCredentials) {
        this.allowCredentials = allowCredentials;
    }
    
    public long getMaxAge() {
        return maxAge;
    }
    
    public void setMaxAge(long maxAge) {
        this.maxAge = maxAge;
    }

    /**
     * Configuração global de CORS
     */
    @Override
    public void addCorsMappings(@org.springframework.lang.NonNull CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(allowedOrigins)
                .allowedMethods(allowedMethods)
                .allowedHeaders(allowedHeaders)
                .allowCredentials(allowCredentials)
                .maxAge(maxAge);
    }

    /**
     * Bean para configuração CORS mais detalhada para Spring Security
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        configuration.setAllowedOrigins(Arrays.asList(allowedOrigins));
        configuration.setAllowedMethods(Arrays.asList(allowedMethods));
        configuration.setAllowedHeaders(Collections.singletonList(allowedHeaders));
        configuration.setAllowCredentials(allowCredentials);
        configuration.setMaxAge(maxAge);
        
        // Headers expostos para o frontend
        configuration.setExposedHeaders(Arrays.asList(
            "Authorization",
            "Content-Type",
            "X-Total-Count",
            "X-Page-Number",
            "X-Page-Size"
        ));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        
        return source;
    }

    /**
     * Configuração de negociação de conteúdo
     */
    @Override
    public void configureContentNegotiation(@org.springframework.lang.NonNull ContentNegotiationConfigurer configurer) {
        configurer
                .favorParameter(false)
                .ignoreAcceptHeader(false)
                .defaultContentType(org.springframework.http.MediaType.APPLICATION_JSON);
    }

    /**
     * Estende os conversores de mensagem HTTP existentes do Spring
     * Adiciona configurações customizadas do Jackson sem remover os conversores padrão
     */
    @Override
    public void extendMessageConverters(@org.springframework.lang.NonNull List<HttpMessageConverter<?>> converters) {
        // Encontra o converter Jackson existente e aplica configurações customizadas
        converters.stream()
                .filter(MappingJackson2HttpMessageConverter.class::isInstance)
                .map(MappingJackson2HttpMessageConverter.class::cast)
                .findFirst()
                .ifPresent(converter -> {
                    // Configurações específicas do Jackson podem ser adicionadas aqui
                    // Por exemplo: configurar ObjectMapper customizado
                    // converter.getObjectMapper().configure(...)
                });
    }

    /**
     * Filtro centralizado de logging de requisições
     * Configurado com FilterRegistrationBean para controle completo sobre:
     * - Padrões de URL aplicáveis
     * - Ordem de execução do filtro
     * - Configurações específicas de logging
     */
    @Bean
    public FilterRegistrationBean<CommonsRequestLoggingFilter> requestLoggingFilter() {
        FilterRegistrationBean<CommonsRequestLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(false); // Por segurança, não logar payloads
        filter.setIncludeHeaders(false);
        filter.setMaxPayloadLength(10000);
        filter.setAfterMessagePrefix("REQUEST DATA: ");
        
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns("/api/*");
        registrationBean.setOrder(1);
        
        return registrationBean;
    }
}

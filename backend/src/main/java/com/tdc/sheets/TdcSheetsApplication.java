package com.tdc.sheets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * Aplicação principal do TDC Sheets
 * Sistema de gerenciamento de fichas para o RPG Tabuleiro do Caos
 * 
 * @author Victor Gabriel Soares
 * @version 0.0.1
 * @since 2025-06-28
 */
@SpringBootApplication(scanBasePackages = "com.tdc.sheets")
@EnableTransactionManagement
@EnableCaching
@EnableConfigurationProperties
public class TdcSheetsApplication {

    /**
     * Configuração inicial da aplicação
     */
    @PostConstruct
    public void init() {
        // Configurar timezone padrão para UTC em produção
        // Em desenvolvimento, usa o timezone local configurado no application.yml
        String profile = System.getProperty("spring.profiles.active", "dev");
        if (!"dev".equals(profile)) {
            TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(TdcSheetsApplication.class, args);
    }
}

// TODO: Adicionar configurações adicionais conforme seções do guia de desenvolvimento
// TODO: Implementar configuração de cache Redis - Seção 17.1
// TODO: Adicionar configuração de monitoramento e métricas - DEPLOY-001
// TODO: Adicionar classes de configuração Spring:
// - SecurityConfig para JWT e autenticação - BACK-005
// - DatabaseConfig para pool de conexões - BACK-004
// - SwaggerConfig para documentação API - DOC-001
// - RedisConfig para cache - PERF-001

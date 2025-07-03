package com.tdc.sheets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
@EnableJpaAuditing // TODO: Configurar auditoria automática para entidades - BACK-002
public class TdcSheetsApplication {

    public static void main(String[] args) {
        // TODO: Adicionar configuração de timezone para UTC
        // TODO: Implementar banner customizado da aplicação
        // TODO: Configurar logs estruturados para produção
        SpringApplication.run(TdcSheetsApplication.class, args);
    }
}

// TODO: Adicionar configurações adicionais conforme seções do guia de desenvolvimento
// TODO: Configurar perfis de ambiente (dev, test, prod) - BACK-001
// TODO: Implementar configuração de cache Redis - Seção 17.1
// TODO: Adicionar configuração de monitoramento e métricas - DEPLOY-001
// TODO: Adicionar classes de configuração Spring:
// - SecurityConfig para JWT e autenticação - BACK-005
// - WebConfig para CORS e configurações web - BACK-001
// - DatabaseConfig para pool de conexões - BACK-004
// - SwaggerConfig para documentação API - DOC-001
// - RedisConfig para cache - PERF-001

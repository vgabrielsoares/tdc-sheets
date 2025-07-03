package com.tdc.sheets.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * Configuração de cache da aplicação
 * 
 * @author Victor Gabriel Soares
 * @since 2025-06-28
 */
@Configuration
@EnableCaching
public class CacheConfig {

    /**
     * Cache manager usando cache local em memória
     */
    @Bean
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();
        
        // Configurar caches disponíveis
        cacheManager.setCacheNames(Arrays.asList(
            "users",                    // Cache de usuários
            "fichas",                   // Cache de fichas
            "arquetipos",               // Cache de arquetipos
            "origens",                  // Cache de origens
            "linhagens",                // Cache de linhagens
            "habilidades",              // Cache de habilidades
            "feiticos",                 // Cache de feitiços
            "equipamentos",             // Cache de equipamentos
            "configuracoes",            // Cache de configurações do sistema
            "stats"                     // Cache de estatísticas
        ));
        
        // Permitir criação dinâmica de novos caches
        cacheManager.setAllowNullValues(false);
        
        return cacheManager;
    }
}

package com.tdc.sheets.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Controller para health checks e informações básicas da aplicação
 * 
 * @author Victor Gabriel Soares
 * @since 2025-06-28
 */
@RestController
@RequestMapping("/health")
public class HealthController {

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @Value("${app.description}")
    private String appDescription;

    /**
     * Endpoint básico de health check
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> health() {
        return ResponseEntity.ok(Map.of(
            "status", "UP",
            "timestamp", LocalDateTime.now(),
            "application", Map.of(
                "name", appName,
                "version", appVersion,
                "description", appDescription
            )
        ));
    }

    /**
     * Endpoint para informações da aplicação
     */
    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> info() {
        return ResponseEntity.ok(Map.of(
            "application", Map.of(
                "name", appName,
                "version", appVersion,
                "description", appDescription
            ),
            "system", Map.of(
                "timestamp", LocalDateTime.now(),
                "timezone", java.time.ZoneId.systemDefault().toString(),
                "java-version", System.getProperty("java.version")
            )
        ));
    }

    /**
     * Endpoint de status simples
     */
    @GetMapping("/status")
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("OK");
    }
}

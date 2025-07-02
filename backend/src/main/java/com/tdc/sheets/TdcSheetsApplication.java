package com.tdc.sheets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
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
public class TdcSheetsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TdcSheetsApplication.class, args);
    }
}

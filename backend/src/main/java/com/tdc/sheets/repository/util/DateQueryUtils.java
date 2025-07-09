package com.tdc.sheets.repository.util;

import java.time.LocalDateTime;
import java.time.LocalDate;

/**
 * Utilitários para consultas de data nos repositories
 */
public class DateQueryUtils {
    
    /**
     * Converte uma LocalDate para o início do dia (00:00:00)
     */
    public static LocalDateTime startOfDay(LocalDate date) {
        return date.atStartOfDay();
    }
    
    /**
     * Converte uma LocalDate para o fim do dia (23:59:59.999999999)
     */
    public static LocalDateTime endOfDay(LocalDate date) {
        return date.atTime(23, 59, 59, 999999999);
    }
    
    /**
     * Converte uma LocalDateTime para o início do dia
     */
    public static LocalDateTime startOfDay(LocalDateTime dateTime) {
        return dateTime.toLocalDate().atStartOfDay();
    }
    
    /**
     * Converte uma LocalDateTime para o fim do dia
     */
    public static LocalDateTime endOfDay(LocalDateTime dateTime) {
        return dateTime.toLocalDate().atTime(23, 59, 59, 999999999);
    }
    
    /**
     * Obtém o início do dia atual
     */
    public static LocalDateTime startOfToday() {
        return LocalDate.now().atStartOfDay();
    }
    
    /**
     * Obtém o fim do dia atual
     */
    public static LocalDateTime endOfToday() {
        return LocalDate.now().atTime(23, 59, 59, 999999999);
    }
    
    /**
     * Obtém o início do dia de ontem
     */
    public static LocalDateTime startOfYesterday() {
        return LocalDate.now().minusDays(1).atStartOfDay();
    }
    
    /**
     * Obtém o fim do dia de ontem
     */
    public static LocalDateTime endOfYesterday() {
        return LocalDate.now().minusDays(1).atTime(23, 59, 59, 999999999);
    }
    
    /**
     * Obtém o início da semana atual (segunda-feira)
     */
    public static LocalDateTime startOfWeek() {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.minusDays(today.getDayOfWeek().getValue() - 1);
        return startOfWeek.atStartOfDay();
    }
    
    /**
     * Obtém o início do mês atual
     */
    public static LocalDateTime startOfMonth() {
        LocalDate today = LocalDate.now();
        LocalDate startOfMonth = today.withDayOfMonth(1);
        return startOfMonth.atStartOfDay();
    }
    
    /**
     * Obtém o início do ano atual
     */
    public static LocalDateTime startOfYear() {
        LocalDate today = LocalDate.now();
        LocalDate startOfYear = today.withDayOfYear(1);
        return startOfYear.atStartOfDay();
    }
}

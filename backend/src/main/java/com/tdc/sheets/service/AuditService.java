package com.tdc.sheets.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Serviço para capturar informações de auditoria das requisições HTTP
 * 
 * @author Victor Gabriel Soares
 * @since 2025-07-03
 */
@Service
public class AuditService {
    
    /**
     * Obtém o endereço IP do cliente da requisição atual
     */
    public String getCurrentIpAddress() {
        HttpServletRequest request = getCurrentRequest();
        if (request == null) {
            return null;
        }
        
        // Verifica headers de proxy primeiro
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        
        // Se tiver múltiplos IPs, pega o primeiro
        if (ipAddress != null && ipAddress.contains(",")) {
            ipAddress = ipAddress.split(",")[0].trim();
        }
        
        return ipAddress;
    }
    
    /**
     * Obtém o User-Agent do cliente da requisição atual
     */
    public String getCurrentUserAgent() {
        HttpServletRequest request = getCurrentRequest();
        return request != null ? request.getHeader("User-Agent") : null;
    }
    
    /**
     * Obtém a requisição HTTP atual do contexto do Spring
     */
    private HttpServletRequest getCurrentRequest() {
        try {
            ServletRequestAttributes requestAttributes = 
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            return requestAttributes != null ? requestAttributes.getRequest() : null;
        } catch (Exception e) {
            // Se não houver contexto de requisição (ex: operações batch), retorna null
            return null;
        }
    }
    
    /**
     * Obtém informações completas de auditoria da requisição atual
     */
    public AuditInfo getCurrentAuditInfo() {
        return new AuditInfo(getCurrentIpAddress(), getCurrentUserAgent());
    }
    
    /**
     * Classe para encapsular informações de auditoria
     */
    public static class AuditInfo {
        private final String ipAddress;
        private final String userAgent;
        
        public AuditInfo(String ipAddress, String userAgent) {
            this.ipAddress = ipAddress;
            this.userAgent = userAgent;
        }
        
        public String getIpAddress() {
            return ipAddress;
        }
        
        public String getUserAgent() {
            return userAgent;
        }
    }
}

package com.tdc.sheets.entity.listener;

import com.tdc.sheets.entity.AuditableEntity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * EntityListener para auditoria automática de entidades AuditableEntity
 * Captura automaticamente informações de IP e User-Agent das requisições
 * 
 * @author Victor Gabriel Soares
 * @since 2025-07-03
 */
public class AuditableEntityListener {
    
    @PrePersist
    public void setCreationAuditInfo(AuditableEntity entity) {
        AuditInfo auditInfo = getCurrentAuditInfo();
        entity.setCreatedIpAddress(auditInfo.getIpAddress());
        
        // Define o ID do usuário criador se disponível
        Long currentUserId = getCurrentUserId();
        if (currentUserId != null) {
            entity.setCreatedByUserId(currentUserId);
        }
    }
    
    @PreUpdate  
    public void setUpdateAuditInfo(AuditableEntity entity) {
        AuditInfo auditInfo = getCurrentAuditInfo();
        entity.setUpdatedIpAddress(auditInfo.getIpAddress());
        
        // Define o ID do usuário atualizador se disponível
        Long currentUserId = getCurrentUserId();
        if (currentUserId != null) {
            entity.setUpdatedByUserId(currentUserId);
        }
    }
    
    /**
     * Obtém informações de auditoria da requisição atual
     */
    private AuditInfo getCurrentAuditInfo() {
        String ipAddress = getCurrentIpAddress();
        return new AuditInfo(ipAddress);
    }
    
    /**
     * Obtém o endereço IP do cliente da requisição atual
     */
    private String getCurrentIpAddress() {
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
     * Obtém o ID do usuário atual do contexto de segurança
     */
    private Long getCurrentUserId() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            
            if (authentication == null || !authentication.isAuthenticated() 
                || "anonymousUser".equals(authentication.getPrincipal())) {
                return null;
            }
            
            // Assumindo que o principal contém o ID do usuário ou username
            // Isso pode precisar ser ajustado baseado na implementação do sistema de autenticação
            Object principal = authentication.getPrincipal();
            
            if (principal instanceof Number) {
                return ((Number) principal).longValue();
            }
            
            if (principal instanceof String) {
                try {
                    return Long.parseLong((String) principal);
                } catch (NumberFormatException e) {
                    // Se não for um número, pode ser username, precisará de lookup no banco
                    return null;
                }
            }
            
            return null;
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Classe para encapsular informações de auditoria
     */
    private static class AuditInfo {
        private final String ipAddress;
        
        public AuditInfo(String ipAddress) {
            this.ipAddress = ipAddress;
        }
        
        public String getIpAddress() {
            return ipAddress;
        }
    }
}

package com.tdc.sheets.entity;

import com.tdc.sheets.entity.listener.AuditableEntityListener;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * Entidade base com auditoria completa para entidades críticas
 * Inclui informações detalhadas de auditoria para rastreamento completo
 * 
 * @author Victor Gabriel Soares
 * @since 2025-07-03
 */
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class, AuditableEntityListener.class})
public abstract class AuditableEntity extends BaseEntity {

    @CreatedBy
    @Column(name = "created_by_user_id", updatable = false)
    private Long createdByUserId;

    @LastModifiedBy
    @Column(name = "updated_by_user_id")
    private Long updatedByUserId;

    @Column(name = "created_ip_address", updatable = false, length = 45)
    private String createdIpAddress;

    @Column(name = "updated_ip_address", length = 45)
    private String updatedIpAddress;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "deleted_by_user_id")
    private Long deletedByUserId;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    // Getters e Setters

    public Long getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(Long createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public Long getUpdatedByUserId() {
        return updatedByUserId;
    }

    public void setUpdatedByUserId(Long updatedByUserId) {
        this.updatedByUserId = updatedByUserId;
    }

    public String getCreatedIpAddress() {
        return createdIpAddress;
    }

    public void setCreatedIpAddress(String createdIpAddress) {
        this.createdIpAddress = createdIpAddress;
    }

    public String getUpdatedIpAddress() {
        return updatedIpAddress;
    }

    public void setUpdatedIpAddress(String updatedIpAddress) {
        this.updatedIpAddress = updatedIpAddress;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Long getDeletedByUserId() {
        return deletedByUserId;
    }

    public void setDeletedByUserId(Long deletedByUserId) {
        this.deletedByUserId = deletedByUserId;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * Método para soft delete da entidade
     */
    public void softDelete(Long deletedByUserId) {
        this.deletedAt = LocalDateTime.now();
        this.deletedByUserId = deletedByUserId;
        this.isActive = false;
    }

    /**
     * Método para reativar entidade deletada
     */
    public void restore() {
        this.deletedAt = null;
        this.deletedByUserId = null;
        this.isActive = true;
    }

    /**
     * Verifica se a entidade foi deletada (soft delete)
     */
    public boolean isDeleted() {
        return deletedAt != null && !isActive;
    }
}

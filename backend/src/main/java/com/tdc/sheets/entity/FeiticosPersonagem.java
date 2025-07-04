package com.tdc.sheets.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * Entidade que representa os feitiços de um personagem
 * Baseada na tabela feiticos_personagem do schema SQL
 */
@Entity
@Table(name = "feiticos_personagem")
@Data
@EqualsAndHashCode(callSuper = true)
public class FeiticosPersonagem extends BaseEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ficha_personagem_id", nullable = false)
    private FichaPersonagem fichaPersonagem;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feiticos_id")
    private Feiticos feiticos;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habilidade")
    private HabilidadesPersonagem habilidade;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

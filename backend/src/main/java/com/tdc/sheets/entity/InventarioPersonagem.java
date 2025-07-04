package com.tdc.sheets.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * Entidade que representa o inventário de um personagem
 * Baseada na tabela inventario_personagem do schema SQL
 */
@Entity
@Table(name = "inventario_personagem")
@Data
@EqualsAndHashCode(callSuper = true)
public class InventarioPersonagem extends BaseEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carga_personagem_id", nullable = false)
    private CargaPersonagem cargaPersonagem;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipamento_id")
    private Equipamentos equipamento;
    
    @Column(name = "item")
    private String item;
    
    @Column(name = "descricao_item", columnDefinition = "TEXT")
    private String descricaoItem;
    
    @Column(name = "quantidade_item")
    private Integer quantidadeItem;
    
    @Column(name = "peso_item")
    private Integer pesoItem;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

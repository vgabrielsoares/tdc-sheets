package com.tdc.sheets.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade que representa uma característica de arquétipo
 * Baseada na tabela caracteristica_arquetipo do schema SQL
 */
@Entity
@Table(name = "caracteristica_arquetipo")
@Data
@EqualsAndHashCode(callSuper = true)
public class CaracteristicaArquetipo extends BaseEntity {
    
    @Column(name = "nome", nullable = false)
    private String nome;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arquetipo_id", nullable = false)
    private Arquetipo arquetipo;
    
    @Column(name = "descricao", columnDefinition = "TEXT", nullable = false)
    private String descricao;
    
    @Column(name = "nivel", nullable = false)
    private Integer nivel;
}

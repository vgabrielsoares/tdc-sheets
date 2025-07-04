package com.tdc.sheets.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade que representa o relacionamento entre personagem e arquetipo
 * Baseada na tabela arquetipos_personagem do schema SQL
 */
@Entity
@Table(name = "arquetipos_personagem")
@Data
@EqualsAndHashCode(callSuper = true)
public class ArquetiposPersonagem extends BaseEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ficha_personagem_id", nullable = false)
    private FichaPersonagem fichaPersonagem;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arquetipo_id", nullable = false)
    private Arquetipo arquetipo;
    
    @Column(name = "nivel_arquetipo", nullable = false)
    private Integer nivelArquetipo;
}

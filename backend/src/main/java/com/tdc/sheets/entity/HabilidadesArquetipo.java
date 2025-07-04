package com.tdc.sheets.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade que representa a relação entre arquetipos e habilidades
 * Baseada na tabela habilidades_arquetipo do schema SQL
 */
@Entity
@Table(name = "habilidades_arquetipo")
@Data
@EqualsAndHashCode(callSuper = true)
public class HabilidadesArquetipo extends BaseEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arquetipo_id", nullable = false)
    private Arquetipo arquetipo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habilidade_id", nullable = false)
    private Habilidade habilidade;
}

package com.tdc.sheets.entity;

import com.tdc.sheets.entity.enums.TiposProficiencia;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade que representa as proficiências de um arquétipo
 * Baseada na tabela proficiencias_arquetipo do schema SQL
 */
@Entity
@Table(name = "proficiencias_arquetipo")
@Data
@EqualsAndHashCode(callSuper = true)
public class ProficienciasArquetipo extends BaseEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arquetipo_id", nullable = false)
    private Arquetipo arquetipo;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "proficiencia")
    private TiposProficiencia proficiencia;
}

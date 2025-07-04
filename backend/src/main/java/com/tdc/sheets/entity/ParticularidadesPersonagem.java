package com.tdc.sheets.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade que representa as particularidades de um personagem
 * Baseada na tabela particularidades_personagem do schema SQL
 */
@Entity
@Table(name = "particularidades_personagem")
@Data
@EqualsAndHashCode(callSuper = true)
public class ParticularidadesPersonagem extends AuditableEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ficha_personagem_id", nullable = false)
    private FichaPersonagem fichaPersonagem;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "particularidade_id")
    private Particularidade particularidade;
}

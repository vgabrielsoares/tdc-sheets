package com.tdc.sheets.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade que representa o relacionamento entre personagem e classe
 * Baseada na tabela classes_personagem do schema SQL
 */
@Entity
@Table(name = "classes_personagem")
@Data
@EqualsAndHashCode(callSuper = true)
public class ClassesPersonagem extends AuditableEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ficha_personagem_id", nullable = false)
    private FichaPersonagem fichaPersonagem;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classe_id", nullable = false)
    private Classe classe;
    
    @Column(name = "nivel", nullable = false)
    private Integer nivel;
}

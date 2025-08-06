package com.tdc.sheets.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade que representa usos específicos de uma habilidade
 * Baseada na tabela uso_habilidade do schema SQL
 */
@Entity
@Table(name = "uso_habilidade")
@Data
@EqualsAndHashCode(callSuper = true)
public class UsoHabilidade extends BaseEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habilidade_id", nullable = false)
    private Habilidade habilidade;
    
    @Column(name = "nome")
    private String nome;
    
    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;
}

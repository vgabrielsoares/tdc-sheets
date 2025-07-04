package com.tdc.sheets.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade que representa uma classe do sistema TDC
 * Baseada na tabela classe do schema SQL
 */
@Entity
@Table(name = "classe")
@Data
@EqualsAndHashCode(callSuper = true)
public class Classe extends BaseEntity {
    
    @Column(name = "nome", nullable = false)
    private String nome;
    
    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requisitos_classe_id", nullable = false)
    private RequisitosClasse requisitosClasse;
    
    @Column(name = "defesa_5")
    private Integer defesa5;
    
    @Column(name = "defesa_10")
    private Integer defesa10;
    
    @Column(name = "defesa_15")
    private Integer defesa15;
}

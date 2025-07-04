package com.tdc.sheets.entity;

import com.tdc.sheets.entity.enums.ArquetipoNome;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade que representa os requisitos para uma classe
 * Baseada na tabela requisitos_classe do schema SQL
 */
@Entity
@Table(name = "requisitos_classe")
@Data
@EqualsAndHashCode(callSuper = true)
public class RequisitosClasse extends BaseEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classe_id", nullable = false)
    private Classe classe;
    
    @Column(name = "nivel_minimo", nullable = false)
    private Integer nivelMinimo;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "primeiro_arquetipo", nullable = false)
    private ArquetipoNome primeiroArquetipo;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "segundo_arquetipo")
    private ArquetipoNome segundoArquetipo;
    
    @Column(name = "primeiro_arquetipo_nivel", nullable = false)
    private Integer primeiroArquetipoNivel;
    
    @Column(name = "segundo_arquetipo_nivel")
    private Integer segundoArquetipoNivel;
}

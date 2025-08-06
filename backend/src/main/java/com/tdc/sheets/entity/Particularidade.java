package com.tdc.sheets.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade que representa uma particularidade do sistema TDC
 * Baseada na tabela particularidade do schema SQL
 */
@Entity
@Table(name = "particularidade")
@Data
@EqualsAndHashCode(callSuper = true)
public class Particularidade extends BaseEntity {
    
    @Column(name = "particularidade", nullable = false)
    private String particularidade;
    
    @Column(name = "efeito", columnDefinition = "TEXT")
    private String efeito;
    
    @Column(name = "oposto")
    private Integer oposto;
    
    @Column(name = "pontos")
    private Integer pontos;
}

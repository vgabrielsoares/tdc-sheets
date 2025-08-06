package com.tdc.sheets.entity;

import com.tdc.sheets.entity.enums.TiposTamanho;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * Entidade que representa os tamanhos no sistema TDC
 * Baseada na tabela tamanho do schema SQL
 */
@Entity
@Table(name = "tamanho")
@Data
@EqualsAndHashCode(callSuper = true)
public class Tamanho extends BaseEntity {
    
    @Enumerated(EnumType.STRING)
    @Column(name = "nome", nullable = false, unique = true)
    private TiposTamanho nome;
    
    @Column(name = "alcance")
    private Integer alcance;
    
    @Column(name = "dano")
    private String dano;
    
    @Column(name = "defesa")
    private Integer defesa;
    
    @Column(name = "quadrados", precision = 10, scale = 2)
    private BigDecimal quadrados;
    
    @Column(name = "peso_carregado")
    private Integer pesoCarregado;
    
    @Column(name = "manobras")
    private Integer manobras;
    
    @Column(name = "nd_rastro")
    private Integer ndRastro;
    
    @Column(name = "acrobacia")
    private Integer acrobacia;
    
    @Column(name = "atletismo")
    private Integer atletismo;
    
    @Column(name = "furtividade")
    private Integer furtividade;
    
    @Column(name = "reflexos")
    private Integer reflexos;
    
    @Column(name = "tenacidade")
    private Integer tenacidade;
}

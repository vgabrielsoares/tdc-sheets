package com.tdc.sheets.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade que representa os modificadores de atributos de uma origem
 * Baseada na tabela atributos_origem do schema SQL
 */
@Entity
@Table(name = "atributos_origem")
@Data
@EqualsAndHashCode(callSuper = true)
public class AtributosOrigem extends BaseEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origem_id", nullable = false)
    private Origem origem;
    
    @Column(name = "valor_agilidade")
    private Integer valorAgilidade;
    
    @Column(name = "valor_constituicao")
    private Integer valorConstituicao;
    
    @Column(name = "valor_forca")
    private Integer valorForca;
    
    @Column(name = "valor_influencia")
    private Integer valorInfluencia;
    
    @Column(name = "valor_mente")
    private Integer valorMente;
    
    @Column(name = "valor_presenca")
    private Integer valorPresenca;
    
    @Column(name = "valor_generico_positivo")
    private Integer valorGenericoPositivo;
    
    @Column(name = "valor_generico_negativo")
    private Integer valorGenericoNegativo;
}

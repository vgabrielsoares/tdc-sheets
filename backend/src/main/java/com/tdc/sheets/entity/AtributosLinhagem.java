package com.tdc.sheets.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade JPA para atributos da linhagem
 * Representa os modificadores de atributos que cada linhagem possui
 */
@Entity
@Table(name = "atributos_linhagem")
@Data
@EqualsAndHashCode(callSuper = false)
public class AtributosLinhagem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "linhagem_id")
    private Linhagem linhagem;

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

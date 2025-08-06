package com.tdc.sheets.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade JPA para características da ancestralidade
 * Representa características específicas de cada linhagem
 */
@Entity
@Table(name = "caracteristicas_ancestralidade")
@Data
@EqualsAndHashCode(callSuper = false)
public class CaracteristicasAncestralidade extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "linhagem_id")
    private Linhagem linhagem;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "fixa")
    private Boolean fixa;
}

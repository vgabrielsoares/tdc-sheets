package com.tdc.sheets.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade JPA para habilidades da origem
 * Representa as habilidades que cada origem concede
 */
@Entity
@Table(name = "habilidades_origem")
@Data
@EqualsAndHashCode(callSuper = false)
public class HabilidadesOrigem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origem_id", nullable = false)
    private Origem origem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habilidade_id", nullable = false)
    private Habilidade habilidade;

    @Column(name = "fixa")
    private Boolean fixa;
}

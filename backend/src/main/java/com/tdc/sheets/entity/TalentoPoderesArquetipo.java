package com.tdc.sheets.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade JPA para talentos e poderes dos arquetipos
 * Representa os talentos e poderes específicos que cada arquetipo pode obter
 */
@Entity
@Table(name = "talento__poderes_arquetipo")
@Data
@EqualsAndHashCode(callSuper = false)
public class TalentoPoderesArquetipo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arquetipo_id")
    private Arquetipo arquetipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requisitos_talentos_poderes_id")
    private RequisitosTalentosPoderes requisitos;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao", columnDefinition = "TEXT", nullable = false)
    private String descricao;

    @Column(name = "linhagem")
    private Boolean linhagem;
}

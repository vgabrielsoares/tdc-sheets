package com.tdc.sheets.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade JPA para origem custom
 * Representa origens customizadas criadas pelos jogadores
 */
@Entity
@Table(name = "origem_custom")
@Data
@EqualsAndHashCode(callSuper = false)
public class OrigemCustom extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ficha_personagem_id")
    private FichaPersonagem fichaPersonagem;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "habilidade_especial", columnDefinition = "TEXT")
    private String habilidadeEspecial;
}

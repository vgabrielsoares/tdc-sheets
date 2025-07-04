package com.tdc.sheets.entity;

import com.tdc.sheets.entity.enums.GrauPericia;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade JPA para requisitos de talentos e poderes
 * Representa os requisitos necessários para obter talentos/poderes
 */
@Entity
@Table(name = "requisitos_talentos_poderes")
@Data
@EqualsAndHashCode(callSuper = false)
public class RequisitosTalentosPoderes extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habilidade_id")
    private Habilidade habilidade;

    @Enumerated(EnumType.STRING)
    @Column(name = "grau_habilidade")
    private GrauPericia grauHabilidade;

    @Column(name = "talento_poder_requisito")
    private Integer talentoPodeRequisito;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "linhagem_id")
    private Linhagem linhagem;
}

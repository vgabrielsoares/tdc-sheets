package com.tdc.sheets.entity;

import com.tdc.sheets.entity.enums.ArquetipoNome;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade JPA para habilidades das classes
 * Representa as habilidades específicas que cada classe concede por nível
 */
@Entity
@Table(name = "habilidades_classe")
@Data
@EqualsAndHashCode(callSuper = false)
public class HabilidadesClasse extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classe_id")
    private Classe classe;

    @Column(name = "nivel")
    private Integer nivel;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

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

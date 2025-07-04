package com.tdc.sheets.entity;

import com.tdc.sheets.entity.enums.TiposProficiencia;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade JPA para proficiências das classes
 * Representa as proficiências que cada classe concede
 */
@Entity
@Table(name = "proficiencias_classe")
@Data
@EqualsAndHashCode(callSuper = false)
public class ProficienciasClasse extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classe_id", nullable = false)
    private Classe classe;

    @Enumerated(EnumType.STRING)
    @Column(name = "proficiencia")
    private TiposProficiencia proficiencia;
}

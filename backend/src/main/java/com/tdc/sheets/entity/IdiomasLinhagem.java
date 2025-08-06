package com.tdc.sheets.entity;

import com.tdc.sheets.entity.enums.Idiomas;
import com.tdc.sheets.entity.enums.Alfabetos;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade JPA para idiomas da linhagem
 * Representa os idiomas que cada linhagem conhece
 */
@Entity
@Table(name = "idiomas_linhagem")
@Data
@EqualsAndHashCode(callSuper = false)
public class IdiomasLinhagem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "linhagem_id")
    private Linhagem linhagem;

    @Enumerated(EnumType.STRING)
    @Column(name = "idioma")
    private Idiomas idioma;

    @Enumerated(EnumType.STRING)
    @Column(name = "alfabeto")
    private Alfabetos alfabeto;
}

package com.tdc.sheets.entity;

import com.tdc.sheets.entity.enums.TiposDeslocamento;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade JPA para deslocamento da linhagem
 * Representa os tipos de deslocamento que cada linhagem possui
 */
@Entity
@Table(name = "deslocamento_linhagem")
@Data
@EqualsAndHashCode(callSuper = false)
public class DeslocamentoLinhagem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "linhagem_id")
    private Linhagem linhagem;

    @Enumerated(EnumType.STRING)
    @Column(name = "deslocamento")
    private TiposDeslocamento deslocamento;

    @Column(name = "valor")
    private Integer valor;
}

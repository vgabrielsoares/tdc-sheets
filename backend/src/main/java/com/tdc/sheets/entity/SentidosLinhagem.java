package com.tdc.sheets.entity;

import com.tdc.sheets.entity.enums.TiposSentidos;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade JPA para sentidos da linhagem
 * Representa os tipos de sentidos que cada linhagem possui
 */
@Entity
@Table(name = "sentidos_linhagem")
@Data
@EqualsAndHashCode(callSuper = false)
public class SentidosLinhagem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "linhagem_id")
    private Linhagem linhagem;

    @Enumerated(EnumType.STRING)
    @Column(name = "audicao", nullable = false)
    private TiposSentidos audicao;

    @Enumerated(EnumType.STRING)
    @Column(name = "olfato", nullable = false)
    private TiposSentidos olfato;

    @Enumerated(EnumType.STRING)
    @Column(name = "visao", nullable = false)
    private TiposSentidos visao;
}

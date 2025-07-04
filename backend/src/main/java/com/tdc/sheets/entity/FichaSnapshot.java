package com.tdc.sheets.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

/**
 * Entidade JPA para snapshots de fichas
 * Representa o versionamento/histórico das fichas de personagem
 */
@Entity
@Table(name = "ficha_snapshot")
@Data
@EqualsAndHashCode(callSuper = false)
public class FichaSnapshot extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ficha_personagem_id", nullable = false)
    private FichaPersonagem fichaPersonagem;

    @Column(name = "versao", nullable = false)
    private Integer versao;

    @Column(name = "motivo", length = 100)
    private String motivo;

    @Column(name = "dados_ficha", columnDefinition = "JSONB")
    private String dadosFicha;

    @Column(name = "data_snapshot")
    private LocalDateTime dataSnapshot = LocalDateTime.now();
}

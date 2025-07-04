package com.tdc.sheets.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

/**
 * Entidade JPA para compartilhamento de fichas
 * Representa o sistema de compartilhamento de fichas entre usuários
 */
@Entity
@Table(name = "compartilhamento_ficha")
@Data
@EqualsAndHashCode(callSuper = false)
public class CompartilhamentoFicha extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ficha_personagem_id", nullable = false)
    private FichaPersonagem fichaPersonagem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "nivel_acesso", length = 20)
    private String nivelAcesso; // 'VISUALIZACAO' ou 'EDICAO'

    @Column(name = "link_token", length = 100, unique = true)
    private String linkToken;

    @Column(name = "expiracao")
    private LocalDateTime expiracao;

    @Column(name = "ativo")
    private Boolean ativo = true;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}

package com.tdc.sheets.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

/**
 * Entidade JPA para log de alterações
 * Representa o sistema de auditoria das alterações nas fichas
 */
@Entity
@Table(name = "log_alteracoes", 
       indexes = {
           @Index(name = "idx_log_ficha", columnList = "ficha_personagem_id"),
           @Index(name = "idx_log_data", columnList = "created_at"),
           @Index(name = "idx_log_user", columnList = "user_id"),
           @Index(name = "idx_log_tabela", columnList = "tabela")
       })
@Data
@EqualsAndHashCode(callSuper = false)
public class LogAlteracoes extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ficha_personagem_id", nullable = false)
    private FichaPersonagem fichaPersonagem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "tabela", length = 100, nullable = false)
    private String tabela;

    @Column(name = "campo", length = 100)
    private String campo;

    @Column(name = "valor_anterior", columnDefinition = "TEXT")
    private String valorAnterior;

    @Column(name = "valor_novo", columnDefinition = "TEXT")
    private String valorNovo;

    @Column(name = "motivo", length = 100)
    private String motivo;

    @Column(name = "ip_address", columnDefinition = "INET")
    private String ipAddress;

    @Column(name = "user_agent", columnDefinition = "TEXT")
    private String userAgent;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}

package com.tdc.sheets.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade que representa as ações de um personagem
 * Baseada na tabela acoes_personagem do schema SQL
 */
@Entity
@Table(name = "acoes_personagem")
@Data
@EqualsAndHashCode(callSuper = true)
public class AcoesPersonagem extends BaseEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ficha_personagem_id", nullable = false)
    private FichaPersonagem fichaPersonagem;
    
    @Column(name = "acao", nullable = false)
    private String acao;
    
    @Column(name = "acao_disponivel", nullable = false)
    private Boolean acaoDisponivel = false;
}

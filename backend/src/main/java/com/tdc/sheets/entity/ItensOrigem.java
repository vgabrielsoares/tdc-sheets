package com.tdc.sheets.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade que representa os itens iniciais de uma origem
 * Baseada na tabela itens_origem do schema SQL
 */
@Entity
@Table(name = "itens_origem")
@Data
@EqualsAndHashCode(callSuper = true)
public class ItensOrigem extends BaseEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origem_id", nullable = false)
    private Origem origem;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipamento_id")
    private Equipamentos equipamento;
    
    @Column(name = "quantidade")
    private Integer quantidade;
}

package com.tdc.sheets.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade que representa a carga de um personagem
 * Baseada na tabela carga_personagem do schema SQL
 */
@Entity
@Table(name = "carga_personagem")
@Data
@EqualsAndHashCode(callSuper = true)
public class CargaPersonagem extends AuditableEntity {
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ficha_personagem_id", nullable = false)
    private FichaPersonagem fichaPersonagem;
    
    @Column(name = "carga_atual")
    private Integer cargaAtual;
    
    @Column(name = "capacidade_carga")
    private Integer capacidadeCarga;
    
    @Column(name = "bonus_capacidade_carga")
    private Integer bonusCapacidadeCarga;
}

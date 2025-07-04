package com.tdc.sheets.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade que representa o dano de um ataque
 * Baseada na tabela dano_ataque do schema SQL
 */
@Entity
@Table(name = "dano_ataque")
@Data
@EqualsAndHashCode(callSuper = true)
public class DanoAtaque extends BaseEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ataque_personagem_id")
    private AtaquesPersonagem ataquePersonagem;
    
    @Column(name = "dado", length = 7)
    private String dado;
    
    @Column(name = "mod")
    private Integer mod;
    
    @Column(name = "bonus")
    private Integer bonus;
    
    @Column(name = "critico", length = 8)
    private String critico;
}

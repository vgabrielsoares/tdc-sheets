package com.tdc.sheets.entity;

import com.tdc.sheets.entity.enums.TiposDano;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade que representa as resistências de um personagem
 * Baseada na tabela resistencias_personagem do schema SQL
 */
@Entity
@Table(name = "resistencias_personagem")
@Data
@EqualsAndHashCode(callSuper = true)
public class ResistenciasPersonagem extends BaseEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ficha_personagem_id")
    private FichaPersonagem fichaPersonagem;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_dano")
    private TiposDano tipoDano;
    
    @Column(name = "resistencia")
    private Integer resistencia = 0;
    
    @Column(name = "aprimorada")
    private Boolean aprimorada = false;
    
    @Column(name = "suprema")
    private Boolean suprema = false;
    
    @Column(name = "lendaria")
    private Boolean lendaria = false;
    
    @Column(name = "invulnerabilidade")
    private Boolean invulnerabilidade = false;
    
    @Column(name = "absorcao")
    private Boolean absorcao = false;
}

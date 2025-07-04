package com.tdc.sheets.entity;

import com.tdc.sheets.entity.enums.TiposSentidos;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade que representa os sentidos de um personagem
 * Baseada na tabela sentidos_personagem do schema SQL
 */
@Entity
@Table(name = "sentidos_personagem")
@Data
@EqualsAndHashCode(callSuper = true)
public class SentidosPersonagem extends AuditableEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ficha_personagem_id", nullable = false)
    private FichaPersonagem fichaPersonagem;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "audicao", nullable = false)
    private TiposSentidos audicao;
    
    @Column(name = "mod_audicao")
    private Integer modAudicao;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "olfato", nullable = false)
    private TiposSentidos olfato;
    
    @Column(name = "mod_olfato")
    private Integer modOlfato;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "visao", nullable = false)
    private TiposSentidos visao;
    
    @Column(name = "mod_visao")
    private Integer modVisao;
}

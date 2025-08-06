package com.tdc.sheets.entity;

import com.tdc.sheets.entity.enums.TiposDeslocamento;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade que representa o deslocamento de um personagem
 * Baseada na tabela deslocamento_personagem do schema SQL
 */
@Entity
@Table(name = "deslocamento_personagem")
@Data
@EqualsAndHashCode(callSuper = true)
public class DeslocamentoPersonagem extends BaseEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ficha_personagem_id", nullable = false)
    private FichaPersonagem fichaPersonagem;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "deslocamento", nullable = false)
    private TiposDeslocamento deslocamento;
    
    @Column(name = "valor", nullable = false)
    private Integer valor;
    
    @Column(name = "bonus")
    private Integer bonus;
}

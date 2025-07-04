package com.tdc.sheets.entity;

import com.tdc.sheets.entity.enums.TiposProficiencia;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade que representa as proficiências de um personagem
 * Baseada na tabela proficiencias_personagem do schema SQL
 */
@Entity
@Table(name = "proficiencias_personagem")
@Data
@EqualsAndHashCode(callSuper = true)
public class ProficienciasPersonagem extends BaseEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ficha_personagem_id", nullable = false)
    private FichaPersonagem fichaPersonagem;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "proficiencia", nullable = false)
    private TiposProficiencia proficiencia;
    
    @Column(name = "proficiente", nullable = false)
    private Boolean proficiente;
}

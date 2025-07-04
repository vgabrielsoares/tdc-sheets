package com.tdc.sheets.entity;

import com.tdc.sheets.entity.enums.Atributos;
import com.tdc.sheets.entity.enums.TiposAlcance;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * Entidade que representa os ataques de um personagem
 * Baseada na tabela ataques_personagem do schema SQL
 */
@Entity
@Table(name = "ataques_personagem")
@Data
@EqualsAndHashCode(callSuper = true)
public class AtaquesPersonagem extends BaseEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ficha_personagem_id")
    private FichaPersonagem fichaPersonagem;
    
    @Column(name = "nome")
    private String nome;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "atributo")
    private Atributos atributo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habilidade_id")
    private Habilidade habilidade;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "alcance")
    private TiposAlcance alcance;
    
    @Column(name = "dado_bonus")
    private Integer dadoBonus = 0;
    
    @Column(name = "mod_bonus")
    private Integer modBonus = 0;
    
    @Column(name = "custo_pp")
    private Integer custoPp;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

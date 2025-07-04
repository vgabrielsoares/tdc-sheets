package com.tdc.sheets.entity;

import com.tdc.sheets.entity.enums.Atributos;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade que representa uma habilidade base do sistema TDC
 * Baseada na tabela habilidade do schema SQL
 */
@Entity
@Table(name = "habilidade")
@Data
@EqualsAndHashCode(callSuper = true)
public class Habilidade extends BaseEntity {
    
    @Column(name = "pericia", nullable = false)
    private String pericia;
    
    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "atributo_chave")
    private Atributos atributoChave;
    
    @Column(name = "penalidade_carga")
    private Boolean penalidadeCarga;
    
    @Column(name = "instrumento")
    private Boolean instrumento;
    
    @Column(name = "proficiencia")
    private Boolean proficiencia;
    
    @Column(name = "combate")
    private Boolean combate;
}

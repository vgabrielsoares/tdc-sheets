package com.tdc.sheets.entity;

import com.tdc.sheets.entity.enums.*;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade que representa um feitiço do sistema TDC
 * Baseada na tabela feiticos do schema SQL
 */
@Entity
@Table(name = "feiticos")
@Data
@EqualsAndHashCode(callSuper = true)
public class Feiticos extends BaseEntity {
    
    @Column(name = "nome", nullable = false)
    private String nome;
    
    @Column(name = "circulo", nullable = false)
    private Integer circulo;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "matriz", nullable = false)
    private MatrizFeiticos matriz;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "alcance")
    private TiposAlcance alcance;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "habilidade_resistencia")
    private HabilidadesResistencia habilidadeResistencia;
    
    @Column(name = "componente_somatico")
    private Boolean componenteSomatico;
    
    @Column(name = "componente_verbal")
    private Boolean componenteVerbal;
    
    @Column(name = "componente_material")
    private Boolean componenteMaterial;
    
    @Column(name = "componente_circular")
    private Boolean componenteCircular;
    
    @Column(name = "componente_material_descricao", columnDefinition = "TEXT")
    private String componenteMaterialDescricao;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tempo_conjuracao", nullable = false)
    private TempoAcoes tempoConjuracao;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_resistencia")
    private TiposResistencia tipoResistencia;
    
    @Column(name = "alvo")
    private String alvo;
    
    @Column(name = "area")
    private String area;
    
    @Column(name = "duracao")
    private String duracao;
    
    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;
    
    @Column(name = "elevacao", columnDefinition = "TEXT")
    private String elevacao;
    
    @Column(name = "aprimoramento", columnDefinition = "TEXT")
    private String aprimoramento;
}

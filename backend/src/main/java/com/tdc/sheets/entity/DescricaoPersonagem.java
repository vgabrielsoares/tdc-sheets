package com.tdc.sheets.entity;

import com.tdc.sheets.entity.enums.TiposTamanho;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade que representa a descrição física e história de um personagem
 * Baseada na tabela descricao_personagem do schema SQL
 */
@Entity
@Table(name = "descricao_personagem")
@Data
@EqualsAndHashCode(callSuper = true)
public class DescricaoPersonagem extends BaseEntity {
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ficha_personagem_id", nullable = false)
    private FichaPersonagem fichaPersonagem;
    
    @Column(name = "genero")
    private String genero;
    
    @Column(name = "pronomes")
    private String pronomes;
    
    @Column(name = "idade")
    private String idade;
    
    @Column(name = "altura")
    private String altura;
    
    @Column(name = "peso")
    private String peso;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tamanho")
    private TiposTamanho tamanho;
    
    @Column(name = "fe")
    private String fe;
    
    @Column(name = "pele")
    private String pele;
    
    @Column(name = "olhos")
    private String olhos;
    
    @Column(name = "cabelos")
    private String cabelos;
    
    @Column(name = "aparencia_outros")
    private String aparenciaOutros;
    
    @Column(name = "particularidades", columnDefinition = "TEXT")
    private String particularidades;
    
    @Column(name = "conceito", columnDefinition = "TEXT")
    private String conceito;
    
    @Column(name = "tracos_personalidade", columnDefinition = "TEXT")
    private String tracosPersonalidade;
    
    @Column(name = "ideais", columnDefinition = "TEXT")
    private String ideais;
    
    @Column(name = "falhas", columnDefinition = "TEXT")
    private String falhas;
    
    @Column(name = "medos", columnDefinition = "TEXT")
    private String medos;
    
    @Column(name = "aliados", columnDefinition = "TEXT")
    private String aliados;
    
    @Column(name = "organizacoes", columnDefinition = "TEXT")
    private String organizacoes;
    
    @Column(name = "historia", columnDefinition = "TEXT")
    private String historia;
    
    @Column(name = "anotacoes", columnDefinition = "TEXT")
    private String anotacoes;
}

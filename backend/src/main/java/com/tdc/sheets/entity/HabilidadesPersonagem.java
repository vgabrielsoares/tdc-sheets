package com.tdc.sheets.entity;

import com.tdc.sheets.entity.enums.Atributos;
import com.tdc.sheets.entity.enums.GrauPericia;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * Entidade que representa as habilidades de um personagem
 * Baseada na tabela habilidades_personagem do schema SQL
 */
@Entity
@Table(name = "habilidades_personagem")
@Data
@EqualsAndHashCode(callSuper = true)
public class HabilidadesPersonagem extends BaseEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ficha_personagem_id", nullable = false)
    private FichaPersonagem fichaPersonagem;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habilidade_id", nullable = false)
    private Habilidade habilidade;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "grau_habilidade", nullable = false)
    private GrauPericia grauHabilidade;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "atributo_chave_personagem")
    private Atributos atributoChavePersonagem;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "atributo_personagem_id", nullable = false)
    private AtributosPersonagem atributoPersonagem;
    
    @Column(name = "mod_dado_habilidade")
    private Integer modDadoHabilidade;
    
    @Column(name = "mod_temp")
    private Integer modTemp;
    
    @Column(name = "mod_outros")
    private Integer modOutros;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

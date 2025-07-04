package com.tdc.sheets.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade que representa a cunhagem/moedas de um personagem
 * Baseada na tabela cunhagem_personagem do schema SQL
 */
@Entity
@Table(name = "cunhagem_personagem")
@Data
@EqualsAndHashCode(callSuper = true)
public class CunhagemPersonagem extends BaseEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carga_personagem_id", nullable = false)
    private CargaPersonagem cargaPersonagem;
    
    @Column(name = "c_fisico")
    private Integer cFisico;
    
    @Column(name = "po_fisico")
    private Integer poFisico;
    
    @Column(name = "pp_fisico")
    private Integer ppFisico;
    
    @Column(name = "c_banco")
    private Integer cBanco;
    
    @Column(name = "po_banco")
    private Integer poBanco;
    
    @Column(name = "pp_banco")
    private Integer ppBanco;
}

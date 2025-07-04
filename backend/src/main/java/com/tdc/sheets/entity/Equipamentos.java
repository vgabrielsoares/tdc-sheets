package com.tdc.sheets.entity;

import com.tdc.sheets.entity.enums.Raridades;
import com.tdc.sheets.entity.enums.TiposEquipamentos;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade que representa equipamentos do sistema TDC
 * Baseada na tabela equipamentos do schema SQL
 */
@Entity
@Table(name = "equipamentos")
@Data
@EqualsAndHashCode(callSuper = true)
public class Equipamentos extends BaseEntity {
    
    @Enumerated(EnumType.STRING)
    @Column(name = "raridade")
    private Raridades raridade;
    
    @Column(name = "peso")
    private Integer peso;
    
    @Column(name = "preco")
    private String preco;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TiposEquipamentos tipo;
    
    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;
}

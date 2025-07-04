package com.tdc.sheets.entity;

import com.tdc.sheets.entity.enums.ClassesFeiticos;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade que representa as classes de um feitiço
 * Baseada na tabela classes_feitico do schema SQL
 */
@Entity
@Table(name = "classes_feitico")
@Data
@EqualsAndHashCode(callSuper = true)
public class ClassesFeitico extends BaseEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feitico_id", nullable = false)
    private Feiticos feitico;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "classe_feitico")
    private ClassesFeiticos classeFeitico;
}

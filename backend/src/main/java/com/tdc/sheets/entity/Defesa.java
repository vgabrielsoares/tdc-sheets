package com.tdc.sheets.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

/**
 * Entidade representando a defesa de um personagem
 * 
 * @author Victor Gabriel Soares
 * @since 2025-06-28
 */
@Entity
@Table(name = "defesa")
public class Defesa extends BaseEntity {

    @NotNull(message = "Defesa do personagem é obrigatória")
    @Column(name = "defesa_personagem", nullable = false)
    private Integer defesaPersonagem = 15;

    @NotNull(message = "Valor da armadura é obrigatório")
    @Column(name = "armadura", nullable = false)
    private Integer armadura = 0;

    @Column(name = "vestindo_armadura")
    private Boolean vestindoArmadura = false;

    @Column(name = "limite_agilidade")
    private Integer limiteAgilidade;

    @Column(name = "outro_mod")
    private Integer outroMod = 0;

    // Relacionamento com FichaPersonagem
    @NotNull(message = "Ficha do personagem é obrigatória")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ficha_personagem_id", nullable = false)
    private FichaPersonagem fichaPersonagem;

    // Constructors
    public Defesa() {}

    public Defesa(FichaPersonagem fichaPersonagem) {
        this.fichaPersonagem = fichaPersonagem;
    }

    // Getters e Setters
    public Integer getDefesaPersonagem() {
        return defesaPersonagem;
    }

    public void setDefesaPersonagem(Integer defesaPersonagem) {
        this.defesaPersonagem = defesaPersonagem;
    }

    public Integer getArmadura() {
        return armadura;
    }

    public void setArmadura(Integer armadura) {
        this.armadura = armadura;
    }

    public Boolean getVestindoArmadura() {
        return vestindoArmadura;
    }

    public void setVestindoArmadura(Boolean vestindoArmadura) {
        this.vestindoArmadura = vestindoArmadura;
    }

    public Integer getLimiteAgilidade() {
        return limiteAgilidade;
    }

    public void setLimiteAgilidade(Integer limiteAgilidade) {
        this.limiteAgilidade = limiteAgilidade;
    }

    public Integer getOutroMod() {
        return outroMod;
    }

    public void setOutroMod(Integer outroMod) {
        this.outroMod = outroMod;
    }

    public FichaPersonagem getFichaPersonagem() {
        return fichaPersonagem;
    }

    public void setFichaPersonagem(FichaPersonagem fichaPersonagem) {
        this.fichaPersonagem = fichaPersonagem;
    }

    // Business Methods

    /**
     * Calcula a defesa total do personagem
     * Formula: defesa base + armadura + outros modificadores
     */
    public Integer getDefesaTotal() {
        Integer defesaBase = defesaPersonagem != null ? defesaPersonagem : 15;
        Integer valorArmadura = (vestindoArmadura && armadura != null) ? armadura : 0;
        Integer outrosMods = outroMod != null ? outroMod : 0;
        
        return defesaBase + valorArmadura + outrosMods;
    }

    @Override
    public String toString() {
        return "Defesa{" +
                "id=" + getId() +
                ", defesaPersonagem=" + defesaPersonagem +
                ", armadura=" + armadura +
                ", vestindoArmadura=" + vestindoArmadura +
                ", limiteAgilidade=" + limiteAgilidade +
                ", outroMod=" + outroMod +
                ", defesaTotal=" + getDefesaTotal() +
                '}';
    }
}

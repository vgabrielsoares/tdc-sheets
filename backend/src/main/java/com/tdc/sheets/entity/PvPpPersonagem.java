package com.tdc.sheets.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * Entidade representando os pontos de vida (PV) e pontos de poder (PP) de um personagem
 * 
 * @author Victor Gabriel Soares
 * @since 2025-06-28
 */
@Entity
@Table(name = "pv_pp_personagem")
public class PvPpPersonagem extends AuditableEntity {

    @NotNull(message = "PV máximo é obrigatório")
    @Min(value = 1, message = "PV máximo deve ser maior que 0")
    @Column(name = "pv_maximo", nullable = false)
    private Integer pvMaximo = 15;

    @NotNull(message = "PV atual é obrigatório")
    @Min(value = 0, message = "PV atual deve ser maior ou igual a 0")
    @Column(name = "pv_atual", nullable = false)
    private Integer pvAtual = 15;

    @Min(value = 0, message = "PV temporário deve ser maior ou igual a 0")
    @Column(name = "pv_temporario")
    private Integer pvTemporario;

    @NotNull(message = "PP máximo é obrigatório")
    @Min(value = 0, message = "PP máximo deve ser maior ou igual a 0")
    @Column(name = "pp_maximo", nullable = false)
    private Integer ppMaximo = 2;

    @NotNull(message = "PP atual é obrigatório")
    @Min(value = 0, message = "PP atual deve ser maior ou igual a 0")
    @Column(name = "pp_atual", nullable = false)
    private Integer ppAtual = 2;

    @Min(value = 0, message = "PP temporário deve ser maior ou igual a 0")
    @Column(name = "pp_temporario")
    private Integer ppTemporario;

    @Column(name = "limite_pp")
    private Integer limitePp;

    @Column(name = "limite_pp_bonus")
    private Integer limitePpBonus;

    // Relacionamento com FichaPersonagem
    @NotNull(message = "Ficha do personagem é obrigatória")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ficha_personagem_id", nullable = false)
    private FichaPersonagem fichaPersonagem;

    // Constructors
    public PvPpPersonagem() {}

    public PvPpPersonagem(FichaPersonagem fichaPersonagem) {
        this.fichaPersonagem = fichaPersonagem;
    }

    public PvPpPersonagem(Integer pvMaximo, Integer ppMaximo, FichaPersonagem fichaPersonagem) {
        this.pvMaximo = pvMaximo;
        this.pvAtual = pvMaximo;
        this.ppMaximo = ppMaximo;
        this.ppAtual = ppMaximo;
        this.fichaPersonagem = fichaPersonagem;
    }

    // Getters e Setters
    public Integer getPvMaximo() {
        return pvMaximo;
    }

    public void setPvMaximo(Integer pvMaximo) {
        this.pvMaximo = pvMaximo;
    }

    public Integer getPvAtual() {
        return pvAtual;
    }

    public void setPvAtual(Integer pvAtual) {
        this.pvAtual = pvAtual;
    }

    public Integer getPvTemporario() {
        return pvTemporario;
    }

    public void setPvTemporario(Integer pvTemporario) {
        this.pvTemporario = pvTemporario;
    }

    public Integer getPpMaximo() {
        return ppMaximo;
    }

    public void setPpMaximo(Integer ppMaximo) {
        this.ppMaximo = ppMaximo;
    }

    public Integer getPpAtual() {
        return ppAtual;
    }

    public void setPpAtual(Integer ppAtual) {
        this.ppAtual = ppAtual;
    }

    public Integer getPpTemporario() {
        return ppTemporario;
    }

    public void setPpTemporario(Integer ppTemporario) {
        this.ppTemporario = ppTemporario;
    }

    public Integer getLimitePp() {
        return limitePp;
    }

    public void setLimitePp(Integer limitePp) {
        this.limitePp = limitePp;
    }

    public Integer getLimitePpBonus() {
        return limitePpBonus;
    }

    public void setLimitePpBonus(Integer limitePpBonus) {
        this.limitePpBonus = limitePpBonus;
    }

    public FichaPersonagem getFichaPersonagem() {
        return fichaPersonagem;
    }

    public void setFichaPersonagem(FichaPersonagem fichaPersonagem) {
        this.fichaPersonagem = fichaPersonagem;
    }

    // Business Methods

    /**
     * Calcula o PV efetivo (atual + temporário)
     */
    public Integer getPvEfetivo() {
        Integer pvBase = pvAtual != null ? pvAtual : 0;
        Integer pvTemp = pvTemporario != null ? pvTemporario : 0;
        return pvBase + pvTemp;
    }

    /**
     * Calcula o PP efetivo (atual + temporário)
     */
    public Integer getPpEfetivo() {
        Integer ppBase = ppAtual != null ? ppAtual : 0;
        Integer ppTemp = ppTemporario != null ? ppTemporario : 0;
        return ppBase + ppTemp;
    }

    /**
     * Verifica se o personagem está inconsciente (PV <= 0)
     */
    public Boolean isInconsciente() {
        return getPvEfetivo() <= 0;
    }

    /**
     * Verifica se o personagem está com PV baixo (< 25% do máximo)
     */
    public Boolean isPvBaixo() {
        if (pvMaximo == null || pvMaximo == 0) return false;
        return getPvEfetivo() < (pvMaximo * 0.25);
    }

    /**
     * Aplica dano ao personagem
     */
    public void aplicarDano(Integer dano) {
        if (dano != null && dano > 0) {
            pvAtual = Math.max(0, pvAtual - dano);
        }
    }

    /**
     * Aplica cura ao personagem
     */
    public void aplicarCura(Integer cura) {
        if (cura != null && cura > 0) {
            pvAtual = Math.min(pvMaximo, pvAtual + cura);
        }
    }

    /**
     * Gasta pontos de poder
     */
    public boolean gastarPp(Integer custo) {
        if (custo != null && custo > 0 && getPpEfetivo() >= custo) {
            ppAtual = Math.max(0, ppAtual - custo);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "PvPpPersonagem{" +
                "id=" + getId() +
                ", pvMaximo=" + pvMaximo +
                ", pvAtual=" + pvAtual +
                ", pvTemporario=" + pvTemporario +
                ", ppMaximo=" + ppMaximo +
                ", ppAtual=" + ppAtual +
                ", ppTemporario=" + ppTemporario +
                ", limitePp=" + limitePp +
                ", limitePpBonus=" + limitePpBonus +
                '}';
    }
}

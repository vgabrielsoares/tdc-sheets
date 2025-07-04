package com.tdc.sheets.entity;

import com.tdc.sheets.entity.enums.ArquetipoNome;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * Entidade representando um arquétipo do sistema TDC
 * 
 * @author Victor Gabriel Soares
 * @since 2025-06-28
 */
@Entity
@Table(name = "arquetipo")
public class Arquetipo extends BaseEntity {

    @NotNull(message = "Nome do arquétipo é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(name = "nome", nullable = false)
    private ArquetipoNome nome;

    @NotNull(message = "PV inicial é obrigatório")
    @Min(value = 1, message = "PV inicial deve ser maior que 0")
    @Column(name = "pv_inicial", nullable = false)
    private Integer pvInicial;

    @NotNull(message = "PP inicial é obrigatório")
    @Min(value = 0, message = "PP inicial deve ser maior ou igual a 0")
    @Column(name = "pp_inicial", nullable = false)
    private Integer ppInicial;

    @NotNull(message = "PV por nível é obrigatório")
    @Min(value = 1, message = "PV por nível deve ser maior que 0")
    @Column(name = "pv_nivel", nullable = false)
    private Integer pvNivel;

    @NotNull(message = "PP por nível é obrigatório")
    @Min(value = 0, message = "PP por nível deve ser maior ou igual a 0")
    @Column(name = "pp_nivel", nullable = false)
    private Integer ppNivel;

    @NotNull(message = "Defesa nível 5 é obrigatória")
    @Column(name = "defesa_5", nullable = false)
    private Integer defesa5;

    @NotNull(message = "Defesa nível 10 é obrigatória")
    @Column(name = "defesa_10", nullable = false)
    private Integer defesa10;

    @NotNull(message = "Defesa nível 15 é obrigatória")
    @Column(name = "defesa_15", nullable = false)
    private Integer defesa15;

    // Constructors
    public Arquetipo() {}

    public Arquetipo(ArquetipoNome nome, Integer pvInicial, Integer ppInicial, Integer pvNivel, Integer ppNivel) {
        this.nome = nome;
        this.pvInicial = pvInicial;
        this.ppInicial = ppInicial;
        this.pvNivel = pvNivel;
        this.ppNivel = ppNivel;
    }

    // Getters e Setters
    public ArquetipoNome getNome() {
        return nome;
    }

    public void setNome(ArquetipoNome nome) {
        this.nome = nome;
    }

    public Integer getPvInicial() {
        return pvInicial;
    }

    public void setPvInicial(Integer pvInicial) {
        this.pvInicial = pvInicial;
    }

    public Integer getPpInicial() {
        return ppInicial;
    }

    public void setPpInicial(Integer ppInicial) {
        this.ppInicial = ppInicial;
    }

    public Integer getPvNivel() {
        return pvNivel;
    }

    public void setPvNivel(Integer pvNivel) {
        this.pvNivel = pvNivel;
    }

    public Integer getPpNivel() {
        return ppNivel;
    }

    public void setPpNivel(Integer ppNivel) {
        this.ppNivel = ppNivel;
    }

    public Integer getDefesa5() {
        return defesa5;
    }

    public void setDefesa5(Integer defesa5) {
        this.defesa5 = defesa5;
    }

    public Integer getDefesa10() {
        return defesa10;
    }

    public void setDefesa10(Integer defesa10) {
        this.defesa10 = defesa10;
    }

    public Integer getDefesa15() {
        return defesa15;
    }

    public void setDefesa15(Integer defesa15) {
        this.defesa15 = defesa15;
    }

    // Business Methods

    /**
     * Calcula o PV total para um determinado nível
     */
    public Integer calcularPvTotal(Integer nivel) {
        if (nivel == null || nivel < 1) return pvInicial;
        return pvInicial + ((nivel - 1) * pvNivel);
    }

    /**
     * Calcula o PP total para um determinado nível
     */
    public Integer calcularPpTotal(Integer nivel) {
        if (nivel == null || nivel < 1) return ppInicial;
        return ppInicial + ((nivel - 1) * ppNivel);
    }

    /**
     * Obtém o bônus de defesa baseado no nível
     */
    public Integer getBonusDefesa(Integer nivel) {
        if (nivel == null || nivel < 5) return 0;
        if (nivel < 10) return defesa5;
        if (nivel < 15) return defesa10;
        return defesa15;
    }

    @Override
    public String toString() {
        return "Arquetipo{" +
                "id=" + getId() +
                ", nome=" + nome +
                ", pvInicial=" + pvInicial +
                ", ppInicial=" + ppInicial +
                ", pvNivel=" + pvNivel +
                ", ppNivel=" + ppNivel +
                ", defesa5=" + defesa5 +
                ", defesa10=" + defesa10 +
                ", defesa15=" + defesa15 +
                '}';
    }
}

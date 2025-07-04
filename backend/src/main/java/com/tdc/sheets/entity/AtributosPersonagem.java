package com.tdc.sheets.entity;

import com.tdc.sheets.entity.enums.Atributos;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * Entidade representando os atributos de um personagem
 * 
 * @author Victor Gabriel Soares
 * @since 2025-06-28
 */
@Entity
@Table(name = "atributos_personagem", 
       uniqueConstraints = @UniqueConstraint(columnNames = {"ficha_personagem_id", "atributo"}),
       indexes = {
           @Index(name = "idx_atributos_ficha", columnList = "ficha_personagem_id"),
           @Index(name = "idx_atributos_tipo", columnList = "atributo"),
           @Index(name = "idx_atributos_ficha_tipo", columnList = "ficha_personagem_id, atributo")
       })
public class AtributosPersonagem extends BaseEntity {

    @NotNull(message = "Atributo é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(name = "atributo", nullable = false)
    private Atributos atributo;

    @NotNull(message = "Valor é obrigatório")
    @Min(value = 1, message = "Valor do atributo deve ser no mínimo 1")
    @Max(value = 30, message = "Valor do atributo deve ser no máximo 30")
    @Column(name = "valor", nullable = false)
    private Integer valor;

    @Min(value = -10, message = "Valor temporário deve ser no mínimo -10")
    @Max(value = 10, message = "Valor temporário deve ser no máximo 10")
    @Column(name = "valor_temp")
    private Integer valorTemp;

    // Relacionamento com FichaPersonagem
    @NotNull(message = "Ficha do personagem é obrigatória")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ficha_personagem_id", nullable = false)
    private FichaPersonagem fichaPersonagem;

    // Constructors
    public AtributosPersonagem() {}

    public AtributosPersonagem(Atributos atributo, Integer valor, FichaPersonagem fichaPersonagem) {
        this.atributo = atributo;
        this.valor = valor;
        this.fichaPersonagem = fichaPersonagem;
    }

    // Getters e Setters
    public Atributos getAtributo() {
        return atributo;
    }

    public void setAtributo(Atributos atributo) {
        this.atributo = atributo;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Integer getValorTemp() {
        return valorTemp;
    }

    public void setValorTemp(Integer valorTemp) {
        this.valorTemp = valorTemp;
    }

    public FichaPersonagem getFichaPersonagem() {
        return fichaPersonagem;
    }

    public void setFichaPersonagem(FichaPersonagem fichaPersonagem) {
        this.fichaPersonagem = fichaPersonagem;
    }

    // Business Methods
    
    /**
     * Calcula o valor efetivo do atributo (valor base + valor temporário)
     */
    public Integer getValorEfetivo() {
        Integer valorBase = valor != null ? valor : 0;
        Integer valorTemporario = valorTemp != null ? valorTemp : 0;
        return valorBase + valorTemporario;
    }

    /**
     * Calcula o modificador do atributo baseado no valor efetivo
     * Formula TDC: (valor - 10) / 2 (arredondado para baixo)
     */
    public Integer getModificador() {
        Integer valorEfetivo = getValorEfetivo();
        return Math.floorDiv(valorEfetivo - 10, 2);
    }

    /**
     * Retorna o bônus do atributo (modificador se positivo, 0 se negativo)
     */
    public Integer getBonus() {
        Integer modificador = getModificador();
        return Math.max(modificador, 0);
    }

    /**
     * Retorna a penalidade do atributo (modificador se negativo, 0 se positivo)
     */
    public Integer getPenalidade() {
        Integer modificador = getModificador();
        return Math.min(modificador, 0);
    }

    @Override
    public String toString() {
        return "AtributosPersonagem{" +
                "id=" + getId() +
                ", atributo=" + atributo +
                ", valor=" + valor +
                ", valorTemp=" + valorTemp +
                ", valorEfetivo=" + getValorEfetivo() +
                ", modificador=" + getModificador() +
                '}';
    }
}

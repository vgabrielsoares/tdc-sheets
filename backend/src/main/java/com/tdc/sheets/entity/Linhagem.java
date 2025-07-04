package com.tdc.sheets.entity;

import com.tdc.sheets.entity.enums.TiposTamanho;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Entidade representando a linhagem de um personagem
 * 
 * @author Victor Gabriel Soares
 * @since 2025-06-28
 */
@Entity
@Table(name = "linhagem")
public class Linhagem extends BaseEntity {

    @NotBlank(message = "Nome da linhagem é obrigatório")
    @Size(max = 100, message = "Nome da linhagem deve ter no máximo 100 caracteres")
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "tamanho")
    private TiposTamanho tamanho;

    @Size(max = 50, message = "Altura média deve ter no máximo 50 caracteres")
    @Column(name = "altura_media", length = 50)
    private String alturaMedia;

    @Size(max = 50, message = "Peso médio deve ter no máximo 50 caracteres")
    @Column(name = "peso_medio", length = 50)
    private String pesoMedio;

    @Size(max = 50, message = "Maioridade deve ter no máximo 50 caracteres")
    @Column(name = "maioridade", length = 50)
    private String maioridade;

    @Size(max = 50, message = "Expectativa de vida deve ter no máximo 50 caracteres")
    @Column(name = "expectativa_vida", length = 50)
    private String expectativaVida;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    // Constructors
    public Linhagem() {}

    public Linhagem(String nome) {
        this.nome = nome;
    }

    public Linhagem(String nome, TiposTamanho tamanho, String descricao) {
        this.nome = nome;
        this.tamanho = tamanho;
        this.descricao = descricao;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TiposTamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(TiposTamanho tamanho) {
        this.tamanho = tamanho;
    }

    public String getAlturaMedia() {
        return alturaMedia;
    }

    public void setAlturaMedia(String alturaMedia) {
        this.alturaMedia = alturaMedia;
    }

    public String getPesoMedio() {
        return pesoMedio;
    }

    public void setPesoMedio(String pesoMedio) {
        this.pesoMedio = pesoMedio;
    }

    public String getMaioridade() {
        return maioridade;
    }

    public void setMaioridade(String maioridade) {
        this.maioridade = maioridade;
    }

    public String getExpectativaVida() {
        return expectativaVida;
    }

    public void setExpectativaVida(String expectativaVida) {
        this.expectativaVida = expectativaVida;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Linhagem{" +
                "id=" + getId() +
                ", nome='" + nome + '\'' +
                ", tamanho=" + tamanho +
                ", alturaMedia='" + alturaMedia + '\'' +
                ", pesoMedio='" + pesoMedio + '\'' +
                ", maioridade='" + maioridade + '\'' +
                ", expectativaVida='" + expectativaVida + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}

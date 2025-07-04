package com.tdc.sheets.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Entidade representando a origem de um personagem
 * 
 * @author Victor Gabriel Soares
 * @since 2025-06-28
 */
@Entity
@Table(name = "origem")
public class Origem extends BaseEntity {

    @NotBlank(message = "Nome da origem é obrigatório")
    @Size(max = 100, message = "Nome da origem deve ter no máximo 100 caracteres")
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "habilidade_especial", columnDefinition = "TEXT")
    private String habilidadeEspecial;

    // Constructors
    public Origem() {}

    public Origem(String nome) {
        this.nome = nome;
    }

    public Origem(String nome, String descricao, String habilidadeEspecial) {
        this.nome = nome;
        this.descricao = descricao;
        this.habilidadeEspecial = habilidadeEspecial;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getHabilidadeEspecial() {
        return habilidadeEspecial;
    }

    public void setHabilidadeEspecial(String habilidadeEspecial) {
        this.habilidadeEspecial = habilidadeEspecial;
    }

    @Override
    public String toString() {
        return "Origem{" +
                "id=" + getId() +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", habilidadeEspecial='" + habilidadeEspecial + '\'' +
                '}';
    }
}

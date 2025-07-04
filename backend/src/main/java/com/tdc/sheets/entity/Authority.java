package com.tdc.sheets.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

/**
 * Entidade representando uma autoridade/role no sistema
 * 
 * @author Victor Gabriel Soares
 * @since 2025-06-28
 */
@Entity
@Table(name = "authorities")
public class Authority extends BaseEntity {

    @NotBlank(message = "Authority é obrigatória")
    @Size(max = 50, message = "Authority deve ter no máximo 50 caracteres")
    @Column(name = "authority", nullable = false, unique = true, length = 50)
    private String authority;

    @Column(name = "descricao", length = 200)
    private String descricao;

    // Relacionamento com usuários
    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();

    // Constructors
    public Authority() {}

    public Authority(String authority) {
        this.authority = authority;
    }

    public Authority(String authority, String descricao) {
        this.authority = authority;
        this.descricao = descricao;
    }

    // Getters e Setters
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    // Helper methods
    public void addUser(User user) {
        users.add(user);
        user.getAuthorities().add(this);
    }

    public void removeUser(User user) {
        users.remove(user);
        user.getAuthorities().remove(this);
    }

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + getId() +
                ", authority='" + authority + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}

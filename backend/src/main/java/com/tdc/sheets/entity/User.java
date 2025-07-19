package com.tdc.sheets.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

/**
 * Entidade representando um usuário do sistema
 * 
 * @author Victor Gabriel Soares
 * @since 2025-06-28
 */
@Entity
@Table(name = "user", indexes = {
    @Index(name = "idx_user_username", columnList = "username"),
    @Index(name = "idx_user_email", columnList = "email"),
    @Index(name = "idx_user_active", columnList = "is_active"),
    @Index(name = "idx_user_deleted", columnList = "deleted_at")
})
public class User extends AuditableEntity {

    @NotBlank(message = "Nome completo é obrigatório")
    @Size(max = 100, message = "Nome completo deve ter no máximo 100 caracteres")
    @Column(name = "nome_completo", nullable = false, length = 100)
    private String nomeCompleto;

    @NotBlank(message = "Username é obrigatório")
    @Size(min = 3, max = 50, message = "Username deve ter entre 3 e 50 caracteres")
    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    @Email(message = "Email deve ter formato válido")
    @Column(name = "email", unique = true, length = 100)
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 60, max = 60, message = "Senha deve ter 60 caracteres (hash BCrypt)")
    @Column(name = "senha", nullable = false, length = 60)
    private String senha;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;

    // Relacionamento com as fichas do usuário
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<FichaPersonagem> fichas = new ArrayList<>();

    // Relacionamento com as authorities/roles
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_authority",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private List<Authority> authorities = new ArrayList<>();

    // Constructors
    public User() {}

    public User(String nomeCompleto, String username, String email, String senha) {
        this.nomeCompleto = nomeCompleto;
        this.username = username;
        this.email = email;
        this.senha = senha;
    }

    // Getters e Setters
    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public List<FichaPersonagem> getFichas() {
        return fichas;
    }

    public void setFichas(List<FichaPersonagem> fichas) {
        this.fichas = fichas;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    // Helper methods
    public void addFicha(FichaPersonagem ficha) {
        fichas.add(ficha);
        ficha.setUser(this);
    }

    public void removeFicha(FichaPersonagem ficha) {
        fichas.remove(ficha);
        ficha.setUser(null);
    }

    public void addAuthority(Authority authority) {
        authorities.add(authority);
    }

    public void removeAuthority(Authority authority) {
        authorities.remove(authority);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", username='" + username + '\'' +
                ", nomeCompleto='" + nomeCompleto + '\'' +
                ", email='" + email + '\'' +
                ", ativo=" + ativo +
                '}';
    }
}

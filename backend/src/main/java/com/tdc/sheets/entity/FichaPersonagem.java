package com.tdc.sheets.entity;

import com.tdc.sheets.entity.enums.TiposTamanho;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

/**
 * Entidade principal representando uma ficha de personagem do sistema TDC
 * 
 * @author Victor Gabriel Soares
 * @since 2025-06-28
 */
@Entity
@Table(name = "ficha_personagem", indexes = {
    @Index(name = "idx_ficha_user_origem", columnList = "user_id, origem_id"),
    @Index(name = "idx_ficha_created_at", columnList = "created_at"),
    @Index(name = "idx_ficha_updated_at", columnList = "updated_at"),
    @Index(name = "idx_ficha_active", columnList = "is_active"),
    @Index(name = "idx_ficha_deleted", columnList = "deleted_at")
})
public class FichaPersonagem extends AuditableEntity {

    @NotBlank(message = "Nome do personagem é obrigatório")
    @Size(max = 100, message = "Nome do personagem deve ter no máximo 100 caracteres")
    @Column(name = "nome_personagem", nullable = false, length = 100)
    private String nomePersonagem;

    @Column(name = "background", columnDefinition = "TEXT")
    private String background;

    @NotNull(message = "Experiência é obrigatória")
    @Min(value = 0, message = "Experiência deve ser maior ou igual a 0")
    @Column(name = "experiencia", nullable = false)
    private Integer experiencia = 0;

    @NotNull(message = "Habilidade assinatura é obrigatória")
    @Column(name = "habilidade_assinatura", nullable = false)
    private Integer habilidadeAssinatura;

    @NotNull(message = "Nível de sorte é obrigatório")
    @Min(value = 0, message = "Nível de sorte deve ser maior ou igual a 0")
    @Column(name = "nivel_sorte", nullable = false)
    private Integer nivelSorte = 0;

    @Enumerated(EnumType.STRING)
    @Column(name = "tamanho")
    private TiposTamanho tamanho;

    // Relacionamentos

    @NotNull(message = "Usuário é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull(message = "Origem é obrigatória")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origem_id", nullable = false)
    private Origem origem;

    @NotNull(message = "Linhagem é obrigatória")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "linhagem_id", nullable = false)
    private Linhagem linhagem;

    // Relacionamentos One-to-One com entidades dependentes
    @OneToOne(mappedBy = "fichaPersonagem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Defesa defesa;

    @OneToOne(mappedBy = "fichaPersonagem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private PvPpPersonagem pvPp;

    @OneToOne(mappedBy = "fichaPersonagem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private DeslocamentoPersonagem deslocamento;

    @OneToOne(mappedBy = "fichaPersonagem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private SentidosPersonagem sentidos;

    @OneToOne(mappedBy = "fichaPersonagem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private AcoesPersonagem acoes;

    @OneToOne(mappedBy = "fichaPersonagem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private CargaPersonagem carga;

    @OneToOne(mappedBy = "fichaPersonagem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private DescricaoPersonagem descricao;

    // Relacionamentos One-to-Many
    @OneToMany(mappedBy = "fichaPersonagem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<AtributosPersonagem> atributos = new ArrayList<>();

    @OneToMany(mappedBy = "fichaPersonagem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<HabilidadesPersonagem> habilidades = new ArrayList<>();

    @OneToMany(mappedBy = "fichaPersonagem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ArquetiposPersonagem> arquetipos = new ArrayList<>();

    @OneToMany(mappedBy = "fichaPersonagem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ClassesPersonagem> classes = new ArrayList<>();

    @OneToMany(mappedBy = "fichaPersonagem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<FeiticosPersonagem> feiticos = new ArrayList<>();

    @OneToMany(mappedBy = "fichaPersonagem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<AtaquesPersonagem> ataques = new ArrayList<>();

    @OneToMany(mappedBy = "fichaPersonagem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ResistenciasPersonagem> resistencias = new ArrayList<>();

    @OneToMany(mappedBy = "fichaPersonagem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ProficienciasPersonagem> proficiencias = new ArrayList<>();

    @OneToMany(mappedBy = "fichaPersonagem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ParticularidadesPersonagem> particularidades = new ArrayList<>();

    @OneToMany(mappedBy = "fichaPersonagem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<CompartilhamentoFicha> compartilhamentos = new ArrayList<>();

    // Constructors
    public FichaPersonagem() {}

    public FichaPersonagem(String nomePersonagem, User user, Origem origem, Linhagem linhagem) {
        this.nomePersonagem = nomePersonagem;
        this.user = user;
        this.origem = origem;
        this.linhagem = linhagem;
    }

    // Getters e Setters
    public String getNomePersonagem() {
        return nomePersonagem;
    }

    public void setNomePersonagem(String nomePersonagem) {
        this.nomePersonagem = nomePersonagem;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public Integer getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(Integer experiencia) {
        this.experiencia = experiencia;
    }

    public Integer getHabilidadeAssinatura() {
        return habilidadeAssinatura;
    }

    public void setHabilidadeAssinatura(Integer habilidadeAssinatura) {
        this.habilidadeAssinatura = habilidadeAssinatura;
    }

    public Integer getNivelSorte() {
        return nivelSorte;
    }

    public void setNivelSorte(Integer nivelSorte) {
        this.nivelSorte = nivelSorte;
    }

    public TiposTamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(TiposTamanho tamanho) {
        this.tamanho = tamanho;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Origem getOrigem() {
        return origem;
    }

    public void setOrigem(Origem origem) {
        this.origem = origem;
    }

    public Linhagem getLinhagem() {
        return linhagem;
    }

    public void setLinhagem(Linhagem linhagem) {
        this.linhagem = linhagem;
    }

    // Getters e Setters para relacionamentos One-to-One
    public Defesa getDefesa() {
        return defesa;
    }

    public void setDefesa(Defesa defesa) {
        this.defesa = defesa;
        if (defesa != null) {
            defesa.setFichaPersonagem(this);
        }
    }

    public PvPpPersonagem getPvPp() {
        return pvPp;
    }

    public void setPvPp(PvPpPersonagem pvPp) {
        this.pvPp = pvPp;
        if (pvPp != null) {
            pvPp.setFichaPersonagem(this);
        }
    }

    // Getters e Setters para relacionamentos One-to-Many
    public List<AtributosPersonagem> getAtributos() {
        return atributos;
    }

    public void setAtributos(List<AtributosPersonagem> atributos) {
        this.atributos = atributos;
    }

    public List<HabilidadesPersonagem> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(List<HabilidadesPersonagem> habilidades) {
        this.habilidades = habilidades;
    }

    public List<ArquetiposPersonagem> getArquetipos() {
        return arquetipos;
    }

    public void setArquetipos(List<ArquetiposPersonagem> arquetipos) {
        this.arquetipos = arquetipos;
    }

    public List<FeiticosPersonagem> getFeiticos() {
        return feiticos;
    }

    public void setFeiticos(List<FeiticosPersonagem> feiticos) {
        this.feiticos = feiticos;
    }

    public List<CompartilhamentoFicha> getCompartilhamentos() {
        return compartilhamentos;
    }

    public void setCompartilhamentos(List<CompartilhamentoFicha> compartilhamentos) {
        this.compartilhamentos = compartilhamentos;
    }

    // Helper methods
    public void addAtributo(AtributosPersonagem atributo) {
        atributos.add(atributo);
        atributo.setFichaPersonagem(this);
    }

    public void removeAtributo(AtributosPersonagem atributo) {
        atributos.remove(atributo);
        atributo.setFichaPersonagem(null);
    }

    // Helper methods removed for compilation - will be added back when needed

    /**
     * Calcula o nível total do personagem baseado na experiência
     */
    public Integer getNivelTotal() {
        // Implementar lógica de cálculo de nível baseado na experiência
        // Por enquanto, retorna nível 1 como padrão
        if (experiencia == null || experiencia < 100) {
            return 1;
        }
        return Math.min((experiencia / 100) + 1, 20); // Máximo nível 20
    }

    @Override
    public String toString() {
        return "FichaPersonagem{" +
                "id=" + getId() +
                ", nomePersonagem='" + nomePersonagem + '\'' +
                ", experiencia=" + experiencia +
                ", nivelSorte=" + nivelSorte +
                ", tamanho=" + tamanho +
                '}';
    }
}

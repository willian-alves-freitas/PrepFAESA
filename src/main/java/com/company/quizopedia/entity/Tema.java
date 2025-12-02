package com.company.quizopedia.entity;

import io.jmix.core.MetadataTools;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "TEMA")
@Entity
public class Tema {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "ICONE")
    private byte[] icone;

    @Column(name = "NOME", nullable = false)
    @Lob
    @NotNull
    private String nome;

    @Column(name = "DESCRICAO")
    @Lob
    private String descricao;

    @Size(message = "{msg://com.company.quizopedia.entity/Tema.conteudos.validation.Size}", min = 1)
    @Composition
    @OneToMany(mappedBy = "tema")
    private List<Conteudo> conteudos;

    @JoinTable(name = "TURMA_TEMA_LINK",
            joinColumns = @JoinColumn(name = "TEMA_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "TURMA_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<Turma> turmas;

    @Composition
    @OneToMany(mappedBy = "tema")
    private List<Questionario> questionarios;

    @Column(name = "REFERENCIA_PONTUACAO", nullable = false)
    @NotNull
    private Integer referenciaPontuacao;

    public Integer getReferenciaPontuacao() {
        return referenciaPontuacao;
    }

    public void setReferenciaPontuacao(Integer referenciaPontuacao) {
        this.referenciaPontuacao = referenciaPontuacao;
    }

    public List<Questionario> getQuestionarios() {
        return questionarios;
    }

    public void setQuestionarios(List<Questionario> questionarios) {
        this.questionarios = questionarios;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    public List<Conteudo> getConteudos() {
        return conteudos;
    }

    public void setConteudos(List<Conteudo> conteudos) {
        this.conteudos = conteudos;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public byte[] getIcone() {
        return icone;
    }

    public void setIcone(byte[] icone) {
        this.icone = icone;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @InstanceName
    @DependsOnProperties({"nome"})
    public String getInstanceName(MetadataTools metadataTools) {
        return metadataTools.format(nome);
    }
}
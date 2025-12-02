package com.company.quizopedia.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "QUESTIONARIO", indexes = {
        @Index(name = "IDX_QUESTIONARIO_TEMA", columnList = "TEMA_ID"),
        @Index(name = "IDX_QUESTIONARIO_USER", columnList = "USER_ID")
})
@Entity
public class Questionario {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Composition
    @OneToMany(mappedBy = "questionario", cascade = CascadeType.PERSIST)
    private List<Questao> questoes;

    @NotNull
    @Column(name = "REALIZACAO", nullable = false)
    private LocalDateTime realizacao;

    @Column(name = "ESTADO", nullable = false)
    @NotNull
    private String estado;

    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "TEMA_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Tema tema;

    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "USER_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    @Positive(message = "{msg://com.company.quizopedia.entity/Questionario.pontuacaoMaxima.validation.Positive}")
    @Column(name = "PONTUACAO_MAXIMA", nullable = false)
    @NotNull
    private Integer pontuacaoMaxima;

    public void setRealizacao(LocalDateTime realizacao) {
        this.realizacao = realizacao;
    }

    public LocalDateTime getRealizacao() {
        return realizacao;
    }

    public List<Questao> getQuestoes() {
        return questoes;
    }

    public void setQuestoes(List<Questao> questoes) {
        this.questoes = questoes;
    }

    public Integer getPontuacaoMaxima() {
        return pontuacaoMaxima;
    }

    public void setPontuacaoMaxima(Integer pontuacaoMaxima) {
        this.pontuacaoMaxima = pontuacaoMaxima;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public Estado getEstado() {
        return estado == null ? null : Estado.fromId(estado);
    }

    public void setEstado(Estado estado) {
        this.estado = estado == null ? null : estado.getId();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}
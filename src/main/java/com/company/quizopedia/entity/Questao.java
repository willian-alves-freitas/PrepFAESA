package com.company.quizopedia.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "QUESTAO", indexes = {
        @Index(name = "IDX_QUESTAO_RESPOSTA", columnList = "RESPOSTA_ID"),
        @Index(name = "IDX_QUESTAO_QUESTIONARIO", columnList = "QUESTIONARIO_ID")
})
@Entity
public class Questao {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "ENUNCIADO", nullable = false)
    @Lob
    @NotNull
    private String enunciado;

    @Composition
    @OneToMany(mappedBy = "questao", cascade = CascadeType.PERSIST)
    private List<Opcao> opcoes;

    @JoinColumn(name = "RESPOSTA_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private Opcao opcaoSelecionada;

    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "QUESTIONARIO_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Questionario questionario;

    public Questionario getQuestionario() {
        return questionario;
    }

    public void setQuestionario(Questionario questionario) {
        this.questionario = questionario;
    }

    public Opcao getOpcaoSelecionada() {
        return opcaoSelecionada;
    }

    public void setOpcaoSelecionada(Opcao opcaoSelecionada) {
        this.opcaoSelecionada = opcaoSelecionada;
    }

    public List<Opcao> getOpcoes() {
        return opcoes;
    }

    public void setOpcoes(List<Opcao> opcoes) {
        this.opcoes = opcoes;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}
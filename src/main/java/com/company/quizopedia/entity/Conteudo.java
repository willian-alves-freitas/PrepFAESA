package com.company.quizopedia.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.FileRef;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@JmixEntity
@Table(name = "CONTEUDO", indexes = {
        @Index(name = "IDX_CONTEUDO_TEMA", columnList = "TEMA_ID")
})
@Entity
public class Conteudo {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @NotBlank(message = "{msg://com.company.quizopedia.entity/Conteudo.nome.validation.NotBlank}")
    @NotEmpty(message = "{msg://com.company.quizopedia.entity/Conteudo.nome.validation.NotEmpty}")
    @Column(name = "NOME", nullable = false)
    @Lob
    @NotNull(message = "{msg://com.company.quizopedia.entity/Conteudo.nome.validation.NotNull}")
    private String nome;

    @Column(name = "DESCRICAO")
    @Lob
    private String descricao;

    @Column(name = "ARQUIVO", nullable = false, length = 1024)
    @NotNull(message = "{msg://com.company.quizopedia.entity/Conteudo.arquivo.validation.NotNull}")
    private FileRef arquivo;

    @OnDeleteInverse(DeletePolicy.CASCADE)
    @JoinColumn(name = "TEMA_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Tema tema;

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public FileRef getArquivo() {
        return arquivo;
    }

    public void setArquivo(FileRef arquivo) {
        this.arquivo = arquivo;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}
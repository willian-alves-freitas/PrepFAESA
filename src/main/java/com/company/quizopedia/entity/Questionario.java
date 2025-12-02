package com.company.quizopedia.entity;

import io.jmix.core.DeletePolicy;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.entity.annotation.OnDeleteInverse;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
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

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "REALIZACAO", nullable = false)
    private Date realizacao;

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

    public Date getRealizacao() {
        return realizacao;
    }

    public void setRealizacao(Date realizacao) {
        this.realizacao = realizacao;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}
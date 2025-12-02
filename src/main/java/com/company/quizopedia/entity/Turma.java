package com.company.quizopedia.entity;

import io.jmix.core.MetadataTools;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "TURMA")
@Entity
public class Turma {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "CODIGO", nullable = false)
    @Lob
    @NotNull
    private String codigo;

    @JoinTable(name = "TURMA_TEMA_LINK",
            joinColumns = @JoinColumn(name = "TURMA_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "TEMA_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<Tema> temas;

    @OneToMany(mappedBy = "turma")
    private List<User> alunos;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<User> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<User> alunos) {
        this.alunos = alunos;
    }

    public List<Tema> getTemas() {
        return temas;
    }

    public void setTemas(List<Tema> temas) {
        this.temas = temas;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @InstanceName
    @DependsOnProperties({"codigo"})
    public String getInstanceName(MetadataTools metadataTools) {
        return metadataTools.format(codigo);
    }
}
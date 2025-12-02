package com.company.quizopedia.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum Estado implements EnumClass<String> {

    CRIADO("A"),
    INICIADO("B"),
    VENCIDO("C"),
    FINALIZADO("D");

    private final String id;

    Estado(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static Estado fromId(String id) {
        for (Estado at : Estado.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}
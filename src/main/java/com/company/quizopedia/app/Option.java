package com.company.quizopedia.app;

import org.springframework.stereotype.Component;

@Component
public class Option {
    private String id;
    private String text;

    // Getters e Setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
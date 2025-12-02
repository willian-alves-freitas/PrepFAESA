package com.company.quizopedia.app;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class Question {
    private String question;
    private List<Option> options;
    private String correctAnswer;
    private Map<String, String> explanations;

    // Getters e Setters
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Option> getOptions() {
        return options;
    }
    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Map<String, String> getExplanations() {
        return explanations;
    }
    public void setExplanations(Map<String, String> explanations) {
        this.explanations = explanations;
    }
}
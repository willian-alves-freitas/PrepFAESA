package com.company.quizopedia.app;

import java.util.List;
import org.springframework.stereotype.Component;


@Component
public class Quiz {

    private List<Question> quiz;

    public List<Question> getQuiz() {
        return quiz;
    }

    public void setQuiz(List<Question> quiz) {
        this.quiz = quiz;
    }
}

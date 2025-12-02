package com.company.quizopedia.view.questionario;


import com.company.quizopedia.app.Option;
import com.company.quizopedia.app.Question;
import com.company.quizopedia.app.Quiz;
import com.company.quizopedia.view.main.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.ViewComponent;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Route(value = "questionario-view", layout = MainView.class)
@ViewController(id = "QuestionarioView")
@ViewDescriptor(path = "questionario-view.xml")
public class QuestionarioView extends StandardView {
    private Quiz quiz;
    private String tema;

    @ViewComponent
    private VerticalLayout vbox;
    @Autowired
    private UiComponents uiComponents;

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Quiz getQuiz(Quiz quiz) {
        return this.quiz;
    }

    public void buildLayout() {
        if (quiz == null)
            return;

        List<RadioButtonGroup> questions = new ArrayList<RadioButtonGroup>();
        VerticalLayout layoutQuestao;
        List<String> options;
        for (Question question: quiz.getQuiz()) {
            layoutQuestao = uiComponents.create(VerticalLayout.class);
            H4 titulo = uiComponents.create(H4.class);
            titulo.setText(question.getQuestion());
            layoutQuestao.add(titulo);

            options = new ArrayList<String>();
            for (Option option: question.getOptions())
                options.add(option.getId()+") "+option.getText());

            RadioButtonGroup radioButtonGroup = uiComponents.create(RadioButtonGroup.class);
            radioButtonGroup.setItems(options);
            radioButtonGroup.setThemeName("vertical");
            layoutQuestao.add(radioButtonGroup);
            questions.add(radioButtonGroup);

            vbox.add(layoutQuestao);
        }

        HorizontalLayout hLayout = uiComponents.create(HorizontalLayout.class);
        Button save = uiComponents.create(Button.class);
        save.setText("Salvar");
        save.setClassName("bg-primary");
        save.getStyle().set("color","white");
        hLayout.add(save);

        save.addClickListener(event -> {

        });

        Button cancel = uiComponents.create(Button.class);
        cancel.setText("Cancelar");
        cancel.setClassName("bg-secundary");
        hLayout.add(cancel);

        cancel.addClickListener(event -> {
            this.closeWithDefaultAction();
        });

        hLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        hLayout.setAlignSelf(FlexComponent.Alignment.CENTER);
        hLayout.setWidthFull();

        vbox.add(hLayout);
    }

    public void setTema(String nome) {
        this.tema = tema;
    }

    public String getTema() {
        return this.tema;
    }
}

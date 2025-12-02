package com.company.quizopedia.view.questionario;


import com.company.quizopedia.app.Option;
import com.company.quizopedia.app.Question;
import com.company.quizopedia.app.Quiz;
import com.company.quizopedia.entity.*;
import com.company.quizopedia.view.main.MainView;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.router.Route;
import io.jmix.core.DataManager;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.ViewComponent;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Route(value = "questionario-view", layout = MainView.class)
@ViewController(id = "QuestionarioView")
@ViewDescriptor(path = "questionario-view.xml")
public class QuestionarioView extends StandardView {

    private Questionario questionario;

    @ViewComponent
    private VerticalLayout vbox;
    @Autowired
    private UiComponents uiComponents;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private CurrentAuthentication currentAuthentication;

    public void setQuiz(Quiz quiz, Tema tema) {
        Questionario questionario = dataManager.create(Questionario.class);
        questionario.setEstado(Estado.INICIADO);
        questionario.setTema(tema);

        List<Questao> questoes = new LinkedList<Questao>();
        List<Opcao> opcoes;
        List<Question> questions = quiz.getQuiz();
        Opcao opcao;
        Questao questao;
        for (Question question: questions) {
            questao = dataManager.create(Questao.class);
            questao.setQuestionario(questionario);
            questao.setEnunciado(question.getQuestion());

            opcoes = new LinkedList<Opcao>();
            for (Option option: question.getOptions()) {
                opcao = dataManager.create(Opcao.class);
                opcao.setQuestao(questao);
                opcao.setDescricao(option.getText());

                if (option.getId().equals(question.getCorrectAnswer()))
                    opcao.setCorreta(true);
                else
                    opcao.setCorreta(false);

                opcao.setExplicacao(question.getExplanations().get(option.getId()));

                opcoes.add(opcao);
            }

            questao.setOpcoes(opcoes);

            questoes.add(questao);
        }

        questionario.setQuestoes(questoes);
        questionario.setUser((User) currentAuthentication.getUser());
        questionario.setRealizacao(LocalDateTime.now());
        questionario.setPontuacaoMaxima(questionario.getTema().getReferenciaPontuacao());

        dataManager.save(questionario);

        this.questionario = questionario;
    }

    public void buildLayout() {
        if (questionario == null)
            return;

        List<RadioButtonGroup<Opcao>> radioButtonGroups = new ArrayList<RadioButtonGroup<Opcao>>();
        VerticalLayout layoutQuestao;
        List<String> options;

        for (Questao questao: questionario.getQuestoes()) {
            layoutQuestao = uiComponents.create(VerticalLayout.class);

            RadioButtonGroup<Opcao> radioButtonGroup = uiComponents.create(RadioButtonGroup.class);
            radioButtonGroup.setLabel(questao.getEnunciado());
            radioButtonGroup.setItems(questao.getOpcoes());
            radioButtonGroup.setThemeName("vertical");
            radioButtonGroup.setRequired(true);
            layoutQuestao.add(radioButtonGroup);
            radioButtonGroups.add(radioButtonGroup);

            vbox.add(layoutQuestao);
        }

        HorizontalLayout hLayout = uiComponents.create(HorizontalLayout.class);
        Button save = uiComponents.create(Button.class);
        save.setText("Salvar");
        save.setClassName("bg-primary");
        save.getStyle().set("color","white");
        hLayout.add(save);

        save.addClickListener(event -> {
            boolean validado = true;
            for (RadioButtonGroup<Opcao> radioButtonGroup: radioButtonGroups) {
                if (radioButtonGroup.getValue() == null) {
                    validado = false;
                    break;
                }
            }
            if (validado) {
                Questao questao;
                for (int i = 0; i < questionario.getQuestoes().size(); i++) {
                    questao = questionario.getQuestoes().get(i);
                    questao.setOpcaoSelecionada(radioButtonGroups.get(i).getValue());
                    dataManager.save(questao);
                    radioButtonGroups.get(i).setReadOnly(true);

                    String explicacao = "";
                    if (questao.getOpcaoSelecionada().getCorreta()) {
                        explicacao += "Correta! ";
                    } else {
                        explicacao += "Incorreta! ";
                    }
                    for (Opcao opcao: questao.getOpcoes())
                        explicacao += opcao.getExplicacao() + "\n";
                    radioButtonGroups.get(i).setHelperText(explicacao);
                }
                questionario.setEstado(Estado.FINALIZADO);
                save.setEnabled(false);
                save.setClassName("bg-contrast-50");
                dataManager.save(questionario);
                System.out.println("Validado");
            }
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

}

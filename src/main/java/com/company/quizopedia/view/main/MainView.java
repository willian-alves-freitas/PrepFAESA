package com.company.quizopedia.view.main;

import com.company.quizopedia.app.Option;
import com.company.quizopedia.app.Question;
import com.company.quizopedia.app.Quiz;
import com.company.quizopedia.entity.Tema;
import com.company.quizopedia.entity.User;
import com.company.quizopedia.view.questionario.QuestionarioView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.groq.sdk.client.GroqClient;
import com.groq.sdk.models.chat.ChatCompletionRequest;
import com.groq.sdk.models.chat.ChatMessage;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.router.Route;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.flowui.*;
import io.jmix.flowui.app.main.StandardMainView;
import io.jmix.flowui.component.SupportsTypedValue;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.util.List;

@Route("")
@ViewController(id = "MainView")
@ViewDescriptor(path = "main-view.xml")
public class MainView extends StandardMainView {

    @ViewComponent
    private H5 contadorMoedas;
    @ViewComponent
    private VerticalLayout vbox;
    @Autowired
    private CurrentAuthentication currentAuthentication;
    @Autowired
    private UiComponents uiComponents;
    @Autowired
    private ViewNavigators viewNavigators;
    @Autowired
    private Dialogs dialogs;
    @Autowired
    private Notifications notifications;
    @Autowired
    private DialogWindows dialogWindows;

    @Subscribe
    public void onInit(final InitEvent event) {

        final User user = (User) currentAuthentication.getUser();
        List<Tema> temas = user.getTurma().getTemas();
        contadorMoedas.setText(String.format("%d moedas", 200));

        HorizontalLayout layoutAuxiliar;
        for (Tema tema: temas) {
            layoutAuxiliar = uiComponents.create(HorizontalLayout.class);
            layoutAuxiliar.setPadding(true);
            layoutAuxiliar.setClassName("bg-primary rounded-l");
            layoutAuxiliar.setWidth("100%");

            H5 titulo = uiComponents.create(H5.class);
            titulo.setText(tema.getNome());
            titulo.getStyle().set("color","white");
            layoutAuxiliar.add(titulo);
            layoutAuxiliar.getStyle().set("cursor","pointer");

            layoutAuxiliar.addClickListener(event1 -> {

                // Initialize client
                GroqClient client = GroqClient.builder()
                        .apiKey("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")
                        .timeout(Duration.ofSeconds(30))
                        .maxRetries(3)
                        .build();

                // Create chat completion
                ChatMessage message = new ChatMessage("user", "Crie um questionário com 5 perguntas de múltipla escolha sobre " + tema.getNome() + ".\n" +
                        "Não use aspas simples (') nem crases (`), utilize apenas aspas duplas (\"). Responda exclusivamente em JSON no seguinte formato:\n" +
                        "\n" +
                        "{\n" +
                        "  \"quiz\": [\n" +
                        "    {\n" +
                        "      \"question\": \"Texto da pergunta\",\n" +
                        "      \"options\": [\n" +
                        "        {\"id\": \"A\", \"text\": \"Opção A\"},\n" +
                        "        {\"id\": \"B\", \"text\": \"Opção B\"},\n" +
                        "        {\"id\": \"C\", \"text\": \"Opção C\"},\n" +
                        "        {\"id\": \"D\", \"text\": \"Opção D\"}\n" +
                        "      ],\n" +
                        "      \"correctAnswer\": \"B\",\n" +
                        "      \"explanations\": {\n" +
                        "        \"A\": \"Por que está incorreta\",\n" +
                        "        \"B\": \"Por que está correta\",\n" +
                        "        \"C\": \"Por que está incorreta\",\n" +
                        "        \"D\": \"Por que está incorreta\"\n" +
                        "      }\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}\n");
                ChatCompletionRequest request = new ChatCompletionRequest("openai/gpt-oss-20b", List.of(message));
                request.setMaxTokens(16384);
                request.setTemperature(0.7);

                var response = client.chat().createCompletion(request);

                if (response.isSuccessful()) {

                    String content = response.getData().getChoices().get(0).getMessage().getContent();

                    ObjectMapper mapper = new ObjectMapper();
                    Quiz quiz = null;
                    try {
                        quiz = mapper.readValue(content, Quiz.class);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }

                    // Acessando dados
                    Question q = quiz.getQuiz().getFirst();
                    System.out.println("Pergunta: " + q.getQuestion());
                    System.out.println("Resposta correta: " + q.getCorrectAnswer());
                    System.out.println("Explicação da opção B: " + q.getExplanations().get("B"));
                    System.out.println(quiz.getQuiz().size());

                    DialogWindow<QuestionarioView> window = dialogWindows.view(this, QuestionarioView.class).build();
                    window.getView().setQuiz(quiz, tema);
                    window.getView().buildLayout();

                    window.open();
                }

            });

            vbox.add(layoutAuxiliar);
        }

    }

    @Subscribe("pesquisaTemas")
    public void onPesquisaTemasTypedValueChange(final SupportsTypedValue.TypedValueChangeEvent<TypedTextField<?>, ?> event) {
        vbox.removeAll();
        final User user = (User) currentAuthentication.getUser();
        List<Tema> temas = user.getTurma().getTemas();
        String valorDigitado = event.getValue() == null ? "" : event.getValue().toString();

        HorizontalLayout layoutAuxiliar;
        for (Tema tema: temas) {
            if (tema.getNome().contains(valorDigitado)) {
                layoutAuxiliar = uiComponents.create(HorizontalLayout.class);
                layoutAuxiliar.setPadding(true);
                layoutAuxiliar.setClassName("bg-primary rounded-l");
                layoutAuxiliar.setWidth("100%");

                H5 titulo = uiComponents.create(H5.class);
                titulo.setText(tema.getNome());
                titulo.getStyle().set("color","white");
                layoutAuxiliar.add(titulo);
                layoutAuxiliar.getStyle().set("cursor","pointer");

                vbox.add(layoutAuxiliar);
            }
        }
    }
}

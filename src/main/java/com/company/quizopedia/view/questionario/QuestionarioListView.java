package com.company.quizopedia.view.questionario;

import com.company.quizopedia.entity.Estado;
import com.company.quizopedia.entity.Questao;
import com.company.quizopedia.entity.Questionario;
import com.company.quizopedia.view.main.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;


@Route(value = "questionarios", layout = MainView.class)
@ViewController(id = "Questionario.list")
@ViewDescriptor(path = "questionario-list-view.xml")
@LookupComponent("questionariosDataGrid")
@DialogMode(width = "64em")
public class QuestionarioListView extends StandardListView<Questionario> {
    @Autowired
    private UiComponents uiComponents;

    @Supply(to = "questionariosDataGrid.pontuacao", subject = "renderer")
    private Renderer<Questionario> questionariosDataGridPontuacaoRenderer() {
        return new ComponentRenderer<>(questionario -> {
            // TODO: create suitable component
            Span span = uiComponents.create(Span.class);

            int pontos = 0;
            if (questionario.getEstado() == Estado.FINALIZADO) {
                for (Questao questao : questionario.getQuestoes()) {
                    if (questao.getOpcaoSelecionada().getCorreta())
                        pontos += questionario.getPontuacaoMaxima() / questionario.getQuestoes().size();
                }
            }
            span.setText(pontos + "");
            return span;
        }); 
    }
}
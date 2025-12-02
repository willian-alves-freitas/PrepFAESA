package com.company.quizopedia.view.user;

import com.company.quizopedia.entity.Estado;
import com.company.quizopedia.entity.Questao;
import com.company.quizopedia.entity.Questionario;
import com.company.quizopedia.entity.User;
import com.company.quizopedia.view.main.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "users", layout = MainView.class)
@ViewController(id = "User.list")
@ViewDescriptor(path = "user-list-view.xml")
@LookupComponent("usersDataGrid")
@DialogMode(width = "64em")
public class UserListView extends StandardListView<User> {
    @Autowired
    private UiComponents uiComponents;

    @Supply(to = "usersDataGrid.pontuacao", subject = "renderer")
    private Renderer<User> usersDataGridPontuacaoRenderer() {
        return new ComponentRenderer<>(user -> {
            // TODO: create suitable component
            Span span = uiComponents.create(Span.class);

            int pontos = 0;
            for (Questionario questionario: user.getQuestionarios()) {
                if (questionario.getEstado() == Estado.FINALIZADO) {
                    for (Questao questao : questionario.getQuestoes()) {
                        if (questao.getOpcaoSelecionada().getCorreta()) {
                            pontos += questionario.getPontuacaoMaxima() / questionario.getQuestoes().size();
                        }
                    }
                }
            }

            span.setText(pontos+"");
            return span;
        }); 
    }
}
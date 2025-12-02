package com.company.quizopedia.view.questionario;

import com.company.quizopedia.entity.Questionario;
import com.company.quizopedia.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "questionarios", layout = MainView.class)
@ViewController(id = "Questionario.list")
@ViewDescriptor(path = "questionario-list-view.xml")
@LookupComponent("questionariosDataGrid")
@DialogMode(width = "64em")
public class QuestionarioListView extends StandardListView<Questionario> {
}
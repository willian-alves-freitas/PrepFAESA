package com.company.quizopedia.view.turma;

import com.company.quizopedia.entity.Turma;
import com.company.quizopedia.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "turmas", layout = MainView.class)
@ViewController(id = "Turma.list")
@ViewDescriptor(path = "turma-list-view.xml")
@LookupComponent("turmasDataGrid")
@DialogMode(width = "64em")
public class TurmaListView extends StandardListView<Turma> {
}
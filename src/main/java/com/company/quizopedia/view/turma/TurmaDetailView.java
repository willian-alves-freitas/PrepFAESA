package com.company.quizopedia.view.turma;

import com.company.quizopedia.entity.Turma;
import com.company.quizopedia.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "turmas/:id", layout = MainView.class)
@ViewController(id = "Turma.detail")
@ViewDescriptor(path = "turma-detail-view.xml")
@EditedEntityContainer("turmaDc")
public class TurmaDetailView extends StandardDetailView<Turma> {
}
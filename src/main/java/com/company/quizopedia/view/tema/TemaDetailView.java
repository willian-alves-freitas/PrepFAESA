package com.company.quizopedia.view.tema;

import com.company.quizopedia.entity.Tema;
import com.company.quizopedia.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "temas/:id", layout = MainView.class)
@ViewController(id = "Tema.detail")
@ViewDescriptor(path = "tema-detail-view.xml")
@EditedEntityContainer("temaDc")
public class TemaDetailView extends StandardDetailView<Tema> {
}
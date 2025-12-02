package com.company.quizopedia.view.tema;

import com.company.quizopedia.entity.Tema;
import com.company.quizopedia.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "temas", layout = MainView.class)
@ViewController(id = "Tema.list")
@ViewDescriptor(path = "tema-list-view.xml")
@LookupComponent("temasDataGrid")
@DialogMode(width = "64em")
public class TemaListView extends StandardListView<Tema> {
}
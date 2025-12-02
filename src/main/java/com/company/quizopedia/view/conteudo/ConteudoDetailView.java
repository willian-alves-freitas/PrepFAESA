package com.company.quizopedia.view.conteudo;

import com.company.quizopedia.entity.Conteudo;
import com.company.quizopedia.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "conteudoes/:id", layout = MainView.class)
@ViewController(id = "Conteudo.detail")
@ViewDescriptor(path = "conteudo-detail-view.xml")
@EditedEntityContainer("conteudoDc")
public class ConteudoDetailView extends StandardDetailView<Conteudo> {
}
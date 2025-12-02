package com.company.quizopedia.view.main;

import com.company.quizopedia.entity.Tema;
import com.company.quizopedia.entity.User;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.AbstractStreamResource;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.app.main.StandardMainView;
import io.jmix.flowui.component.SupportsTypedValue;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.view.Subscribe;
import io.jmix.flowui.view.ViewComponent;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;

import java.io.ByteArrayInputStream;
import java.util.LinkedList;
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

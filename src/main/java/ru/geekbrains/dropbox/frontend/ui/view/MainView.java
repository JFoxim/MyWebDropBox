package ru.geekbrains.dropbox.frontend.ui.view;

import com.vaadin.navigator.View;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class MainView extends VerticalLayout implements View {
    public MainView() {
        Label label = new Label("MainView.class");
        addComponent(label);
    }
}

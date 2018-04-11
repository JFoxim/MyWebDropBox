package ru.geekbrains.dropbox.frontend.ui.view;

import com.vaadin.navigator.View;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class LoginView extends VerticalLayout implements View {
    public LoginView() {
        Label label = new Label("LoginView.class");
        addComponent(label);
    }
}

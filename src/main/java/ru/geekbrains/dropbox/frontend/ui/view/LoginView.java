package ru.geekbrains.dropbox.frontend.ui.view;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.geekbrains.dropbox.frontend.service.MyAuthenticationManager;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;


@Component
@UIScope
@SpringView(name = "LoginView")
public class LoginView extends VerticalLayout implements View {

    private Panel pnlAutheticate;

    public LoginView() {

        HorizontalLayout authLayout = new HorizontalLayout();
        TextField loginTextField = new TextField();
        loginTextField.setPlaceholder("Login");
        PasswordField passwordField = new PasswordField();
        passwordField.setPlaceholder("Password");
        Button btnLogin = new Button("Войти");

        btnLogin.addClickListener(clickEvent -> {


                try {
                    AuthenticationManager am = new MyAuthenticationManager();
                    Authentication request = new UsernamePasswordAuthenticationToken(loginTextField.getValue(),
                            passwordField.getValue());
                    Authentication result = am.authenticate(request);
                    SecurityContextHolder.getContext().setAuthentication(result);
                    loginTextField.setVisible(false);
                    passwordField.setVisible(false);
                    btnLogin.setVisible(false);
                    Notification.show("Вы вошли как " + loginTextField.getValue());
                    pnlAutheticate.setCaption("Вы вошли как " + loginTextField.getValue());
                    getUI().getNavigator().navigateTo("");
                } catch(AuthenticationException e) {
                    pnlAutheticate.setCaption("Неверный логин или пароль");
                }
        });
        authLayout.addComponents(loginTextField, passwordField, btnLogin);

        pnlAutheticate = new Panel("Введите логин и пароль");
        pnlAutheticate.setContent(authLayout);
        pnlAutheticate.setSizeUndefined();

        addComponent(pnlAutheticate);


    }
}

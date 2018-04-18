package ru.geekbrains.dropbox.frontend.ui.view;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.geekbrains.dropbox.frontend.service.FilesService;


@SpringView(name = "login")
public class LoginView extends VerticalLayout implements View {

    @Autowired
    private FilesService filesService;

    public LoginView() {
        TextField txtUser = new TextField();
        txtUser.setPlaceholder("Пользователь");
        PasswordField txtPassword = new PasswordField();
        txtPassword.setPlaceholder("Пароль");
        Button btnLogin = new Button("Вход", clickEvent -> {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    txtUser.getValue(),
                    txtPassword.getValue()
            );
            SecurityContextHolder.getContext().setAuthentication(token);
            getUI().getNavigator().navigateTo("");
        });
        addComponents(txtUser, txtPassword, btnLogin);
    }
}

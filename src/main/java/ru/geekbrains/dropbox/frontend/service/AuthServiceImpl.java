package ru.geekbrains.dropbox.frontend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.dropbox.backend.service.AuthenticationService;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    AuthenticationService authenticationService;

    @Override
    public boolean login(String user, String pass) {
        return authenticationService.login(user, pass);
    }

    @Override
    public boolean logout() {
        return authenticationService.logout();
    }
}

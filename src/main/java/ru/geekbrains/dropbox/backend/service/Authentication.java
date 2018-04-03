package ru.geekbrains.dropbox.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.dropbox.backend.dao.AutheticationDAO;

@Service
public class Authentication implements AuthenticationService {
    @Autowired
    AutheticationDAO autheticationDAO;

    @Override
    public boolean login(String user, String pass) {
        return autheticationDAO.login(user, pass);
    }

    @Override
    public boolean logout() {
        return true;
    }
}

package ru.geekbrains.dropbox.backend.service;

public interface AuthenticationService {
    boolean login(String user, String pass);
    boolean logout();
}

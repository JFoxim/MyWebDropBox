package ru.geekbrains.dropbox.frontend.service;

public interface AuthService {
    boolean login(String user, String pass);
    boolean logout();
}

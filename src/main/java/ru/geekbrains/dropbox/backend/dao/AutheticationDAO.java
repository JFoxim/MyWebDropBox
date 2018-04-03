package ru.geekbrains.dropbox.backend.dao;

public interface AutheticationDAO {
    boolean login(String user, String pass);
}

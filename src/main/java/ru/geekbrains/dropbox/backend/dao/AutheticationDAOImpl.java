package ru.geekbrains.dropbox.backend.dao;

import org.springframework.stereotype.Repository;


import java.util.HashMap;
import java.util.Map;

@Repository
public class AutheticationDAOImpl implements AutheticationDAO {
    public static final Map<String, String> users = new HashMap<String, String>() {{
       put("user1", "user1");
       put("user2", "user2");
       put("user3", "user3");
    }};

    @Override
    public boolean login(String user, String pass) {
        return users.get(user) != null && users.get(user).equals(pass);
    }
}

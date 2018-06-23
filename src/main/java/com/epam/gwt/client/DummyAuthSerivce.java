package com.epam.gwt.client;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DummyAuthSerivce implements AutenthicationService {

    private Map<String, String> users = new HashMap<String, String>() {
        {
            put("admin", "132");
            put("xD", "123");
            put("elefus", "123");
        }
    };

    @Override
    public boolean login(String login, String password) {
        return Optional.ofNullable(users.get(login))
                       .filter(pass -> pass.equals(password))
                       .isPresent();
    }
}

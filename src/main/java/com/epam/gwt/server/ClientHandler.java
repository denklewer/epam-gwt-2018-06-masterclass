package com.epam.gwt.server;

import java.util.ArrayList;
import java.util.List;

public class ClientHandler {

    private List<String> responds = new ArrayList<>();

    public synchronized List<String> getResponds() {
        List<String> result = this.responds;
        responds = new ArrayList<>();
        return result;
    }

    public synchronized void addResponse(String response) {
        responds.add(response);
    }
}

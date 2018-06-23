package com.epam.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsonUtils;
import com.google.gwt.http.client.*;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;

public class Chat implements EntryPoint {

    private final FlexTable table = new FlexTable();

    @Override
    public void onModuleLoad() {
        table.setText(0, 0, "ID");
        table.setText(0, 1, "Login");
        table.setText(0, 2, "AvatarURL");
        table.setText(0, 3, "Link");

        RootPanel.get("container").add(table);

        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, "https://api.github.com/users");
        builder.setHeader("Authorization", "token 70b729a6fa2df226fbf84f2bc55e02a822f28f7e");
        builder.setHeader("Content-Type", "application/json; charset=utf-8");

        try {
            builder.sendRequest(null, new RequestCallback() {
                @Override
                public void onResponseReceived(Request request, Response response) {
                    if (response.getStatusCode() == 200) {
                        JsArray arr = JsonUtils.safeEval(response.getText()).cast();
                        for (int i = 0; i < arr.length(); ++i) {
                            User user = arr.get(i).cast();
                            table.setText(i + 1, 0, String.valueOf(user.getId()));
                            table.setText(i + 1, 1, user.getLogin());
                            table.setText(i + 1, 2, user.getAvatarUrl());

                            Anchor anchor = new Anchor("->", "http://www.github.com/" + user.getLogin());
                            table.setWidget(i + 1, 3, anchor);
                        }
                    }
                }

                @Override
                public void onError(Request request, Throwable exception) {
                    System.out.println("Error");
                }
            });
        } catch (RequestException e) {
            e.printStackTrace();
        }
    }
}

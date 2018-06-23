package com.epam.gwt.client;

import com.epam.gwt.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.media.client.Audio;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import java.util.List;
import java.util.StringJoiner;

public class Chat implements EntryPoint {

    private final AvatarServiceAsync avatarService = GWT.create(AvatarService.class);
    private final AutenthicationService authService = new DummyAuthSerivce();
    private final ChatServiceAsync chatService = GWT.create(ChatService.class);

    private final Audio notification = Audio.createIfSupported();
    private final LoginForm loginForm = new LoginForm(authService::login, this::userLoggedIn);
    private final TextArea chatArea = new TextArea();
    private final TextBox messageField = new TextBox();

    private final StringJoiner messages = new StringJoiner("\n");
    private String userName;

    @Override
    public void onModuleLoad() {
        notification.setSrc("/sounds/notification.mp3");

        chatArea.setWidth("100%");
        RootPanel.get("chatArea").add(chatArea);

        messageField.setWidth("100%");
        RootPanel.get("messageField").add(messageField);

        Button sendButton = Button.wrap(Document.get().getElementById("sendButton"));
        sendButton.addClickHandler(event -> sendMessage());
        messageField.addKeyPressHandler(event -> {
            if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
                sendMessage();
            }
        });

        Image defaultAvatar = new Image("/images/default-avatar.png");
        defaultAvatar.setHeight("100%");
        defaultAvatar.setWidth("100%");
        RootPanel.get("avatarDiv").add(defaultAvatar);

        Button loadAvatar = Button.wrap(Document.get().getElementById("loadAvatarButton"));
        loadAvatar.addClickHandler(event -> avatarService.getAvatarURL(userName, new AsyncCallback<String>() {

            @Override
            public void onSuccess(String url) {
                Image userAvatar = new Image(url);
                userAvatar.setWidth("100%");
                userAvatar.setHeight("100%");
                RootPanel avatarDiv = RootPanel.get("avatarDiv");
                avatarDiv.remove(0);
                avatarDiv.add(userAvatar);
            }

            @Override
            public void onFailure(Throwable caught) {

            }
        }));

        loginForm.center();
    }

    private void sendMessage() {
        String message = messageField.getText();
        if (FieldVerifier.isValidMessage(message)) {
            chatService.sendMessage(userName, message, new AsyncCallback<List<String>>() {

                private final AsyncCallback<List<String>> original = getMessagesCallback();
                @Override
                public void onFailure(Throwable caught) {
                    original.onFailure(caught);
                }

                @Override
                public void onSuccess(List<String> result) {
                    original.onSuccess(result);
                    messageField.setText("");
                }
            });
        }
    }

    private AsyncCallback<List<String>> getMessagesCallback() {
        return new AsyncCallback<List<String>>() {
            @Override
            public void onFailure(Throwable caught) {
                // TODO
            }

            @Override
            public void onSuccess(List<String> result) {
                if (!result.isEmpty()) {
                    result.forEach(messages::add);
                    chatArea.setText(messages.toString());
                    notification.play();
                }
            }
        };
    }

    private void userLoggedIn(String userName) {
        this.userName = userName;
        Document.get().getElementById("userNameLabel").<Element>cast().setInnerText(userName);
        new Timer() {
            @Override
            public void run() {
                chatService.getMessages(userName, getMessagesCallback());
            }
        }.scheduleRepeating(500);
    }
}

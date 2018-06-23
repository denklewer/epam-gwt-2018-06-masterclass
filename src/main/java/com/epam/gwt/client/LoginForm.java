package com.epam.gwt.client;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.ui.*;

import java.util.function.BiPredicate;
import java.util.function.Consumer;

public class LoginForm extends DialogBox {

    public LoginForm(BiPredicate<String, String> checkLogin, Consumer<String> loginConsumer) {
        VerticalPanel dialogVPanel = new VerticalPanel();
        dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
        dialogVPanel.addStyleName("dialogVPanel");

        TextBox loginField = new TextBox();
        loginField.setStyleName("loginFields");
        dialogVPanel.add(loginField);

        PasswordTextBox passwordField = new PasswordTextBox();
        passwordField.setStyleName("loginFields");
        dialogVPanel.add(passwordField);

        Button loginButton = new Button("Login");
        loginButton.getElement().setId("loginButton");
        loginButton.setStyleName("loginButton");
        dialogVPanel.add(loginButton);

        Label loginFailedLabel = new Label("Incorrect login or password!");
        loginFailedLabel.setVisible(false);
        dialogVPanel.add(loginFailedLabel);
        setWidget(dialogVPanel);

        Runnable handleLoginAttempt = () -> {
            boolean isLoginSucceed = checkLogin.test(loginField.getText(), passwordField.getText());
            loginFailedLabel.setVisible(!isLoginSucceed);

            if (isLoginSucceed) {
                loginConsumer.accept(loginField.getText());
                hide();
            }
        };

        loginButton.addClickHandler(event -> handleLoginAttempt.run());
        passwordField.addKeyPressHandler(event -> {
            if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
                handleLoginAttempt.run();
            }
        });
    }
}

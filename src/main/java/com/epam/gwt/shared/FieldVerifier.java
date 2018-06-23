package com.epam.gwt.shared;

public class FieldVerifier {

  public static boolean isValidMessage(String message) {
    return message != null && !message.isEmpty();
  }
}

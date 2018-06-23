package com.epam.gwt.client;

import com.epam.gwt.shared.FieldVerifier;
import com.google.gwt.junit.client.GWTTestCase;

/**
 * GWT JUnit tests must extend GWTTestCase.
 */
public class ChatTest extends GWTTestCase {

  /**
   * Must refer to a valid module that sources this class.
   */
  public String getModuleName() {
    return "com.epam.gwt.ChatJUnit";
  }

  /**
   * Tests the FieldVerifier.
   */
  public void testFieldVerifier() {
    assertFalse(FieldVerifier.isValidMessage(null));
    assertFalse(FieldVerifier.isValidMessage(""));
    assertFalse(FieldVerifier.isValidMessage("a"));
    assertFalse(FieldVerifier.isValidMessage("ab"));
    assertFalse(FieldVerifier.isValidMessage("abc"));
    assertTrue(FieldVerifier.isValidMessage("abcd"));
  }

  /**
   * This test will send a request to the server using the greetServer method in
   * ChatService and verify the response.
   */
  public void testGreetingService() {
  }


}

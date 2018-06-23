package com.epam.gwt;

import com.epam.gwt.client.ChatTest;
import com.google.gwt.junit.tools.GWTTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;

public class ChatSuite extends GWTTestSuite {
  public static Test suite() {
    TestSuite suite = new TestSuite("Tests for Chat");
    suite.addTestSuite(ChatTest.class);
    return suite;
  }
}

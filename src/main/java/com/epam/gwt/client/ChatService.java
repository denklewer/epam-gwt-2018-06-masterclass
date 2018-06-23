package com.epam.gwt.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

@RemoteServiceRelativePath("messages")
public interface ChatService extends RemoteService {

  List<String> sendMessage(String userName, String message) throws IllegalArgumentException;

  List<String> getMessages(String userName);
}

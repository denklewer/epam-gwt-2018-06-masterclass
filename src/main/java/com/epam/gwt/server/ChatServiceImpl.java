package com.epam.gwt.server;

import com.epam.gwt.client.ChatService;
import com.epam.gwt.shared.FieldVerifier;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChatServiceImpl extends RemoteServiceServlet implements ChatService {

  private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH.mm.ss");
  private Map<String, ClientHandler> clients = new ConcurrentHashMap<>();

  @Override
  public List<String> sendMessage(String userName, String message) throws IllegalArgumentException {
    ClientHandler clientHandler = clients.computeIfAbsent(userName, user -> new ClientHandler());

    String formattedMessage = String.format("%s - %s: %s", timeFormatter.format(ZonedDateTime.now()), userName, message);
    clients.values().forEach(client -> client.addResponse(formattedMessage));
    return clientHandler.getResponds();
  }

  @Override
  public List<String> getMessages(String userName) {
    return clients.computeIfAbsent(userName, user -> new ClientHandler())
                  .getResponds();
  }
}
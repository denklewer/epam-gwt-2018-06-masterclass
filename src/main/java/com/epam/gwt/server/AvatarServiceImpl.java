package com.epam.gwt.server;

import com.epam.gwt.client.AvatarService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.InputStreamReader;

public class AvatarServiceImpl extends RemoteServiceServlet implements AvatarService {

    @Override
    public String getAvatarURL(String userName) {
        HttpUriRequest request = RequestBuilder.get()
                                               .setUri("https://api.github.com/users/" + userName)
                                               .setHeader("Authorization", "token 906737a87f093628d3f6cc6525bb991b77e5b297")
                                               .build();

        try (CloseableHttpClient client = HttpClientBuilder.create().build();
             CloseableHttpResponse response = client.execute(request)) {
            if (response.getStatusLine().getStatusCode() == 200) {
                JsonObject object = new Gson().fromJson(new InputStreamReader(response.getEntity().getContent()), JsonObject.class);
                return object.get("avatar_url").getAsString();
            }

        } catch (IOException e) {
            // TODO
            e.printStackTrace();
        }
        return null;
    }
}

package com.epam.gwt.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("avatars")
public interface AvatarService extends RemoteService {

    String getAvatarURL(String userName);
}

package com.epam.gwt.client;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Overlay type
 *
 *
 * JNI = Java Native Interface
 * JSNI = JavaScript Native Interface
 */
public class User extends JavaScriptObject {

    protected User() {}

    public final native int getId() /*-{ return this.id }-*/;
    public final native String getLogin()  /*-{ return this.login }-*/;
    public final native String getAvatarUrl()  /*-{ return this.avatar_url }-*/;
}

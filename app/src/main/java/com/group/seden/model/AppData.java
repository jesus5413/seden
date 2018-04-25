package com.group.seden.model;

import android.app.Application;
import android.content.pm.ApplicationInfo;

/**
 * Used to store application wide information accessible to all activities and model classes
 */
public class AppData extends Application {

    public static final UserSession session = UserSession.getInstance();

    public static final MessageList messageInbox = MessageList.getInstance();

}

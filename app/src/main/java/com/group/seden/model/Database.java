package com.group.seden.model;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

/**
 * @author Isaac Buitrago
 *
 * Singleton class that handles the connection to the Firebase database.
 * It is used to handle the sending of Message to a device and the
 * receiving of a message from another device.
 */
public class Database {

    private MyFirebaseMessagingService messgingService; // used to receive a message

    private MyFirebaseInstanceIdService instanceId;     // used to generate the user's Firebase instance id

    private static Database instance;                   // reference to Database

    // There should only be one instance to the Database in the app
    private Database(){}

    /**
     *
     * @return Single Database instance
     */
    public static synchronized Database getInstance()
    {
        if(instance == null)
        {
          instance = new Database();
        }
        return(instance);
    }

    /**
     * Sends a message upstream to the Firebase server
     * @param message for the recipient
     */
    public void sendMessage(Message message)
    {
        FirebaseMessaging.getInstance().send(
                new RemoteMessage.Builder(SENDER_ID)
                        .setMessageId(id)
                        .addData(message.getSenderid(), message.getMessage())
                        .build());
    }

}

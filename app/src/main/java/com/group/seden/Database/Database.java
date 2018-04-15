package com.group.seden.Database;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;
import com.group.seden.model.Message;
import com.group.seden.model.UserSession;

import java.util.HashMap;

/**
 * Class to store data in the database.
 * It is also used to send Messages to a device and
 * receive messages from another device.
 * @author JesusNieto
 * @author Isaac Buitrago
 */
public class Database {

    private static DatabaseReference mDatabase;
    //private static DatabaseReference userChild;

    /**
     * Stores user info when they create an account. They will have a unique ID and
     * token which will be stored in the database as well.
     * @param email
     * @param password
     * @param userName
     * example: database/users/user
     */
    public static void storeUserInDBChild(String email, String password, String userName){
        mDatabase = FirebaseDatabase.getInstance().getReference(); // this points to the main database
        HashMap<String, String> childInfo = new HashMap<String, String>();  // creating hashmap to store data to commit to the users node
        childInfo.put("UserName", userName);
        childInfo.put("Email", email);
        childInfo.put("Password", password);
        childInfo.put("UniqueID", mDatabase.push().getKey());
        mDatabase.child("users").push().setValue(childInfo); // this points to the database, then to the users node and then stores a new node in the users nodes with a unique ID.

    }

    /**
     * Used to update the registration token in the Database once the token is regenerated.
     */
    public static void updateUserTokenInDB(String token)
    {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        //mDatabase.child("users")
    }

    /**
     * Sends a message upstream to the Firebase server
     * @param message for the recipient
     */
    public void sendMessage(Message message)
    {
//        FirebaseMessaging.getInstance().send(
//                new RemoteMessage.Builder(SENDER_ID)
//                        .setMessageId(id)
//                        .addData(message.getSenderid(), message.getMessage())
//                        .build());
    }




}

package com.group.seden;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseException;
import com.group.seden.Database.Database;
import com.group.seden.model.Message;


import org.junit.Before;
import org.junit.Test;

public class DatabaseTest
{
    static Message message =  new Message();

    Context appContext = InstrumentationRegistry.getTargetContext();

    @Before
    public void setUp()
    {

        // Initialize FirebaseApp
        FirebaseApp.initializeApp(appContext);

        message.setMsgText("Test123");
        message.setSenderId("1235454");
        message.setRecipientId("12334566");
        message.setDeleteTime(3);
    }

    @Test
    public void sendMessage()
    {

        try {
            Database.sendMessage(message);
        } catch(DatabaseException e){

            System.out.println(e.getMessage());
        }

        System.out.println("Your message was successfully sent to the recipient!");
    }

}

package com.group.seden.Database;

import com.google.firebase.database.DatabaseException;
import com.group.seden.model.Message;
import android.os.Process;
import android.test.ServiceTestCase;
import android.test.mock.MockApplication;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DatabaseTest
{
    static Message message =  new Message();

    @BeforeClass
    public static void init()
    {
        message.setMessage("Test123");
        message.setSenderid("1235454");
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

        System.out.println("Your message was successfully sent to the recipient !");
    }

}

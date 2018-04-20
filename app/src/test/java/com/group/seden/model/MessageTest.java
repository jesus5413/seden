package com.group.seden.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by robbie neuhaus on 4/14/2018.
 */
public class MessageTest {
    @Test
    public void delete() throws Exception {
        int seconds = 5;
        Message message = new Message("test");
        message.setDeleteTime(seconds);
        System.out.println("Message: " + message.getMessage());
        message.delete();
        System.out.println("Deleting Message");
        Thread.sleep((seconds + 1) * 1000);
        if (message.getMessage() != null)
            System.out.println("Message failed to delete: " + message.getMessage());
        else
            System.out.println("Message Deleted: " + message.getMessage());
        assert(message.getMessage() == null);
    }

}
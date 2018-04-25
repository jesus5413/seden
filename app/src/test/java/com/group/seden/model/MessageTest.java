package com.group.seden.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by robbie neuhaus on 4/14/2018.
 */
public class MessageTest {
    @Test
    public void TimeOutDelete() throws Exception {
        int seconds = 2;
        Message message1 = new Message("test");
        message1.setDeleteTime(seconds);
        Message message2 = new Message("another test");
        message2.setDeleteTime(seconds);
        Message message3 = new Message("a third test");
        message3.setDeleteTime(seconds);
        MessageList messages = new MessageList();
        messages.addMessage(message1);
        messages.addMessage(message2);
        messages.addMessage(message3);
        int i = 0;
        for (Message msg : messages) {
            i++;
            System.out.println("Message " + i + ": " + msg.getMsgText());
        }
        messages.TimeOutDelete(message1);
        Thread.sleep(1);
        messages.TimeOutDelete(message3);
        System.out.println("Deleting Message");
        Thread.sleep((seconds + 2) * 1000);
        i = 0;
        for (Message msg : messages) {
            i++;
            System.out.println("Message " + i + ": " + msg.getMsgText());
        }
    }

}
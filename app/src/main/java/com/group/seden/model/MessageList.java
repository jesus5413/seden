package com.group.seden.model;

/**
 * Created by robbie neuhaus on 4/14/2018.
 */

import java.util.ArrayList;
import java.util.Iterator;


public class MessageList implements Iterable<Message>
{
    private ArrayList<Message> messages= new ArrayList<>();
    private static MessageList instance;

    private MessageList(){};

    public static MessageList getInstance()
    {
        if(instance == null)
            instance =  new MessageList();

        return (instance);
    }

    public ArrayList<Message> getMessageList(){
        return this.messages;
    }

    public void addMessage(Message message){
        this.messages.add(message);
    }

    //public void TimeOutDelete(Message message) {
    //    new TimeOut(message, message.getDeleteTime(), this);
    //}

    public void deleteMessage(Message message) {
        this.getMessageList().remove(message);
    }

    @Override
    public Iterator<Message> iterator() {
        return messages.iterator();
    }
}

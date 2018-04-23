package com.group.seden.model;

import java.util.ArrayList;
import java.util.Iterator;


public class MessageList implements Iterable<Message> {
    private ArrayList<Message> messages= new ArrayList<>();

    public ArrayList<Message> getMessageList(){
        return this.messages;
    }

    public void addMessage(Message message){
        this.messages.add(message);
    }

    public void TimeOutDelete(Message message) {
        new TimeOut(message, message.getDeleteTime(), this);
    }

    public void deleteMessage(Message message) {
        this.getMessageList().remove(message);
    }

    @Override
    public Iterator<Message> iterator() {
        return messages.iterator();
    }
}

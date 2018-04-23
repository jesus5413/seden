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

    public void TimeOutDeleteMessage(Message message) {
        message.TimeOutDelete(this);
    }

    public void deleteMessage(Message message) {
        message.delete();
        this.removeMessage();
    }
    
    public void removeMessage() {
        for (Message msg : this.messages){
            if (msg.getMsgText() == null) {
                this.messages.remove(msg);
                break;
            }
        }
    }

    @Override
    public Iterator<Message> iterator() {
        return messages.iterator();
    }
}

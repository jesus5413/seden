/**
 * @author Isaac Buitrago
 *
 * This class stores the users message. A message can be a text string, image, file, or video.
 */
 package com.group.seden.model;


public class Message{

   private String message;  // the users message

    private String senderid; // the username of the sender

    private int timer = 5*60;


    public String getSenderid()
    {
        return senderid;
    }

    public void setSenderid(String senderid)
    {
        this.senderid = senderid;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public void setDeleteTime(int timer){
        this.timer = timer;
    }

    public int getDeleteTime(int timer){
        return this.timer;
    }

    /**
     *  Deletes a message
     */
    public Boolean delete()
    {

        new TimeOut(this, timer);
        if (this.getMessage() == null)
            return true;
        else
            return false;
    }

    /**
     * Sends the message to Firebase Cloud Messaging
     */
    public void send()
    {

    }

}
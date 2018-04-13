/**
 * @author Isaac Buitrago
 *
 * This class stores the users message. A message can be a text string, image, file, or video.
 */
 package com.group.seden.model;

public class Message{

   private String message;  // the users message

    private String senderid; // the username of the sender


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

    /**
     *  Deletes a message
     */
    public void delete()
    {
        this.message = null;
    }

    /**
     * Sends the message to Firebase Cloud Messaging
     */
    public void send()
    {

    }
}
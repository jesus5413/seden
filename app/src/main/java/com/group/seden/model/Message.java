/**
 * @author Isaac Buitrago
 *
 * This class stores the users message. A message can be a text string, image, file, or video.
 */
 package com.group.seden.model;


public class Message{

    private String message;     // the users message

    private String senderId;    // uniqueId of the sender

    private String recipientId; // uniqueId of the recipient

    private int timer = 5;


    /**
     * getters and setters for message
     * @return
     */
    public String getSenderid()
    {
        return senderId;
    }

    public void setSenderid(String senderid)
    {
        this.senderId = senderid;
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

    public String getRecipientId()
    {
        return recipientId;
    }

    public void setRecipientId(String recipientId)
    {
        this.recipientId = recipientId;
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

}
/**
 * @author Isaac Buitrago
 *
 * This class is used to store a message.
 * A message can consist of text, an image, a file, or a video.
 * A message can also be deleted.
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


}
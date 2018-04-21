 /**
 * @author Isaac Buitrago
 *
 * This class stores the users message. A message can be a text string, image, file, or video.
 */
 package com.group.seden.model;

import static com.group.seden.model.Encryption.*;

public class Message{

    private String msgText;     // the users message

    private String senderId;    // the username of the sender

    private String recipientId; // the id of the receiver of the message

    private int timer = 5;

    /**
     * Constructor that accepts an initial Message
     * @param msgText message to be sent
     */
    public Message(String msgText)
    {
        this.msgText = msgText;
    }

    /**
     * Default constructor
     */
    public Message(){}


    public String getMsgText()
    {
        return msgText;
    }

    public void setMsgText(String msgText)
    {
        this.msgText = msgText;
    }

    public String getSenderId()
    {
        return senderId;
    }

    public void setSenderId(String senderid)
    {
        this.senderId = senderid;
    }

    public void setDeleteTime(int timer)
    {
        this.timer = timer;
    }

    public int getDeleteTime()
    {
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
        return this.getMsgText() == null;
    }


}
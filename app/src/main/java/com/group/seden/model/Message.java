/**
 * @author Isaac Buitrago
 *
 * This class stores the users message. A message can be a text string, image, file, or video.
 */
package com.group.seden.model;

public class Message{

    private String msgText;  // the users message

    private String senderID; // the username of the sender

    private String recipientID;

    private boolean isEncrypted = false;

    private int timer = 5*60;
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



    public Message(String senderID, String recipientID, String message){
        this.msgText = message;
        this.senderID = senderID;
        this.recipientID = recipientID;
    }

    public Message(String senderID, String recipientID, String message, boolean isEncrypted){
        this.msgText = message;
        this.senderID = senderID;
        this.recipientID = recipientID;
        this.isEncrypted = isEncrypted;
    }

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public String getRecipientID() {
        return recipientID;
    }

    public void setRecipientID(String senderID) {
        this.recipientID = senderID;
    }

    public boolean getIsEncrypted() {
        return isEncrypted;
    }

    public void setIsEncrypted(boolean isEncrypted) {
        this.isEncrypted = isEncrypted;
    }

    public String getMsgText() {
        return msgText;
    }

    public void setMsgText(String text) {
        this.msgText = text;
    }

    public void setDeleteTime(int timer)
    {
        this.timer = timer;
    }

    public int getDeleteTime()
    {
        return this.timer;
    }


    //Deletes a message after a certain amount of time
    public void TimeOutDelete(MessageList messages) {
        new TimeOut(this, timer, messages);
    }

    //Deletes a message immediately
    public void delete() {
        this.setMsgText(null);
    }

}
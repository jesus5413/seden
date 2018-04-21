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


    public Message() {
    }

    public Message(String text) {
        this.msgText = text;
    }

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

    public void setDeleteTime(int timer){
        this.timer = timer;
    }

    public int getDeleteTime(){
        return this.timer;
    }

    /**
     *  Deletes a message
     */
    public Boolean delete() {
        new TimeOut(this, timer);
        return this.getMsgText() == null;
    }

    /**
     * Sends the message to Firebase Cloud Messaging
     */
    public void send() {

    }

}
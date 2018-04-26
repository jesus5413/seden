package com.group.seden.model;

public class RecieveMessage {
    private String DeleTime;
    private String Encrypted;
    private String Message;
    private String RecipientID;
    private String SenderID;

    public RecieveMessage(){

    }


    public String getDeleTime() {
        return this.DeleTime;
    }

    public void setDeleTime(String deleTime) {
        this.DeleTime = deleTime;
    }

    public String getEncrypted() {
        return this.Encrypted;
    }

    public void setEncrypted(String encrypted) {
        this.Encrypted = encrypted;
    }

    public String getMessage() {
        return this.Message;
    }

    public void setMessage(String message) {
        this.Message = message;
    }

    public String getRecipientID() {
        return this.RecipientID;
    }

    public void setRecipientID(String recipientID) {
        this.RecipientID = recipientID;
    }

    public String getSenderID() {
        return this.SenderID;
    }

    public void setSenderID(String senderID) {
        this.SenderID = senderID;
    }
}

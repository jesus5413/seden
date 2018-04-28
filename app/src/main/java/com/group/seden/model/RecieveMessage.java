package com.group.seden.model;

public class RecieveMessage {
    private String DeleTime;
    private String Encrypted;
    private String Message;
    private String RecipientId;
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

    public String getRecipientId() {
        return this.RecipientId;
    }

    public void setRecipientId(String recipientId) {
        this.RecipientId = recipientId;
    }

    public String getSenderID() {
        return this.SenderID;
    }

    public void setSenderID(String senderID) {
        this.SenderID = senderID;
    }
}

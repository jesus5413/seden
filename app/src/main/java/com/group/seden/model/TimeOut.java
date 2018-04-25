package com.group.seden.model;

/**
 * Created by robbie neuhaus on 4/14/2018.
 */

import java.util.Timer;
import java.util.TimerTask;

public class TimeOut {
    private Timer timer;
    private Message message;
    private MessageList messages;

    public TimeOut(Message message, int seconds, MessageList messages) {

        timer = new Timer();
        this.message = message;
        this.messages = messages;
        timer.schedule(new deleteTask(message, messages), seconds*1000);
    }

    class deleteTask extends TimerTask {

        Message message;
        MessageList messages;

        deleteTask(Message message, MessageList messages){
            this.message = message;
            this.messages = messages;
        }

        public void run() {
            this.message.setMsgText(null);
            for (Message msg : this.messages){
                if (msg.getMsgText() == null) {
                    this.messages.getMessageList().remove(msg);
                    break;
                }
            }
            timer.cancel(); //Terminate the timer thread
        }
    }
}

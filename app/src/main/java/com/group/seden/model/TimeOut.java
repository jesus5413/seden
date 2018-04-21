package com.group.seden.model;

/**
 * Created by robbie neuhaus on 4/14/2018.
 */

import java.util.Timer;
import java.util.TimerTask;

public class TimeOut {
    private Timer timer;
    private Message message;
    public TimeOut(Message message, int seconds) {

        timer = new Timer();
        this.message = message;
        timer.schedule(new deleteTask(message), seconds*60*1000);//TODO: Remove *60 for demo
    }

    class deleteTask extends TimerTask {

        Message message;

        deleteTask(Message message){
            this.message = message;
        }

        public void run() {
            this.message.setMsgText(null);
            timer.cancel(); //Terminate the timer thread
        }
    }
}

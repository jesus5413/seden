package com.group.seden.Database;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.group.seden.model.Message;

public class Receiving extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if(!remoteMessage.getData().isEmpty()){
            Message msg = new Message();
            msg.setMessage(remoteMessage.getData().get("m"));
            msg.setSenderid(remoteMessage.getFrom());
            msg.setDeleteTime(300);
            // this deleteTime is hard coded right now
        }
    }
}// END MODEL CLASS Receiving

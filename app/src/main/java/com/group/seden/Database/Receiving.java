package com.group.seden.Database;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.group.seden.model.Message;
import com.group.seden.model.UserSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Receiving {

    private static DatabaseReference myRef;
    private Message message;
    static private UserSession session = UserSession.getInstance();

    public static ArrayList<Message> getMessages() {
        //final Message msg = new Message();
        myRef = FirebaseDatabase.getInstance().getReference().child("messages");
        final ArrayList<Message> messagesList = new ArrayList<>();


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated
                Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                //HashMap<String, String> hm = dataSnapshot.getValue(HashMap.class);

            Message msg = new Message();
            for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                msg.setRecipientID((String) messageSnapshot.child("RecipientId").getValue());
                msg.setMsgText((String) messageSnapshot.child("Message").getValue());
            }

            System.out.println(msg);

            if (session.getUserName() != null) {
                if (session.getUserName().equals(msg.getRecipientID())) {
                    messagesList.add(msg);
                }
            }

                /*
                msg.setIsEncrypted(true);
                msg.setMsgText(valueRegex);
                //TODO: regex value for 28 character string and setSenderID to that
                //TODO: regex value for time in seconds
                msg.setSenderID(dataSnapshot.getKey());
                msg.setDeleteTime(timeRegex);
                msg.setRecipientID(receiveRegex);
                */
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                System.out.printf("Failed to read the DB value\n");
            }
        });

        return messagesList;
    }// END getMessage() method
}// END MODEL CLASS Receiving

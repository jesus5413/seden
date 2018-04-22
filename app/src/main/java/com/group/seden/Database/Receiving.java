package com.group.seden.Database;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.group.seden.model.Message;
import com.group.seden.model.UserSession;

public class Receiving {

    private static DatabaseReference myRef;
    private Message message;
    private UserSession session;

    public Message getMessage() {
        Message msg = new Message();
        myRef = FirebaseDatabase.getInstance().getReference().child("messages").child("startMessage");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated
                String value = dataSnapshot.getValue(String.class);
                System.out.printf("Value is : %s\n", value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                System.out.printf("Failed to read the DB value\n");
            }
        });

        return msg;
    }
}// END MODEL CLASS Receiving

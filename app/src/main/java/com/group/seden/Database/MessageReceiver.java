package com.group.seden.Database;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.group.seden.model.Message;
import com.group.seden.model.MessageList;
import com.group.seden.model.UserSession;

import java.util.HashMap;
import java.util.List;

/**
 * Used to securely deliver messages from the User's Firebase MessageInbox
 * and store it in their MessageList
 * @author  Isaac Buitrago
 * @author Johnathan Guzman
 */

public class MessageReceiver
{

    private static DatabaseReference myRef;
    private static UserSession session;
    private MessageList messageList;

    /**
     * Constructor to set up the Database reference, initialize the current session,
     * and create a MessageList.
     */
    public MessageReceiver()
    {
        session = UserSession.getInstance();

        myRef = FirebaseDatabase.getInstance().getReference().child("MessageInbox").child(session.getUserName());

        messageList =  MessageList.getInstance();
    }

    /**
     * Poll the Firebase database for any new messages for the current user
     */
    public void pollMessages() throws DatabaseException
    {
        // add message to the MessageList when callback fired
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                GenericTypeIndicator<HashMap<String, String>> hm = new GenericTypeIndicator<HashMap<String, String>>() {};

                HashMap<String, String> messages = dataSnapshot.getValue(hm);

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                databaseError.toException();
            }


        });

    }

}

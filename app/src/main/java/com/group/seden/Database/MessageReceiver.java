package com.group.seden.Database;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.group.seden.model.Encryption;
import com.group.seden.model.Message;
import com.group.seden.model.MessageList;
import java.util.HashMap;

/**
 * Used to securely deliver messages from the User's Firebase MessageInbox
 * and store it in their MessageList
 * @author  Isaac Buitrago
 * @author Johnathan Guzman
 */

public class MessageReceiver
{

    private static DatabaseReference myRef;

    public MessageList inbox;


    /**
     * Constructor to set up the Database reference, initialize the current session,
     * and create a MessageList.
     */
    public MessageReceiver(String username)
    {
        myRef = FirebaseDatabase.getInstance().getReference().child("MessageInbox").child(username);

        inbox = MessageList.getInstance();
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

                Message messg = new Message();

                messg.setSenderID(messages.get("SenderID"));

                messg.setMsgText(messages.get("Message"));

                messg.setDeleteTime(Integer.parseInt(messages.get("DeleteTime")));

                messg.setIsEncrypted(Boolean.parseBoolean(messages.get("Encrypted")));

                inbox.addMessage(messg);

                if(messg.getIsEncrypted())
                    Encryption.decrypt(messg, 12345);

                System.out.println(messg.getMsgText());

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                databaseError.toException();
            }


        });

    }

}

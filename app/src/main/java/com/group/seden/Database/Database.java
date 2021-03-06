package com.group.seden.Database;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.group.seden.model.Message;
import com.group.seden.model.UserSession;

import java.util.HashMap;

import static com.google.firebase.database.DatabaseError.DISCONNECTED;

/**
 * Class to store data in the database
 * @author JesusNieto
 * @author Isaac Buitrago
 */
public class Database {

    private static DatabaseReference mDatabase;
    private static  UserSession userInfo;

    /**
     * Stores user info when they create an account. They will have a unique ID which stores in the database as well.
     * @param email
     * @param password
     * @param userName
     * example: database/users/user
     */
    public static void storeUserInDBChild(String email, String password, String userName, String uID){
        mDatabase = FirebaseDatabase.getInstance().getReference(); // this points to the main database
        HashMap<String, String> childInfo = new HashMap<String, String>();  // creating hashmap to store data to commit to the users node
        childInfo.put("UserName", userName);
        childInfo.put("Email", email);
        childInfo.put("Password", password);
        childInfo.put("UniqueID", uID);
        childInfo.put("AccountLocked", "false");
        mDatabase.child("users").child(userName).setValue(childInfo); // this points to the database, then to the users node and then stores a new node in the users nodes with a unique ID.

    }

    /**
     * Used to send a message to a recipient. It is assumed that all the fields of the message are set and valid.
     * throws DatabaseException when the Database cannot process the message
     * @param message to send to a remote device, all fields must be set
     */
    public static void sendMessage(Message message) throws DatabaseException
    {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        HashMap<String, String> childInfo = new HashMap<>();
        childInfo.put("Message", message.getMsgText());
        childInfo.put("RecipientId", message.getRecipientID());
        childInfo.put("SenderID", message.getSenderID());
        childInfo.put("DeleteTime", Integer.toString(message.getDeleteTime()));
        childInfo.put("Encrypted", String.valueOf(message.getIsEncrypted()));

        // title the username
        mDatabase.child("messages").child(message.getRecipientID()).child(message.getSenderID()).setValue(childInfo, new DatabaseReference.CompletionListener() {

            @Override
            public void onComplete(DatabaseError error, DatabaseReference ref)
            {
                if(error != null)
                {
                    error.toException();
                }
            }
        });
    }

    /**
     * Used to delete the last sent message
     * throws DatabaseException when the Database cannot remove the child node
     */
    public static void deleteLastMessage() throws DatabaseException
    {
        mDatabase = FirebaseDatabase.getInstance().getReference();

        userInfo = UserSession.getInstance();

        //delete the message
        mDatabase.child("messages").child(userInfo.getUserName()).removeValue(
                new DatabaseReference.CompletionListener() {

                    // throw an error if unable to delete the last message
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if(databaseError != null)
                            databaseError.toException();
                    }
                }

        );

    }

    /**
     * Used to control access of an employee or client account by locking and unlocking the account
     * with the specified username.
     * throws DatabaseException when the Database cannot process the username
     * @param username who's account needs to be locked
     * @param lock true if the user will be locked out of their account, false otherwise
     */
    public static void controlAccountAccess(String username, boolean lock) throws DatabaseException
    {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child(username);

        if(lock)
        {
                mDatabase.child("AccountLocked").setValue("true", new DatabaseReference.CompletionListener() {

                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError != null)
                            databaseError.toException();

                    }
                });
        }

        else
        {
                mDatabase.child("AccountLocked").setValue("false", new DatabaseReference.CompletionListener() {

                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError != null)
                        databaseError.toException();
                }
            });
        }

    }


}

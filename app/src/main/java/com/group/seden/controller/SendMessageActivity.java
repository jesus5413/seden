package com.group.seden.controller;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group.seden.Database.Database;
import com.group.seden.R;
import com.group.seden.model.*;

import java.util.Iterator;

/*

    Paul Murray
    controller for activity_send_message

 */
public class SendMessageActivity extends AppCompatActivity{

    private Button sendMessageButton;
    public static Button encryptButton;
    public static EditText messageContent;
    private TextView messageRecipient;
    public static Message message = new Message();
    private Button acceptButton;

    //dialog box variables
    private boolean timeChecked;
    private boolean keyChecked;
    private String keyString;

    //pass into dialog to return
    //public static Boolean usePassword;
    public static Long password;

    private AlertDialog dialog;
    private UserSession session;
    private FirebaseUser currentuser;

    //The ID of the recipient of the message
    private String recipientID;
    private String senderID;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        messageContent = (EditText) findViewById(R.id.messageField);

        //initialize global variables for dialog
        //usePassword = false;
        password = null;

        //Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        recipientID = intent.getExtras().getString("uID");
        userName = intent.getExtras().getString("userName");
        senderID = intent.getExtras().getString("senderuID");
        System.out.println("-------------------" + recipientID);
        System.out.println(userName);
        System.out.println(senderID);

        //Log.d(" --------------",userName );
        //set id of GUI components
        TextView messageRecipient = (TextView)findViewById(R.id.recieveTextView1);
        messageRecipient.setText("To: " + userName);

        //find id of buttons from
        Button sendMessageButton = (Button)findViewById(R.id.sendMessageButton1);
        Button encryptButton = (Button)findViewById(R.id.encryptButton1);
        //calls class to handle button press
        sendMessageHandle(sendMessageButton);

        EncryptHandle(encryptButton);
    }



//when send button is pressed fill in and send message class to database
    private void sendMessageHandle(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //pulls the text from text input and converts it to a string

                String messageString = messageContent.getText().toString();

                //Declares values for message success/fail toast
                Context context = getApplicationContext();
                CharSequence text = "";
                int duration = Toast.LENGTH_SHORT;

                //if (password == null)
                    //usePassword = false;

                //If encryption key is used, constructs message with key is tru.
                //Otherwise, constructs message with key false.
                if (password != null) {
                    message = new Message(senderID, recipientID,
                            messageString, true);
                    //Encryption.encrypt(message, password);
                }else
                    message = new Message(senderID, recipientID,
                            messageString);

                //Tries to send message to database
                if (!message.getMsgText().equals(""))
                    try {
                        Database.sendMessage(message);
                        System.out.println("Successful");
                        text = "You message was successfully sent!";
                    } catch(DatabaseException e) {
                        System.out.println("Unsuccessful");
                        text = "Message sending failure! ";
                    } finally {
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        finish();
                } else {
                    text = "Please enter a message before clicking send.";
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

            }
        });

    }
// when encrypt button is pressed
    private void EncryptHandle(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EncryptOptionsDialog encryptOptionsDialog = new EncryptOptionsDialog();
                encryptOptionsDialog.show(getFragmentManager(), null);


                System.out.println("Encrypt options");
            }
        });

    }

    // code below for future improvements
    /*
    public void EncryptOptionsFragment() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new DecryptSuccessDialog();
        dialog.show(getFragmentManager(), "NoticeDialogFragment");
    }
    */





}

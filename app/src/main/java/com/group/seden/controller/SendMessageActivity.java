package com.group.seden.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseException;
import com.group.seden.Database.Database;
import com.group.seden.R;
import com.group.seden.model.*;

/*

    Paul Murray
    controller for activity_send_message

 */
public class SendMessageActivity extends AppCompatActivity{

    private Button sendMessageButton;
    private Button encryptButton;
    private EditText messageContent;
    private TextView messageRecipient;
    private Message outGoingMessage;
    private Button acceptButton;

    //dialog box variables
    private boolean timeChecked;
    private boolean keyChecked;
    private String keyString;

    //pass into dialog to return
    public static Boolean usePassword;
    public static Long password;

    private AlertDialog dialog;
    private Message message;
    private UserSession session;

    //The ID of the recipient of the message
    private String recipientID = "user001";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        //initialize global variables for dialog
        usePassword = false;
        password = null;

        //Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String recipient = intent.getExtras().getString("uID");

        //set id of GUI components
        TextView messageRecipient = (TextView)findViewById(R.id.recieveTextView1);
        messageRecipient.setText("To: " + recipient);

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
                EditText messageContent = (EditText) findViewById(R.id.messageField);
                String messageString = messageContent.getText().toString();

                //Declares values for message success/fail toast
                Context context = getApplicationContext();
                CharSequence text = "";
                int duration = Toast.LENGTH_SHORT;

                //Initializes sessionID
                session = UserSession.getInstance();

                //Message object to send
                Message message;

                if (password == null)
                    usePassword = false;

                //If encryption key is used, constructs message with key is tru.
                //Otherwise, constructs message with key false.
                if (usePassword == true) {
                    message = new Message(session.getUniqueID(), recipientID,
                            messageString, usePassword);
                    Encryption.encrypt(message, password);
                }else
                    message = new Message(session.getUniqueID(), recipientID,
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



}

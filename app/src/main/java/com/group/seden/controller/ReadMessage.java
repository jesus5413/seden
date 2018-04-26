package com.group.seden.controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.group.seden.R;
import com.group.seden.model.Encryption;
import com.group.seden.model.Message;

import org.w3c.dom.Text;

public class ReadMessage extends FragmentActivity implements DecryptSuccessDialog.DecryptSuccessDialogListener{
    public static final String SENDER_EXTRA = "com.group.seden.MESSAGE";

    private String messageSender;
    private String messageContent;
    private Message tempMess;

    //from intent
    private String sender;
    private String recipient;
    // declare layout component variables
    private Button decryptButton;
    private EditText passwordEnter;
    private Long passwordLong;
    private TextView messageTextView;
    private TextView senderTextView;

    private boolean isEncrypted;
    private String messageBoolean;

    private Button replyButton;

// temporary message
    private Message tempMessage;

    //number of decrypt attempts
    private int decryptTry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_message);

        //allow window to readjust when keyboard comes up
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE| WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        // initialize decrypt tries
        decryptTry = 0;

        //Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        sender = intent.getExtras().getString("senderuID");
        recipient = intent.getExtras().getString("ruID");
        String messageText = intent.getExtras().getString("message");
        messageBoolean = intent.getExtras().getString("boolean");

        //set encrypt boolean
        if(messageBoolean.equals("true")){
            isEncrypted = true;
        }else{
            isEncrypted = false;
        }

        //create temporary message
        tempMessage = new Message(recipient,recipient,messageText,isEncrypted);


// get gui component ids
        Button replyButton = (Button)findViewById(R.id.replyButton1);
        decryptButton = (Button)findViewById(R.id.decryptButton1);
        passwordEnter = (EditText) findViewById(R.id.enterPasswordEditText1);
        messageTextView = (TextView) findViewById(R.id.messageContentTextView1);
        senderTextView = (TextView) findViewById(R.id.senderNameTextView1);

        //set From text
        senderTextView.setText("From: " + sender);
        //set message content
        messageTextView.setText(tempMessage.getMsgText());

        //checks if the message is encrypted
        if(tempMessage.getIsEncrypted() == true){
            // put code to enable decrypt option
            EnableButtons();

        }else{
            DisableButtons();
        }

        //calls class to handle button press
        ReplyHandle(replyButton);
        PressDecryption();

    }

    //opens a compose message
    private void ReplyHandle(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent startIntent = new Intent(ReadMessage.this, SendMessageActivity.class);
                startIntent.putExtra("uID", sender);
                startIntent.putExtra("userName",sender);
                startIntent.putExtra("senderuID",recipient);
                startActivity(startIntent);

            }
        });

    }
// code when user presses the decrypt button
    private void PressDecryption(){
        decryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                decryptTry++;


                //get password from password input box
                if (!passwordEnter.getText().toString().equals(""))
                    passwordLong = Long.parseLong(passwordEnter.getText().toString());

                Encryption.decrypt(tempMessage,passwordLong);
                //set message text box
                messageTextView.setText(tempMessage.getMsgText());

                showDecryptSuccessDialog();


            }


        });

    }
    //enables decrypt button and text box
    public void EnableButtons(){
        passwordEnter.setEnabled(true);
        decryptButton.setEnabled(true);
        passwordEnter.setVisibility(View.VISIBLE);
        decryptButton.setVisibility(View.VISIBLE);

    }
    //enables decrypt button and text box
    public void DisableButtons(){
        passwordEnter.setEnabled(false);
        decryptButton.setEnabled(false);
        passwordEnter.setVisibility(View.GONE);
        decryptButton.setVisibility(View.GONE);

    }

    public void showDecryptSuccessDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new DecryptSuccessDialog();
        dialog.show(getFragmentManager(), "NoticeDialogFragment");
    }


    // The dialog fragment receives a reference to this Activity through the
    // Fragment.onAttach() callback, which it uses to call the following methods
    // defined by the NoticeDialogFragment.NoticeDialogListener interface
    @Override
    public void onDialogYesClick(DialogFragment dialog) {
        // User touched the dialog's yes button
        //disable the decrypt button
        DisableButtons();
    }

    @Override
    public void onDialogNoClick(DialogFragment dialog) {
        // User touched the no button in the dialog
      Encryption.encrypt(tempMessage,passwordLong);

        //set message text box
        messageTextView.setText(tempMessage.getMsgText());

        if(decryptTry >= 3){
            finish();
        }
    }


}


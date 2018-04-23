package com.group.seden.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.group.seden.R;
import com.group.seden.model.Encryption;
import com.group.seden.model.Message;

import org.w3c.dom.Text;

public class ReadMessage extends AppCompatActivity {
    public static final String SENDER_EXTRA = "com.group.seden.MESSAGE";

    private String messageSender;
    private String messageContent;
    private Message tempMess;

    // declare layout component variables
    private Button decryptButton;
    private EditText passwordEnter;
    private Long passwordLong;
    private TextView messageTextView;
    private TextView senderTextView;

    private boolean isEncrypted;

    private Button replyButton;

    private Message tempMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_message);

        //place holders
        messageSender = "user000";
        messageContent = "Hello user000";
        isEncrypted = true;

        tempMessage = new Message();

        if(isEncrypted == true){
            // put code to enable decrypt option
        }

        Button replyButton = (Button)findViewById(R.id.replyButton1);
        Button decryptButton = (Button)findViewById(R.id.decryptButton1);
        passwordEnter = (EditText) findViewById(R.id.enterPasswordEditText1);
        messageTextView = (TextView) findViewById(R.id.messageContentTextView1);
        senderTextView = (TextView) findViewById(R.id.senderNameTextView1);

        //calls class to handle button press
        ReplyHandle(replyButton);
        PressDecryption(decryptButton);

    }

    //opens a compose message
    private void ReplyHandle(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           //     Intent intent = new Intent(this, SendMessageActivity.class);
          //      String message = messageSender;
           //     intent.putExtra(SENDER_EXTRA, message);
           //     startActivity(intent);

            }
        });

    }
// code when user presses the decrypt button
    private void PressDecryption(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get password from password input box
                if (!passwordEnter.getText().toString().equals(""))
                    passwordLong = Long.parseLong(passwordEnter.getText().toString());

                Encryption.decrypt(tempMessage,passwordLong);
            }


        });

    }

}

package com.group.seden.controller;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.group.seden.R;
import com.group.seden.model.Encryption;
import com.group.seden.model.Message;

public class ReadMessage extends AppCompatActivity {
    public static final String SENDER_EXTRA = "com.group.seden.MESSAGE";

    private String messageSender;
    private String messageContent;
    private TextView messageRecipient;
    private Message tempMess;

    private boolean isEncrypted;

    private Button replyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_message);

        messageSender = "user000";
        messageContent = "Hello user000";
        isEncrypted = true;
        Message tempMess;

        if(isEncrypted == true){
            LaunchDecryption();
        }

        Button replyButton = (Button)findViewById(R.id.replyButton1);
        //calls class to handle button press
        ReplyHandle(replyButton);

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

    private void LaunchDecryption(){

        final EncryptionDialog dialog=new EncryptionDialog();
        dialog.onButtonAccept =new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), dialog.edit_text.getText(), Toast.LENGTH_SHORT).show();
            }
        };

        dialog.show(getFragmentManager(),null);

        int key = 111;
        Encryption.decrypt(tempMess, key);
    }

}

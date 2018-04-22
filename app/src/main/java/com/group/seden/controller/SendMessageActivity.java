package com.group.seden.controller;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseException;
import com.group.seden.Database.Database;
import com.group.seden.R;
import com.group.seden.model.Message;
import com.group.seden.model.UserSession;

public class SendMessageActivity extends AppCompatActivity {

    private Button sendMessageButton;
    private EditText messageContent;
    private Message message;
    private UserSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        Button sendMessageButton = (Button)findViewById(R.id.sendMessageButton1);
        //calls class to handle button press
        sendMessageHandle(sendMessageButton);
    }




    private void sendMessageHandle(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //pulls the text from text input and converts it to a string
                EditText messageContent = (EditText) findViewById(R.id.textInputLayout);

                String messageString = messageContent.getText().toString();

                Context context = getApplicationContext();

                CharSequence text = "Your message was sent !";

                int duration = Toast.LENGTH_SHORT;

                session = UserSession.getInstance();

                message = new Message(messageString);

                message.setSenderID(session.getUniqueID());

                message.setRecipientID("cROCGICa8iN42AVgjmW2b5vhPr72"); // hardcoded recipient Id for now

                message.setIsEncrypted(true);

                System.out.println("Sending");

                try {
                    Database.sendMessage(message);

                } catch(DatabaseException e) {

                    text = "Your message could not reach the recipient, Please try again";
                } finally {

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

            }
        });

    }
}

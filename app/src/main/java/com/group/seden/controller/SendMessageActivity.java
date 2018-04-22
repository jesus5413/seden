package com.group.seden.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.group.seden.R;
import com.group.seden.model.Message;

public class SendMessageActivity extends AppCompatActivity{

    private Button sendMessageButton;
    private Button encryptButton;
    private EditText messageContent;
    private TextView messageRecipient;
    private Message outGoingMessage;

    //dialog box variables
    private boolean timeChecked;
    private boolean keyChecked;
    private String keyString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(ReadMessage.SENDER_EXTRA);

        outGoingMessage = new Message();
        outGoingMessage.setSenderID("user000");
        outGoingMessage.setRecipientID("user000");

        //set id of GUI components
        TextView messageRecipient = (TextView)findViewById(R.id.recieveTextView1);
        messageRecipient.setText(outGoingMessage.getRecipientID());

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
                EditText messageContent = (EditText) findViewById(R.id.textInputLayout);
                String messageString = messageContent.getText().toString();



                System.out.println("Sending");
                //send message code here
                Context context = getApplicationContext();
                CharSequence text = "Your Message was sent ";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();


                //fill in message object
                outGoingMessage.setMsgText(messageString);

                //Database.sendMessage(outgoingMessage);

                finish();
            }
        });

    }

    private void EncryptHandle(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*
                AlertDialog.Builder builder = new AlertDialog.Builder(SendMessageActivity.this);
                LayoutInflater inflater = SendMessageActivity.this.getLayoutInflater();
                builder.setView(inflater.inflate(R.layout.activity_decrypt_options_dialog, null));

                builder.create().show();
                */
                // use
                final EncryptionDialog dialog=new EncryptionDialog();
                dialog.onButtonAccept =new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), dialog.edit_text.getText(), Toast.LENGTH_SHORT).show();
                    }
                };

                dialog.show(getFragmentManager(),null);


                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout



                System.out.println("Encrypt options");



            }
        });

    }


    private void FinishEncryptHandle(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder builder = new AlertDialog.Builder(SendMessageActivity.this);
                LayoutInflater inflater = SendMessageActivity.this.getLayoutInflater();
                builder.setView(inflater.inflate(R.layout.activity_decrypt_options_dialog, null));

                builder.create().show();

                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout



                System.out.println("Encrypt options");



            }
        });

    }

}

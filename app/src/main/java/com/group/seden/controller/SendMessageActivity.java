package com.group.seden.controller;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.group.seden.R;

public class SendMessageActivity extends AppCompatActivity {

    private Button sendMessageButton;
    private EditText messageContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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


                System.out.println("Sending");
                //send message code here
                Context context = getApplicationContext();
                CharSequence text = "Your Message was sent ";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }
        });

    }
}

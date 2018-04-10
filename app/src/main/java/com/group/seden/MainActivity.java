package com.group.seden;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button logIn = (Button) findViewById(R.id.logInButton);
    }

    public  boolean logInHandle(Button button){


        return true;
    }




}

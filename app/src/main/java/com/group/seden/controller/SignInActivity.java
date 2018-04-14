package com.group.seden.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.group.seden.R;

public class SignInActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button logIn;
    private EditText email;
    private EditText password;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Sign In");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logIn = (Button) findViewById(R.id.logInButton);
        email = (EditText) findViewById(R.id.emailEditText);
        password = (EditText) findViewById(R.id.paswwordEditText);


        mAuth = FirebaseAuth.getInstance();
        logInHandle(logIn);



    }

    @Override
    public void onStart(){
        super.onStart();
        currentUser =  mAuth.getCurrentUser();

        // checks to see if there is a user, and if there is, go to the main app.
        if(currentUser != null){
            Intent startIntent = new Intent(SignInActivity.this, AppActivity.class);
            startActivity(startIntent);
            finish();
        }

    }




    private void logInHandle(Button button){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("tapped");
                // logging in database code here

                signIn(email.getText().toString(), password.getText().toString());


            }
        });


    }

    private void signIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task){
                        if(task.isSuccessful()){
                            System.out.println("logged in");
                            // goes to the main app
                            Intent startIntent = new Intent(SignInActivity.this, AppActivity.class);
                            startActivity(startIntent);
                            finish();
                        }else{
                            System.out.println("user doesnt exist");
                            Toast.makeText(SignInActivity.this, "Please ask admin for an account", Toast.LENGTH_LONG).show();
                        }

                        if(!task.isSuccessful()){
                             System.out.println("some error");
                        }


                    }

                });

    }












}

package com.group.seden.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.group.seden.Database.Database;
import com.group.seden.R;

public class CreateAccountActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private Button createButton;
    private EditText email;
    private EditText userName;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Create Account");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        email = (EditText) findViewById(R.id.emailEditText2);
        userName = (EditText) findViewById(R.id.userNameEditText2);
        password = (EditText) findViewById(R.id.passwordEditText2);
        createButton = (Button) findViewById(R.id.createButton);
        mAuth = FirebaseAuth.getInstance();
        createAccountHandle(createButton);

    }

    private void createAccountHandle(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("tapped");

                createAccount(email.getText().toString(), password.getText().toString(), userName.getText().toString());


            }
        });

    }

    private void createAccount(final String email, final String password, final String userName){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    currentUser = FirebaseAuth.getInstance().getCurrentUser();
                    String uId = currentUser.getUid();
                    Database.storeUserInDBChild(email, password, userName, uId);
                    System.out.println("account created");
                }else {
                    System.out.println("user exists");
                }

                if(!task.isSuccessful()){
                    System.out.println("some other error");
                }
            }
        });

    }




}

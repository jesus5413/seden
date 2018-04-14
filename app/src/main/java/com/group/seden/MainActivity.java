package com.group.seden;

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

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button logIn;
    private EditText email;
    private EditText password;
    private EditText userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logIn = (Button) findViewById(R.id.logInButton);
        email = (EditText) findViewById(R.id.emailEditText);
        password = (EditText) findViewById(R.id.paswwordEditText);
        userName = (EditText) findViewById(R.id.userNameEditText);

        mAuth = FirebaseAuth.getInstance();
        logInHandle(logIn);


    }



    private void logInHandle(Button button){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("tapped");
                // logging in database code here

                signIn(email.getText().toString(), password.getText().toString());
                //signUp(email.getText().toString(), password.getText().toString(), userName.getText().toString());

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
                        }else{
                            System.out.println("user doesnt exist");
                            Toast.makeText(MainActivity.this, "Please ask admin for an account", Toast.LENGTH_LONG).show();
                        }

                        if(!task.isSuccessful()){
                             System.out.println("some error");
                        }


                    }

                });

    }

    private void signUp(final String email, final String password, final String userName){

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Database.Database.storeUserInDBChild(email, password, userName);
                    System.out.println("created accounf");
                }else{
                    System.out.println("user exists already");
                }
                if(!task.isSuccessful()){
                    System.out.println("there was some error");
                }

            }
        });



    }










}

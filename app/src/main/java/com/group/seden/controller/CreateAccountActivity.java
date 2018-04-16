package com.group.seden.controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group.seden.Database.Database;
import com.group.seden.R;
import com.group.seden.model.UserSession;

/**
 * This class for system admin to create accounts for users
 *
 * @author jesusnieto
 */
public class CreateAccountActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private Button createButton;
    private EditText email;
    private EditText userName;
    private EditText password;
    private ProgressDialog logInProgress;
    private UserSession userInfo;
    private DatabaseReference mDatabase;

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

        logInProgress = new ProgressDialog(this);

    }

    private void createAccountHandle(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("tapped");

                if(!TextUtils.isEmpty(email.getText().toString())
                        && !TextUtils.isEmpty(password.getText().toString())
                        && !TextUtils.isEmpty(userName.getText().toString())){
                            logInProgress.setTitle("Creating Account");
                            logInProgress.setMessage("Creating Account");
                            logInProgress.setCancelable(false);
                            logInProgress.show();

                            userNameChecker(userName.getText().toString(), password.getText().toString(), email.getText().toString());
                }


            }
        });

    }


    private void userNameChecker(final String userName, final String password, final String email){
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("users").child(userName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    logInProgress.hide();
                    Toast.makeText(CreateAccountActivity.this, "Please use a different username.", Toast.LENGTH_LONG).show();
                }else{
                    createAccount(email, password, userName);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("some error");

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
                    FirebaseAuth.getInstance().signOut();
                    tempLogIn(Database.sysAdmin);
                    logInProgress.dismiss();
                    Intent startIntent = new Intent(CreateAccountActivity.this, AppActivity.class);
                    startActivity(startIntent);
                    finish();
                }else {
                    System.out.println("user exists/email has been used before");
                    logInProgress.hide();
                }

            }
        });

    }

    private void tempLogIn(UserSession user){
        mAuth.signInWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    System.out.println("successful login");
                }else{
                    System.out.println("ERROR");
                }

            }
        });

    }




}

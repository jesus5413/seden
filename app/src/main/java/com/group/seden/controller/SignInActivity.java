package com.group.seden.controller;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group.seden.Database.Database;
import com.group.seden.R;
import com.group.seden.model.UserSession;

/**
 *
 * This is the controller class for the sign in UI
 * @author jesusnieto
 * @author Isaac Buitrago
 */
public class SignInActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button logIn;
    private EditText userName;
    private EditText password;
    private FirebaseUser currentUser;
    private ProgressDialog logInProgress;
    private DatabaseReference mDatabase;
    private UserSession userInfo;
    private String uID;
    private String username;
    private int counter = 0;
    private final static String TAG = "SingInActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Sign In");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logIn = (Button) findViewById(R.id.logInButton);
        userName = (EditText) findViewById(R.id.emailEditText);
        password = (EditText) findViewById(R.id.passwordEditText);

        mAuth = FirebaseAuth.getInstance();
        logInProgress = new ProgressDialog(this);
        logInHandle(logIn);


    }

    /**
     * checks if there is a signed in user already.
     */
    @Override
    public void onStart(){
        super.onStart();
        currentUser =  mAuth.getCurrentUser();
        // checks to see if there is a user, and if there is, go to the main app.

//        if(currentUser != null){
//            uID = currentUser.getUid();
//
//            username = userName.getText().toString();
//
//            Intent startIntent = new Intent(SignInActivity.this, AppActivity.class);
//            startIntent.putExtra("uID", uID);
//            startIntent.putExtra("Username", username);
//            startActivity(startIntent);
//            finish();
//        }

//        if(currentUser != null){
//            uID = currentUser.getUid();
//
//            Intent startIntent = new Intent(SignInActivity.this, AppActivity.class);
//            startIntent.putExtra("uID", uID);
//            startActivity(startIntent);
//            finish();
//        }


    }


    /**
     * this is the login handle when button is pressed
     * @param button
     */
    private void logInHandle(final Button button){

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("tapped");

                // lock the user out of the account
                if(counter >= 2 ){
                    //button.setEnabled(false);
                    Toast.makeText(SignInActivity.this, "You've been locked out of the app for failed attempts. Contact your system administrator.",
                            Toast.LENGTH_LONG).show();
                    try {

                        boolean lock = true;

                        Database.controlAccountAccess(username, lock);

                    } catch(DatabaseException e) {

                        Log.e(TAG, e.getMessage());
                    }
                }

                if(!TextUtils.isEmpty(userName.getText().toString()) && !TextUtils.isEmpty(password.getText().toString())){

                    logInProgress.setTitle("Logging In");
                    logInProgress.setMessage("Checking Credentials");
                    logInProgress.setCancelable(false);
                    logInProgress.show();

                    userNameCheck(userName.getText().toString(), password.getText().toString());
                    counter++;

                }else{
                    Toast.makeText(SignInActivity.this, "Please input username/password.", Toast.LENGTH_SHORT).show();

                    counter++;
                }
            }
        });


    }


    /**
     * Checks for the username if it's in the database
     * if it is, then it calls the sign in method and signs in the
     * user.
     * @param email
     * @param password
     */
    private void userNameCheck(final String email, final String password){

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("users").child(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserSession temp;
                if(dataSnapshot.exists()){

                    temp = dataSnapshot.getValue(UserSession.class);

                    userInfo = temp;

                    trueSignIn(userInfo.getEmail(), password);
                }else{
                    System.out.println("some error, user doesnt exists");
                    logInProgress.hide();
                    Toast.makeText(SignInActivity.this, "Please ask admin for an account or check your credentials.", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("some error, user doesnt exists");
                logInProgress.hide();
                Toast.makeText(SignInActivity.this, "Please ask admin for an account or check your credentials.", Toast.LENGTH_LONG).show();

            }
        });

    }

    /**
     * signs in the user with email and password.
     * Moves the view to the main app.
     * @param email
     * @param password
     */
    private void trueSignIn(String email, String password){

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task){

                        if(task.isSuccessful()){
                            currentUser =  mAuth.getCurrentUser();
                            uID = currentUser.getUid();
                            System.out.println("logged in");

                            username = userName.getText().toString();

                            // goes to the main app
                            logInProgress.dismiss();
                            Intent startIntent = new Intent(SignInActivity.this, AppActivity.class);
                            startIntent.putExtra("uID", uID);
                            startIntent.putExtra("Username", username);
                            startActivity(startIntent);
                            finish();
                        }else{
                            System.out.println("user doesnt exist or password is wrong -- sign in");
                            logInProgress.hide();
                            Toast.makeText(SignInActivity.this, "Please ask admin for an account or check your credentials", Toast.LENGTH_LONG).show();
                        }
                    }

                });

    }
}
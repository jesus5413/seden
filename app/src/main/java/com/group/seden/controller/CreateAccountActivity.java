package com.group.seden.controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.auth.UserProfileChangeRequest;
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
    private String uID;

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


    /**
     * This functions controls the on click handle for the button
     * @param button
     */
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

    /**
     * checks to see if the username exists already in the database and if it doesnt,
     * it calls the create account method
     * @param userName
     * @param password
     * @param email
     */
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

    /**
     * Creates the account
     * @param email
     * @param password
     * @param userName
     */
    private void createAccount(final String email, final String password, final String userName){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    currentUser = FirebaseAuth.getInstance().getCurrentUser();
                    String uId = currentUser.getUid();
                    Database.storeUserInDBChild(email, password, userName, uId);
                    //setDisplayName();
                    System.out.println("account created");
//                    FirebaseAuth.getInstance().signOut();
//                    tempLogIn(email, password);
//
//                    String displayNAme = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
//                    System.out.println("-------------------------------------------------" + displayNAme);

                    FirebaseAuth.getInstance().signOut();

                    tempLogIn("sysAdmin@yahoo.com", "sysAdmin");

                    logInProgress.dismiss();

                }else {
                    System.out.println("user exists/email has been used before");
                    logInProgress.hide();
                }

            }
        });

    }

    /**
     * re logs in the sys admin to the application again
     * @param email
     * @param Password
     */
    private void tempLogIn(String email, String Password){
        mAuth.signInWithEmailAndPassword(email, Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    currentUser = FirebaseAuth.getInstance().getCurrentUser();
                    String uId2 = currentUser.getUid();
                    Intent startIntent = new Intent(CreateAccountActivity.this, AppActivity.class);
                    startIntent.putExtra("uID", uId2);
                    startActivity(startIntent);
                    finish();

                    System.out.println("----------------successful login");
                }else{
                    System.out.println("ERROR");
                }

            }
        });

    }

//    private void setDisplayName(){
//        final FirebaseUser user = mAuth.getCurrentUser();
//
//        if(user != null){
//           UserProfileChangeRequest.Builder builder = new UserProfileChangeRequest.Builder();
//           builder.setDisplayName(userName.getText().toString());
//           UserProfileChangeRequest profileUpdated = builder.build();
//
//           user.updateProfile(profileUpdated).addOnCompleteListener(new OnCompleteListener<Void>() {
//               @Override
//               public void onComplete(@NonNull Task<Void> task) {
//                    if(task.isSuccessful()){
//                        Log.d("Update", "Profile name updated");
//                    }
//               }
//           });
//
//
//        }


//    }





}

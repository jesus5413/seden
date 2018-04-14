package com.group.seden.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.group.seden.R;
public class AppActivity extends AppCompatActivity {
    private Toolbar mToolBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setTitle("Welcome to Seden");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);
        mToolBar = (Toolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolBar); // sets the toolbar layout to the main ap so it can be used
        getSupportActionBar().setTitle("Seden"); // sets the view title


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId() == R.id.main_logout_button){
            FirebaseAuth.getInstance().signOut(); // signs out the user

            //goes to the sign in view
            Intent startIntent = new Intent(AppActivity.this, SignInActivity.class);
            startActivity(startIntent);
            finish();
        }

        return true;
    }
}

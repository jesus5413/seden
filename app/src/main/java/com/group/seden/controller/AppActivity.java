package com.group.seden.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.group.seden.R;


import com.group.seden.Database.Database;

/**
 * This is the controller class for the main app view
 * @author JesusNieto
 * @author Isaac Buitrago
 */
public class AppActivity extends AppCompatActivity {
    private Toolbar mToolBar;
    private FirebaseAuth mAuth;
    private ViewPager mViewPager;
    private SectionsPagerAdapter mAdapter;
    private TabLayout mTabLayout;
    private Intent intent;
    private String uID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setTitle("Welcome to Seden");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);
        mToolBar = (Toolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolBar); // sets the toolbar layout to the main ap so it can be used
        getSupportActionBar().setTitle("Seden"); // sets the view title
        mAuth = FirebaseAuth.getInstance();

        intent = getIntent();
        uID = intent.getExtras().getString("uID");





        mViewPager = (ViewPager) findViewById(R.id.tabPager);
        mAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager.setAdapter(mAdapter);
        mTabLayout = (TabLayout)findViewById(R.id.main_tabs);
        mTabLayout.setupWithViewPager(mViewPager);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem createButton = menu.findItem(R.id.main_CreateAccount_button);

        if(uID.equals("eKdbub51mzb0owVqvta63wKzihN2")){  // checks to disable or enable button for sysAdmin
            createButton.setVisible(true);
        }else{
            createButton.setVisible(false);

        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);


        // goes to the create account activity
        if(item.getItemId() == R.id.main_CreateAccount_button){
            Intent startIntent = new Intent(AppActivity.this, CreateAccountActivity.class);
            startActivity(startIntent);
            finish();

        }

        // goes to SendMessageActivity
        if(item.getItemId() == R.id.main_ComposeMessage_button){
            Intent startIntent = new Intent(AppActivity.this, SendMessageActivity.class);
            startActivity(startIntent);
            finish();
        }

        // logs out the user
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

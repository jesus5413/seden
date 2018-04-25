package com.group.seden.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseException;
import com.group.seden.Database.MessageDeliverer;
import com.group.seden.Database.Receiving;
import com.group.seden.R;


import com.group.seden.Database.Database;
import com.group.seden.model.Message;
import com.group.seden.model.MessageList;

import java.util.ArrayList;

/**
 * This is the controller class for the main app view
 * @author JesusNieto
 */
public class AppActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mRecyclerAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Toolbar mToolBar;
    private FirebaseAuth mAuth;
    private ViewPager mViewPager;
    private SectionsPagerAdapter mAdapter;
    private TabLayout mTabLayout;
    private Intent intent;
    private String uID;
    private MessageList messageList = MessageList.getInstance();
    private MessageDeliverer youGotMail;
    private static final String TAG  = "AppActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setTitle("Welcome to Seden");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);
        mToolBar = (Toolbar) findViewById(R.id.main_page_toolbar);
        //mRecyclerView = (RecyclerView) findViewById(R.id.inboxRecycler);
        setSupportActionBar(mToolBar); // sets the toolbar layout to the main ap so it can be used
        getSupportActionBar().setTitle("Seden"); // sets the view title
        mAuth = FirebaseAuth.getInstance();
        youGotMail =  new MessageDeliverer();

        try {
            youGotMail.pollMessages();
        } catch(DatabaseException e)
        {
            Log.e(TAG, e.getMessage());
        }

        for(Message message: messageList)
        {
            System.out.println(message.toString());
        }

        intent = getIntent();

        uID = intent.getExtras().getString("uID");
       // mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
       // mRecyclerView.setLayoutManager(mLayoutManager);
        //mAdapter = new MyAdapter("our string");

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
        if(item.getItemId() == R.id.main_CreateAccount_button)
        {
            Intent startIntent = new Intent(AppActivity.this, CreateAccountActivity.class);
            startActivity(startIntent);
            finish();
        }

        // logs out the user
        if(item.getItemId() == R.id.main_logout_button)
        {
            FirebaseAuth.getInstance().signOut(); // signs out the user
            //goes to the sign in view
            Intent startIntent = new Intent(AppActivity.this, SignInActivity.class);
            startActivity(startIntent);
            finish();
        }

        return true;
    }
}

package com.group.seden.controller;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.group.seden.R;
import com.group.seden.model.UserSession;

/**
 * @author jesusnieto
 * A simple {@link Fragment} subclass.
 * Controller for the Contact list
 */
public class RequestFragment extends Fragment {

    private RecyclerView mAllUsers;
    private DatabaseReference mUserDatabase;
    private View mView;
    private Query userQuery;
    private FirebaseRecyclerAdapter firebaseRecyclerAdapter;

    public RequestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_request, container, false);
        mAllUsers = (RecyclerView) mView.findViewById(R.id.user_RecyclerView);

        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();
        onActivityStarted();
        firebaseRecyclerAdapter.startListening();
    }

    public void onActivityStarted() {
        super.onStart();
        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("users");
        userQuery = mUserDatabase.orderByKey();
        mAllUsers.setHasFixedSize(true);
        mAllUsers.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<UserSession> userOption = new
                FirebaseRecyclerOptions.Builder<UserSession>().setQuery(userQuery, UserSession.class).build();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<UserSession, AllUsersViewHolder>(userOption) {
            @Override
            protected void onBindViewHolder(AllUsersViewHolder holder, int position, final UserSession model) {
                holder.setName(model.getUserName());
                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CharSequence options[] = new CharSequence[]{"Send Message"};
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("Send Message?");
                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                              // go to the new view
                                if(i == 0){
                                    // this is point to first option which is send message
                                    // going to new view code is here. We will do that at meeting

                                    Intent startIntent = new Intent(getContext(), SendMessageActivity.class);
                                    startIntent.putExtra("uID", model.getUniqueID());
                                    startActivity(startIntent);

                                }
                            }
                        });
                        builder.show();
                    }
                });

            }


            @Override
            public AllUsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_single_layout, parent,false);
                return new AllUsersViewHolder(view);
            }
        };
        mAllUsers.setAdapter(firebaseRecyclerAdapter);


    }


    public class AllUsersViewHolder extends RecyclerView.ViewHolder{
        View mView;

        public AllUsersViewHolder(View itemView){
            super(itemView);
            mView = itemView;

        }

        public void setName(String name){
            TextView userName = mView.findViewById(R.id.userName_single);
            userName.setText(name);
        }
    }
}

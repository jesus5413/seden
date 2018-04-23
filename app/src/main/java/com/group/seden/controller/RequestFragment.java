package com.group.seden.controller;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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
            protected void onBindViewHolder( AllUsersViewHolder holder, int position, UserSession model) {
                holder.setName(model.getUserName());
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

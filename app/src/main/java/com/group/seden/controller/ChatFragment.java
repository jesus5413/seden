package com.group.seden.controller;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.group.seden.model.RecieveMessage;

/**
 * A simple {@link Fragment} subclass.
 *
 * Controller for the ChatInbox
 */
public class ChatFragment extends Fragment {
    private RecyclerView allChat;
    private DatabaseReference mUserDB;
    private DatabaseReference mMessageDB;
    private View mView;
    private Query chatQuery;
    private String recieverUI;
    private FirebaseRecyclerAdapter firebaseRecyclerAdapter;

    private String userName;
    private String message;


    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_chat, container, false);
        allChat = (RecyclerView) mView.findViewById(R.id.all_chats);
        Intent intent = new Intent();
        recieverUI = getActivity().getIntent().getExtras().getString("uID");
        allChat.setHasFixedSize(true);
        allChat.setLayoutManager(new LinearLayoutManager(getContext()));




        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();
        onActivityStarted();
        firebaseRecyclerAdapter.startListening();
    }

    public void onActivityStarted(){
        mUserDB = FirebaseDatabase.getInstance().getReference().child("messages").child(recieverUI);
        chatQuery = mUserDB.orderByKey();


        FirebaseRecyclerOptions<RecieveMessage> userOption = new
                FirebaseRecyclerOptions.Builder<RecieveMessage>().setQuery(chatQuery, RecieveMessage.class).build();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<RecieveMessage, ChatViewHolder>(userOption) {
            @Override
            protected void onBindViewHolder(@NonNull ChatViewHolder holder, int position, final RecieveMessage model) {

                    holder.setName(model.getSenderID());
                    holder.setMessage(model.getMessage());

                    holder.mView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            CharSequence options[] = new CharSequence[]{"Reply"};
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle("Reply?");
                            builder.setItems(options, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if(i == 0){
                                        Intent startIntent = new Intent(getContext(), ReadMessage.class);
                                        startIntent.putExtra("senderuID", model.getSenderID());
                                        startIntent.putExtra("message", model.getMessage());
                                        startIntent.putExtra("boolean", model.getEncrypted());
                                        startIntent.putExtra("ruID", model.getRecipientID());
                                        startActivity(startIntent);

                                    }

                                }
                            });
                            builder.show();



                        }


                    });




            }

            @NonNull
            @Override
            public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_single_message_list, parent, false);
                return new ChatViewHolder(view);
            }
        };

        allChat.setAdapter(firebaseRecyclerAdapter);





    }


    public class ChatViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public ChatViewHolder(View itemView){
            super(itemView);
            mView = itemView;
        }

        public void setName(String userName){
            TextView name = mView.findViewById(R.id.user_Chat_name);
            name.setText(userName);
        }

        public void setMessage(String message){
            TextView m = mView.findViewById(R.id.user_chat_message);
            m.setText(message);
        }


    }








}











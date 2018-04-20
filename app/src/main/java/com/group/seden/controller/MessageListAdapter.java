package com.group.seden.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.group.seden.R;
import com.group.seden.model.Message;

import java.util.List;

/**
 * @author Isaac Buitrago
 *
 * MessageListAdapter manages the ViewHolders for the RecyclerView. This
 * will manage the text message bubble views that users create when composing and
 * receiving messages.
 */
public class MessageListAdapter extends RecyclerView.Adapter
{
    private Context context;            // context of the app
    private List<Message> messageList;  // container for incoming an outgoing messages

    public MessageListAdapter(Context context, List<Message> messageList)
    {
        context = context;
        messageList = messageList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return null;
    }

    /**
     * Replace the contents of a view, invoked by the layout manager
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {

    }

    @Override
    public int getItemCount()
    {
        return(messageList.size());
    }

    /**
     * Used to bind data to a layout_sent_message view
     */
    private class SentMessageViewHolder extends RecyclerView.ViewHolder
    {

        private TextView messageText, timeText;     // views to be inflated

        public SentMessageViewHolder(View itemView)
        {
            super(itemView);

            messageText = (TextView) itemView.findViewById(R.id.messageBodyTextView);
            timeText = (TextView) itemView.findViewById(R.id.messageTimeTextView);
        }




    }

}

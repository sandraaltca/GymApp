package com.example.sandra.gymapp.ArrayAdapter;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sandra.gymapp.R;
import com.firebase.client.Query;

import com.example.sandra.gymapp.classesjava.*;
import com.firebase.ui.FirebaseListAdapter;
import com.squareup.picasso.Picasso;

/**
 *
 * This class is an example of how to use FirebaseListAdapter. It uses the <code>Chat</code> class to encapsulate the
 * data for each individual chat message
 */
public class ChatListAdapter extends FirebaseListAdapter<Chat> {

    // The mUsername for this client. We use this to indicate which messages originated from this user
    private String mUsername;
    private Context context;

    public ChatListAdapter(Query ref, Activity activity, int layout, String mUsername, Context context) {
        super(activity, Chat.class, layout, ref);
        this.mUsername = mUsername;
        this.context=context;
    }

    /**
     *
     * @param view A view instance corresponding to the layout we passed to the constructor.
     * @param chat An instance representing the current state of a chat message
     */
    @Override
    protected void populateView(View view, Chat chat) {

           // Map a Chat object to an entry in our listview
           String author = chat.getAuthor();
           TextView authorText = (TextView) view.findViewById(R.id.author);
           ImageView image = (ImageView) view.findViewById(R.id.picChat);
           authorText.setText(author + ": ");

           if (author != null &&! author.equals("Centro")) {
               authorText.setTextColor(Color.GRAY);
               Picasso.with(context)
                       .load(R.mipmap.client)
                       .fit()
                       .into(image);
           } else {
               authorText.setTextColor(Color.DKGRAY);
               Picasso.with(context)
                       .load(R.mipmap.gimnas)
                       .fit()
                       .into(image);
           }
           ((TextView) view.findViewById(R.id.message)).setText(chat.getMessage());

    }
}


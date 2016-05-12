package com.example.sandra.gymapp.Centro;

import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sandra.gymapp.ArrayAdapter.ChatListAdapter;
import com.example.sandra.gymapp.FireBase.FireBaseConfiguracio;
import com.example.sandra.gymapp.MainActivity;
import com.example.sandra.gymapp.R;
import com.example.sandra.gymapp.classesjava.Chat;
import com.example.sandra.gymapp.classesjava.Cliente;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;


public class ContactaCentro extends Fragment {

    private String mUsername;
    private Firebase mFirebaseRef;
    private ValueEventListener mConnectedListener;
    private ChatListAdapter mChatListAdapter;
    EditText inputText;

    public ContactaCentro() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_contacta_centro, container, false);
        inputText = (EditText) rootView.findViewById(R.id.message_input);
        nomsUsuari();

        FireBaseConfiguracio fireBaseConfiguracio = new FireBaseConfiguracio();
        fireBaseConfiguracio.configFirebase(getContext());
        mFirebaseRef =fireBaseConfiguracio.getRutinesChat();


        inputText = (EditText) rootView.findViewById(R.id.message_input);
        inputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_NULL && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    enviarMissatge();
                }
                return true;
            }
        });



        rootView.findViewById(R.id.senChat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarMissatge();
            }
        });
        final ListView listView = (ListView)rootView.findViewById(R.id.chat);
        // Tell our list adapter that we only want 50 messages at a time


        Query queryRef = mFirebaseRef.orderByChild("uidUser").equalTo(MainActivity.uid);
        mChatListAdapter = new ChatListAdapter(queryRef, getActivity(), R.layout.chat_missatge, mUsername,getContext());
        listView.setAdapter(mChatListAdapter);
        mChatListAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(mChatListAdapter.getCount() - 1);
            }
        });

        // Finally, a little indication of connection status
        mConnectedListener = mFirebaseRef.getRoot().child(".info/connected").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean connected = (Boolean) dataSnapshot.getValue();
                if (connected) {
                    //Toast.makeText(ContactaCentro.this, "Connected to Firebase", Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(ContactaCentro.this, "Disconnected from Firebase", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                // No-op
            }
        });

        return rootView;
    }


    private void nomsUsuari() {
        Firebase.setAndroidContext(getContext());
        Firebase ref = new Firebase("https://testgimmapp.firebaseio.com/");
        Firebase ref2 = ref.child("Clientes");
        Query queryRef = ref2.orderByChild("uid").equalTo(MainActivity.uid);

        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                Cliente a=  snapshot.getValue(Cliente.class);
                mUsername = a.getNombre();
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }
    private void enviarMissatge() {
        String input = inputText.getText().toString();
        if (!input.equals("")) {
            // Create our 'model', a Chat object
            Chat chat = new Chat(input, mUsername, MainActivity.uid);
            // Create a new, auto-generated child of that chat location, and save our chat data there
            mFirebaseRef.push().setValue(chat);
            inputText.setText("");
        }
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

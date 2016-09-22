package com.example.liana.messenger;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

/**
 * Created by student on 9/19/16.
 */
public class UsersList extends Fragment implements ListView.OnItemClickListener, DataUpdateListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.users_list, container, false);



        String[] users = { "Liana","Artur"};



        ListView usersList = (ListView)view.findViewById(R.id.lw_user_list);
        OnlineUsersAdapter adapter = new OnlineUsersAdapter(getContext(),android.R.layout.simple_list_item_1, users);
        usersList.setAdapter(adapter);
        usersList.setOnItemClickListener(this);
        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ChatActivity.userText = ((TextView)view).getText().toString();

        ChatWindow chatWindow = new ChatWindow();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container, chatWindow).commit();
        //Toast.makeText(UsersList.this, String.valueOf(id), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dataReceiver(Map<String, String> map) {

    }

    @Override
    public void UpdateUserList() {

    }
}

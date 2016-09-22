package com.example.liana.messenger;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by student on 9/19/16.
 */
public class ChatWindow extends Fragment implements MessageListener{
    private TextView userName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chat_window, container, false);
        userName = (TextView)view.findViewById(R.id.tv_username);
        userName.setText(ChatActivity.userText);

        return view;
    }

    @Override
    public void updateMessageWindow(String str) {

    }

    @Override
    public void instantUpdateWindow(String str) {

    }
}

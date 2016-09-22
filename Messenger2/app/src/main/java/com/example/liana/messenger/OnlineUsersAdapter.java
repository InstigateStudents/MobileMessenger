package com.example.liana.messenger;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by student on 9/22/16.
 */
public class OnlineUsersAdapter extends ArrayAdapter<String> {
    private Map<String, User> onlineUsers = null;

    public OnlineUsersAdapter(Context context, int resource, String[] strarr) {
        super(context, resource, strarr);

        
    }

    public void setOnlinesMap(Map<String, User> map)
    {
        onlineUsers = map;
    }



}

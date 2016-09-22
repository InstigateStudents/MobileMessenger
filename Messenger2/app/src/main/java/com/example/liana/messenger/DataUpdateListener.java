package com.example.liana.messenger;

import java.util.Map;

/**
 * Created by student on 9/20/16.
 */
public interface DataUpdateListener {
    public void dataReceiver(Map<String, String> map);
    public void UpdateUserList();
}

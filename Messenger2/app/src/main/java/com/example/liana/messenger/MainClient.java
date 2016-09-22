package com.example.liana.messenger;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by student on 9/20/16.
 */
public class MainClient  extends AsyncTask<String, Void , Void> {
    public static final int PORT = 12345;
    public static final String ip = "192.168.68.111";
    public Socket clientSocket = null;
   // public Map<String, String> onlineUsers = null;
    private PrintWriter writer= null;
    private BufferedReader reader= null;
    Timer updater = null;
    ResultListener lst = null;
    DataUpdateListener ulst = null;

    public MainClient(ResultListener lst) {
     //   onlineUsers = new HashMap();
        this.lst = lst;
    }

    public void setDULObject(DataUpdateListener utls)
    {
        this.ulst = utls;
    }

    public void connect() {
        try {
            clientSocket = new Socket(InetAddress.getByName(ip),PORT);
        } catch (IOException e) {
            e.printStackTrace();
            //handle exception
        }
    }

    public void disConnect()
    {
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
            //handle exception
        }
    }

    public boolean login(String uName, String password)
    {
        char[] buffer = new char[1024];
        connect();
        try {
            writer = new PrintWriter(clientSocket.getOutputStream());
            writer.write("login?" + uName + ";" + password);
            writer.flush();
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            reader.read(buffer);
            String buf = new String(buffer);
            boolean fl = buf.contains("YES");
            Log.d("MYLOG", buf);
            if(fl)
            {
                updater = new Timer();
                updater.schedule(new TimerTask() {
                    Map map = new HashMap<String, String>();
                    @Override
                    public void run() {
                        map = getOnlines();
                        ulst.dataReceiver(map);
                    }
                }, 5000);
                return true;
            } else {
                return false;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        catch (NullPointerException e1) {
            e1.printStackTrace();
            return false;
        }
    }

    public boolean register(String uName, String password)
    {

        char[] bufer = new char[1024];
        connect();
        try {
            writer = new PrintWriter(clientSocket.getOutputStream());
            writer.write("registration?" + uName + ";" + password);
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            reader.read(bufer);
            return bufer.toString().contains("YES");

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean logout()
    {
        char[] buffer = new char[1024];
        connect();
        try {
            writer = new PrintWriter(clientSocket.getOutputStream());
            writer.write("logout?");
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            reader.read(buffer);
            return buffer.toString().contains("YES");

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Map<String, String> getOnlines()
    {
        Map onlineUsers  = new HashMap<String, String>();
        char[] buffer = new char[4096];
        try {
            writer = new PrintWriter(clientSocket.getOutputStream());
            writer.write("update?");
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            reader.read(buffer);
            setMap(buffer, onlineUsers);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return onlineUsers;
    }

    public void setMap(char[] buf, Map<String, String> map) {
        int index1 = 0, index2 = 0, index3 = 0;
        String str = buf.toString();
        String ipUser = new String();
        String ip = new String();
        String user = new String();
        while (index3 != -1) {
            index3 = str.indexOf("\n", index1);
            ipUser = str.substring(index1, index3);
            index2 = ipUser.indexOf(";", index1);
            ip = ipUser.substring(index1, index2);
            user = ipUser.substring(index2+1, index3);
            map.put(ipUser, user);
        }
    }

    @Override
    protected Void doInBackground(String... params) {
        switch (params[0]) {
            case "login":
                boolean loginResult = login(params[1], params[2]);
                lst.onResultLogin(loginResult);
                break;
            case "registration":
                lst.onResultRegister(register(params[1], params[2]));
                break;
            case "logout":
                lst.onLogout(logout());
                break;
        }
        return null;
    }


}

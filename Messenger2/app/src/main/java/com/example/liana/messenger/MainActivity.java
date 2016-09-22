package com.example.liana.messenger;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Map;

public class MainActivity extends FragmentActivity implements View.OnClickListener, ResultListener {
    private Button loginButton = null;
    private Button regButton = null;
    private EditText username = null;
    private EditText password = null;
    MainClient mainClient = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        loginButton = (Button)findViewById(R.id.btn_login);
        loginButton.setOnClickListener(this);

        regButton = (Button)findViewById(R.id.btn_register);
        regButton.setOnClickListener(this);

        username = (EditText)findViewById(R.id.et_username);
        password = (EditText)findViewById(R.id.et_password);

        mainClient = new MainClient(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
              //  mainClient.setCaller();
                mainClient.execute("login", username.getText().toString(),
                        password.getText().toString());
                break;
            case R.id.btn_register:
                mainClient.execute("registration", username.getText().toString(),
                        password.getText().toString());
                break;
        }
    }


    @Override
    public void onResultLogin(final boolean isSuccess) {
    runOnUiThread(new Runnable() {
    @Override
    public void run() {
        if (isSuccess)
        {
            Intent intent = new Intent(MainActivity.this, ChatActivity.class);
            startActivity(intent);
        }

    }
});
    }

    @Override
    public void onResultRegister(boolean isSuccess) {

    }


    @Override
    public void onLogout(boolean flag) {

    }
}

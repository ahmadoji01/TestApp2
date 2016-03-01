package com.tanyadong.testapp2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
{

    EditText etUsername, etPassword;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        session = new SessionManager(getApplicationContext());
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etUsername = (EditText)findViewById(R.id.etUsername);
        etPassword = (EditText)findViewById(R.id.etPassword);
    }

    public void OnLogin(View view)
    {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, username, password);
        if(session.isLoggedIn())
        {
            startActivity(new Intent(this, MenuActivity.class));
        }
    }

    public void OpenRegister(View view)
    {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        if(session.isLoggedIn())
        {
            startActivity(new Intent(this, MenuActivity.class));
        }
    }
}

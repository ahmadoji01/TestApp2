package com.tanyadong.testapp2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

public class ShowActivity extends AppCompatActivity
{
    SessionManager session;
    EditText etName, etSurname, etAge, etEmail, etUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        session = new SessionManager(getApplicationContext());
        setContentView(R.layout.activity_show);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etName = (EditText)findViewById(R.id.etName);
        etSurname = (EditText)findViewById(R.id.etSurname);
        etAge = (EditText)findViewById(R.id.etAge);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etUsername = (EditText)findViewById(R.id.etUsername);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        HashMap<String, String> userDetail = session.getUserDetails();
        etName.setText(userDetail.get("name"), TextView.BufferType.EDITABLE);
        etSurname.setText(userDetail.get("surname"), TextView.BufferType.EDITABLE);
        etAge.setText(userDetail.get("age"), TextView.BufferType.EDITABLE);
        etEmail.setText(userDetail.get("email"), TextView.BufferType.EDITABLE);
        etUsername.setText(userDetail.get("username"), TextView.BufferType.EDITABLE);
    }

    public void onLogout(View view)
    {
        session.logoutUser();
        startActivity(new Intent(this, MainActivity.class));
    }

}

package com.tanyadong.testapp2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

/**
 * Created by michaelknight123 on 2/9/2016.
 */
public class LoginActivity extends AppCompatActivity
{

    EditText etUsername1, etPassword1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername1 = (EditText)findViewById(R.id.etUsername1);
        etPassword1 = (EditText)findViewById(R.id.etPassword1);
    }

    public void OnLogin(View view)
    {
        String username = etUsername1.getText().toString();
        String password = etPassword1.getText().toString();
        String type = "login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, username, password);
    }

}

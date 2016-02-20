package com.tanyadong.testapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    EditText etName, etSurname, etAge, etEmail, etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etName = (EditText)findViewById(R.id.etName);
        etSurname = (EditText)findViewById(R.id.etSurname);
        etAge = (EditText)findViewById(R.id.etAge);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etUsername = (EditText)findViewById(R.id.etUsername);
        etPassword = (EditText)findViewById(R.id.etPassword);
    }

    public void onReg(View view)
    {
        String str_name = etName.getText().toString();
        String str_surname = etSurname.getText().toString();
        String str_age = etAge.getText().toString();
        String str_email = etEmail.getText().toString();
        String str_username = etUsername.getText().toString();
        String str_password = etPassword.getText().toString();
        String type = "register";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, str_name, str_surname, str_age, str_email, str_username, str_password);
    }

    public void goLogin(View view)
    {
        startActivity(new Intent(this, MainActivity.class));
    }

}

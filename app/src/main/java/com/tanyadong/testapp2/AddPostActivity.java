package com.tanyadong.testapp2;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

/**
 * Created by michaelknight123 on 2/28/2016.
 */
public class AddPostActivity extends AppCompatActivity implements AsyncResponse
{
    private AlertDialog alertDialog;
    public BgWorkerThread bgWorkerThread = new BgWorkerThread(this);
    private EditText etPostContent;
    private SessionManager session;
    private String threadID;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Intent intentExtra = getIntent();
        Bundle extraBundle = intentExtra.getExtras();
        if(!extraBundle.isEmpty())
            threadID = extraBundle.getString("threadID");
        else
            threadID = "1";

        super.onCreate(savedInstanceState);
        session = new SessionManager(getApplicationContext());
        setContentView(R.layout.activity_add_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etPostContent = (EditText)findViewById(R.id.etPostContent);
    }

    public void onSubmitPostClick(View view)
    {
        String postContent = etPostContent.getText().toString();
        String userID = session.getUserID();
        String type = "addpost";

        bgWorkerThread.delegate = this;
        bgWorkerThread.execute(type, postContent, threadID, userID);
    }

    public void processFinish(String output)
    {
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Status");
        alertDialog.setMessage(output);
        alertDialog.show();
    }
}

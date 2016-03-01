package com.tanyadong.testapp2;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

/**
 * Created by michaelknight123 on 2/24/2016.
 */
public class AddForumActivity extends AppCompatActivity implements AsyncResponse
{
    AlertDialog alertDialog;
    public BgWorkerForum bgWorkerForum = new BgWorkerForum(this);
    private EditText etThreadTitle, etThreadContent;
    private SessionManager session;
    private String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Intent intentExtra = getIntent();
        Bundle extraBundle = intentExtra.getExtras();
        if(!extraBundle.isEmpty())
            categoryName = extraBundle.getString("categoryName");
        else
            categoryName = "1";

        super.onCreate(savedInstanceState);
        session = new SessionManager(getApplicationContext());
        setContentView(R.layout.activity_add_forum);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etThreadTitle = (EditText)findViewById(R.id.etThreadTitle);
        etThreadContent = (EditText)findViewById(R.id.etPostContent);
    }

    public void onSubmitClick(View view)
    {
        String threadTitle = etThreadTitle.getText().toString();
        String threadContent = etThreadContent.getText().toString();
        String userID = session.getUserID();
        String type = "postforum";

        bgWorkerForum.delegate = this;
        bgWorkerForum.execute(type, threadTitle, threadContent, userID, categoryName);
    }

    public void processFinish(String output)
    {
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Status");
        alertDialog.setMessage(output);
        alertDialog.show();

        Intent intent = new Intent(this, ForumActivity.class);
        intent.putExtra("categoryName", categoryName);
        startActivity(intent);
    }

}

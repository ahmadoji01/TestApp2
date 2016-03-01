package com.tanyadong.testapp2;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by michaelknight123 on 2/15/2016.
 */
public class ForumActivity extends AppCompatActivity implements AsyncResponse
{

    AlertDialog alertDialog;
    public BgWorkerForum bgWorkerForum = new BgWorkerForum(this);
    private Button addForum;
    private List<Forum> myForum = new ArrayList<Forum>();
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
        setContentView(R.layout.activity_forum);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.i("Category Name: ", categoryName);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        myForum.clear();
        String type = "retrforum";
        bgWorkerForum = new BgWorkerForum(this);
        bgWorkerForum.delegate = this;
        bgWorkerForum.execute(type, categoryName);
    }

    public void processFinish(String output)
    {
        BufferedReader bufferedReader = new BufferedReader(new StringReader(output));
        String line = "";
        try
        {
            int i = 0;
            String splitString [] = new String[4];
            while ((line = bufferedReader.readLine()) != null)
            {
                splitString[i] = line;
                i++;
                if(i == 4)
                {
                    populateForumList(splitString[0], splitString[1], splitString[2], splitString[3]);
                    i = 0;
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        populateListView();
        registerClickCallback();
    }

    private void populateForumList(String title, String date, String user, String threadID)
    {
        myForum.add(new Forum(title, date, R.drawable.avatar_profile, user, threadID));
    }

    private void populateListView()
    {
        ArrayAdapter<Forum> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.forumsListView);
        list.setAdapter(adapter);
    }

    private void registerClickCallback()
    {
        final Intent intent = new Intent(ForumActivity.this, ShowForumActivity.class);
        ListView list = (ListView) findViewById(R.id.forumsListView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Forum clickedForum = myForum.get(position);
                intent.putExtra("threadID", clickedForum.getThreadID());
                startActivity(intent);
            }
        });
    }

    private class MyListAdapter extends ArrayAdapter<Forum>
    {

        public MyListAdapter()
        {
            super(ForumActivity.this, R.layout.forum_item, myForum);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.forum_item, parent, false);
            }

            Forum currentForum = myForum.get(position);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.ivIcon);
            imageView.setImageResource(currentForum.getIconID());

            TextView titleText = (TextView) itemView.findViewById(R.id.tvThreadTitle);
            titleText.setText(currentForum.getThreadTitle());

            TextView overviewText = (TextView) itemView.findViewById(R.id.tvThreadOverview);
            overviewText.setText(currentForum.getThreadOverview());

            TextView starterText = (TextView) itemView.findViewById(R.id.tvThreadStarter);
            starterText.setText(currentForum.getThreadStarter());

            return itemView;
        }
    }

    public void onAddForumClick(View view)
    {
        Intent intent = new Intent(this, AddForumActivity.class);
        intent.putExtra("categoryName", categoryName);
        startActivity(intent);
    }

}
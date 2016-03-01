package com.tanyadong.testapp2;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.StackView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by michaelknight123 on 2/27/2016.
 */
public class ShowForumActivity extends AppCompatActivity implements AsyncResponse
{

    LinearLayout layout;
    public BgWorkerThread bgWorkerThread = new BgWorkerThread(this);
    private String threadID;
    private AlertDialog alertDialog;
    private TextView tvThreadTitle, tvThreadBody, tvUsername, tvDate, tvVote;
    private List<Thread> myThread = new ArrayList<Thread>();

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
        setContentView(R.layout.activity_show_forum);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        layout = (LinearLayout)findViewById(R.id.linPost);

        tvThreadTitle = (TextView)findViewById(R.id.tvThreadTitle);
        tvThreadBody = (TextView)findViewById(R.id.tvThreadBody);
        tvUsername = (TextView)findViewById(R.id.tvUsername);
        tvDate = (TextView)findViewById(R.id.tvDate);
        tvVote = (TextView)findViewById(R.id.tvVote);
    }

    @Override
    protected void onResume() {
        super.onResume();
        myThread.clear();
        layout.removeAllViews();
        String type = "retrthread";
        bgWorkerThread = new BgWorkerThread(this);
        bgWorkerThread.delegate = this;
        bgWorkerThread.execute(type, threadID);
    }

    private void changeThreadInfo(String threadTitle, String date, String threadBody, String username, String votes)
    {
        tvThreadTitle.setText(threadTitle);
        tvThreadBody.setText(threadBody);
        tvUsername.setText(username);
        tvDate.setText(date);
        tvVote.setText(votes);
    }

    private void populateThreadList(String postBody, String postVote, String postUsername, String postDate)
    {
        myThread.add(new Thread(postBody, postVote, R.drawable.avatar_profile, postUsername, postDate));
    }

    private void populateListView()
    {
        LayoutInflater inflater = LayoutInflater.from(this);
        for (int i=0; i<myThread.size(); i++)
        {
            View view  = inflater.inflate(R.layout.thread_item, layout, false);
            Thread currentThread = myThread.get(i);

            ImageView imageView = (ImageView)view.findViewById(R.id.ivPostIcon);
            imageView.setImageResource(currentThread.getIconID());

            TextView postBodyText = (TextView)view.findViewById(R.id.tvPostBody);
            postBodyText.setText(currentThread.getPostBody());

            TextView postDateText = (TextView)view.findViewById(R.id.tvPostDate);
            postDateText.setText(currentThread.getPostDate());

            TextView postUsernameText = (TextView)view.findViewById(R.id.tvPostUsername);
            postUsernameText.setText(currentThread.getPostUsername());

            TextView postVoteText = (TextView)view.findViewById(R.id.tvPostVotes);
            postVoteText.setText(currentThread.getPostVote());

            layout.addView(view);
        }
    }

    private class MyListAdapter extends ArrayAdapter<Thread>
    {

        public MyListAdapter()
        {
            super(ShowForumActivity.this, R.layout.thread_item, myThread);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.thread_item, parent, false);
            }

            Thread currentThread = myThread.get(position);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.ivPostIcon);
            imageView.setImageResource(currentThread.getIconID());

            TextView postBodyText = (TextView) itemView.findViewById(R.id.tvPostBody);
            postBodyText.setText(currentThread.getPostBody());

            TextView postDateText = (TextView) itemView.findViewById(R.id.tvPostDate);
            postDateText.setText(currentThread.getPostDate());

            TextView postUsernameText = (TextView) itemView.findViewById(R.id.tvPostUsername);
            postUsernameText.setText(currentThread.getPostUsername());

            TextView postVoteText = (TextView) itemView.findViewById(R.id.tvPostVotes);
            postVoteText.setText(currentThread.getPostVote());

            return itemView;
        }
    }

    public void processFinish(String output)
    {
        BufferedReader bufferedReader = new BufferedReader(new StringReader(output));
        String line = "";
        try
        {
            int i = 0;
            int check = 0;
            String splitString [] = new String[5];
            while ((line = bufferedReader.readLine()) != null)
            {
                splitString[i] = line;
                i++;
                if(i == 5)
                {
                    if(check == 0)
                    {
                        changeThreadInfo(splitString[0], splitString[1], splitString[2], splitString[3], splitString[4]);
                        check++;
                    }
                    else
                        populateThreadList(splitString[2], splitString[4], splitString[3], splitString[1]);
                    i = 0;
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        populateListView();
    }

    public void onAddPostClick(View view)
    {
        Intent intent = new Intent(this, AddPostActivity.class);
        intent.putExtra("threadID", threadID);
        startActivity(intent);
    }

}

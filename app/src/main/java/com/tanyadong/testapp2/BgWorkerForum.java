package com.tanyadong.testapp2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by michaelknight123 on 2/25/2016.
 */
public class BgWorkerForum extends AsyncTask<String,Void,String>
{
    public AsyncResponse delegate = null;
    Context context;
    AlertDialog alertDialog;
    SessionManager session;

    BgWorkerForum(Context context)
    {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params)
    {
        String type = params[0];
        String post_url = "http://128.199.182.254/android_login_api/postforum.php";
        String retr_url = "http://128.199.182.254/android_login_api/retrforum.php";

        if(type.equals("postforum"))
        {
            try
            {
                session = new SessionManager(this.context);
                String threadTitle = params[1];
                String threadContent = params[2];
                String userID = params[3];
                String categoryName = params[4];
                URL url = new URL(post_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("threadTitle", "UTF-8") + "=" + URLEncoder.encode(threadTitle, "UTF-8") + "&"
                        + URLEncoder.encode("postBody", "UTF-8") + "=" + URLEncoder.encode(threadContent, "UTF-8") + "&"
                        + URLEncoder.encode("userId", "UTF-8") + "=" + URLEncoder.encode(userID, "UTF-8") + "&"
                        + URLEncoder.encode("categoryName", "UTF-8") + "=" + URLEncoder.encode(categoryName, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while((line = bufferedReader.readLine()) != null)
                {
                    result += line;
                }
                Log.i("Result: ", result);
                if(!result.contains("Error") || !result.contains("error"))
                {
                    result = "Thread successfully posted!";
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            }
            catch(MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        else if(type.equals("retrforum"))
        {
            try
            {
                String categoryName = params[1];
                URL url = new URL(retr_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("categoryName", "UTF-8") + "=" + URLEncoder.encode(categoryName, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while((line = bufferedReader.readLine()) != null)
                {
                    result += line;
                    result += "\n";
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            }
            catch(MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result)
    {
        delegate.processFinish(result);
    }
}

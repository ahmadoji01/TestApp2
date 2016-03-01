package com.tanyadong.testapp2;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

/**
 * Created by michaelknight123 on 2/19/2016.
 */
public class MenuActivity extends AppCompatActivity
{

    TextView tvKonsultasi, tvEvent, tvProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvKonsultasi = (TextView)findViewById(R.id.tvKonsultasi);
        tvEvent = (TextView)findViewById(R.id.tvEvent);
        tvProfile = (TextView)findViewById(R.id.tvProfile);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/bariolregular.otf");
        tvKonsultasi.setTypeface(typeface);
        tvEvent.setTypeface(typeface);
        tvProfile.setTypeface(typeface);
    }

    public void onKonsultasiClick(View view)
    {
        startActivity(new Intent(this, CategoryActivity.class));
    }

    public void onProfileClick(View view)
    {
        startActivity(new Intent(this, ShowActivity.class));
    }


}

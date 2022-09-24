package com.techsof.demoapp;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Splash_Screen extends AppCompatActivity {
    private  static  int SPLASH_TIME_OUT = 3000 ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);
        ImageView img = (ImageView) findViewById(R.id.imageView2);

        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.parseColor("#f0f5f9")));
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setElevation(0);


        new Handler().postDelayed(new Runnable() {
            @Override

            public void run() {
                Intent homeIntent = new Intent(Splash_Screen.this,MainActivity.class) ;
                Splash_Screen.this.startActivity(homeIntent);
                Splash_Screen.this.finish();

            }
        },SPLASH_TIME_OUT);


    }
    }


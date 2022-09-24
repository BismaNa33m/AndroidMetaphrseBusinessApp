package com.techsof.demoapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Impressum extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout eDrawerlayout;
    private ActionBarDrawerToggle eToggle;
    NavigationView nav;
    TextView t3,t4;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_impressum);

        nav = (NavigationView) findViewById(R.id.navigation1);

        t3 = (TextView)  findViewById(R.id.textView30);
        t4 = (TextView)  findViewById(R.id.textView39);
        eDrawerlayout = (DrawerLayout) findViewById(R.id.drawer1);
        eToggle = new ActionBarDrawerToggle(this, eDrawerlayout, R.string.Open, R.string.Close);
        eDrawerlayout.addDrawerListener(eToggle);
        eToggle.syncState();
        nav.setNavigationItemSelectedListener(this);
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.parseColor("#f0f5f9")));
        eDrawerlayout.setDrawerListener(eToggle);

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);

        t3.setText(Html.fromHtml(getResources().getString(R.string.txtreferen)));
        t4.setText(Html.fromHtml(getResources().getString(R.string.txtreferenc)));

    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(eToggle.onOptionsItemSelected(item))
        {

        }

        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();


        switch (id) {
            case R.id.home:
                startActivity(new Intent(Impressum.this, MainActivity.class));

                break;

            case R.id.web:
                Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://metaphrase.online"));
                startActivity(browser);
                break;

            case R.id.help:
                startActivity(new Intent(Impressum.this, Feedback.class));
                break;
            case R.id.upload:


                if(isConnected())
                {



                    startActivity(new Intent(Impressum.this, Auftrag.class));

                }
                else
                {
                    Toast.makeText(this, "Bitte überprüfen Sie Ihre Internetverbindung!", Toast.LENGTH_SHORT)
                            .show();

                }

                break;



        }
        eDrawerlayout.closeDrawer(GravityCompat.START);
        return true;

    }

    public void onClickLink(View view)
    {
        Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ec.europa.eu/consumers/odr/main/index.cfm?event=main.home.chooseLanguage"));
        startActivity(browser);
    }
    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }

}

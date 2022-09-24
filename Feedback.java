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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Feedback extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout eDrawerlayout;
    private ActionBarDrawerToggle eToggle;
    NavigationView nav;
    TextView text ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        nav = (NavigationView) findViewById(R.id.navigation1);
        TextView text = (TextView) findViewById(R.id.textView28);

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
                startActivity(new Intent(Feedback.this, MainActivity.class));

                break;

            case R.id.web:
                Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://metaphrase.online"));
                startActivity(browser);
                break;
            case R.id.help:
                startActivity(new Intent(Feedback.this, Feedback.class));
                break;
            case R.id.upload:

                if(isConnected())
                {



                    startActivity(new Intent(Feedback.this, Auftrag.class));

                }
                else
                {

                    Toast.makeText(this, "Bitte überprüfen Sie Ihre Internetverbindung!", Toast.LENGTH_SHORT)
                            .show();

                }


        }
        eDrawerlayout.closeDrawer(GravityCompat.START);
        return true;
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

    public void ClickAg(View view)
    {

        startActivity(new Intent(Feedback.this, AGB.class));
    }

    public void ClickDatenz(View view)
    {
        startActivity(new Intent(Feedback.this,Datenz.class));
    }
    public void ClickAuf(View view)
    {
        if(isConnected())
        {
            startActivity(new Intent(Feedback.this,Auftrag.class));
        }
        else
        {
            Toast.makeText(this, "Bitte überprüfen Sie Ihre Internetverbindung!", Toast.LENGTH_SHORT)
                    .show();
        }

    }
    public void ClickCont(View  view)
    {
        startActivity(new Intent(Feedback.this,Contact_us.class));
    }
    public void Clickmail(View view)
    {
        if(isConnected()) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"service@metaphrase.net"});
            intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
            intent.putExtra(Intent.EXTRA_TEXT, "mail body");
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Bitte überprüfen Sie Ihre Internetverbindung!", Toast.LENGTH_SHORT)
                    .show();

        }
    }




}


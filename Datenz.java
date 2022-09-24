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

public class Datenz extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    private DrawerLayout eDrawerlayout;
    private ActionBarDrawerToggle eToggle;
    NavigationView nav;
    TextView t1, t2 , t3,t4 , t5 , t6 , t7 , t8 , t9 , t10 , t11 , t12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datenz);
        nav = (NavigationView) findViewById(R.id.navigation1);

        t1 = (TextView)  findViewById(R.id.textView66);
        t2 = (TextView)  findViewById(R.id.textView68);

        t3 = (TextView)  findViewById(R.id.textView72);
        t4 = (TextView)  findViewById(R.id.textView74);

        t5 = (TextView)  findViewById(R.id.textView76);
        t6 = (TextView)  findViewById(R.id.textView78);

        t7 = (TextView)  findViewById(R.id.textView82);
        t8 = (TextView)  findViewById(R.id.textView84);

        t9 = (TextView)  findViewById(R.id.textView86);
        t10 = (TextView)  findViewById(R.id.textView100);

        t11 = (TextView)  findViewById(R.id.textView104);
        t12 = (TextView)  findViewById(R.id.textView105);


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

        t1.setText(Html.fromHtml(getResources().getString(R.string.fblink)));
        t2.setText(Html.fromHtml(getResources().getString(R.string.linkg)));
        t3.setText(Html.fromHtml(getResources().getString(R.string.klin)));
        t4.setText(Html.fromHtml(getResources().getString(R.string.lopa)));
        t5.setText(Html.fromHtml(getResources().getString(R.string.kiplan)));
        t6.setText(Html.fromHtml(getResources().getString(R.string.fink)));
        t7.setText(Html.fromHtml(getResources().getString(R.string.molp)));
        t8.setText(Html.fromHtml(getResources().getString(R.string.poli)));
        t9.setText(Html.fromHtml(getResources().getString(R.string.sunati)));
        t10.setText(Html.fromHtml(getResources().getString(R.string.siopi)));
        t11.setText(Html.fromHtml(getResources().getString(R.string.refce)));
        t12.setText(Html.fromHtml(getResources().getString(R.string.otoman)));







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
                startActivity(new Intent(Datenz.this, MainActivity.class));

                break;

            case R.id.web:
                Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://metaphrase.online"));
                startActivity(browser);
                break;

            case R.id.help:
                startActivity(new Intent(Datenz.this, Feedback.class));
                break;
            case R.id.upload:

                if(isConnected())
                {


                    startActivity(new Intent(Datenz.this, Auftrag.class));


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

    public void onClickfbl(View view)
    {
        Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://developers.facebook.com/docs/plugins/?locale=de_DE"));
        startActivity(browser);
    }
    public void onClickLinko(View view)
    {
        Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://de-de.facebook.com/about/privacy/"));
        startActivity(browser);
    }
    public void onClickLinlo(View view)
    {
        Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://tools.google.com/dlpage/"));
        startActivity(browser);
    }
    public void onClickLinro(View view)
    {
        Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.de/intl/de/policies/privacy/"));
        startActivity(browser);
    }

    public void onClickLinmo(View view)
    {
        Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com/analytics/terms/de.html"));
        startActivity(browser);
    }
    public void onClickLinka(View view)
    {
        Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse(" https://www.google.com/intl/de_de/analytics/"));
        startActivity(browser);
    }
    public void onClickLinta(View view)
    {
        Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse(" https://developers.google.com/+/ "));
        startActivity(browser);
    }
    public void onClickLinga(View view)
    {
        Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse(" https://www.google.de/intl/de/policies/privacy/ "));
        startActivity(browser);
    }

    public void onClickLinya(View view)
    {
        Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse(" https://developers.google.com/+/web/buttons-policyabgerufen  "));
        startActivity(browser);
    }

    public void onClickLinkop(View view)
    {
        Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse(" https://www.google.de/intl/de/policies/privacy/  "));
        startActivity(browser);
    }

    public void onClickLinkopi(View view)
    {
        Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse(" https://help.instagram.com/155833707900388und   "));
        startActivity(browser);
    }

    public void onClickLinkoto(View view)
    {
        Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/about/legal/privacy/ "));
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



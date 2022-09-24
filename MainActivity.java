package com.techsof.demoapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



import java.io.File;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout eDrawerlayout;
    private ActionBarDrawerToggle eToggle;
    NavigationView nav;
    private ArrayList<Uri> attachments = new ArrayList<>();
    private Context context;
    private File file;
    TextView one;
    TextView five;
    TextView three;
    TextView four;

    private Uri filePath;
    private Bitmap bitmap;
    private static final int Pick_Req = 1;
    Menu menu;
    ImageView img;
    Animation animbounce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView one = (TextView) findViewById(R.id.textView2);
        final TextView five = (TextView) findViewById(R.id.textView5);
        final TextView three = (TextView) findViewById(R.id.textView3);
        final TextView four = (TextView) findViewById(R.id.textview4);
        ImageView img = (ImageView) findViewById(R.id.imageView);


        nav = (NavigationView) findViewById(R.id.navigation1);


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


        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        Bounce_interpolator interpolator = new Bounce_interpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);


        img.startAnimation(myAnim);







    }



    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (eToggle.onOptionsItemSelected(item)) {

        }

        return true;
    }


    public void onClickLan(View view) {

        TextView two = (TextView) findViewById(R.id.textView2);
        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.animation2);
        two.startAnimation(animation1);
        startActivity(new Intent(MainActivity.this, Languages.class));
    }

    public void onClickPrices(View view) {

        TextView three = (TextView) findViewById(R.id.textView3);
        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.animation2);
        three.startAnimation(animation1);
        startActivity(new Intent(MainActivity.this,Prices.class));

    }


    public void onClickUber(View view) {

        TextView five = (TextView) findViewById(R.id.textView5);
        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.animation2);
        five.startAnimation(animation1);


        startActivity(new Intent(MainActivity.this, contactus.class));
    }



    public void onClickService(View view) {

        TextView four = (TextView) findViewById(R.id.textView4);
        Animation animation1 =
                AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.animation2);
        four.startAnimation(animation1);

        startActivity(new Intent(MainActivity.this, Service.class));
    }

    public void onClickimg(View view)
    {

        ImageView img = (ImageView) findViewById(R.id.imageView);

        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.anim3);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        Bounce_interpolator interpolator = new Bounce_interpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);


        img.startAnimation(myAnim);
    }


    public void onClickkontacti(View view) {
        startActivity(new Intent(MainActivity.this, Contact_us.class));
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        int id = item.getItemId();


        switch (id) {
            case R.id.home:
                startActivity(new Intent(MainActivity.this, MainActivity.class));

                break;

            case R.id.web:
                Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://metaphrase.online"));
                startActivity(browser);
                break;

            case R.id.help:
                startActivity(new Intent(MainActivity.this, Feedback.class));
                break;

            case R.id.upload:
                if (isConnected()) {

                    startActivity(new Intent(MainActivity.this, Auftrag.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Bitte überprüfen Sie Ihre Internetverbindung!", Toast.LENGTH_SHORT).show();
                }


                break;


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




}
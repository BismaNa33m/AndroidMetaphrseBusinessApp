package com.techsof.demoapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;


import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.google.android.material.navigation.NavigationView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;






public class Auftrag extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    ProgressDialog pdiaglog = null;




    private DrawerLayout eDrawerlayout;
    private ActionBarDrawerToggle eToggle;
    NavigationView nav;

    //    private static final int REQUEST_CODE = 101;
    private Button attachBtn1, attachBtn2, attachBtn3, submitButton;
    private TextView file1, file2, file3;
    private EditText name, editTextfromEmail, phone_no, body;
    private ArrayList<Uri> attachments = new ArrayList<>();
    private static final int attachment1 = 101, attachment2 = 102, attachment3 = 103;
    private Context context = null;
    private File file;

    WebView mwebView;
    private ValueCallback<Uri> mUploadMessage;
    public ValueCallback<Uri[]> uploadMessage;
    public static final int REQUEST_SELECT_FILE = 100;
    private final static int FILECHOOSER_RESULTCODE = 1;





    @SuppressLint({"SetJavaScriptEnabled", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auftrag);
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

        mwebView = (WebView) findViewById(R.id.webview);
        if (Build.VERSION.SDK_INT >= 23 && (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)) {

            ActivityCompat.requestPermissions(Auftrag.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1);

        }

        assert mwebView != null;
        WebSettings webSettings = mwebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webSettings.setAllowFileAccess(true);

        mwebView.getSettings().setSupportZoom(true);

        mwebView.getSettings().setBuiltInZoomControls(true);


        mwebView.loadUrl("https://www.metaphrase.online/kontaktt.php");

//
        mwebView.setWebChromeClient(new WebChromeClient() {
            // For 3.0+ Devices (Start)
            // onActivityResult attached before constructor
            protected void openFileChooser(ValueCallback uploadMsg, String acceptType) {
                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                startActivityForResult(Intent.createChooser(i, "File Browser"), FILECHOOSER_RESULTCODE);
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            public boolean onShowFileChooser(WebView mWebView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                if (uploadMessage != null) {
                    uploadMessage.onReceiveValue(null);
                    uploadMessage = null;
                }

                uploadMessage = filePathCallback;

                Intent intent = fileChooserParams.createIntent();
                try {
                    startActivityForResult(intent, REQUEST_SELECT_FILE);
                } catch (ActivityNotFoundException e) {
                    uploadMessage = null;
                    Toast.makeText(getApplicationContext(), "Cannot Open File Chooser", Toast.LENGTH_LONG).show();
                    return false;
                }
                return true;
            }

            protected void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                mUploadMessage = uploadMsg;
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "File Browser"), FILECHOOSER_RESULTCODE);
            }

            protected void openFileChooser(ValueCallback<Uri> uploadMsg) {
                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);
            }
        });//

    }


    //For Android 3.0+


    // Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (eToggle.onOptionsItemSelected(item)) {

        }

        return true;
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();


        switch (id) {
            case R.id.home:
                startActivity(new Intent(Auftrag.this, MainActivity.class));

                break;

            case R.id.web:
                Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://metaphrase.online"));
                startActivity(browser);
                break;

            case R.id.help:
                startActivity(new Intent(Auftrag.this, Feedback.class));
                break;
            case R.id.upload:
                if (isConnected()) {

                    startActivity(new Intent(Auftrag.this, Auftrag.class));


                } else {
                    Toast.makeText(this, "Bitte überprüfen Sie Ihre Internetverbindung!", Toast.LENGTH_SHORT)
                            .show();
                }
                break;


        }
        eDrawerlayout.closeDrawer(GravityCompat.START);
        return true;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            if (requestCode == REQUEST_SELECT_FILE)
            {
                if (uploadMessage == null)
                    return;
                uploadMessage.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, intent));
                uploadMessage = null;
            }
        }
        else if (requestCode == FILECHOOSER_RESULTCODE)
        {
            if (null == mUploadMessage)
                return;
            // Use MainActivity.RESULT_OK if you're implementing WebView inside Fragment
            // Use RESULT_OK only if you're implementing WebView inside an Activity
            Uri result = intent == null || resultCode != MainActivity.RESULT_OK ? null : intent.getData();
            mUploadMessage.onReceiveValue(result);
            mUploadMessage = null;
        }
        else
            Toast.makeText(getApplicationContext(), "Failed to Upload Image", Toast.LENGTH_LONG).show();
    }


    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }





}

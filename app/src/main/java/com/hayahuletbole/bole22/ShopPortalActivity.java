package com.hayahuletbole.bole22;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

//TODO create a repository on GitLab and push it on to there
//TODO accurately comment the entire source code so as to help your self and the next programmer to come along
//TODO perhaps add a README.md file to help guide & organize the whole development & developers

public class ShopPortalActivity extends AppCompatActivity {
    private static final String SHOP_ADDRESS = "http://www.22bole.com";
    private WebView shopWebView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        shopWebView = new WebView(this);
        setContentView(shopWebView);

        shopWebView.loadUrl(SHOP_ADDRESS);
        WebSettings webSettings = shopWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        shopWebView.setWebViewClient(new WebViewClient());
        shopWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            WebView.setWebContentsDebuggingEnabled(true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && shopWebView.canGoBack()) {
            shopWebView.goBack();
            return true;
        }

        //If it wasn't the Back key or there's no web page history, bubble up to the default
        //system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        //check if the key event was the forward button and if
        // there's a path forward with in the history
        if ((keyCode == KeyEvent.KEYCODE_FORWARD) && shopWebView.canGoForward()) {
            shopWebView.goForward();
            return true;
        }

        //if it wasn't the forward button or there's no web page history, bubble up to the
        //default system behavior
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.jobs_activity:
                Intent jobIntent = new Intent(this, JobsPortalActivity.class);
                startActivity(jobIntent);
                return true;
            case R.id.business_portal_activity:
                Intent businessPortalIntent = new Intent(this, BusinessPortalActivity.class);
                startActivity(businessPortalIntent);
                return true;
            case R.id.refresh:
                shopWebView.reload();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

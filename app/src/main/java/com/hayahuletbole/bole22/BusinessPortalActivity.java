package com.hayahuletbole.bole22;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

//TODO accurately comment the entire source code so as to help your self and the next programmer to come along
//TODO perhaps add a README.md file to help guide & organize the whole development & developers

public class BusinessPortalActivity extends AppCompatActivity {
    private static final String BUSINESS_PORTAL_ADDRESS = "http://www.22bole.com/businessportal";
    private WebView businessPortalWebView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        businessPortalWebView = new WebView(this);
        setContentView(businessPortalWebView);

        businessPortalWebView.loadUrl(BUSINESS_PORTAL_ADDRESS);
        WebSettings webSettings = businessPortalWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        businessPortalWebView.setWebViewClient(new WebViewClient());
        businessPortalWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            WebView.setWebContentsDebuggingEnabled(true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && businessPortalWebView.canGoBack()) {
            businessPortalWebView.goBack();
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
        if ((keyCode == KeyEvent.KEYCODE_FORWARD) && businessPortalWebView.canGoForward()) {
            businessPortalWebView.goForward();
            return true;
        }

        //if it wasn't the forward button or there's no web page history, bubble up to the
        //default system behavior
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_jobs_business, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.refresh) {
            businessPortalWebView.reload();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

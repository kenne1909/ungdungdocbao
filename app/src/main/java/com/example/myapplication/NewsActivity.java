package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class NewsActivity extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        webView=(WebView) findViewById(R.id.webviewTinTuc);

        Intent intent= getIntent();
        String link= intent.getStringExtra("linkTicTuc");
        webView.loadUrl(link);

        // click vaof dudowngf dan k nhay ra app cua minh
        webView.setWebViewClient(new WebViewClient());


        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        //thực thi mã JavaScript
        webView.getSettings().setJavaScriptEnabled(true);

        webView.scrollTo(0, 0);
    }
}
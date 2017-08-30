package com.example.ipandaitems.view.video;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.example.ipandaitems.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoWebs extends AppCompatActivity {

    @BindView(R.id.video_back)
    ImageView videoBack;
    @BindView(R.id.video_web)
    WebView videoWeb;
    @BindView(R.id.shoucan)
    CheckBox shoucan;
    @BindView(R.id.denxiang)
    ImageView denxiang;
    private String web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videoweb);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        web = intent.getStringExtra(" web");
        WebSettings webSettings=videoWeb.getSettings();
        webSettings.setSupportZoom(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDisplayZoomControls(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        videoWeb.loadUrl(web);
        videoWeb.setWebChromeClient(new WebChromeClient());

    }


}

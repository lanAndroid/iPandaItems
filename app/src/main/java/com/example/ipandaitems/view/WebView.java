package com.example.ipandaitems.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ipandaitems.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebView extends AppCompatActivity {

    @BindView(R.id.web_finish)
    ImageView webFinish;
    @BindView(R.id.web_title)
    TextView webTitle;
    @BindView(R.id.webview)
    android.webkit.WebView webview;
    @BindView(R.id.shoucans)
    CheckBox shoucans;
    @BindView(R.id.fenxiang)
    CheckBox fenxiang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        String name = getIntent().getStringExtra("name");
        String url = getIntent().getStringExtra("url");
        webTitle.setText(name);


        WebSettings webSettings=webview.getSettings();
        webSettings.setSupportZoom(true);
        webSettings.setDisplayZoomControls(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        webview.loadUrl(url);
        webview.setWebChromeClient(new WebChromeClient());
        initView();
    }

    private void initView() {
        shoucans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shoucans.isChecked()){
                    Toast.makeText(WebView.this,"已收藏",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(WebView.this,"取消收藏",Toast.LENGTH_SHORT).show();
                }
            }
        });
        fenxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(WebView.this,"分享",Toast.LENGTH_SHORT).show();
            }
        });
    }
}

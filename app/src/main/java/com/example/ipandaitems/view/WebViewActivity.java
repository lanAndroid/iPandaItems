package com.example.ipandaitems.view;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ipandaitems.R;
import com.example.ipandaitems.base.BaseActivity;

import java.sql.SQLException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends BaseActivity {


    @BindView(R.id.web_finish)
    ImageView webFinish;
    @BindView(R.id.web_title)
    TextView webTitle;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.shoucans)
    CheckBox shoucans;
    @BindView(R.id.fenxiang)
    CheckBox fenxiang;

    @Override
    protected int layoutID() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initView() throws SQLException {
        ButterKnife.bind(this);
        String name = getIntent().getStringExtra("name");
        String url = getIntent().getStringExtra("url");
        webTitle.setText(name);
        webview.loadUrl(url);
    }

    @Override
    protected void loadData() {
        webFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initListener() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

package com.example.ipandaitems.view.video;

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
import com.example.ipandaitems.model.greendao.DaoMaster;
import com.example.ipandaitems.model.greendao.DaoSession;
import com.example.ipandaitems.model.greendao.User;
import com.example.ipandaitems.model.greendao.UserDao;

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
    private String name;
    private String url;
    private Long id;
    private String time;
    private String img;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "ur.db");
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        userDao = daoSession.getUserDao();
        name = getIntent().getStringExtra("name");
        url = getIntent().getStringExtra("url");
        id = getIntent().getLongExtra("id", 0);
        time = getIntent().getStringExtra("time");
        img = getIntent().getStringExtra("img");
        webTitle.setText(name);


        User user = userDao.queryBuilder().where(UserDao.Properties.Name.eq(name)).build().unique();
        if (user != null) {
            shoucans.setChecked(true);
        } else {
            shoucans.setChecked(false);
        }


        WebSettings webSettings = webview.getSettings();
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
                User user = userDao.queryBuilder().where(UserDao.Properties.Name.eq(name)).build().unique();
                if (user != null) {
                    userDao.deleteByKey(user.getId());
                    Toast.makeText(WebView.this, "已收藏", Toast.LENGTH_SHORT).show();
                } else {
                    userDao.insert(new User(null, img, name, time, url));
                    Toast.makeText(WebView.this, "取消收藏", Toast.LENGTH_SHORT).show();
                }
            }
        });
        fenxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(WebView.this, "分享", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

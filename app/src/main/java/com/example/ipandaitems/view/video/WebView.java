package com.example.ipandaitems.view.video;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.share.WbShareHandler;
import com.sina.weibo.sdk.utils.Utility;

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
    private WbShareHandler shareHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        String name = getIntent().getStringExtra("name");
        String url = getIntent().getStringExtra("url");
        webTitle.setText(name);


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
                if (shoucans.isChecked()) {
                    Toast.makeText(WebView.this, "已收藏", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(WebView.this, "取消收藏", Toast.LENGTH_SHORT).show();
                }
            }
        });
        fenxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(WebView.this, "分享", Toast.LENGTH_SHORT).show();
                initFenXiang();
            }
        });
    }

    private void initFenXiang() {
        shareHandler = new WbShareHandler(this);
        shareHandler.registerApp();
        WeiboMultiMessage multiMessage = new WeiboMultiMessage();
        multiMessage.mediaObject = getWebpageObj();
        shareHandler.shareMessage(multiMessage, false);
    }

    /**
     * 创建多媒体（网页）消息对象。
     *
     * @return 多媒体（网页）消息对象。
     */
    private WebpageObject getWebpageObj() {
        WebpageObject mediaObject = new WebpageObject();
        mediaObject.identify = Utility.generateGUID();
        mediaObject.title = "测试title";   //      这里填写想要分享的title
        mediaObject.description = "测试描述"; //      这里填写想要分享的描述
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.person_sign);
        // 设置 Bitmap 类型的图片到视频对象里         设置缩略图。 注意：最终压缩过的缩略图大小不得超过 32kb。
        mediaObject.setThumbImage(bitmap);
        mediaObject.actionUrl = "http://news.sina.com.cn/c/2013-10-22/021928494669.shtml"; //      这里填写想要分享的url
        mediaObject.defaultText = "Webpage 默认文案"; //      这里填写想要分享的默认语句
        return mediaObject;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        shareHandler.doResultIntent(intent, new com.sina.weibo.sdk.share.WbShareCallback() {
            @Override
            public void onWbShareSuccess() {
                Toast.makeText(WebView.this, "分享成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onWbShareCancel() {
                Toast.makeText(WebView.this, "取消分享", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onWbShareFail() {
                Toast.makeText(WebView.this, "分享失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

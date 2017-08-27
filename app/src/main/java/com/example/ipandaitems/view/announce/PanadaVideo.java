package com.example.ipandaitems.view.announce;

import android.content.Intent;
import android.os.Bundle;

import com.example.ipandaitems.R;
import com.example.ipandaitems.base.BaseActivity;

import java.sql.SQLException;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by 1 on 2017/8/26.
 */

public class PanadaVideo extends BaseActivity {
    @BindView(R.id.vita_video)
    VideoView vitaVideo;
    private String path;

    @Override
    protected int layoutID() {
        return R.layout.ann_panadavideo;
    }

    @Override
    protected void initView() throws SQLException {
        Intent intent = getIntent();
        path = intent.getStringExtra("path");
    }

    @Override
    protected void loadData() {
        vitaVideo.setVideoPath(path);
        vitaVideo.start();
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

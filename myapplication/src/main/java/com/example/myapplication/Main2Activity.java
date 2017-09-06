package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class Main2Activity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Vitamio.isInitialized(this);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        VideoView viewById = (VideoView) findViewById(R.id.video_surfaceView);
        viewById.setVideoURI(Uri.parse(url));//设置视频地址
        MediaController controller = new MediaController(this);
        viewById.setMediaController(controller);//设置Controller，注意一定要io.vov.vitamio.widget.MediaController
        controller.setMediaPlayer(viewById);//给Controller设置VideoView
        viewById.requestFocus();//获得焦点
        viewById.start();//开始播放
    }
}

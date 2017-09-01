package com.example.ipandaitems.view.livechina;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.ipandaitems.R;

import io.vov.vitamio.widget.VideoView;

public class DisplayActivity extends AppCompatActivity {

    private VideoView video_view;
    private ImageView app_video_play;
    private LinearLayout app_video_bottom_box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        initView();
    }

    private void initView() {
        video_view = (VideoView) findViewById(R.id.video_view);
        app_video_play = (ImageView) findViewById(R.id.app_video_play);
        app_video_bottom_box = (LinearLayout) findViewById(R.id.app_video_bottom_box);
        final String url = getIntent().getStringExtra("url");
//        app_video_play.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                app_video_play.setVisibility(View.GONE);
//                app_video_bottom_box.setVisibility(View.GONE);
//                Toast.makeText(DisplayActivity.this, "播放", Toast.LENGTH_SHORT).show();
//                video_view.setVideoURI(url);
//                video_view.setVideoQuality(MediaPlayer.VIDEOQUALITY_LOW);
//                video_view.requestFocus();
//                video_view.start();
//            }
//        });


    }
}

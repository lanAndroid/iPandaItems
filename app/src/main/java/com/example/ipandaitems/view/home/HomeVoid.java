package com.example.ipandaitems.view.home;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ipandaitems.R;


public class HomeVoid extends AppCompatActivity {
    //    VideoView v;
    String ss = "http://vod.cntv.lxdns.com/flash/mp4video61/TMS/2017/09/01/ddded68aa4c1430691ee0fb48c6118d1_h264418000nero_aac32.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_void);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }


        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

//        Log.e("这是播放器的网址",url+"空？");
//        v= (VideoView) findViewById(R.id.home_videoview);
//        MediaController controller = new MediaController(this);
//        v.setVideoURI(Uri.parse(url));
//        v.setMediaController(controller);
//        v.requestFocus();
//        v.start();

    }
}

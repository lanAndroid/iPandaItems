package com.example.ipandaitems.view.home;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.example.ipandaitems.R;

import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class HomeVoid extends AppCompatActivity {
    VideoView videoView;



    String ss="http://vod.cntv.lxdns.com/flash/mp4video61/TMS/2017/09/01/ddded68aa4c1430691ee0fb48c6118d1_h264418000nero_aac32.mp4";
    private String url;

    private RelativeLayout mMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题

        setContentView(R.layout.activity_home_void);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        android:screenOrientation="landscape"

        videoView= (VideoView) findViewById(R.id.home_videoview);
        mMain= (RelativeLayout) findViewById(R.id.relativelayout);


        mMain.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        Intent intent=getIntent();
        url = intent.getStringExtra("url");

        Log.e("这是播放器的网址", url +"空？");


String chian="http://3811.liveplay.myqcloud.com/live/flv/3811_channel1739.flv?AUTH=mSU2tMnq7k0NLpHj4jEaFevOVbq626heGilFAWMAWiKoW1diRhRN9eT3kSeaOi2/srX19XM3mfT0sacm5/Xuqw==fhgcdgm";
        String sss="//http://vdn.live.cntv.cn/api2/live.do?channel=pa://cctv_p2p_hdzjjmht&client=androidapp";

        String pandan="http://ipanda.vtime.cntv.cloudcdn.net:8000/live/flv/channel54?AUTH=jF5aA0p1k+NiC33CmBvd55wNlyeLIhF6n9J3cyZDWm+eLpu0zT355Q2m4vCSoXhSkpDgeRna36gsaksu0VlFHQ==";


        videoView.setVideoURI(Uri.parse(url));//设置视频地址
        MediaController controller = new MediaController(this);
        videoView.setMediaController(controller);//设置Controller，注意一定要io.vov.vitamio.widget.MediaController
         controller.setMediaPlayer(videoView);//给Controller设置VideoView
        videoView.requestFocus();//获得焦点
        //设置视频大小的，比如缩放，全屏，比例等
//        videoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
        videoView.start();//开始播放




    }
    @Override
    protected void onPause() {
        super.onPause();
        videoView.stopPlayback();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    protected void onResume() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


    }
        super.onResume();

    }
}

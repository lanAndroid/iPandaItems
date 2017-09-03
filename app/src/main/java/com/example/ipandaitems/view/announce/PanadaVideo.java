package com.example.ipandaitems.view.announce;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ipandaitems.R;
import com.example.ipandaitems.model.entry.AnnBean;
import com.example.ipandaitems.model.entry.PanadaBean;
import com.example.ipandaitems.model.entry.VideoBeanr;
import com.example.ipandaitems.presenter.annpresenter.AnnIPresenter;
import com.example.ipandaitems.presenter.annpresenter.AnnIPresenterImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class PanadaVideo extends AppCompatActivity implements AnnView {

    @BindView(R.id.vita_video)
    VideoView vitaVideo;
    @BindView(R.id.btu_start)
    ImageView btuStart;
    @BindView(R.id.iv)
    ImageView iv;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置成横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.ann_panadavideo);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        ButterKnife.bind(this);

        Log.d("haha", "调用设置全屏");


        AnnIPresenter annIPresenter = new AnnIPresenterImpl(this);
        Intent intent = getIntent();
        path = intent.getStringExtra("path");
        annIPresenter.videoGet(path);


    }

    @Override
    protected void onPause() {
        super.onPause();
        vitaVideo.stopPlayback();
    }

    //    private void setFullScreen() {
//        RelativeLayout.LayoutParams layoutParams1 = new RelativeLayout.LayoutParams(
//                RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
//        vitaVideo.setLayoutParams(layoutParams1);
//    }

    @Override
    public void onSuccess(AnnBean annBean) {

    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void panadaSuccess(PanadaBean panadaBean) {

    }

    @Override
    public void videoSuccess(VideoBeanr videoBeanr) {
        VideoBeanr.VideoBean video = videoBeanr.getVideo();
        List<VideoBeanr.VideoBean.ChaptersBean> chapters = video.getChapters();
        final String url = chapters.get(0).getUrl();

        btuStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PanadaVideo.this,"你点击了",Toast.LENGTH_SHORT).show();
                    iv.setVisibility(View.GONE);
                btuStart.setVisibility(View.GONE);
                if (Vitamio.isInitialized(PanadaVideo.this)) {
//            vitaVideo.setVisibility(View.GONE);
                    vitaVideo.setVideoURI(Uri.parse(url));//设置视频地址
                    MediaController controller = new MediaController(PanadaVideo.this);
                    vitaVideo.setMediaController(controller);//设置Controller，注意一定要io.vov.vitamio.widget.MediaController
                    //   controller.setMediaPlayer(vita);//给Controller设置VideoView
                    vitaVideo.requestFocus();//获得焦点
                    //设置视频大小的，比如缩放，全屏，比例等
                    vitaVideo.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
                    vitaVideo.start();//开始播放
                }
            }
        });
    }
}

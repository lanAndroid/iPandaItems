package com.example.ipandaitems.view.announce;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ipandaitems.R;
import com.example.ipandaitems.base.BaseActivity;
import com.example.ipandaitems.model.entry.AnnBean;
import com.example.ipandaitems.model.entry.PanadaBean;
import com.example.ipandaitems.model.entry.VideoBeanr;
import com.example.ipandaitems.presenter.annpresenter.AnnIPresenterImpl;
import com.example.ipandaitems.utils.UrlUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.sql.SQLException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by 1 on 2017/8/25.
 */

class PanadaTop extends BaseActivity implements AnnView {
    @BindView(R.id.ti)
    TextView ti;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.listzankai)
    CheckBox listzankai;
    @BindView(R.id.panadaContent)
    TextView panadaContent;
    @BindView(R.id.web_finish)
    ImageView webFinish;
    @BindView(R.id.toptitle)
    TextView toptitle;
    @BindView(R.id.panadarecy)
    XRecyclerView panadarecy;
    @BindView(R.id.vita)
    VideoView vita;
    private AnnIPresenterImpl annIPresenter;
    private int page = 1;
    private String url;


    @Override
    protected int layoutID() {
        return R.layout.ann_panadatop;
    }

    @Override
    protected void initView() throws SQLException {

    }

    @Override
    protected void loadData() {
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String sid = intent.getStringExtra("sid");
        System.out.println(id + "------------------------------");
        url = UrlUtils.GNNGNNVIDEO + "vsid=" + id + "&n=7&serviceId=panda&o=desc&of=time&p=";
        //视频拼接
        annIPresenter = new AnnIPresenterImpl(this);
        String s = UrlUtils.VIDEO_URL + sid;
        System.out.println(s + "??????");
        annIPresenter.panadaGet(url + page);
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

    @Override
    public void onSuccess(AnnBean annBean) {

    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void panadaSuccess(PanadaBean panadaBean) {
        final List<PanadaBean.VideoBean> video = panadaBean.getVideo();
        PanadaBean.VideosetBean videoset = panadaBean.getVideoset();
        PanadaBean.VideosetBean._$0Bean videoset_$0 = videoset.get_$0();

        String name = videoset_$0.getName();
        toptitle.setText(name);

        String desc = videoset_$0.getDesc();
        panadaContent.setText(desc);

        listzankai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listzankai.isChecked()) {
                    panadaContent.setVisibility(View.VISIBLE);
                } else {
                    panadaContent.setVisibility(View.GONE);
                }
            }
        });
        panadarecy.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        Panadapter adaper = new Panadapter(PanadaTop.this, video);
        panadarecy.setAdapter(adaper);
        panadarecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                video.clear();
                annIPresenter.panadaGet(url + page);
                panadarecy.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        annIPresenter.panadaGet(url + page);
                        panadarecy.loadMoreComplete();
                    }
                }, 2000);

            }
        });
    }

    @Override
    public void videoSuccess(VideoBeanr videoBeanr) {

        VideoBeanr.VideoBean video = videoBeanr.getVideo();
        List<VideoBeanr.VideoBean.Chapters3Bean> chapters3 = video.getChapters3();



        //VideoView是否准备好 的回调方法

        for (int i = 0; i < chapters3.size(); i++) {
            String url = chapters3.get(i).getUrl();
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            vita.setVideoURI(Uri.parse(url));
            vita.start();
        }
    }
}

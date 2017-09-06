package com.example.ipandaitems.view.announce;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.chanven.lib.cptr.recyclerview.RecyclerAdapterWithHF;
import com.example.ipandaitems.R;
import com.example.ipandaitems.base.BaseActivity;
import com.example.ipandaitems.model.entry.AnnBean;
import com.example.ipandaitems.model.entry.PanadaBean;
import com.example.ipandaitems.model.entry.VideoBeanr;
import com.example.ipandaitems.presenter.annpresenter.AnnIPresenter;
import com.example.ipandaitems.presenter.annpresenter.AnnIPresenterImpl;
import com.example.ipandaitems.utils.UrlUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.sql.SQLException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.utils.Log;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

import static java.security.AccessController.getContext;


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
    @BindView(R.id.video_start)
    ImageView videoStart;
    @BindView(R.id.vita)
    VideoView vita;
    @BindView(R.id.panadashoucan)
    CheckBox panadashoucan;
    @BindView(R.id.panadafenxiang)
    ImageView panadafenxiang;
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
        String s = UrlUtils.VIDEO_URL + id;
        System.out.println(s + "??????");
        annIPresenter.panadaGet(url + page);
    }

    @Override
    protected void initListener() {
        panadashoucan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (panadashoucan.isChecked()){
                    Toast.makeText(PanadaTop.this,"已经收藏",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(PanadaTop.this,"取消收藏",Toast.LENGTH_SHORT).show();
                }
            }
        });
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        final int width = metric.widthPixels;     // 屏幕宽度（像素）
        final int height = metric.heightPixels;   // 屏幕高度（像素）
        final View inflate = View.inflate(PanadaTop.this, R.layout.videodenxiang, null);
        TextView tv = inflate.findViewById(R.id.sina);
        TextView wxtv = inflate.findViewById(R.id.wx);
        TextView py = inflate.findViewById(R.id.penyouquan);


        Button btn_quxiao = inflate.findViewById(R.id.btn_quxiao);
        final PopupWindow popupWindow = new PopupWindow(width,height/3);
        btn_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        panadafenxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                popupWindow.setContentView(inflate);
                popupWindow.setFocusable(true);

                popupWindow.setOutsideTouchable(true);
                // 设置popupWindow的显示位置，此处是在手机屏幕底部且水平居中的位置
                popupWindow.showAtLocation(inflate, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });
        webFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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

        String vid = panadaBean.getVideo().get(0).getVid();
        String s = UrlUtils.VIDEO_URL + vid;
        annIPresenter.videoGet(s);
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
        RecyclerAdapterWithHF myadapter = new RecyclerAdapterWithHF((RecyclerView.Adapter) adaper);
        panadarecy.setAdapter(myadapter);
        //item点击事件
        myadapter.setOnItemClickListener(new RecyclerAdapterWithHF.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerAdapterWithHF adapter, RecyclerView.ViewHolder vh, final int position) {
                String vid = video.get(position).getVid();
                String s = UrlUtils.VIDEO_URL + vid;
                //annIPresenter.videoGet(s);
                Toast.makeText(PanadaTop.this, "你麻皮", Toast.LENGTH_SHORT).show();
                AnnIPresenter annIPresenter = new AnnIPresenterImpl(PanadaTop.this);
                annIPresenter.videoGet(s);
            }
        });
        //XRecycleView的上拉加载下拉刷新
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
    protected void onDestroy() {
        super.onDestroy();
        vita.stopPlayback();
    }

    @Override
    public void videoSuccess(VideoBeanr videoBeanr) {

        VideoBeanr.VideoBean video = videoBeanr.getVideo();

        List<VideoBeanr.VideoBean.ChaptersBean> chapters3 = video.getChapters();


        //VideoView是否准备好 的回调方法
        final String urll = videoBeanr.getVideo().getChapters().get(0).getUrl();

        Log.e("---------------->", urll);
        for (int i = 0; i < chapters3.size(); i++) {
            String image = chapters3.get(i).getImage();
            //  String url = chapters3.get(i).getUrl();
        }
        //自动播放
        if (Vitamio.isInitialized(PanadaTop.this)) {
            videoStart.setVisibility(View.GONE);
            vita.setVideoURI(Uri.parse(urll));//设置视频地址
            MediaController controller = new MediaController(PanadaTop.this);
            vita.setMediaController(controller);//设置Controller，注意一定要io.vov.vitamio.widget.MediaController
            //   controller.setMediaPlayer(vita);//给Controller设置VideoView
            vita.requestFocus();//获得焦点
            vita.start();//开始播放

            Toast.makeText(PanadaTop.this, "1111", Toast.LENGTH_SHORT).show();
        }
        //点击播放
//            videoStart.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if ( Vitamio.isInitialized(PanadaTop.this)) {
//                        videoStart.setVisibility(View.GONE);
//                        vita.setVideoURI(Uri.parse(urll));//设置视频地址
//                        MediaController controller = new MediaController(PanadaTop.this);
//                        vita.setMediaController(controller);//设置Controller，注意一定要io.vov.vitamio.widget.MediaController
//                        //   controller.setMediaPlayer(vita);//给Controller设置VideoView
//                        vita.requestFocus();//获得焦点
//                    vita.start();//开始播放
//
//                    Toast.makeText(PanadaTop.this, "1111", Toast.LENGTH_SHORT).show();
//                    }else {
//                        Toast.makeText(PanadaTop.this,"没有初始化vitamio",Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });

    }
}

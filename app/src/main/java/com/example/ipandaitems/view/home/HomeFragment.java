package com.example.ipandaitems.view.home;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ipandaitems.R;
import com.example.ipandaitems.base.BaseFragment;
import com.example.ipandaitems.model.entry.home.HomeBean;
import com.example.ipandaitems.model.entry.home.HomeChianVideo;
import com.example.ipandaitems.model.entry.home.HomeMarvellBean;
import com.example.ipandaitems.model.entry.home.HomePandanVideo;
import com.example.ipandaitems.model.entry.home.HomeRollVideo;
import com.example.ipandaitems.model.entry.home.HomeRollingBean;
import com.example.ipandaitems.presenter.homepresenter.HomePresenterImpl;
import com.example.ipandaitems.presenter.homepresenter.homeadapter.HomeAdapter;
import com.example.ipandaitems.utils.GlideImageLoader;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张豫耀 on 2017/8/23.
 */

public class HomeFragment extends BaseFragment implements IHomeFragment, View.OnClickListener {

    XRecyclerView homefragmentXrv;

    HomePresenterImpl homeIPresenter;
    private View view_banner;
    private Banner mybanner;
    private TextView livetv;
    private TextView findtv;
    private ImageView banner_imag;
    private TextView banner_brief1, banner_brief2;


    private List<HomeBean.DataBean.BigImgBean> bigImg = new ArrayList<>();
    private List<HomeBean.DataBean.PandaeyeBean.ItemsBean> items = new ArrayList<>();

    private List<HomeMarvellBean.ListBean> homeMarvellBeanList = new ArrayList<>();
    private List<HomeRollingBean.ListBean> homeRollingBeanList = new ArrayList<>();
    private HomeAdapter homeAdapter;
    private List<HomeBean.DataBean.ChinaliveBean.ListBeanX> listBeanXes;
    private List<HomeBean.DataBean.PandaliveBean.ListBean> been = new ArrayList<>();
    private HomeBean.DataBean data = new HomeBean.DataBean();
    private HomeBean.DataBean.PandaeyeBean pandaeye;

   private String banner_pid;
    private String banner_url;
    private String panadBroadcastOne_url;
    private String panadBroadcastTne_url;
    private String brodcast_url;
    private String brodcasttwo_url;

    @Override
    protected int layoutID() {


        return R.layout.home_fragment;

    }


    @Override
    protected void initView(View view) {
        homefragmentXrv = view.findViewById(R.id.homefragment_xrv);


        homeIPresenter = new HomePresenterImpl(this);
        homeIPresenter.getHomeBean();


    }

    @Override
    protected void loadData() {


    }

    @Override
    protected void initListener() {

    }


    @Override
    public void gethomebean(HomeBean homeBean) {
        homeIPresenter.getHomeMarvellBean();
        data = homeBean.getData();

        if (data.getBigImg().size() < 0) {
            Log.e("TAG", "没有值");
        } else {
            bigImg = data.getBigImg();
            items = data.getPandaeye().getItems();
            listBeanXes = data.getChinalive().getList();
            been.addAll(data.getPandalive().getList());
            Log.e("TAG", "bean" + been.size() + "");
            Log.e("TAG", bigImg.get(1).getImage() + "图片");
            pandaeye = data.getPandaeye();

        }


    }

    @Override
    public void gethomeMarvellbean(HomeMarvellBean homeMarvellBean) {
        homeIPresenter.getHomeRollingBean();
        homeMarvellBeanList = homeMarvellBean.getList();


        Log.e("TAG","gethomeRollingbean方法外"+homeRollingBeanList.size());
        Log.e("TAG","homeMarvellBeanList方法里"+homeMarvellBeanList.size());


    }

    @Override
    public void gethomeRollingbean(HomeRollingBean homeRollingBean) {

        //全部获取数据
        homeRollingBeanList = homeRollingBean.getList();


        Log.e("TAG", "gethomeRollingbean方法里" + homeRollingBeanList.size());
        Log.e("TAG", "homeMarvellBeanList方法外" + homeMarvellBeanList.size());
        Log.e("TAG", "data" + "在gethomeRollingbean方法里" + data.getBigImg().size());
        view_banner = LayoutInflater.from(getContext()).inflate(R.layout.home_banner, null);
        mybanner = (Banner) view_banner.findViewById(R.id.home_banner_banner);

        livetv = (TextView) view_banner.findViewById(R.id.home_banner_pandaeye_tv1);
        findtv = (TextView) view_banner.findViewById(R.id.home_banner_pandaeye_tv2);
        banner_brief1 = (TextView) view_banner.findViewById(R.id.home_banner_brief1);
        banner_brief2 = (TextView) view_banner.findViewById(R.id.home_banner_brief2);
        banner_brief2.setOnClickListener(this);
        banner_brief1.setOnClickListener(this);
        banner_imag = (ImageView) view_banner.findViewById(R.id.home_banner_imag);
        livetv.setText(pandaeye.getItems().get(0).getBrief());
        findtv.setText(pandaeye.getItems().get(1).getTitle());
        banner_brief1.setText(pandaeye.getItems().get(0).getBrief());
        banner_brief2.setText(pandaeye.getItems().get(1).getBrief());
        Glide.with(getContext()).load(pandaeye.getPandaeyelogo()).into(banner_imag);

        List<String> imags = new ArrayList<>();
        List<String> titlles = new ArrayList<>();

        if (bigImg.size() > 0) {
            for (int a = 0; a < bigImg.size(); a++) {
                imags.add(bigImg.get(a).getImage());
                titlles.add(bigImg.get(a).getTitle());

            }


            mybanner.setImageLoader(new GlideImageLoader());

            mybanner.setBannerTitles(titlles);
            mybanner.setImages(imags);
            mybanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
            mybanner.setBannerAnimation(Transformer.DepthPage);
            mybanner.isAutoPlay(true);
            mybanner.setDelayTime(1000);
            mybanner.setIndicatorGravity(BannerConfig.CENTER);
            mybanner.start();
            final HomePresenterImpl    homePresenter1 = new HomePresenterImpl(this);
            mybanner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(getContext(), "这是" + bigImg.get(position).getPid(), Toast.LENGTH_SHORT).show();
                    banner_pid = bigImg.get(position).getPid();
                    homePresenter1.setHomeBannerVideoURl(HomeAdapter.Roll_url+banner_pid);
                    Log.e("这是来获取的轮播图的地址",HomeAdapter.Roll_url+banner_pid+"kong?");


                }
            });

            livetv.setText(items.get(0).getTitle());
            findtv.setText(items.get(1).getTitle());
            livetv.setOnClickListener(this);
            findtv.setOnClickListener(this);
            Log.e("TAG", "bean" + been.size() + "");
            homeAdapter = new HomeAdapter(getContext(), listBeanXes, homeRollingBeanList,
                    been, homeMarvellBeanList);
            RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getContext(), GridLayoutManager.VERTICAL, false);
            homefragmentXrv.setLayoutManager(layoutManager1);
            homefragmentXrv.addHeaderView(view_banner);
            homefragmentXrv.setLoadingMoreEnabled(true);
            homefragmentXrv.setLoadingMoreEnabled(true);
            homefragmentXrv.setAdapter(homeAdapter);

            homefragmentXrv.setRefreshProgressStyle(ProgressStyle.LineScalePulseOutRapid);
            homefragmentXrv.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
            homefragmentXrv.setLoadingListener(new XRecyclerView.LoadingListener() {
                @Override
                public void onRefresh() {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            homeAdapter.notifyDataSetChanged();
                            homefragmentXrv.refreshComplete();
                        }

                    }, 500);
                }

                @Override
                public void onLoadMore() {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            homeAdapter.notifyDataSetChanged();
                            homefragmentXrv.refreshComplete();
                        }

                    }, 500);

                }
            });


//            homefragmentXrv.setAdapter(homeAdapter);


            Toast.makeText(getContext(), "来数据了", Toast.LENGTH_SHORT).show();
            Log.e("TAG", "aaaa有值");


        } else {
            Toast.makeText(getContext(), "没数据", Toast.LENGTH_SHORT).show();
            Log.e("TAG", "aaaa没有值");
        }


    }

    @Override
    public void gethomePandanVideo(HomePandanVideo homePandanVideo) {

    }

    @Override
    public void gethomeChianVideo(HomeChianVideo homeChianVideo) {

    }


    @Override
    public void gethomeRollingVido(HomeRollVideo homeRollVideo) {


    }

    @Override
    public void gethomemarvellVido(HomeRollVideo homeRollVideo) {

    }

    @Override
    public void gethomebannerVido(HomeRollVideo homeRollVideo) {
        banner_url = homeRollVideo.getVideo().getLowChapters().get(0).getUrl();
        if(banner_url==null){
            Log.e("+++++","没有参数");
        }else{
            Intent intent=new Intent(getContext(),HomeVoid.class);
            intent.putExtra("url",banner_url);
            Log.e("轮播跳转之前的地址",banner_url+"啊");
            getContext().startActivity(intent);
        }

    }

    @Override
    public void gethomebrodcastVido(HomeRollVideo homeRollVideo) {
        brodcast_url = homeRollVideo.getVideo().getLowChapters().get(0).getUrl();
        if(brodcast_url==null){
            Log.e("+++++","没有参数");
        }else{
            Intent intent=new Intent(getContext(),HomeVoid.class);
            intent.putExtra("url",brodcast_url);
            Log.e("播报1跳转之前的地址",brodcast_url+"啊");
            getContext().startActivity(intent);
        }
    }

    @Override
    public void gethomebrodcasttwoVido(HomeRollVideo homeRollVideo) {
        brodcasttwo_url = homeRollVideo.getVideo().getLowChapters().get(0).getUrl();
        if(brodcasttwo_url==null){
            Log.e("+++++","没有参数");
        }else{
            Intent intent=new Intent(getContext(),HomeVoid.class);
            intent.putExtra("url",brodcasttwo_url);
            Log.e("播报2跳转之前的地址",brodcasttwo_url+"啊");
            getContext().startActivity(intent);
        }
    }


    @Override
    public void onClick(View view) {
        final HomePresenterImpl    homePresenter2 = new HomePresenterImpl(this);
        String base = HomeAdapter.Roll_url;
        switch (view.getId()) {
            case R.id.home_banner_pandaeye_tv1:
                panadBroadcastOne_url = pandaeye.getItems().get(0).getPid();
                homePresenter2.setHomeBrodcastVideoURl(base +panadBroadcastOne_url);


                break;
            case R.id.home_banner_pandaeye_tv2:
                panadBroadcastTne_url = pandaeye.getItems().get(1).getPid();
                homePresenter2.setHomeBrodcastTwoVideoURl(base +panadBroadcastTne_url);
                break;



        }
    }
}
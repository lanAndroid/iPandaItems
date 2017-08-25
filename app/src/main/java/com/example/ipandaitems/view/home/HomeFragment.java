package com.example.ipandaitems.view.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ipandaitems.R;
import com.example.ipandaitems.base.BaseFragment;
import com.example.ipandaitems.model.entry.HomeBean;
import com.example.ipandaitems.presenter.homepresenter.HomePresenterImpl;
import com.example.ipandaitems.utils.GlideImageLoader;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 张豫耀 on 2017/8/23.
 */

public class HomeFragment extends BaseFragment implements IHomeFragment {

    @BindView(R.id.homefragment_xrv)
    XRecyclerView homefragmentXrv;
    Unbinder unbinder;

    HomePresenterImpl homeIPresenter;
  private   View view_banner;
   private Banner mybanner;
    private TextView livetv;
    private TextView findtv;


    private List<HomeBean.DataBean.BigImgBean> bigImg=new ArrayList<>();
    private List<HomeBean.DataBean.PandaeyeBean.ItemsBean> items=new ArrayList<>();


    @Override
    protected int layoutID() {
        return R.layout.home_fragment;
    }

    //asd
    @Override
    protected void initView(View view) {
    homeIPresenter=new HomePresenterImpl(this);



        view_banner=LayoutInflater.from(getContext()).inflate(R.layout.home_banner,null);
        mybanner=view_banner.findViewById(R.id.home_banner_banner);
        livetv=view_banner.findViewById(R.id.home_banner_live_tv);
        findtv=view.findViewById(R.id.home_banner_find_tv);
        List<String> imags=new ArrayList<>();
        List<String> titlles=new ArrayList<>();
        if(bigImg.size()>0){
            for(int a=0;a<bigImg.size();a++){
                imags.add(bigImg.get(a).getImage());
                titlles.add(bigImg.get(a).getTitle());

            }
            mybanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
            mybanner.setImageLoader(new GlideImageLoader());

            mybanner.setBannerTitles(titlles);
            mybanner.setImages(imags);
            mybanner.setBannerAnimation(Transformer.DepthPage);
            mybanner.isAutoPlay(true);
            mybanner.setDelayTime(1500);
            mybanner.setIndicatorGravity(BannerConfig.CENTER);
            mybanner.start();

            livetv.setText(items.get(0).getTitle());
            findtv.setText(items.get(1).getTitle());
            homefragmentXrv.addHeaderView(view_banner);
        }else{
//            Toast.makeText(getContext(), "没数据", Toast.LENGTH_SHORT).show();
        }




    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void gethomebean(HomeBean homeBean) {

        HomeBean.DataBean data = homeBean.getData();
        bigImg = data.getBigImg();
        items = data.getPandaeye().getItems();

        Toast.makeText(getContext(), bigImg.get(1).getImage()+"图片", Toast.LENGTH_SHORT).show();
        Log.e("TAG",bigImg.get(1).getImage()+"图片");




    }
}

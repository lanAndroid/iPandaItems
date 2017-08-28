package com.example.ipandaitems.view.pandalive.plfragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.ipandaitems.R;
import com.example.ipandaitems.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @name yanantian
 * @motto 莫羡他人谢语花, 腹有诗书气自华
 * @E-mail 1173568715@qq.com
 * @WX 15978622391
 */

public class PLFLive extends BaseFragment {


//    @BindView(R.id.pl_live_video)
//    io.vov.vitamio.widget.VideoView plLiveVideo;
    @BindView(R.id.pl_live_name)
    TextView plLiveName;
    @BindView(R.id.pl_live_abstract)
    ImageView plLiveAbstract;
    @BindView(R.id.pl_live_content)
    TextView plLiveContent;
    @BindView(R.id.pl_live_tablayout)
    TabLayout plLiveTablayout;
    @BindView(R.id.pl_live_viewpager)
    ViewPager plLiveViewpager;
    @BindView(R.id.pl_live_scroll)
    ScrollView plLiveScroll;
    Unbinder unbinder;

    @Override
    protected int layoutID() {
        return R.layout.pl_live;
    }

    @Override
    protected void initView(View view) {

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

    @OnClick({R.id.pl_live_abstract, R.id.pl_live_content})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pl_live_abstract:
                break;
            case R.id.pl_live_content:
                break;
        }
    }
}

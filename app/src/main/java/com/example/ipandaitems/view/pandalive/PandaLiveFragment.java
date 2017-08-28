package com.example.ipandaitems.view.pandalive;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ipandaitems.R;
import com.example.ipandaitems.base.BaseFragment;
import com.example.ipandaitems.view.pandalive.plfragment.PLF2AmaPhotoes;
import com.example.ipandaitems.view.pandalive.plfragment.PLF3Courage;
import com.example.ipandaitems.view.pandalive.plfragment.PLF4Sprout;
import com.example.ipandaitems.view.pandalive.plfragment.PLF5Record;
import com.example.ipandaitems.view.pandalive.plfragment.PLF6TOP;
import com.example.ipandaitems.view.pandalive.plfragment.PLF7Things;
import com.example.ipandaitems.view.pandalive.plfragment.PLF8Specials;
import com.example.ipandaitems.view.pandalive.plfragment.PLF9Original;
import com.example.ipandaitems.view.pandalive.plfragment.PLFLive;
import com.example.ipandaitems.view.pandalive.view.PandaLiveViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by 张豫耀 on 2017/8/23.
 */

public class PandaLiveFragment extends BaseFragment {
    @BindView(R.id.pl_tavlayout)
    TabLayout plTavlayout;
    Unbinder unbinder;
    @BindView(R.id.pl_viewpager)
    PandaLiveViewPager plViewpager;
    private ArrayList<Fragment> list = new ArrayList<>();
    private PandaLLiveAdapter pla;

    @Override
    protected int layoutID() {
        return R.layout.pandalive_fragment;
    }

    @Override
    protected void initView(View view) {
        list.add(new PLFLive());
        list.add(new PLF2AmaPhotoes());
        list.add(new PLF3Courage());
        list.add(new PLF4Sprout());
        list.add(new PLF5Record());
        list.add(new PLF6TOP());
        list.add(new PLF7Things());
        list.add(new PLF8Specials());
        list.add(new PLF9Original());
        pla = new PandaLLiveAdapter(getChildFragmentManager(), list);
        plViewpager.setAdapter(pla);
        plTavlayout.setupWithViewPager(plViewpager);
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
}

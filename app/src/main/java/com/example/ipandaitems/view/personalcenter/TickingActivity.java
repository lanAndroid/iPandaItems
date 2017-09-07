package com.example.ipandaitems.view.personalcenter;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.example.ipandaitems.R;
import com.example.ipandaitems.base.BaseActivity;
import com.example.ipandaitems.view.personalcenter.adapter.TickAdapter;
import com.example.ipandaitems.view.personalcenter.fragment.CommonFragment;
import com.example.ipandaitems.view.personalcenter.fragment.CounterFragment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TickingActivity extends BaseActivity {


    @BindView(R.id.tick_finish)
    ImageView tickFinish;
    @BindView(R.id.tick_tabLayout)
    TabLayout tickTabLayout;
    @BindView(R.id.tick_viewpager)
    ViewPager tickViewpager;
    private List<String> strlist = new ArrayList<>();
    private List<Fragment> fralist = new ArrayList<>();

    @Override
    protected int layoutID() {
        return R.layout.activity_ticking;
    }

    @Override
    protected void initView() throws SQLException {

    }

    @Override
    protected void loadData() {
        strlist.add("遇到的问题");
        strlist.add("常见问题");
        fralist.add(new CommonFragment());
        fralist.add(new CounterFragment());
        for (int i = 0; i < strlist.size(); i++) {
            tickTabLayout.addTab(tickTabLayout.newTab().setText(strlist.get(i)));
        }
        FragmentManager manager = getSupportFragmentManager();
        TickAdapter adapter = new TickAdapter(manager, fralist, strlist);
        tickViewpager.setAdapter(adapter);
        tickTabLayout.setupWithViewPager(tickViewpager);
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

    @OnClick(R.id.tick_finish)
    public void onViewClicked() {
        finish();
    }
}

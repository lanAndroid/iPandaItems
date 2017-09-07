package com.example.ipandaitems.view.personalcenter;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.ipandaitems.R;
import com.example.ipandaitems.base.BaseActivity;
import com.example.ipandaitems.view.ui.collect.AmazingFragment;
import com.example.ipandaitems.view.ui.collect.SoLiveFragment;
import com.example.ipandaitems.view.ui.collect.adapter.CollectAdapter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CollectActivity extends BaseActivity {


    @BindView(R.id.collect_finish)
    ImageView collectFinish;

    @BindView(R.id.collect_tab)
    TabLayout collectTab;
    @BindView(R.id.collect_viewpager)
    ViewPager collectViewpager;
    private List<Fragment> list = new ArrayList<>();
    private List<String> strlist = new ArrayList<>();

    @Override
    protected int layoutID() {
        return R.layout.activity_collect;
    }

    @Override
    protected void initView() throws SQLException {

    }

    @Override
    protected void loadData() {
        list.add(new SoLiveFragment());
        list.add(new AmazingFragment());
        FragmentManager manager = getSupportFragmentManager();
        strlist.add("直播");
        strlist.add("精彩看点");
        for (int i = 0; i < strlist.size(); i++) {
            collectTab.addTab(collectTab.newTab().setText(strlist.get(i)));
        }
        CollectAdapter adapter = new CollectAdapter(manager, list, strlist);
        collectViewpager.setAdapter(adapter);
        collectTab.setupWithViewPager(collectViewpager);
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

    @OnClick({R.id.collect_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.collect_finish:
                finish();
                break;
        }
    }
}

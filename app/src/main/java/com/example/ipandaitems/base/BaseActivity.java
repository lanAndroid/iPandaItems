package com.example.ipandaitems.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.ipandaitems.App;
import com.zhy.autolayout.AutoLayoutActivity;

import java.sql.SQLException;

import butterknife.ButterKnife;
import io.vov.vitamio.Vitamio;


/**
 * Created by xiaogang on 2017/6/20.
 */

public abstract class BaseActivity extends AutoLayoutActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutID());
        Vitamio.isInitialized(this);
        ButterKnife.bind(this);
        App.baseActivity = this;
        try {
            initView();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        loadData();
        initListener();
    }

    protected abstract int layoutID();

    protected abstract void initView() throws SQLException;

    protected abstract void loadData();

    protected abstract void initListener();

    @Override
    protected void onResume() {
        super.onResume();
        App.baseActivity = this;
    }
}

package com.example.ipandaitems.view.personalcenter;

import android.content.Intent;

import com.example.ipandaitems.R;
import com.example.ipandaitems.base.BaseActivity;
import com.example.ipandaitems.view.ShellActivity;

import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;

public class HelloActivity extends BaseActivity {


    @Override
    protected int layoutID() {
        return R.layout.activity_hello;
    }

    @Override
    protected void initView() throws SQLException {
        ButterKnife.bind(this);
    }

    @Override
    protected void loadData() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(HelloActivity.this, ShellActivity.class));
                finish();
            }
        }, 2000);
    }

    @Override
    protected void initListener() {

    }
}

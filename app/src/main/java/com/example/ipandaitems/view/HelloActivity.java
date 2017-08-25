package com.example.ipandaitems.view;

import android.content.Intent;

import com.example.ipandaitems.R;
import com.example.ipandaitems.base.BaseActivity;

import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

public class HelloActivity extends BaseActivity {


    @Override
    protected int layoutID() {
        return R.layout.activity_hello;
    }

    @Override
    protected void initView() throws SQLException {

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

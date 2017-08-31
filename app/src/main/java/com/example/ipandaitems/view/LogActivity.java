package com.example.ipandaitems.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.ipandaitems.R;
import com.example.ipandaitems.base.BaseActivity;
import com.example.ipandaitems.view.registerandlogin.loginActivity;

import java.sql.SQLException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;


public class LogActivity extends BaseActivity {


    @BindView(R.id.personal_back_img)
    ImageView personal_back_img;
    @BindView(R.id.person_no_login_layout)
    RelativeLayout personNoLoginLayout;

    @BindView(R.id.personal_history_layout)
    RelativeLayout personalHistoryLayout;
    @BindView(R.id.personal_shoucang_layout)
    RelativeLayout personalShoucangLayout;
    @BindView(R.id.personal_set_layout)
    RelativeLayout personalSetLayout;

    @Override
    protected int layoutID() {
        return R.layout.activity_log;
    }

    @Override
    protected void initView() throws SQLException {
        ButterKnife.bind(this);
    }

    @Override
    protected void loadData() {

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

    @Optional
    @OnClick({R.id.personal_back_img, R.id.person_no_login_layout, R.id.personal_history_layout, R.id.personal_shoucang_layout, R.id.personal_set_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.personal_back_img:
                finish();
                break;
            case R.id.person_no_login_layout:
                startActivity(new Intent(LogActivity.this, loginActivity.class));
                break;
            case R.id.personal_history_layout:
                break;
            case R.id.personal_shoucang_layout:
                break;
            case R.id.personal_set_layout:
                break;
        }
    }
}

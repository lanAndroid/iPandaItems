package com.example.ipandaitems.view.registerandlogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ipandaitems.R;
import com.example.ipandaitems.base.BaseActivity;

import java.sql.SQLException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

public class loginActivity extends BaseActivity {


    @BindView(R.id.login_finish)
    ImageView loginFinish;
    @BindView(R.id.login_regis)
    TextView loginRegis;

    @Override
    protected int layoutID() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() throws SQLException {

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
    @OnClick({R.id.login_finish, R.id.login_regis})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_finish:
                finish();
                break;
            case R.id.login_regis:
                startActivity(new Intent(loginActivity.this, RegisterActivity.class));
                break;
        }
    }
}

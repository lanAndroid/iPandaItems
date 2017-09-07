package com.example.ipandaitems.view.personalcenter;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.ipandaitems.R;
import com.example.ipandaitems.base.BaseActivity;

import java.sql.SQLException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegardsActivity extends BaseActivity {


    @BindView(R.id.regards_finish)
    ImageView regardsFinish;

    @Override
    protected int layoutID() {
        return R.layout.activity_regards;
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

    @OnClick(R.id.regards_finish)
    public void onViewClicked() {
        finish();
    }
}

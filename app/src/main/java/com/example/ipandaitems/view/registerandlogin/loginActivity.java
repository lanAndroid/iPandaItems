package com.example.ipandaitems.view.registerandlogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ipandaitems.R;
import com.example.ipandaitems.base.BaseActivity;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.sql.SQLException;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class loginActivity extends BaseActivity {


    @BindView(R.id.login_finish)
    ImageView loginFinish;
    @BindView(R.id.login_regis)
    TextView loginRegis;
    @BindView(R.id.llweixinlogin)
    LinearLayout llweixinlogin;
    @BindView(R.id.llqqlogin)
    LinearLayout llqqlogin;
    @BindView(R.id.llsinalogin)
    LinearLayout llsinalogin;

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


    @OnClick({R.id.llweixinlogin, R.id.llqqlogin, R.id.llsinalogin, R.id.login_finish, R.id.login_regis})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.login_finish:
                finish();
                break;
            case R.id.login_regis:
                startActivity(new Intent(loginActivity.this, RegisterActivity.class));
                break;
            case R.id.llweixinlogin:
                UMShareAPI.get(this).getPlatformInfo(loginActivity.this, SHARE_MEDIA.WEIXIN, authListener);
                break;
            case R.id.llqqlogin:
                UMShareAPI.get(this).getPlatformInfo(loginActivity.this, SHARE_MEDIA.QQ, authListener);
                break;
            case R.id.llsinalogin:
                UMShareAPI.get(this).getPlatformInfo(loginActivity.this, SHARE_MEDIA.SINA, authListener);
                break;
        }
    }


    private UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            Toast.makeText(loginActivity.this, "成功了", Toast.LENGTH_LONG).show();

        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(loginActivity.this, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(loginActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    //  回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
      //  UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}

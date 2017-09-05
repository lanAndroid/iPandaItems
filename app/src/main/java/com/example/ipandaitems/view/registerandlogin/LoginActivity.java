package com.example.ipandaitems.view.registerandlogin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ipandaitems.R;
import com.example.ipandaitems.base.BaseActivity;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.share.WbShareHandler;
import com.sina.weibo.sdk.utils.Utility;

import java.sql.SQLException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import io.vov.vitamio.utils.Log;

public class LoginActivity extends BaseActivity {


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
    @BindView(R.id.edit_account)
    EditText editAccount;
    @BindView(R.id.hint_account)
    TextView hintAccount;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.hint_password)
    TextView hintPassword;
    @BindView(R.id.personal_login_forget_pwd)
    TextView personalLoginForgetPwd;
    @BindView(R.id.loding_btn)
    TextView lodingBtn;
    @BindView(R.id.login_in_layout)
    LinearLayout loginInLayout;
    //    @BindView(R.id.login_button)
//    LoginButton loginButton;
    private SsoHandler mSsoHandler;
    private Oauth2AccessToken mAccessToken;
    private WbShareHandler shareHandler;
    private ShareUrils shareUrils;
//    private CallbackManager callbackManager;

    @Override
    protected int layoutID() {
//        FacebookSdk.sdkInitialize(getApplicationContext());
        return R.layout.activity_login;
    }

    @Override
    protected void initView() throws SQLException {

    }

    @Override
    protected void loadData() {
//        callbackManager = CallbackManager.Factory.create();
//        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                Log.e("abc", "onSuccess");
//            }
//
//            @Override
//            public void onCancel() {
//                Log.e("abc", "onCancel");
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//                Log.e("abc", "onError");
//            }
//        });
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
    @OnClick({R.id.login_finish, R.id.login_regis, R.id.llweixinlogin, R.id.llqqlogin, R.id.llsinalogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_finish:
                finish();
                break;
            case R.id.login_regis:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.llweixinlogin:
//                shareUrils = new ShareUrils(LoginActivity.this);
//                shareUrils.initFenXiang("wo", "hehe", null, "http://www.baidu.com", "wo ca");
                break;
            case R.id.llqqlogin://  QQ登录
                initFenXiang();
                break;
            case R.id.llsinalogin://    新浪微博
                initWebSDK();
                break;
        }
    }

    private void initFenXiang() {
        shareHandler = new WbShareHandler(this);
        shareHandler.registerApp();
        WeiboMultiMessage multiMessage = new WeiboMultiMessage();
        multiMessage.mediaObject = getWebpageObj();
        shareHandler.shareMessage(multiMessage, false);
    }

    /**
     * 创建多媒体（网页）消息对象。
     *
     * @return 多媒体（网页）消息对象。
     */
    private WebpageObject getWebpageObj() {
        WebpageObject mediaObject = new WebpageObject();
        mediaObject.identify = Utility.generateGUID();
        mediaObject.title = "测试title";   //      这里填写想要分享的title
        mediaObject.description = "测试描述"; //      这里填写想要分享的描述
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.person_sign);
        // 设置 Bitmap 类型的图片到视频对象里         设置缩略图。 注意：最终压缩过的缩略图大小不得超过 32kb。
        mediaObject.setThumbImage(bitmap);
        mediaObject.actionUrl = "http://news.sina.com.cn/c/2013-10-22/021928494669.shtml"; //      这里填写想要分享的url
        mediaObject.defaultText = "Webpage 默认文案"; //      这里填写想要分享的默认语句
        return mediaObject;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        shareHandler.doResultIntent(intent, new com.sina.weibo.sdk.share.WbShareCallback() {
            @Override
            public void onWbShareSuccess() {
                Toast.makeText(LoginActivity.this, "分享成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onWbShareCancel() {
                Toast.makeText(LoginActivity.this, "取消分享", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onWbShareFail() {
                Toast.makeText(LoginActivity.this, "分享失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //新浪微博初始化，对应的参数分别是app_key,回调地址，和权限
    private void initWebSDK() {
        mSsoHandler = new SsoHandler(LoginActivity.this);
        mSsoHandler.authorize(new SelfWbAuthListener());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
//        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

//    public void getFaceBookToken(View view) {
//        AccessToken mAccessToken = AccessToken.getCurrentAccessToken();
//        Log.e("token", "token :" + mAccessToken.getToken() + "," + "user_id" + mAccessToken.getUserId());
//    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        AppEventsLogger.activateApp(this);
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        AppEventsLogger.deactivateApp(this);
//    }

    //请求数据
    private class SelfWbAuthListener implements WbAuthListener {
        //请求成功
        @Override
        public void onSuccess(final Oauth2AccessToken token) {
            LoginActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mAccessToken = token;
                    String name = token.getPhoneNum();
                    String token1 = token.getToken();
                    Bundle bundle = token.getBundle();
                    String token2 = token.getRefreshToken();
                    long time = token.getExpiresTime();
                    String uid = token.getUid();
                    Log.e("login", "name==" + name + "----time==" + time + "----uid==" + uid);
                    Log.e("login", "name==" + token1 + "----time==" + bundle + "----uid==" + token2);
                    if (mAccessToken.isSessionValid()) {
                        // 显示 Token
//                     updateTokenView(false);
                        // 保存 Token 到 SharedPreferences
                        AccessTokenKeeper.writeAccessToken(LoginActivity.this, mAccessToken);
                        Toast.makeText(LoginActivity.this,
                                "name==" + name + "----time==" + time + "----uid==" + uid, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        @Override
        public void cancel() {
            Toast.makeText(LoginActivity.this,
                    "取消了", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onFailure(WbConnectErrorMessage errorMessage) {
            Toast.makeText(LoginActivity.this, errorMessage.getErrorMessage(), Toast.LENGTH_LONG).show();
        }

    }

}
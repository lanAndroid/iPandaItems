package com.example.ipandaitems.view.registerandlogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ipandaitems.R;
import com.example.ipandaitems.base.BaseActivity;

import java.sql.SQLException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class loginActivity extends BaseActivity {

//    private Oauth2AccessToken mAccessToken;
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
//    private WbShareHandler shareHandler;
//    private SsoHandler mSsoHandler;

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
                //       UMShareAPI.get(this).getPlatformInfo(loginActivity.this, SHARE_MEDIA.WEIXIN, authListener);
                break;
            case R.id.llqqlogin:
                //initFenXiang();
                break;
            case R.id.llsinalogin:
              //  initWebSDK();
                break;
        }
    }

//    private void initFenXiang() {
//        shareHandler = new WbShareHandler(this);
//        shareHandler.registerApp();
//        WeiboMultiMessage multiMessage = new WeiboMultiMessage();
//        multiMessage.mediaObject = getWebpageObj();
//        shareHandler.shareMessage(multiMessage, false);
//    }

    /**
     * 创建多媒体（网页）消息对象。
     *
     * @return 多媒体（网页）消息对象。
//     */
//    private WebpageObject getWebpageObj() {
//        WebpageObject mediaObject = new WebpageObject();
//        mediaObject.identify = Utility.generateGUID();
//        mediaObject.title = "测试title";   //      这里填写想要分享的title
//        mediaObject.description = "测试描述"; //      这里填写想要分享的描述
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.person_sign);
//        // 设置 Bitmap 类型的图片到视频对象里         设置缩略图。 注意：最终压缩过的缩略图大小不得超过 32kb。
//        mediaObject.setThumbImage(bitmap);
//        mediaObject.actionUrl = "http://news.sina.com.cn/c/2013-10-22/021928494669.shtml"; //      这里填写想要分享的url
//        mediaObject.defaultText = "Webpage 默认文案"; //      这里填写想要分享的默认语句
//        return mediaObject;
//    }
//
//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        shareHandler.doResultIntent(intent, new com.sina.weibo.sdk.share.WbShareCallback() {
//            @Override
//            public void onWbShareSuccess() {
//                Toast.makeText(loginActivity.this, "分享成功", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onWbShareCancel() {
//                Toast.makeText(loginActivity.this, "取消分享", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onWbShareFail() {
//                Toast.makeText(loginActivity.this, "分享失败", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    //新浪微博初始化，对应的参数分别是app_key,回调地址，和权限
//    private void initWebSDK() {
//        mSsoHandler = new SsoHandler(loginActivity.this);
//        mSsoHandler.authorize(new SelfWbAuthListener());
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (mSsoHandler != null) {
//            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
//        }
////        callbackManager.onActivityResult(requestCode, resultCode, data);
//    }
//
////    public void getFaceBookToken(View view) {
////        AccessToken mAccessToken = AccessToken.getCurrentAccessToken();
////        Log.e("token", "token :" + mAccessToken.getToken() + "," + "user_id" + mAccessToken.getUserId());
////    }
//
////    @Override
////    protected void onResume() {
////        super.onResume();
////        AppEventsLogger.activateApp(this);
////    }
////
////    @Override
////    protected void onPause() {
////        super.onPause();
////        AppEventsLogger.deactivateApp(this);
////    }
//
//    //请求数据
//    private class SelfWbAuthListener implements WbAuthListener {
//        //请求成功
//        @Override
//        public void onSuccess(final Oauth2AccessToken token) {
//            loginActivity.this.runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    mAccessToken = token;
//                    String name = token.getPhoneNum();
//                    String token1 = token.getToken();
//                    Bundle bundle = token.getBundle();
//                    String token2 = token.getRefreshToken();
//                    long time = token.getExpiresTime();
//                    String uid = token.getUid();
//                    Log.e("login", "name==" + name + "----time==" + time + "----uid==" + uid);
//                    Log.e("login", "name==" + token1 + "----time==" + bundle + "----uid==" + token2);
//                    if (mAccessToken.isSessionValid()) {
//                        // 显示 Token
////                     updateTokenView(false);
//                        // 保存 Token 到 SharedPreferences
//                        AccessTokenKeeper.writeAccessToken(loginActivity.this, mAccessToken);
//                        Toast.makeText(loginActivity.this,
//                                "name==" + name + "----time==" + time + "----uid==" + uid, Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        }
//
//        @Override
//        public void cancel() {
//            Toast.makeText(loginActivity.this,
//                    "取消了", Toast.LENGTH_LONG).show();
//        }
//
//        @Override
//        public void onFailure(WbConnectErrorMessage errorMessage) {
//            Toast.makeText(loginActivity.this, errorMessage.getErrorMessage(), Toast.LENGTH_LONG).show();
//        }
//
//    }

}

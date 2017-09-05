package com.example.ipandaitems;

import android.app.Application;

import com.example.ipandaitems.base.BaseActivity;
import com.example.ipandaitems.base.BaseFragment;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;


/**
 * Created by 张豫耀 on 2017/8/23.
 */
//  dh1n0nLB/AY3z2NIphI35Th+N2s=
public class App extends Application {
    public static BaseActivity baseActivity;
    public static BaseFragment baseFragment;

    @Override
    public void onCreate() {
        super.onCreate();
//        initWebSDK();
        WbSdk.install(this, new AuthInfo(this, Constants.APP_KEY, Constants.REDIRECT_URL,
                Constants.SCOPE));

//        FacebookSdk.sdkInitialize(getApplicationContext());
//        AppEventsLogger.activateApp(this);
//        logger.logPurchase(BigDecimal.valueOf(4.32), Currency.getInstance("USD"));

    }
    public interface Constants {
        /**
         * 当前 DEMO 应用的 APP_KEY，第三方应用应该使用自己的 APP_KEY 替换该 APP_KEY
         */
        public static final String APP_KEY = "1931506723";

        /**
         * 当前 DEMO 应用的回调页，第三方应用可以使用自己的回调页。
         * 建议使用默认回调页：https://api.weibo.com/oauth2/default.html
         */
        public static final String REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";

        /**
         * WeiboSDKDemo 应用对应的权限，第三方开发者一般不需要这么多，可直接设置成空即可。
         * 详情请查看 Demo 中对应的注释。
         */
        public static final String SCOPE = null;
//                "email,direct_messages_read,direct_messages_write,"
//                        + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
//                        + "follow_app_official_microblog," + "invitation_write";
    }


}

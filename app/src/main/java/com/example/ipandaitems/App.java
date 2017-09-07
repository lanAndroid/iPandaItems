package com.example.ipandaitems;

import android.app.Application;

import com.example.ipandaitems.base.BaseActivity;
import com.example.ipandaitems.base.BaseFragment;


import io.vov.vitamio.Vitamio;


/**
 * Created by 张豫耀 on 2017/8/23.
 */

public class App extends Application {
    private static App app;
    public static BaseActivity baseActivity;
    public static BaseFragment baseFragment;
    private static App instance = null;
    private SharePreferenceUtil mSpUtil;
    public static final String SP_FILE_NAME = "SP_FILE";
    private App mediaPlayer;


    public static App getInstance() {
        return app;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Vitamio.isInitialized(getApplicationContext());
//        WbSdk.install(this, new AuthInfo(this, Constants.APP_KEY, Constants.REDIRECT_URL,
//                Constants.SCOPE));
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

    public synchronized SharePreferenceUtil getSpUtil() {
        if (mSpUtil == null)
            mSpUtil = new SharePreferenceUtil(this, SP_FILE_NAME);
        return mSpUtil;
    }
}

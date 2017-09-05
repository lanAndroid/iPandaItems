package com.example.ipandaitems;

import android.app.Application;

import com.example.ipandaitems.base.BaseActivity;
import com.example.ipandaitems.base.BaseFragment;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import io.vov.vitamio.Vitamio;


/**
 * Created by 张豫耀 on 2017/8/23.
 */

public class App extends Application {
    public static BaseActivity baseActivity;
    public static BaseFragment baseFragment;
    private static App instance = null;

    private App mediaPlayer;

    public static App getInstance() {
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("1106391626", "PfMTND1DLsKr0duA");
        PlatformConfig.setSinaWeibo("1135120968", "50bd410855c387951e9c6923343e0655", "https://api.weibo.com/oauth2/default.html");
        UMShareAPI.get(this);
        instance = this;
        Vitamio.isInitialized(getApplicationContext());
    }
}

package com.example.ipandaitems;

import android.app.Application;

import com.example.ipandaitems.base.BaseActivity;
import com.example.ipandaitems.base.BaseFragment;


/**
 * Created by 张豫耀 on 2017/8/23.
 */

public class App extends Application {
    public static BaseActivity baseActivity;
    public static BaseFragment baseFragment;
    @Override
    public void onCreate() {
        super.onCreate();
    }
}

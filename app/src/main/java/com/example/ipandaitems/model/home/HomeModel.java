package com.example.ipandaitems.model.home;


import com.example.ipandaitems.model.Callback;
import com.example.ipandaitems.model.entry.home.HomeBean;
import com.example.ipandaitems.model.entry.home.HomeChianVideo;
import com.example.ipandaitems.model.entry.home.HomeMarvellBean;
import com.example.ipandaitems.model.entry.home.HomePandanVideo;
import com.example.ipandaitems.model.entry.home.HomeRollVideo;
import com.example.ipandaitems.model.entry.home.HomeRollingBean;

//xx
public interface HomeModel {

    void RequestHomeGet(Callback<HomeBean> callback);

    void RequestHomeMarvellGet(Callback<HomeMarvellBean> callback);

    void RequestHomeRollingGet(Callback<HomeRollingBean> callback);

    //modle 给网络地址传值，并返回滚滚视频视频实体类
    void RequestHomeRollingGet(String url,Callback<HomeRollVideo> callback);

    //modle 给网络地址传值，并返回精彩一克视频实体类
    void RequestHomeMarvellGet(String url,Callback<HomeRollVideo> callback);

    //modle 给网络地址传值，并返回轮播图视频实体类
    void RequestHomeBanner(String url,Callback<HomeRollVideo> callback);

//首页熊猫直播
    void RequestHomePandanVideoGet(String url,Callback<HomePandanVideo> callback);

    //首页直播中国
    void RequestHomeChianVoidGet(String url,Callback<HomeChianVideo> callback);


//两次对于首页广播视频地址的请求
    void Requestgethomebroadcastvideo(String url,Callback<HomeRollVideo> callback);

    void Requestgethomebroadcasttwovideo(String url,Callback<HomeRollVideo> callback);
}

package com.example.ipandaitems.presenter.homepresenter;

public interface HomeIPresenter {
    void getHomeBean( );
    void getHomeMarvellBean( );
    void getHomeRollingBean( );

    //给modle传过去视频的网络请求地址
    void setHomeRollVideo(String url);


    void setHomeRollVideoURl(String url);
//给modle传过去轮播图的地址
    void setHomeBannerVideoURl(String url);

    //给modle传过去熊猫广播的地址
    void setHomeBrodcastVideoURl(String url);

    //给modle传过去熊猫广播的地址2
    void setHomeBrodcastTwoVideoURl(String url);
    //传首页熊猫直播的地址
    void setHomePandanVideo(String url);
    //传 首页直播中国的地址
    void setHomeChianVideo(String url);
}
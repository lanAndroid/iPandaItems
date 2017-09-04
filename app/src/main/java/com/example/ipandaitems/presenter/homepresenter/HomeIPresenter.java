package com.example.ipandaitems.presenter.homepresenter;

public interface HomeIPresenter {
    void getHomeBean( );
    void getHomeMarvellBean( );
    void getHomeRollingBean( );

    //给modle传过去视频的网络请求地址
    void setHomeRollVideo(String url);


    void setHomeRollVideoURl(String url);
}
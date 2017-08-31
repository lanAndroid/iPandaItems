package com.example.ipandaitems.model.video;


import com.example.ipandaitems.model.Callback;
import com.example.ipandaitems.model.entry.TopBean;
import com.example.ipandaitems.model.entry.TopListBean;

//xx
public interface VideoModel {

    void doTop(Callback<TopBean> callback);

    //topList
    void doTopList(String url, Callback<TopListBean> callback);
}

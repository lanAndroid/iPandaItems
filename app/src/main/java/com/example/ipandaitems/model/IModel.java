package com.example.ipandaitems.model;


import com.example.ipandaitems.model.entry.AnnBean;
import com.example.ipandaitems.model.entry.Bean;
import com.example.ipandaitems.model.entry.HomeBean;
import com.example.ipandaitems.model.entry.pandalive.PLAmaPhotoes;
import com.example.ipandaitems.model.entry.pandalive.PLHome;
import com.example.ipandaitems.model.entry.pandalive.PLLive;

import java.util.Map;

import io.reactivex.Observer;


public interface IModel {
    void RequestGet(Observer<Bean> observer);

    void RequestPost(Map<String, String> map, Observer<Bean> observer);

    void AnnRequsetGet(Observer<AnnBean> observer);

    void RequestHomeGet(Observer<HomeBean> observer);


    //    熊猫直播 第一次网络获取
    void doGet(Observer<PLHome> observer);

    //    熊猫直播 第二次网络获取
    void doGets(Observer<PLLive> observer);

    //    熊猫直播 精彩一刻
    void doAmaPhotoes(Observer<PLAmaPhotoes> observer);


}

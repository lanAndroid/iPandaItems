package com.example.ipandaitems.model.panda;


import com.example.ipandaitems.model.Callback;
import com.example.ipandaitems.model.entry.pandalive.PLAmaPhotoes;
import com.example.ipandaitems.model.entry.pandalive.PLHome;
import com.example.ipandaitems.model.entry.pandalive.PLLive;

//xx
public interface PandaModel {

    //    熊猫直播 第一次网络获取
    void doGet(Callback<PLHome> callback);

    //    熊猫直播 第二次网络获取
    void doGets(Callback<PLLive> callback);

    //    熊猫直播 精彩一刻
    void doAmaPhotoes(Callback<PLAmaPhotoes> callback);


}

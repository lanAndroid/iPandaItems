package com.example.ipandaitems.model;


import com.example.ipandaitems.model.entry.AnnBean;
import com.example.ipandaitems.model.entry.Bean;
import com.example.ipandaitems.model.entry.HomeBean;
import com.example.ipandaitems.model.entry.TopBean;
import com.example.ipandaitems.model.entry.TopListBean;
import com.example.ipandaitems.model.entry.livechina.livechinaBean;
import com.example.ipandaitems.model.entry.pandalive.PLAmaPhotoes;
import com.example.ipandaitems.model.entry.pandalive.PLHome;
import com.example.ipandaitems.model.entry.pandalive.PLLive;
import com.example.ipandaitems.model.retrofit.RetrofitUtils;

import java.util.Map;

import io.reactivex.Observer;


public class ModelImpl implements IModel {

    @Override
    public void RequestGet(Observer<Bean> observer) {

        RetrofitUtils.getmRetrofitUtils_Demo().GetNetwork(observer);

    }

    @Override
    public void RequestPost(Map<String, String> map, Observer<Bean> observer) {
        RetrofitUtils.getmRetrofitUtils_Demo().PostNetwork(map, observer);
    }

    @Override
    public void AnnRequsetGet(Observer<AnnBean> observer) {
        RetrofitUtils.getmRetrofitUtils_Demo().annGetNetWork(observer);
    }

    @Override
    public void RequestHomeGet(Observer<HomeBean> observer) {
        RetrofitUtils.getmRetrofitUtils_Demo().GetHomeNetwork(observer);
    }

    //    熊猫直播 第一次网络获取
    @Override
    public void doGet(Observer<PLHome> observer) {
        RetrofitUtils.getmRetrofitUtils_Demo().getPandaLive(observer);
    }

    //    熊猫直播 第二次网络获取
    @Override
    public void doGets(Observer<PLLive> observer) {
        RetrofitUtils.getmRetrofitUtils_Demo().getPandaLives(observer);
    }

    //    熊猫直播 精彩一刻
    @Override
    public void doAmaPhotoes(Observer<PLAmaPhotoes> observer) {
        RetrofitUtils.getmRetrofitUtils_Demo().getPLAmaPhotoes(observer);
    }

//  熊猫播报top
    @Override
    public void doTop(Observer<TopBean> observer) {
        RetrofitUtils.getmRetrofitUtils_Demo().getTOP(observer);
    }

    @Override
    public void doTopList(String url, Observer<TopListBean> observer) {
        RetrofitUtils.getmRetrofitUtils_Demo().getTopList(observer,url);
    }
    ///
}

package com.example.ipandaitems.model;


import com.example.ipandaitems.model.entry.AnnBean;
import com.example.ipandaitems.model.entry.Bean;
import com.example.ipandaitems.model.entry.TopBean;
import com.example.ipandaitems.model.entry.TopListBean;
import com.example.ipandaitems.model.entry.home.HomeBean;
import com.example.ipandaitems.model.entry.home.HomeMarvellBean;
import com.example.ipandaitems.model.entry.home.HomeRollingBean;
import com.example.ipandaitems.model.entry.livechina.livechinaBean;
import com.example.ipandaitems.model.entry.livechina.livechinacontentbean;
import com.example.ipandaitems.model.entry.livechina.livechinavideobean;
import com.example.ipandaitems.model.entry.originalbean;
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

    @Override
    public void RequestHomeMarvellGet(Observer<HomeMarvellBean> observer) {
//        RetrofitUtils.getmRetrofitUtils_Demo().GetHomeMarvellwork(observer);
    }

    @Override
    public void RequestHomeRollingGet(Observer<HomeRollingBean> observer) {
//        RetrofitUtils.getmRetrofitUtils_Demo().GetHomeRollingwork(observer);
    }

    @Override
    public void RequestOriGinalGet(Observer<originalbean> observer) {

    }

    @Override
    public void doGet(Observer<PLHome> observer) {

    }

    @Override
    public void doGets(Observer<PLLive> observer) {

    }

    @Override
    public void doAmaPhotoes(PLAmaPhotoes observer) {

    }

    @Override
    public void doTop(Observer<TopBean> observer) {

    }

    @Override
    public void doTopList(String url, Observer<TopListBean> observer) {

    }

    @Override
    public void RequestChinaGet(Observer<livechinaBean> observer) {

    }

    @Override
    public void RequestChinaContentGet(String url, Observer<livechinacontentbean> observer) {

    }

    @Override
    public void RequestChinaVideoGet(Map<String, String> map, Observer<livechinavideobean> observer) {

    }

}

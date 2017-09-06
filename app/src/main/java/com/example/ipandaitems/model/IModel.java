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

import java.util.Map;

import io.reactivex.Observer;

//xx
public interface IModel  {
    void RequestGet(Observer<Bean> observer);

    void RequestPost(Map<String, String> map, Observer<Bean> observer);

    void AnnRequsetGet(Observer<AnnBean> observer);

    void RequestHomeGet(Observer<HomeBean> observer);


    //    熊猫直播 第一次网络获取
    void doGet(Observer<PLHome> observer);

    //    熊猫直播 第二次网络获取
    void doGets(Observer<PLLive> observer);

    //    熊猫直播 精彩一刻
    void doAmaPhotoes(PLAmaPhotoes observer);

    // 熊猫播报top
    void doTop(Observer<TopBean> observer);

    //topList
    void doTopList(String url, Observer<TopListBean> observer);

    void RequestChinaGet(Observer<livechinaBean> observer);

    void RequestChinaContentGet(String url, Observer<livechinacontentbean> observer);

    void RequestChinaVideoGet(Map<String, String> map, Observer<livechinavideobean> observer);

    void RequestHomeMarvellGet(Observer<HomeMarvellBean> observer);

    void RequestHomeRollingGet(Observer<HomeRollingBean> observer);

    void RequestOriGinalGet(Observer<originalbean> observer);
}

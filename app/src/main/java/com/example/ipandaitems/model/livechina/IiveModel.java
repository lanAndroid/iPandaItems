package com.example.ipandaitems.model.livechina;


import com.example.ipandaitems.model.Callback;
import com.example.ipandaitems.model.entry.livechina.livechinavideobean;
import com.example.ipandaitems.view.livechina.entity.LiveChinaAllTablist;
import com.example.ipandaitems.view.livechina.entity.LiveChinaBean;

// 直播中国
public interface IiveModel {

    void RequestChinaGet(Callback<LiveChinaAllTablist> callback);

    void RequestChinaContentGet(String url, Callback<LiveChinaBean> callback);

    void RequestChinaVideoGet(String url, Callback<livechinavideobean> callback);

}

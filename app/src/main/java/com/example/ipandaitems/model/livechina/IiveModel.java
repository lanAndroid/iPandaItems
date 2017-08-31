package com.example.ipandaitems.model.livechina;


import com.example.ipandaitems.model.Callback;
import com.example.ipandaitems.model.entry.livechina.livechinaBean;
import com.example.ipandaitems.model.entry.livechina.livechinacontentbean;
import com.example.ipandaitems.model.entry.livechina.livechinavideobean;

// 直播中国
public interface IiveModel {

    void RequestChinaGet(Callback<livechinaBean> callback);

    void RequestChinaContentGet(String url, Callback<livechinacontentbean> callback);

    void RequestChinaVideoGet(String url, Callback<livechinavideobean> callback);

}

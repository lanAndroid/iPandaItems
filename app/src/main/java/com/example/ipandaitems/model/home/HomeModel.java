package com.example.ipandaitems.model.home;


import com.example.ipandaitems.model.Callback;
import com.example.ipandaitems.model.entry.home.HomeBean;
import com.example.ipandaitems.model.entry.home.HomeMarvellBean;
import com.example.ipandaitems.model.entry.home.HomeRollingBean;

//xx
public interface HomeModel {

    void RequestHomeGet(Callback<HomeBean> callback);

    void RequestHomeMarvellGet(Callback<HomeMarvellBean> callback);

    void RequestHomeRollingGet(Callback<HomeRollingBean> callback);


}

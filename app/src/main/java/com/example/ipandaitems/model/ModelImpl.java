package com.example.ipandaitems.model;


import com.example.ipandaitems.model.entry.AnnBean;
import com.example.ipandaitems.model.entry.Bean;
import com.example.ipandaitems.model.entry.HomeBean;
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

}

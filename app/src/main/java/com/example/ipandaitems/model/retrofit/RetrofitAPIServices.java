package com.example.ipandaitems.model.retrofit;


import com.example.ipandaitems.model.entry.AnnBean;
import com.example.ipandaitems.model.entry.Bean;
import com.example.ipandaitems.model.entry.HomeBean;
import com.example.ipandaitems.model.entry.livechina.livechinaBean;
import com.example.ipandaitems.utils.UrlUtils;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by 韩志军 on 2017/8/19.
 */

public interface RetrofitAPIServices {

    @FormUrlEncoded
    @POST("http://appnew.ccoo.cn/appserverapi.ashx")
    Observable<Bean> getServices(@FieldMap Map<String, String> map);

    @GET("http://123.206.14.104:8080/TakeoutService/home?latitude=116.30142&longitude=40.05087")
    Observable<Bean> getServicesGET();

    @GET("http://www.ipanda.com/kehuduan/video/index.json")
    Observable<AnnBean> annServicesGET();

    @GET("http://www.ipanda.com/kehuduan/video/index.json")
    Observable<HomeBean> getHomeGET();


    // 直播中国
    @GET(UrlUtils.LIVECHINAURL)
    Observable<livechinaBean> getLiveChinaGET();

}

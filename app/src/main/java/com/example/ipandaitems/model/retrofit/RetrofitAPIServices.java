package com.example.ipandaitems.model.retrofit;


import com.example.ipandaitems.model.entry.AnnBean;
import com.example.ipandaitems.model.entry.Bean;
import com.example.ipandaitems.model.entry.PanadaBean;
import com.example.ipandaitems.model.entry.RegisterBean;
import com.example.ipandaitems.model.entry.TopBean;
import com.example.ipandaitems.model.entry.TopListBean;
import com.example.ipandaitems.model.entry.VideoBeanr;
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
import com.example.ipandaitems.utils.UrlUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

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

    @GET(UrlUtils.HOMEURLALL)
    Observable<HomeBean> getHomeGET();



    // 直播中国
    @GET(UrlUtils.LIVECHINAURL)
    Observable<livechinaBean> getLiveChinaGET();

    //    熊猫直播 第一次网络获取
    @GET(UrlUtils.PANDALIVE)
    Observable<PLHome> getPandaLiveGET();

    //    熊猫直播 第二次网络获取
    @GET(UrlUtils.MULITANGLE)
    Observable<PLLive> getPandaLivesGET();

    //    熊猫直播 精彩一刻
    @GET(UrlUtils.ORIGINALNEWS)
    Observable<PLAmaPhotoes> getPLAmaPhotoesGET();


    @GET()
    Observable<livechinacontentbean> getLiveChinaContentGET(@Url String url);


    @GET()
    Observable<livechinavideobean> getLiveChinaVideoGrt(@Url String url);

    @GET("http://www.ipanda.com/kehuduan/shipinliebieye/jingcaiyike/index.json")
    Observable<HomeMarvellBean> getHomeMarvellGET();

    @GET("http://www.ipanda.com/kehuduan/shipinliebieye/video/index.json")
    Observable<HomeRollingBean> getHomeRollingGET();

    @GET("http://www.ipanda.com/kehuduan/PAGE14501767715521482/index.json")
    Observable<originalbean> getOriGinalbeanGET();

    @GET(UrlUtils.TOP_MAP)
    Observable<TopBean> getTopGET();

    //TopList
    @GET
    Observable<TopListBean> getTopList(@Url String url);
    //滚滚视频item进入后的数据
    @GET
    Observable<PanadaBean> getPanada(@Url String url);

    @GET
    Observable<VideoBeanr> getVideo(@Url  String url);


    //注册
    @POST
    Observable<ResponseBody> postReist(@Url String url , @QueryMap HashMap<String,String> map);
    //获取验证码
    @POST
    Observable<ResponseBody> postRegistCode(@Url String url,@QueryMap HashMap<String,String> map);

}


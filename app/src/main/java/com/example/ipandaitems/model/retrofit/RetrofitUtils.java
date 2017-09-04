package com.example.ipandaitems.model.retrofit;


import com.example.ipandaitems.model.entry.AnnBean;
import com.example.ipandaitems.model.entry.Bean;
import com.example.ipandaitems.model.entry.TopBean;
import com.example.ipandaitems.model.entry.TopListBean;
import com.example.ipandaitems.model.entry.home.HomeBean;
import com.example.ipandaitems.model.entry.home.HomeMarvellBean;
import com.example.ipandaitems.model.entry.home.HomeRollingBean;
import com.example.ipandaitems.model.entry.home.HomeZhiBoVideoBean;
import com.example.ipandaitems.model.entry.livechina.livechinaBean;
import com.example.ipandaitems.model.entry.livechina.livechinacontentbean;
import com.example.ipandaitems.model.entry.livechina.livechinavideobean;
import com.example.ipandaitems.model.entry.originalbean;
import com.example.ipandaitems.model.entry.pandalive.PLAmaPhotoes;
import com.example.ipandaitems.model.entry.pandalive.PLHome;
import com.example.ipandaitems.model.entry.pandalive.PLLive;
import com.example.ipandaitems.model.entry.pandalive.PLVideo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 韩志军 on 2017/8/19.
 */

public class RetrofitUtils {
    private static RetrofitUtils mRetrofitUtils = null;
    private final RetrofitAPIServices apiServices;
    String url = "http://115.182.9.189/api/getVideoInfoForCBox.do";

    Map<String, String> map = new HashMap<>();

    private RetrofitUtils() {
        OkHttpClient okhttp = new OkHttpClient.Builder()
                .connectTimeout(50, TimeUnit.SECONDS)
                .readTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS).build();
        apiServices = new Retrofit.Builder()
                .client(okhttp)
                .baseUrl("https://www.baidu.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build().create(RetrofitAPIServices.class);

    }

    public static RetrofitUtils getmRetrofitUtils_Demo() {
        if (mRetrofitUtils == null)
            mRetrofitUtils = new RetrofitUtils();
        return mRetrofitUtils;
    }


    public void PostNetwork(Map<String, String> map, Observer<Bean> observer) {
        Observable<Bean> services = apiServices.getServices(map);
        services.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void GetNetwork(Observer<Bean> observer) {
        Observable<Bean> services = apiServices.getServicesGET();
        services.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);

        Observable<HomeBean> homeBeanObservable = apiServices.getHomeGET();

        services.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }


    //首页 精彩一刻网络请求
    public void GetHomeMarvellwork(Observer<HomeMarvellBean> observer) {

        Observable<HomeMarvellBean> MarvellObservable = apiServices.getHomeMarvellGET();

        MarvellObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    //首页 滚滚视频网络请求
    public void GetHomeRollingwork(Observer<HomeRollingBean> observer) {

        Observable<HomeRollingBean> RollingObservable = apiServices.getHomeRollingGET();

        RollingObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void annGetNetWork(Observer<AnnBean> observer) {
        Observable<AnnBean> annBeanObservable = apiServices.annServicesGET();
        annBeanObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    // 直播中国
    public void chinaGetNetWork(Observer<livechinaBean> observer) {
        Observable<livechinaBean> annBeanObservable = apiServices.getLiveChinaGET();
        annBeanObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    //    熊猫首页特 第一次网络获取
    public void getPandaLive(Observer<PLHome> observer) {
        Observable<PLHome> pandaLiveGET = apiServices.getPandaLiveGET();
        pandaLiveGET.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    //    熊猫首页第二次网络获取
    public void getPandaLives(Observer<PLLive> observer) {
        Observable<PLLive> pandaLiveGET = apiServices.getPandaLivesGET();
        pandaLiveGET.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    //    熊猫直播 播放视频
    public void getVideo(String url, Observer<PLVideo> observer) {
        Observable<PLVideo> plVideoObservable = apiServices.getPLVideo(url);
        plVideoObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    //    熊猫直播 精彩一刻
    public void getPLAmaPhotoes(Observer<PLAmaPhotoes> observer) {
        Observable<PLAmaPhotoes> plAmaPhotoesGET = apiServices.getPLAmaPhotoesGET();
        plAmaPhotoesGET.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }


    public void getLiveChinaContent(String url, Observer<livechinacontentbean> observer) {
        Observable<livechinacontentbean> livechinacontent = apiServices.getLiveChinaContentGET(url);
        livechinacontent.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void getLiveChinaVideo(String url, Observer<livechinavideobean> observer) {
        Observable<livechinavideobean> LiveChinaVideoGrt = apiServices.getLiveChinaVideoGrt(url);
        LiveChinaVideoGrt.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void getOriGinal(Observer<originalbean> observer) {
        Observable<originalbean> LiveChinaVideoGrt = apiServices.getOriGinalbeanGET();
        LiveChinaVideoGrt.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void getTOP(Observer<TopBean> observer) {
        Observable<TopBean> topGET = apiServices.getTopGET();
        topGET.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    //TopList列表
    public void getTopList(String url, Observer<TopListBean> observer) {
        Observable<TopListBean> topList = apiServices.getTopList(url);
        topList.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }


}

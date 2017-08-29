package com.example.ipandaitems.model.retrofit;


import com.example.ipandaitems.model.entry.AnnBean;
import com.example.ipandaitems.model.entry.Bean;
import com.example.ipandaitems.model.entry.HomeBean;
import com.example.ipandaitems.model.entry.livechina.livechinaBean;
import com.example.ipandaitems.model.entry.livechina.livechinacontentbean;
import com.example.ipandaitems.model.entry.livechina.livechinavideobean;
import com.example.ipandaitems.model.entry.pandalive.PLAmaPhotoes;
import com.example.ipandaitems.model.entry.pandalive.PLHome;
import com.example.ipandaitems.model.entry.pandalive.PLLive;

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

    //首页 网络请求
    public void GetHomeNetwork(Observer<HomeBean> observer) {

        Observable<HomeBean> homeBeanObservable = apiServices.getHomeGET();

        homeBeanObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
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

    //    熊猫直播 第一次网络获取
    public void getPandaLive(Observer<PLHome> observer) {
        Observable<PLHome> pandaLiveGET = apiServices.getPandaLiveGET();
        pandaLiveGET.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    //    熊猫直播 第二次网络获取
    public void getPandaLives(Observer<PLLive> observer) {
        Observable<PLLive> pandaLiveGET = apiServices.getPandaLivesGET();
        pandaLiveGET.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
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

    public void getLiveChinaVideo(Map<String, String> map, Observer<livechinavideobean> observer) {
        Observable<livechinavideobean> LiveChinaVideoGrt = apiServices.getLiveChinaVideoGrt(map);
        LiveChinaVideoGrt.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

}

package com.example.ipandaitems.model.retrofit;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Message;
import android.webkit.CookieManager;

import com.example.ipandaitems.App;
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
import com.example.ipandaitems.view.registerandlogin.fragment.PhoneRegFragment;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.eventbus.Subscribe;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 韩志军 on 2017/8/19.
 */

public class RetrofitUtils {
   // private static String JSESSIONID = null ;
    private static RetrofitUtils mRetrofitUtils = null;
    private final RetrofitAPIServices apiServices;
    private final RetrofitAPIServices apiService;
    String from = "http://cbox_mobile.regclientuser.cntv.cn";

    private RetrofitUtils() {
       /*CookieJar cookieJar=new CookieJar() {
            List<Cookie> array=new ArrayList<>();
            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
               array=cookies;
                for (Cookie cookie:array) {
                    if ("JSESSIONID".equals(cookie.name())){
                        JSESSIONID=cookie.value();
                        System.out.println(JSESSIONID+"--------------------工具类");
                    }
                }
            }
            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                return array;
            }
        };*/

        //OkHttpClient client= new  OkHttpClient.Builder().cookieJar(cookieJar).build();
        //获取验证码
        OkHttpClient okhttp = new OkHttpClient.Builder()
                .connectTimeout(50, TimeUnit.SECONDS)
                .readTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS)
               // .cookieJar(cookieJar)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request=chain.request().newBuilder()
                        .addHeader("Referer", URLEncoder.encode(from, "UTF-8"))
                        .addHeader("User-Agent", URLEncoder.encode("CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"))
                        .addHeader("Cookie", "JSESSIONID=" + PhoneRegFragment.JSESSIONID)
                                .build();
                        return chain.proceed(request);
                    }
                })
                //.retryOnConnectionFailure(true)
                .build();
        OkHttpClient zhuce = new OkHttpClient.Builder()
                .connectTimeout(50, TimeUnit.SECONDS)
                .readTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS)
                // .cookieJar(cookieJar)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request=chain.request().newBuilder()
                                .addHeader("Referer",  URLEncoder.encode("http://cbox_mobile.regclientuser.cntv.cn", "UTF-8"))
                                .addHeader("User-Agent",  URLEncoder.encode("CNTV_APP_CLIENT_CBOX_MOBILE", "UTF-8"))
                                .addHeader("Cookie", "JSESSIONID=" + PhoneRegFragment.JSESSIONID)
                                .build();
                        return chain.proceed(request);
                    }
                })
                //.retryOnConnectionFailure(true)
                .build();
        apiServices = new Retrofit.Builder()
                .client(okhttp)
                .baseUrl("https://www.baidu.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build().create(RetrofitAPIServices.class);

        apiService = new Retrofit.Builder()
                .client(zhuce)
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
    //滚滚视频item进入后的
    public void getPanadaData(String url, Observer<PanadaBean> observer){
        Observable<PanadaBean> panada = apiServices.getPanada(url);
        panada.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
    //图片点击进入视频
    public void getVideoData(String url,Observer<VideoBeanr> observer){
        Observable<VideoBeanr> video=apiServices.getVideo(url);
        video.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    //注册
    public void postRegistNumber(String url,HashMap<String,String> map,Observer<Object> observer){
        Observable<ResponseBody> responseBodyObservable = apiService.postReist(url, map);
        responseBodyObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
    //短信
    public void postRegist(String url,HashMap<String,String> map,Observer<Object> observer){
        Observable<ResponseBody> responseBodyObservable = apiServices.postRegistCode(url, map);
        responseBodyObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }
}

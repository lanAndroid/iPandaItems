package com.example.ipandaitems.model.livechina;


import com.example.ipandaitems.model.Callback;
import com.example.ipandaitems.model.entry.livechina.livechinavideobean;
import com.example.ipandaitems.model.retrofit.RetrofitUtils;
import com.example.ipandaitems.view.livechina.entity.LiveChinaAllTablist;
import com.example.ipandaitems.view.livechina.entity.LiveChinaBean;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


public class liveModelImpl implements IiveModel {


    @Override
    public void RequestChinaGet(final Callback<LiveChinaAllTablist> callback) {
        RetrofitUtils.getmRetrofitUtils_Demo().chinaGetNetWork(new Observer<LiveChinaAllTablist>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull LiveChinaAllTablist livechinaBean) {
                callback.succeed(livechinaBean);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                callback.nothing(e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void RequestChinaContentGet(String url, final Callback<LiveChinaBean> callback) {
        RetrofitUtils.getmRetrofitUtils_Demo().getLiveChinaContent(url, new Observer<LiveChinaBean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull LiveChinaBean livechinacontentbean) {
                callback.succeed(livechinacontentbean);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                callback.nothing(e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void RequestChinaVideoGet(String url, final Callback<livechinavideobean> callback) {
        RetrofitUtils.getmRetrofitUtils_Demo().getLiveChinaVideo(url, new Observer<livechinavideobean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull livechinavideobean livechinavideobean) {
                callback.succeed(livechinavideobean);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                callback.nothing(e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }
}

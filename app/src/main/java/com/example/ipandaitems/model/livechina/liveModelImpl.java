package com.example.ipandaitems.model.livechina;


import com.example.ipandaitems.model.Callback;
import com.example.ipandaitems.model.entry.livechina.livechinaBean;
import com.example.ipandaitems.model.entry.livechina.livechinacontentbean;
import com.example.ipandaitems.model.entry.livechina.livechinavideobean;
import com.example.ipandaitems.model.retrofit.RetrofitUtils;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


public class liveModelImpl implements IiveModel {


    @Override
    public void RequestChinaGet(final Callback<livechinaBean> callback) {
        RetrofitUtils.getmRetrofitUtils_Demo().chinaGetNetWork(new Observer<livechinaBean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull livechinaBean livechinaBean) {
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
    public void RequestChinaContentGet(String url, final Callback<livechinacontentbean> callback) {
        RetrofitUtils.getmRetrofitUtils_Demo().getLiveChinaContent(url, new Observer<livechinacontentbean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull livechinacontentbean livechinacontentbean) {
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

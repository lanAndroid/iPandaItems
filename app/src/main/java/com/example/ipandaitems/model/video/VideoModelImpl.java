package com.example.ipandaitems.model.video;


import com.example.ipandaitems.model.Callback;
import com.example.ipandaitems.model.entry.TopBean;
import com.example.ipandaitems.model.entry.TopListBean;
import com.example.ipandaitems.model.retrofit.RetrofitUtils;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


public class VideoModelImpl implements VideoModel {


    @Override
    public void doTop(final Callback<TopBean> callback) {
        RetrofitUtils.getmRetrofitUtils_Demo().getTOP(new Observer<TopBean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull TopBean topBean) {
                callback.succeed(topBean);
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
    public void doTopList(String url, final Callback<TopListBean> callback) {
        RetrofitUtils.getmRetrofitUtils_Demo().getTopList(url, new Observer<TopListBean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull TopListBean topListBean) {
                callback.succeed(topListBean);
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

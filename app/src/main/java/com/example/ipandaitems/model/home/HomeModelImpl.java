package com.example.ipandaitems.model.home;


import com.example.ipandaitems.model.Callback;
import com.example.ipandaitems.model.entry.home.HomeBean;
import com.example.ipandaitems.model.entry.home.HomeMarvellBean;
import com.example.ipandaitems.model.entry.home.HomeRollingBean;
import com.example.ipandaitems.model.entry.home.HomeVideoBean;
import com.example.ipandaitems.model.retrofit.RetrofitUtils;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


public class HomeModelImpl implements HomeModel {

    @Override
    public void RequestHomeGet(final Callback<HomeBean> callback) {
       /* RetrofitUtils.getmRetrofitUtils_Demo().GetHomeNetwork(new Observer<HomeBean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull HomeBean homeBean) {
                callback.succeed(homeBean);

            }

            @Override
            public void onError(@NonNull Throwable e) {
                callback.nothing(e.toString());
            }

            @Override
            public void onComplete() {

            }
        });*/
    }

    @Override
    public void RequestHomeMarvellGet(final Callback<HomeMarvellBean> callback) {
        RetrofitUtils.getmRetrofitUtils_Demo().GetHomeMarvellwork(new Observer<HomeMarvellBean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull HomeMarvellBean homeMarvellBean) {
                callback.succeed(homeMarvellBean);
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
    public void RequestHomeRollingGet(final Callback<HomeRollingBean> callback) {
        RetrofitUtils.getmRetrofitUtils_Demo().GetHomeRollingwork(new Observer<HomeRollingBean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull HomeRollingBean homeRollingBean) {
                callback.succeed(homeRollingBean);
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
    public void RequestHomeVoidGet(final Callback<HomeVideoBean> callback) {
        RetrofitUtils.getmRetrofitUtils_Demo().GetHomeVidoBean(new Observer<HomeVideoBean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull HomeVideoBean homeVideoBean) {
                callback.succeed(homeVideoBean);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}

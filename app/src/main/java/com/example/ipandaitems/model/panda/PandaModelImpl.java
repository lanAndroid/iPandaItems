package com.example.ipandaitems.model.panda;


import com.example.ipandaitems.model.Callback;
import com.example.ipandaitems.model.entry.pandalive.PLAmaPhotoes;
import com.example.ipandaitems.model.entry.pandalive.PLHome;
import com.example.ipandaitems.model.entry.pandalive.PLLive;
import com.example.ipandaitems.model.retrofit.RetrofitUtils;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


public class PandaModelImpl implements PandaModel {


    @Override
    public void doGet(final Callback<PLHome> callback) {
        RetrofitUtils.getmRetrofitUtils_Demo().getPandaLive(new Observer<PLHome>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull PLHome plHome) {
                callback.succeed(plHome);
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
    public void doGets(final Callback<PLLive> callback) {
        RetrofitUtils.getmRetrofitUtils_Demo().getPandaLives(new Observer<PLLive>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull PLLive plLive) {
                callback.succeed(plLive);
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
    public void doAmaPhotoes(final Callback<PLAmaPhotoes> callback) {
        RetrofitUtils.getmRetrofitUtils_Demo().getPLAmaPhotoes(new Observer<PLAmaPhotoes>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull PLAmaPhotoes plAmaPhotoes) {
                callback.succeed(plAmaPhotoes);
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

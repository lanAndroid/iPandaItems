package com.example.ipandaitems.model.announce;


import com.example.ipandaitems.model.Callback;
import com.example.ipandaitems.model.entry.AnnBean;
import com.example.ipandaitems.model.retrofit.RetrofitUtils;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


public class AnnModelImpl implements AnnModel {


    @Override
    public void AnnRequsetGet(final Callback<AnnBean> callback) {
        RetrofitUtils.getmRetrofitUtils_Demo().annGetNetWork(new Observer<AnnBean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull AnnBean annBean) {
                callback.succeed(annBean);
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

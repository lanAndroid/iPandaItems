package com.example.ipandaitems.model.original;

import com.example.ipandaitems.model.Callback;
import com.example.ipandaitems.model.entry.originalbean;
import com.example.ipandaitems.model.retrofit.RetrofitUtils;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by 张豫耀 on 2017/8/31.
 */

public class OriModelImpl implements OriModel {
    @Override
    public void RequestOriGinalGet(final Callback<originalbean> callback) {
        RetrofitUtils.getmRetrofitUtils_Demo().getOriGinal(new Observer<originalbean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull originalbean originalbean) {
                callback.succeed(originalbean);
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

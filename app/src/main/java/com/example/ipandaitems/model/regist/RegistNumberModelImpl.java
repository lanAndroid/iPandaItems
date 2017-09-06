package com.example.ipandaitems.model.regist;

import com.example.ipandaitems.model.Callback;
import com.example.ipandaitems.model.retrofit.RetrofitUtils;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * Created by 1 on 2017/9/6.
 */

public class RegistNumberModelImpl implements RegistNumberModel {
    @Override
    public void registPost(String url, HashMap<String, String> map, final Callback<ResponseBody> callback) {
        RetrofitUtils.getmRetrofitUtils_Demo().postRegistNumber(url, map, new Observer<Object>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                ResponseBody res=(ResponseBody)o;
                callback.succeed(res);
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

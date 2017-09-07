package com.example.ipandaitems.model.phone;

import android.util.Log;

import com.example.ipandaitems.model.Callback;
import com.example.ipandaitems.model.retrofit.RetrofitUtils;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by 张豫耀 on 2017/9/6.
 */

public class PhoneModelImpl implements PhoneModel {
    @Override
    public void RetrofitYanzhengMa(String phone, String yanzhengma, String cookie, final Callback<String> callback) {
        String url = "http://reg.cntv.cn/regist/getVerifiCode.action?Referer=http://cbox_mobile.regclientuser.cntv.cn&User-Agent=CNTV_APP_CLIENT_CBOX_MOBILE&Cookie=JSESSIONID=" + cookie + "&method=getRequestVerifiCodeM&mobile=" + phone + "&verfiCodeType=1&verificationCode=" + yanzhengma;
        Log.e("------------->", url);
        RetrofitUtils.getmRetrofitUtils_Demo().GetYanzhengma(url, new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull String s) {
                callback.succeed(s);
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

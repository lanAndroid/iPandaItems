package com.example.ipandaitems.presenter.regis;

import com.example.ipandaitems.model.Callback;
import com.example.ipandaitems.model.regist.RegistModel;
import com.example.ipandaitems.model.regist.RegistModelImpl;
import com.example.ipandaitems.view.registerandlogin.RegistInfo;

import java.util.HashMap;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by 1 on 2017/9/5.
 */

public class RegisPresenterImpl implements RegisPresenter{
    private RegistInfo registInfo;
    private final RegistModel registModel;

    public RegisPresenterImpl(RegistInfo registInfo) {
        this.registInfo=registInfo;
        registModel = new RegistModelImpl();
    }

    @Override
    public void getCode(String url, HashMap<String, String> map) {
        registModel.postCode(url, map, new Callback<ResponseBody>() {
            @Override
            public void succeed(ResponseBody responseBody) {
                registInfo.onSuccess(responseBody);
            }

            @Override
            public void nothing(String str) {
                registInfo.onError(str);
            }
        });
    }
}

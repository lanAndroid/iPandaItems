package com.example.ipandaitems.presenter.regis;

import com.example.ipandaitems.model.Callback;
import com.example.ipandaitems.model.regist.RegistNumberModel;
import com.example.ipandaitems.model.regist.RegistNumberModelImpl;
import com.example.ipandaitems.view.registerandlogin.RegistNumberInfo;

import java.util.HashMap;

import okhttp3.ResponseBody;

/**
 * Created by 1 on 2017/9/6.
 */

public class RegisNumberPresenterImpl implements RegisNumberPresenter {

    private RegistNumberInfo registNumberInfo;
    private final RegistNumberModel registNumberModel;

    public RegisNumberPresenterImpl(RegistNumberInfo registNumberInfo) {
        this.registNumberInfo=registNumberInfo;
        registNumberModel = new RegistNumberModelImpl();
    }


    @Override
    public void postParams(String url, HashMap<String, String> map) {
        registNumberModel.registPost(url, map, new Callback<ResponseBody>() {
            @Override
            public void succeed(ResponseBody responseBody) {
                registNumberInfo.onNumberSuccess(responseBody);
            }

            @Override
            public void nothing(String str) {
                registNumberInfo.onNumberError(str);
            }
        });
    }
}

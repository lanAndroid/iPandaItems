package com.example.ipandaitems.view.registerandlogin;

import okhttp3.ResponseBody;

/**
 * Created by 1 on 2017/9/6.
 */

public interface RegistNumberInfo {
    void onNumberSuccess(ResponseBody responseBody);
    void onNumberError(String e);
}

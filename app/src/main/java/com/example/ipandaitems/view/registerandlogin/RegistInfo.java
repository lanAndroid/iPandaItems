package com.example.ipandaitems.view.registerandlogin;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by 1 on 2017/9/4.
 */

public interface RegistInfo {
        void onSuccess(ResponseBody responseBody);
    void onError(String e);
}

package com.example.ipandaitems.model.phone;

import com.example.ipandaitems.model.Callback;

/**
 * Created by 张豫耀 on 2017/9/6.
 */

public interface PhoneModel {
    void RetrofitYanzhengMa(String phone, String yanzhengma,String cookie, Callback<String> callback);
}

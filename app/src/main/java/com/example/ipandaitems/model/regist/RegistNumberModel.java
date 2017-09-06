package com.example.ipandaitems.model.regist;

import com.example.ipandaitems.model.Callback;

import java.util.HashMap;

import okhttp3.ResponseBody;

/**
 * Created by 1 on 2017/9/6.
 */

public interface RegistNumberModel {
    void registPost(String url, HashMap<String,String> map, Callback<ResponseBody> callback);
}

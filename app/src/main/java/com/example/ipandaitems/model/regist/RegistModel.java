package com.example.ipandaitems.model.regist;

import com.example.ipandaitems.model.Callback;

import java.util.HashMap;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by 1 on 2017/9/5.
 */

public interface RegistModel {
       void postCode(String url,HashMap<String,String> map,Callback<ResponseBody> callback);
}

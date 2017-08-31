package com.example.ipandaitems.model;

/**
 * Created by 张豫耀 on 2017/8/30.
 */

public interface Callback<T> {
    void succeed(T t);
    void nothing(String str);
}

package com.example.ipandaitems.model.person;

import com.example.ipandaitems.model.Callback;

/**
 * Created by xiaogang on 2017/9/6.
 */

public interface IpersonModle {
    //
    void getuser(String name, String pwd, Callback callback);
    void  getname(String url,Callback callback);

}

package com.example.ipandaitems.model.home;


import com.example.ipandaitems.model.Callback;
import com.example.ipandaitems.model.entry.home.HomeBean;
import com.example.ipandaitems.model.entry.home.HomeMarvellBean;
import com.example.ipandaitems.model.entry.home.HomeRollVideo;
import com.example.ipandaitems.model.entry.home.HomeRollingBean;
<<<<<<< .merge_file_a08920
import com.example.ipandaitems.model.entry.home.HomeVideoBean;
=======
import com.example.ipandaitems.model.entry.home.HomeZhiBoVideoBean;
>>>>>>> .merge_file_a12584

//xx
public interface HomeModel {

    void RequestHomeGet(Callback<HomeBean> callback);

    void RequestHomeMarvellGet(Callback<HomeMarvellBean> callback);

    void RequestHomeRollingGet(Callback<HomeRollingBean> callback);

<<<<<<< .merge_file_a08920
    void RequestHomeVoidGet(Callback<HomeVideoBean> callback);
=======
    //modle 给网络地址传值，并返回视频实体类
    void RequestHomeRollingGet(String url,Callback<HomeRollVideo> callback);


    void RequestHomeVoidGet(String url,Callback<HomeZhiBoVideoBean> callback);

>>>>>>> .merge_file_a12584



}

package com.example.ipandaitems.view.home;


import com.example.ipandaitems.model.entry.home.HomeBean;
import com.example.ipandaitems.model.entry.home.HomeChianVideo;
import com.example.ipandaitems.model.entry.home.HomeMarvellBean;
import com.example.ipandaitems.model.entry.home.HomePandanVideo;
import com.example.ipandaitems.model.entry.home.HomeRollVideo;
import com.example.ipandaitems.model.entry.home.HomeRollingBean;

/**
 * Created by xiaogang on 2017/8/25.
 */

public interface  IHomeFragment {
    void gethomebean(HomeBean homeBean);
    void gethomeMarvellbean(HomeMarvellBean homeMarvellBean);
    void gethomeRollingbean(HomeRollingBean homeRollingBean);



    void gethomePandanVideo(HomePandanVideo homePandanVideo);


    void gethomeChianVideo(HomeChianVideo homeChianVideo);

    void gethomeRollingVido(HomeRollVideo homeRollVideo);

    void gethomemarvellVido(HomeRollVideo homeRollVideo);


    void gethomebannerVido(HomeRollVideo homeRollVideo);


    void gethomebrodcastVido(HomeRollVideo homeRollVideo);

    void gethomebrodcasttwoVido(HomeRollVideo homeRollVideo);


}

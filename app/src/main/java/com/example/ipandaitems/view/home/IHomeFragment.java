package com.example.ipandaitems.view.home;


import com.example.ipandaitems.model.entry.home.HomeBean;
import com.example.ipandaitems.model.entry.home.HomeMarvellBean;
import com.example.ipandaitems.model.entry.home.HomeRollingBean;
import com.example.ipandaitems.model.entry.home.HomeVideoBean;

/**
 * Created by xiaogang on 2017/8/25.
 */

public interface  IHomeFragment {
    void gethomebean(HomeBean homeBean);
    void gethomeMarvellbean(HomeMarvellBean homeMarvellBean);
    void gethomeRollingbean(HomeRollingBean homeRollingBean);
    void gethomeViodbean(HomeVideoBean homeVideoBean);
}

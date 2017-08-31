package com.example.ipandaitems.view.video;

import com.example.ipandaitems.model.entry.TopBean;
import com.example.ipandaitems.model.entry.TopListBean;

/**
 * Created by 1 on 2017/8/29.
 */

public interface VideoInfo {
    void onSuccess(TopBean dataBean);
    void onError(String e);
    void topListSuccess(TopListBean topListBean);
    void topListError(String e);
}

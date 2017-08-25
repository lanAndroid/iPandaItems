package com.example.ipandaitems.view.announce;


import com.example.ipandaitems.model.entry.AnnBean;

/**
 * Created by 1 on 2017/8/24.
 */

public interface AnnView {
    void onSuccess(AnnBean annBean);
    void onError(String error);
}

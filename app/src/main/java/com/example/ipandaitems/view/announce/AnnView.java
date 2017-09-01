package com.example.ipandaitems.view.announce;


import com.example.ipandaitems.model.entry.AnnBean;
import com.example.ipandaitems.model.entry.PanadaBean;
import com.example.ipandaitems.model.entry.VideoBeanr;

/**
 * Created by 1 on 2017/8/24.
 */

public interface AnnView {
    void onSuccess(AnnBean annBean);
    void onError(String error);
    void panadaSuccess(PanadaBean panadaBean);
    void videoSuccess(VideoBeanr videoBeanr);
}

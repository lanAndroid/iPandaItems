package com.example.ipandaitems.model.announce;


import com.example.ipandaitems.model.Callback;
import com.example.ipandaitems.model.entry.VideoBeanr;
import com.example.ipandaitems.model.entry.AnnBean;
import com.example.ipandaitems.model.entry.PanadaBean;

//xx
public interface AnnModel {
    void AnnRequsetGet(Callback<AnnBean> callback);

    void panadaRequestGet(String url, Callback<PanadaBean> callback);

    void videoRequestGet(String url, Callback<VideoBeanr> callback);
}

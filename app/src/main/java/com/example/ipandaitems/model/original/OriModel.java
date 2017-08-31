package com.example.ipandaitems.model.original;

import com.example.ipandaitems.model.Callback;
import com.example.ipandaitems.model.entry.originalbean;

/**
 * Created by 张豫耀 on 2017/8/31.
 */

public interface OriModel {
    void RequestOriGinalGet(Callback<originalbean> callback);
}

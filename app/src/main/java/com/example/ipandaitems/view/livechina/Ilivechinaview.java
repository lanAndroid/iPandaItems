package com.example.ipandaitems.view.livechina;

import com.example.ipandaitems.model.entry.livechina.livechinavideobean;
import com.example.ipandaitems.view.livechina.entity.LiveChinaAllTablist;
import com.example.ipandaitems.view.livechina.entity.LiveChinaBean;

/**
 * Created by 张豫耀 on 2017/8/25.
 */

public interface Ilivechinaview {
    void succeed(LiveChinaAllTablist livechinaBean);

    void succeedcontent(LiveChinaBean livechinacontentbean);

    void succeedvideo(livechinavideobean livechinavideobean);

    void Failure();
}

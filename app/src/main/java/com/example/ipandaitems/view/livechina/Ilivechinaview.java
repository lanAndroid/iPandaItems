package com.example.ipandaitems.view.livechina;

import com.example.ipandaitems.model.entry.livechina.livechinaBean;
import com.example.ipandaitems.model.entry.livechina.livechinacontentbean;
import com.example.ipandaitems.model.entry.livechina.livechinavideobean;

/**
 * Created by 张豫耀 on 2017/8/25.
 */
//aaaaaaa
public interface Ilivechinaview {
    void succeed(livechinaBean livechinaBean);

    void succeedcontent(livechinacontentbean livechinacontentbean);

    void succeedvideo(livechinavideobean livechinavideobean);

    void Failure();
}

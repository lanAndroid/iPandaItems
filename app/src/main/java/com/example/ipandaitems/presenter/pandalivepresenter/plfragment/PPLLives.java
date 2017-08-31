package com.example.ipandaitems.presenter.pandalivepresenter.plfragment;

import com.example.ipandaitems.model.Callback;
import com.example.ipandaitems.model.entry.pandalive.PLHome;
import com.example.ipandaitems.model.entry.pandalive.PLLive;
import com.example.ipandaitems.model.panda.PandaModelImpl;
import com.example.ipandaitems.view.pandalive.plfragment.PLFLiveView;

/**
 * @name yanantian
 * @motto 莫羡他人谢语花, 腹有诗书气自华
 * @E-mail 1173568715@qq.com
 * @WX 15978622391
 */

public class PPLLives implements PPLLive {

    private PLFLiveView pplLive;
    PandaModelImpl ml;

    public PPLLives(PLFLiveView pplLive) {
        this.pplLive = pplLive;
        ml = new PandaModelImpl();
    }

    @Override
    public void getViews() {
        ml.doGet(new Callback<PLHome>() {
            @Override
            public void succeed(PLHome plHome) {
                pplLive.getDatas(plHome);
            }

            @Override
            public void nothing(String str) {

            }
        });
    }

    @Override
    public void getLives() {
        ml.doGets(new Callback<PLLive>() {
            @Override
            public void succeed(PLLive plLive) {
                pplLive.getDataLive(plLive);
            }

            @Override
            public void nothing(String str) {

            }
        });
    }
}

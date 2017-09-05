package com.example.ipandaitems.presenter.pandalivepresenter.plfragment;

import com.example.ipandaitems.model.Callback;
import com.example.ipandaitems.model.entry.pandalive.PLAmaPhotoes;
import com.example.ipandaitems.model.panda.PandaModelImpl;
import com.example.ipandaitems.view.pandalive.plfragment.PLF2AmaPhotoesView;
import com.example.ipandaitems.view.pandalive.plfragment.PLF3Courage;
import com.example.ipandaitems.view.pandalive.plfragment.PLF4Sprout;
import com.example.ipandaitems.view.pandalive.plfragment.PLF5Record;
import com.example.ipandaitems.view.pandalive.plfragment.PLF6TOP;
import com.example.ipandaitems.view.pandalive.plfragment.PLF7Things;
import com.example.ipandaitems.view.pandalive.plfragment.PLF8Specials;
import com.example.ipandaitems.view.pandalive.plfragment.PLF9Original;

/**
 * @name yanantian
 * @motto 莫羡他人谢语花, 腹有诗书气自华
 * @E-mail 1173568715@qq.com
 * @WX 15978622391
 */

public class PPLAmaPhotoe4 implements PPLAmaPhotoes {
    PandaModelImpl ml;
    PLF4Sprout plf4Sprout;

    public PPLAmaPhotoe4(PLF4Sprout plf4Sprout) {
        this.plf4Sprout = plf4Sprout;
        ml = new PandaModelImpl();
    }

    @Override
    public void initData() {
        ml.doAmaPhotoes(new Callback<PLAmaPhotoes>() {
            @Override
            public void succeed(PLAmaPhotoes plAmaPhotoes) {
                plf4Sprout.initData(plAmaPhotoes);
            }

            @Override
            public void nothing(String str) {

            }
        });
    }
}

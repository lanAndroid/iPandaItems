package com.example.ipandaitems.presenter.pandalivepresenter.plfragment;

import com.example.ipandaitems.model.Callback;
import com.example.ipandaitems.model.entry.pandalive.PLAmaPhotoes;
import com.example.ipandaitems.model.panda.PandaModelImpl;
import com.example.ipandaitems.view.pandalive.plfragment.PLF2AmaPhotoesView;

/**
 * @name yanantian
 * @motto 莫羡他人谢语花, 腹有诗书气自华
 * @E-mail 1173568715@qq.com
 * @WX 15978622391
 */

public class PPLAmaPhotoe implements PPLAmaPhotoes {
    PLF2AmaPhotoesView pv2;
    PandaModelImpl ml;

    public PPLAmaPhotoe(PLF2AmaPhotoesView pv2) {
        this.pv2 = pv2;
        ml = new PandaModelImpl();
    }


    @Override
    public void initData() {
        ml.doAmaPhotoes(new Callback<PLAmaPhotoes>() {
            @Override
            public void succeed(PLAmaPhotoes plAmaPhotoes) {
                pv2.initData(plAmaPhotoes);
            }

            @Override
            public void nothing(String str) {

            }
        });
    }
}

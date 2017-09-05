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

public class PPLAmaPhotoe9 implements PPLAmaPhotoes {
    PandaModelImpl ml;
    PLF9Original plf9Original;


    public PPLAmaPhotoe9(PLF9Original plf9Original) {
        this.plf9Original = plf9Original;    ml = new PandaModelImpl();
    }

    @Override
    public void initData() {
        ml.doAmaPhotoes(new Callback<PLAmaPhotoes>() {
            @Override
            public void succeed(PLAmaPhotoes plAmaPhotoes) {
                plf9Original.initData(plAmaPhotoes);
            }

            @Override
            public void nothing(String str) {

            }
        });
    }
}

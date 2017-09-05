package com.example.ipandaitems.presenter.pandalivepresenter.plfragment;

import com.example.ipandaitems.model.Callback;
import com.example.ipandaitems.model.entry.pandalive.PLAmaPhotoes;
import com.example.ipandaitems.model.panda.PandaModelImpl;
import com.example.ipandaitems.view.pandalive.plfragment.PLF5Record;

/**
 * @name yanantian
 * @motto 莫羡他人谢语花, 腹有诗书气自华
 * @E-mail 1173568715@qq.com
 * @WX 15978622391
 */

public class PPLAmaPhotoe5 implements PPLAmaPhotoes {
    PandaModelImpl ml;
    PLF5Record plf5Record;


    public PPLAmaPhotoe5(PLF5Record plf5Record) {
        this.plf5Record = plf5Record;
        ml = new PandaModelImpl();
    }

    @Override
    public void initData() {
        ml.doAmaPhotoes(new Callback<PLAmaPhotoes>() {
            @Override
            public void succeed(PLAmaPhotoes plAmaPhotoes) {
                plf5Record.initData(plAmaPhotoes);
            }

            @Override
            public void nothing(String str) {

            }
        });
    }
}

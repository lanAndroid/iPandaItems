package com.example.ipandaitems.presenter.pandalivepresenter.plfragment;

import com.example.ipandaitems.model.ModelImpl;
import com.example.ipandaitems.model.entry.pandalive.PLAmaPhotoes;
import com.example.ipandaitems.view.pandalive.plfragment.PLF2AmaPhotoesView;

/**
 * @name yanantian
 * @motto 莫羡他人谢语花, 腹有诗书气自华
 * @E-mail 1173568715@qq.com
 * @WX 15978622391
 */

public class PPLAmaPhotoe implements PPLAmaPhotoes {
    PLF2AmaPhotoesView pv2;
    ModelImpl ml;

    public PPLAmaPhotoe(PLF2AmaPhotoesView pv2) {
        this.pv2 = pv2;
        ml = new ModelImpl();
    }


    @Override
    public void initData() {
        ml.doAmaPhotoes(new PLAmaPhotoes());

    }
}

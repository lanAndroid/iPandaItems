package com.example.ipandaitems.presenter.pandalivepresenter.plfragment;

import android.support.v4.app.FragmentActivity;

import com.example.ipandaitems.model.ModelImpl;
import com.example.ipandaitems.model.entry.pandalive.PLAmaPhotoes;
import com.example.ipandaitems.view.pandalive.plfragment.PLF2AmaPhotoesView;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

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
        ml=new ModelImpl();
    }


    @Override
    public void initData() {
        ml.doAmaPhotoes(new Observer<PLAmaPhotoes>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull PLAmaPhotoes plAmaPhotoes) {
                pv2.initData(plAmaPhotoes);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}

package com.example.ipandaitems.presenter.pandalivepresenter.plfragment;

import com.example.ipandaitems.model.ModelImpl;
import com.example.ipandaitems.model.entry.pandalive.PLHome;
import com.example.ipandaitems.model.entry.pandalive.PLLive;
import com.example.ipandaitems.view.pandalive.plfragment.PLFLiveView;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @name yanantian
 * @motto 莫羡他人谢语花, 腹有诗书气自华
 * @E-mail 1173568715@qq.com
 * @WX 15978622391
 */

public class PPLLives implements PPLLive {

    private PLFLiveView pplLive;
    ModelImpl ml;

    public PPLLives(PLFLiveView pplLive) {
        this.pplLive = pplLive;
        ml = new ModelImpl();
    }

    @Override
    public void getViews() {
        ml.doGet(new Observer<PLHome>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull PLHome plHome) {
                pplLive.getDatas(plHome);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getLives() {
        ml.doGets(new Observer<PLLive>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull PLLive plLive) {
                pplLive.getDataLive(plLive);
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

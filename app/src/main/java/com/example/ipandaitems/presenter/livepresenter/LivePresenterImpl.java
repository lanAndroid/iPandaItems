package com.example.ipandaitems.presenter.livepresenter;


import com.example.ipandaitems.model.ModelImpl;
import com.example.ipandaitems.model.entry.livechina.livechinaBean;
import com.example.ipandaitems.model.entry.livechina.livechinacontentbean;
import com.example.ipandaitems.model.entry.livechina.livechinavideobean;
import com.example.ipandaitems.view.livechina.Ilivechinaview;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class LivePresenterImpl implements LiveIPresenter {

    private Ilivechinaview ilivechinaview;
    private ModelImpl model;

    public LivePresenterImpl(Ilivechinaview ilivechinaview) {
        this.ilivechinaview = ilivechinaview;
        model = new ModelImpl();
    }


    @Override
    public void chinaget() {
        model.RequestChinaGet(new Observer<livechinaBean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull livechinaBean livechinaBean) {
                ilivechinaview.succeed(livechinaBean);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                ilivechinaview.Failure();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void chinacontent(String url) {
        model.RequestChinaContentGet(url, new Observer<livechinacontentbean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull livechinacontentbean livechinacontentbean) {
                ilivechinaview.succeedcontent(livechinacontentbean);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                ilivechinaview.Failure();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void chinavideo(Map<String, String> map) {
        model.RequestChinaVideoGet(map, new Observer<livechinavideobean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull livechinavideobean livechinavideobean) {
                ilivechinaview.succeedvideo(livechinavideobean);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                ilivechinaview.Failure();
            }

            @Override
            public void onComplete() {

            }
        });
    }


}

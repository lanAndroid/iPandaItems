package com.example.ipandaitems.presenter.livepresenter;


import com.example.ipandaitems.model.ModelImpl;
import com.example.ipandaitems.model.entry.livechina.livechinaBean;
import com.example.ipandaitems.view.livechina.Ilivechinaview;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class LivePresenterImpl implements LiveIPresenter, Observer<livechinaBean> {

    private Ilivechinaview ilivechinaview;
    private ModelImpl model;

    public LivePresenterImpl(Ilivechinaview ilivechinaview) {
        this.ilivechinaview = ilivechinaview;
        model = new ModelImpl();
    }

    @Override
    public void chinaget() {
        model.RequestChinaGet(this);
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }
//aa
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
}

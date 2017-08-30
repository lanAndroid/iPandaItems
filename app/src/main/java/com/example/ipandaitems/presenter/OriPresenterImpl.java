package com.example.ipandaitems.presenter;

import com.example.ipandaitems.model.entry.originalbean;
import com.example.ipandaitems.view.originalIView;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by 张豫耀 on 2017/8/29.
 */

public class OriPresenterImpl implements OriPersenter, Observer<originalbean> {
    private originalIView originalIView;
    private ModelImpl iModel;

    public OriPresenterImpl(com.example.ipandaitems.view.originalIView originalIView) {
        this.originalIView = originalIView;
        iModel = new ModelImpl();
    }

    @Override
    public void OriGinalGet() {
        iModel.RequestOriGinalGet(this);
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull originalbean originalbean) {
        originalIView.succeed(originalbean);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        originalIView.Failure();
    }

    @Override
    public void onComplete() {

    }
}

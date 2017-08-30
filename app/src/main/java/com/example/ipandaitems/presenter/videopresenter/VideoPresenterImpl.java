package com.example.ipandaitems.presenter.videopresenter;


import com.example.ipandaitems.model.IModel;
import com.example.ipandaitems.model.ModelImpl;
import com.example.ipandaitems.model.entry.TopBean;
import com.example.ipandaitems.model.entry.TopListBean;
import com.example.ipandaitems.view.video.VideoInfo;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

import com.example.ipandaitems.model.IModel;
import com.example.ipandaitems.model.ModelImpl;
import com.example.ipandaitems.model.entry.TopBean;
import com.example.ipandaitems.model.entry.TopListBean;
import com.example.ipandaitems.view.video.VideoFragment;
import com.example.ipandaitems.view.video.VideoInfo;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class VideoPresenterImpl implements VideoIPresenter {
    private VideoInfo videoInfo;
    private final IModel iModel;

    public VideoPresenterImpl(VideoInfo videoInfo) {
        this.videoInfo=videoInfo;
        iModel = new ModelImpl();
    }

    @Override
    public void top() {
        iModel.doTop(new Observer<TopBean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull TopBean dataBean) {
                videoInfo.onSuccess(dataBean);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                videoInfo.onError(e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void topList(String url) {
        iModel.doTopList(url, new Observer<TopListBean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull TopListBean topListBean) {
                videoInfo.topListSuccess(topListBean);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                videoInfo.topListError(e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }
    private VideoInfo videoInfo;
    private final IModel iModel;

    public VideoPresenterImpl(VideoInfo videoInfo) {
        this.videoInfo=videoInfo;
        iModel = new ModelImpl();
    }

    @Override
    public void top() {
        iModel.doTop(new Observer<TopBean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull TopBean dataBean) {
                videoInfo.onSuccess(dataBean);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                videoInfo.onError(e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void topList(String url) {
        iModel.doTopList(url, new Observer<TopListBean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull TopListBean topListBean) {
                videoInfo.topListSuccess(topListBean);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                videoInfo.topListError(e.toString());
            }

            @Override
            public void onComplete() {

            }
        });
    }
}

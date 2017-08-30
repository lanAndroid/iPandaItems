package com.example.ipandaitems.presenter.videopresenter;


import com.example.ipandaitems.model.Callback;
import com.example.ipandaitems.model.entry.TopBean;
import com.example.ipandaitems.model.entry.TopListBean;
import com.example.ipandaitems.model.video.VideoModelImpl;
import com.example.ipandaitems.view.video.VideoInfo;

public class VideoPresenterImpl implements VideoIPresenter {
    private VideoInfo videoInfo;
    private final VideoModelImpl iModel;

    public VideoPresenterImpl(VideoInfo videoInfo) {
        this.videoInfo = videoInfo;
        iModel = new VideoModelImpl();
    }


    @Override
    public void top() {
        iModel.doTop(new Callback<TopBean>() {
            @Override
            public void succeed(TopBean topBean) {
                videoInfo.onSuccess(topBean);
            }

            @Override
            public void nothing(String str) {
                videoInfo.onError(str);
            }
        });
    }

    @Override
    public void topList(String url) {
        iModel.doTopList(url, new Callback<TopListBean>() {
            @Override
            public void succeed(TopListBean topListBean) {
                videoInfo.topListSuccess(topListBean);
            }

            @Override
            public void nothing(String str) {
                videoInfo.onError(str);
            }
        });
    }
}

package com.example.ipandaitems.presenter.annpresenter;


import com.example.ipandaitems.model.Callback;
import com.example.ipandaitems.model.entry.VideoBeanr;
import com.example.ipandaitems.model.announce.AnnModelImpl;
import com.example.ipandaitems.model.entry.AnnBean;
import com.example.ipandaitems.model.entry.PanadaBean;
import com.example.ipandaitems.view.announce.AnnView;

public class AnnIPresenterImpl implements AnnIPresenter {
    private AnnView annView;
    private final AnnModelImpl model;

    public AnnIPresenterImpl(AnnView annView) {
        this.annView = annView;
        model = new AnnModelImpl();
    }

    @Override
    public void annGet() {
        model.AnnRequsetGet(new Callback<AnnBean>() {
            @Override
            public void succeed(AnnBean annBean) {
                annView.onSuccess(annBean);
            }

            @Override
            public void nothing(String str) {
                annView.onError(str);
            }
        });
    }

    @Override
    public void panadaGet(String url) {
        model.panadaRequestGet(url, new Callback<PanadaBean>() {
            @Override
            public void succeed(PanadaBean panadaBean) {
                annView.panadaSuccess(panadaBean);
            }

            @Override
            public void nothing(String str) {
                annView.onError(str);
            }
        });
    }

    @Override
    public void videoGet(String url) {
        model.videoRequestGet(url, new Callback<VideoBeanr>() {
            @Override
            public void succeed(VideoBeanr videoBeanr) {
                annView.videoSuccess(videoBeanr);
            }

            @Override
            public void nothing(String str) {
                annView.onError(str);
            }
        });
    }

}

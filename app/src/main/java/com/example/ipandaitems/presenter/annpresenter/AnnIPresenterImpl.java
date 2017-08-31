package com.example.ipandaitems.presenter.annpresenter;


import com.example.ipandaitems.model.Callback;
import com.example.ipandaitems.model.announce.AnnModelImpl;
import com.example.ipandaitems.model.entry.AnnBean;
import com.example.ipandaitems.view.announce.AnnView;
import com.example.ipandaitems.view.announce.AnnounceFragment;

public class AnnIPresenterImpl implements AnnIPresenter {
    private AnnView annView;
    private final AnnModelImpl model;

    public AnnIPresenterImpl(AnnounceFragment annView) {
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


}

package com.example.ipandaitems.presenter;

import com.example.ipandaitems.model.Callback;
import com.example.ipandaitems.model.entry.originalbean;
import com.example.ipandaitems.model.original.OriModelImpl;
import com.example.ipandaitems.view.navigation.originalIView;


/**
 * Created by 张豫耀 on 2017/8/29.
 */

public class OriPresenterImpl implements OriPersenter {
    private originalIView originalIView;
    private OriModelImpl iModel;

    public OriPresenterImpl(originalIView originalIView) {
        this.originalIView = originalIView;
        iModel = new OriModelImpl();
    }

    @Override
    public void OriGinalGet() {
        iModel.RequestOriGinalGet(new Callback<originalbean>() {
            @Override
            public void succeed(originalbean originalbean) {
                originalIView.succeed(originalbean);
            }

            @Override
            public void nothing(String str) {
                originalIView.Failure();
            }
        });
    }


}

package com.example.ipandaitems.presenter.livepresenter;


import com.example.ipandaitems.model.Callback;
import com.example.ipandaitems.model.entry.livechina.livechinaBean;
import com.example.ipandaitems.model.entry.livechina.livechinacontentbean;
import com.example.ipandaitems.model.entry.livechina.livechinavideobean;
import com.example.ipandaitems.model.livechina.liveModelImpl;
import com.example.ipandaitems.view.livechina.Ilivechinaview;

import java.util.Map;

public class LivePresenterImpl implements LiveIPresenter {

    private Ilivechinaview ilivechinaview;
    private liveModelImpl model;

    public LivePresenterImpl(Ilivechinaview ilivechinaview) {
        this.ilivechinaview = ilivechinaview;
        model = new liveModelImpl();
    }


    @Override
    public void chinaget() {
        model.RequestChinaGet(new Callback<livechinaBean>() {
            @Override
            public void succeed(livechinaBean livechinaBean) {
                ilivechinaview.succeed(livechinaBean);
            }

            @Override
            public void nothing(String str) {

            }
        });
    }

    @Override
    public void chinacontent(String url) {
        model.RequestChinaContentGet(url, new Callback<livechinacontentbean>() {
            @Override
            public void succeed(livechinacontentbean livechinacontentbean) {
                ilivechinaview.succeedcontent(livechinacontentbean);
            }

            @Override
            public void nothing(String str) {
                ilivechinaview.Failure();
            }
        });
    }

    @Override
    public void chinavideo(Map<String, String> map) {

    }
}

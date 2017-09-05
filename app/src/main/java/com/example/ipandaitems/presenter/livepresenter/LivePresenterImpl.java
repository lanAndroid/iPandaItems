package com.example.ipandaitems.presenter.livepresenter;


import com.example.ipandaitems.model.Callback;
import com.example.ipandaitems.model.entry.livechina.livechinavideobean;
import com.example.ipandaitems.model.livechina.liveModelImpl;
import com.example.ipandaitems.view.livechina.Ilivechinaview;
import com.example.ipandaitems.view.livechina.entity.LiveChinaAllTablist;
import com.example.ipandaitems.view.livechina.entity.LiveChinaBean;

public class LivePresenterImpl implements LiveIPresenter {

    private Ilivechinaview ilivechinaview;
    private liveModelImpl model;

    public LivePresenterImpl(Ilivechinaview ilivechinaview) {
        this.ilivechinaview = ilivechinaview;
        model = new liveModelImpl();
    }


    @Override
    public void chinaget() {
        model.RequestChinaGet(new Callback<LiveChinaAllTablist>() {
            @Override
            public void succeed(LiveChinaAllTablist livechinaBean) {
                ilivechinaview.succeed(livechinaBean);
            }

            @Override
            public void nothing(String str) {

            }
        });
    }

    @Override
    public void chinacontent(String url) {
        model.RequestChinaContentGet(url, new Callback<LiveChinaBean>() {
            @Override
            public void succeed(LiveChinaBean livechinacontentbean) {
                ilivechinaview.succeedcontent(livechinacontentbean);
            }

            @Override
            public void nothing(String str) {
                ilivechinaview.Failure();
            }
        });
    }

    @Override
    public void chinavideo(String url) {
        model.RequestChinaVideoGet(url, new Callback<livechinavideobean>() {
            @Override
            public void succeed(livechinavideobean livechinavideobean) {
                ilivechinaview.succeedvideo(livechinavideobean);
            }

            @Override
            public void nothing(String str) {
                ilivechinaview.Failure();
            }
        });
    }
}

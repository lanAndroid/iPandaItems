package com.example.ipandaitems.view.livechina;

import android.view.View;

import com.example.ipandaitems.base.BaseFragment;
import com.example.ipandaitems.model.entry.livechina.livechinaBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * Created by 张豫耀 on 2017/8/28.
 */

public class LiveChinaFragment extends BaseFragment implements Ilivechinaview {

    private String url;
    XRecyclerView mXRecyclerView;
//    LiveChinaZhiboItemAdapter adapter;

    public LiveChinaFragment(String url) {
        this.url = url;
    }

    @Override
    public void succeed(livechinaBean livechinaBean) {

    }

    @Override
    public void Failure() {

    }

    @Override
    protected int layoutID() {
        return 0;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {

    }
}

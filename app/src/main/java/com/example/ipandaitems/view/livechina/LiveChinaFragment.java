package com.example.ipandaitems.view.livechina;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.example.ipandaitems.R;
import com.example.ipandaitems.base.BaseFragment;
import com.example.ipandaitems.model.entry.livechina.livechinaBean;
import com.example.ipandaitems.model.entry.livechina.livechinacontentbean;
import com.example.ipandaitems.model.entry.livechina.livechinavideobean;
import com.example.ipandaitems.presenter.livepresenter.LivePresenterImpl;
import com.example.ipandaitems.view.livechina.adapter.LiveChinaZhiboItemAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张豫耀 on 2017/8/28.
 */

public class LiveChinaFragment extends BaseFragment implements Ilivechinaview {

    private String url;
    XRecyclerView mXRecyclerView;
    LiveChinaZhiboItemAdapter adapter;
    private List<livechinacontentbean.LiveBean> been;
    private LivePresenterImpl presenter;

    public LiveChinaFragment(String url) {
        this.url = url;
    }

    @Override
    public void succeed(livechinaBean livechinaBean) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void succeedcontent(livechinacontentbean livechinacontentbean) {
        been = new ArrayList<>();
        been.addAll(livechinacontentbean.getLive());
        mXRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mXRecyclerView.setLoadingMoreEnabled(false);
        mXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //refresh data here刷新
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        been.clear();
                        presenter.chinacontent(url);
                        adapter.notifyDataSetChanged();
                        mXRecyclerView.refreshComplete();
                    }

                }, 2500);
            }

            @Override
            public void onLoadMore() {
                mXRecyclerView.loadMoreComplete();
            }
        });
        adapter = new LiveChinaZhiboItemAdapter(getContext(), been);
        mXRecyclerView.setAdapter(adapter);
    }

    @Override
    public void succeedvideo(livechinavideobean livechinavideobean) {

    }

    @Override
    public void Failure() {

    }

    @Override
    protected int layoutID() {
        return R.layout.fragment_livechina_fragment;
    }

    @Override
    protected void initView(View view) {
        mXRecyclerView = (XRecyclerView) view.findViewById(R.id.lc_rv);
        presenter = new LivePresenterImpl(this);
        Log.e("--------------->",url);
        presenter.chinacontent(url);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {

    }
}
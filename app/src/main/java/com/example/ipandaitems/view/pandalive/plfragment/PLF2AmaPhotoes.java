package com.example.ipandaitems.view.pandalive.plfragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ipandaitems.R;
import com.example.ipandaitems.base.BaseFragment;
import com.example.ipandaitems.model.entry.pandalive.PLAmaPhotoes;
import com.example.ipandaitems.presenter.pandalivepresenter.plfragment.PPLAmaPhotoe;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @name yanantian
 * @motto 莫羡他人谢语花, 腹有诗书气自华
 * @E-mail 1173568715@qq.com
 * @WX 15978622391
 */

public class PLF2AmaPhotoes extends BaseFragment implements PLF2AmaPhotoesView {
    @BindView(R.id.pl_2_recycler)
    XRecyclerView pl2Recycler;
    Unbinder unbinder;
    private PL2AdapterAmaPhotoes adapter;
    private List<PLAmaPhotoes.VideoBean> list;

    @Override
    protected int layoutID() {
        return R.layout.pl_2amaphotoes;
    }

    @Override
    protected void initView(View view) {
        PPLAmaPhotoe ppa = new PPLAmaPhotoe(this);
        ppa.initData();
        pl2Recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PL2AdapterAmaPhotoes(list, getActivity());
        pl2Recycler.setAdapter(adapter);
    }

    @Override
    public void initData(PLAmaPhotoes plAmaPhotoes) {
        Log.e("tina", plAmaPhotoes.getVideo().size() + "");
        list = plAmaPhotoes.getVideo();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

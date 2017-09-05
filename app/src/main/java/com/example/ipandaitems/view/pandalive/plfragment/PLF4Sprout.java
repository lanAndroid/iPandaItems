package com.example.ipandaitems.view.pandalive.plfragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.ipandaitems.R;
import com.example.ipandaitems.base.BaseFragment;
import com.example.ipandaitems.model.entry.pandalive.PLAmaPhotoes;
import com.example.ipandaitems.presenter.pandalivepresenter.plfragment.PPLAmaPhotoe;
import com.example.ipandaitems.presenter.pandalivepresenter.plfragment.PPLAmaPhotoe4;
import com.example.ipandaitems.view.pandalive.adapter.PL2AdapterAmaPhotoes;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
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

public class PLF4Sprout extends BaseFragment implements PLF2AmaPhotoesView {
    @BindView(R.id.pl_4_recycler)
    XRecyclerView pl4Recycler;
    Unbinder unbinder;
    private PL2AdapterAmaPhotoes adapter;
    private List<PLAmaPhotoes.VideoBean> list;

    @Override
    protected void initView(View view) {
        pl4Recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        pl4Recycler.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        pl4Recycler.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        PPLAmaPhotoe4 ppa = new PPLAmaPhotoe4(this);
        ppa.initData();
    }

    @Override
    public void initData(final PLAmaPhotoes plAmaPhotoes) {
        Log.e("tina", plAmaPhotoes.getVideo().size() + "");
        list = plAmaPhotoes.getVideo();
        list.addAll(plAmaPhotoes.getVideo());
        if (adapter == null) {
            adapter = new PL2AdapterAmaPhotoes(list, getActivity());
            pl4Recycler.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
        pl4Recycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override//下拉刷新
            public void onRefresh() {
                pl4Recycler.refreshComplete();//下拉刷新完成
            }

            @Override //加载更多
            public void onLoadMore() {
                Toast.makeText(getContext(), "jlsdk;af", Toast.LENGTH_SHORT).show();
                list.addAll(plAmaPhotoes.getVideo());
                pl4Recycler.loadMoreComplete();//加载更多完成
            }
        });

        View footview = LayoutInflater.from(getContext()).inflate(R.layout.pl_recycler_foot, null);
        Button but = footview.findViewById(R.id.pl_recycler_foot_but);
        pl4Recycler.setFootView(footview);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "jlsdk;af", Toast.LENGTH_SHORT).show();
                list.addAll(plAmaPhotoes.getVideo());
            }
        });

        adapter.setOnItemClickList(new PL2AdapterAmaPhotoes.onItemClickListener() {
            @Override
            public void onClicks(List lists, int pos) {
                Log.e("tian", lists.toString() + "-------" + pos);
                Toast.makeText(getContext(), "跳转至播放页,暂时未完成", Toast.LENGTH_SHORT).show();
            }
        });
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
    protected int layoutID() {
        return R.layout.pl_4sprout;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

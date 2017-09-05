package com.example.ipandaitems.view.ui.collect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chanven.lib.cptr.recyclerview.RecyclerAdapterWithHF;
import com.example.ipandaitems.R;
import com.example.ipandaitems.base.BaseFragment;
import com.example.ipandaitems.model.greendao.DaoMaster;
import com.example.ipandaitems.model.greendao.DaoSession;
import com.example.ipandaitems.model.greendao.Data;
import com.example.ipandaitems.model.greendao.DataDao;
import com.example.ipandaitems.view.livechina.DisplayActivity;
import com.example.ipandaitems.view.ui.collect.adapter.SoLiveAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 张豫耀 on 2017/9/4.
 */

public class SoLiveFragment extends BaseFragment {
    @BindView(R.id.solive_recycler)
    RecyclerView soliveRecycler;
    Unbinder unbinder;
    private List<Data> datas = new ArrayList<>();
    private DataDao dataDao;
    private RecyclerAdapterWithHF hf;

    @Override
    protected int layoutID() {
        return R.layout.fragment_solive;
    }

    @Override
    protected void initView(View view) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getActivity(), "dt.db");
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        dataDao = daoSession.getDataDao();

        SoLiveAdapter adapter = new SoLiveAdapter(getActivity(), datas);
        hf = new RecyclerAdapterWithHF((RecyclerView.Adapter) adapter);
        soliveRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        soliveRecycler.setAdapter(hf);
        hf.setOnItemClickListener(new RecyclerAdapterWithHF.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerAdapterWithHF adapter, RecyclerView.ViewHolder vh, int position) {
                Intent intent = new Intent(getActivity(), DisplayActivity.class);
                intent.putExtra("name", datas.get(position).getName());
                intent.putExtra("url", datas.get(position).getUrl());
                intent.putExtra("img", datas.get(position).getImg());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void loadData() {
        datas.clear();
        List<Data> DataAll = dataDao.loadAll();
        datas.addAll(DataAll);
        hf.notifyDataSetChanged();
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

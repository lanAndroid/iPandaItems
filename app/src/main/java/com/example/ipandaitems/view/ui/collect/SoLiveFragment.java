package com.example.ipandaitems.view.ui.collect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

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
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 张豫耀 on 2017/9/4.
 */

public class SoLiveFragment extends BaseFragment implements SoLiveAdapter.CheckInterface {
    @BindView(R.id.solive_recycler)
    RecyclerView soliveRecycler;
    Unbinder unbinder;
    @BindView(R.id.bianji_btn)
    CheckBox bianjiBtn;
    @BindView(R.id.all_btn)
    CheckBox allBtn;
    @BindView(R.id.delete_btn)
    Button deleteBtn;
    @BindView(R.id.histor_lin)
    LinearLayout historLin;
    private List<Data> datas = new ArrayList<>();
    private DataDao dataDao;
    private RecyclerAdapterWithHF hf;
    private boolean flag = false;
    private SoLiveAdapter adapter;

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

        adapter = new SoLiveAdapter(getActivity(), datas);
        hf = new RecyclerAdapterWithHF((RecyclerView.Adapter) adapter);
        soliveRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        soliveRecycler.setAdapter(hf);
        adapter.setCheckInterface(this);
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

    @OnClick({R.id.bianji_btn, R.id.all_btn, R.id.delete_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bianji_btn:
                flag = !flag;
                if (flag) {
                    bianjiBtn.setText("完成");
                    adapter.isShow(true);
                    historLin.setVisibility(View.VISIBLE);
                } else {
                    bianjiBtn.setText("编辑");
                    adapter.isShow(false);
                    historLin.setVisibility(View.GONE);
                }
                break;
            case R.id.all_btn:
                if (datas.size() != 0) {
                    if (allBtn.isChecked()) {
                        for (int i = 0; i < datas.size(); i++) {
                            datas.get(i).setChoosed(true);
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        for (int i = 0; i < datas.size(); i++) {
                            datas.get(i).setChoosed(false);
                        }
                        adapter.notifyDataSetChanged();
                    }
                }
                break;
            case R.id.delete_btn:
                delete();
                break;
        }
    }

    private void delete() {
        List<Data> toBeDeleteGroups = new ArrayList<Data>();
        for (int i = 0; i < datas.size(); i++) {
            Data group = datas.get(i);
            if (group.isChoosed()) {
                toBeDeleteGroups.add(group);
            }
        }

        for (Data sto : toBeDeleteGroups
                ) {
            dataDao.delete(sto);
        }
        datas.clear();
        List<Data> DataAll = dataDao.loadAll();
        datas.addAll(DataAll);
        hf.notifyDataSetChanged();
    }


    @Override
    public void checkGroup(int position, boolean isChecked) {
        datas.get(position).setChoosed(isChecked);
        if (isAllCheck())
            allBtn.setChecked(true);
        else
            allBtn.setChecked(false);
        adapter.notifyDataSetChanged();

    }

    private boolean isAllCheck() {
        for (Data group : datas) {
            if (!group.isChoosed())
                return false;
        }
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

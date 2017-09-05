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
import com.example.ipandaitems.model.greendao.User;
import com.example.ipandaitems.model.greendao.UserDao;
import com.example.ipandaitems.view.livechina.DisplayActivity;
import com.example.ipandaitems.view.ui.collect.adapter.AmazingAdapter;
import com.example.ipandaitems.view.video.WebView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 张豫耀 on 2017/9/4.
 */

public class AmazingFragment extends BaseFragment {
    @BindView(R.id.amazing_recycler)
    RecyclerView amazingRecycler;
    Unbinder unbinder;
    private List<User> list = new ArrayList<>();
    private UserDao userDao;
    private RecyclerAdapterWithHF withHF;

    @Override
    protected int layoutID() {
        return R.layout.fragment_amazing;
    }

    @Override
    protected void initView(View view) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getActivity(), "ur.db");
        DaoMaster daoMaster = new DaoMaster(helper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        userDao = daoSession.getUserDao();

        AmazingAdapter adapter = new AmazingAdapter(getActivity(), list);
        withHF = new RecyclerAdapterWithHF((RecyclerView.Adapter) adapter);
        amazingRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        amazingRecycler.setAdapter(withHF);
        withHF.setOnItemClickListener(new RecyclerAdapterWithHF.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerAdapterWithHF adapter, RecyclerView.ViewHolder vh, int position) {
                if (list.get(position).getTime() == null) {
                    Intent intent = new Intent(getActivity(), DisplayActivity.class);
                    intent.putExtra("name", list.get(position).getName());
                    intent.putExtra("url", list.get(position).getUrl());
                    intent.putExtra("img", list.get(position).getImg());
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getActivity(), WebView.class);
                    intent.putExtra("name", list.get(position).getName());
                    intent.putExtra("url", list.get(position).getUrl());
                    intent.putExtra("img", list.get(position).getImg());
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void loadData() {
        list.clear();
        List<User> USerAll = userDao.loadAll();
        list.addAll(USerAll);
        withHF.notifyDataSetChanged();
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

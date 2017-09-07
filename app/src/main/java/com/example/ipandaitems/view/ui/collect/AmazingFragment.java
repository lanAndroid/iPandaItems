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
import com.example.ipandaitems.model.greendao.User;
import com.example.ipandaitems.model.greendao.UserDao;
import com.example.ipandaitems.view.livechina.DisplayActivity;
import com.example.ipandaitems.view.ui.collect.adapter.AmazingAdapter;
import com.example.ipandaitems.view.video.WebView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 张豫耀 on 2017/9/4.
 */

public class AmazingFragment extends BaseFragment implements AmazingAdapter.CheckInterface {
    @BindView(R.id.amazing_recycler)
    RecyclerView amazingRecycler;
    Unbinder unbinder;
    @BindView(R.id.bianji_btn)
    CheckBox bianjiBtn;
    @BindView(R.id.all_btn)
    CheckBox allBtn;
    @BindView(R.id.delete_btn)
    Button deleteBtn;
    @BindView(R.id.histor_lin)
    LinearLayout historLin;
    private List<User> list = new ArrayList<>();
    private UserDao userDao;
    private RecyclerAdapterWithHF withHF;
    private boolean flag = false;
    private AmazingAdapter adapter;

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

        adapter = new AmazingAdapter(getActivity(), list);
        withHF = new RecyclerAdapterWithHF((RecyclerView.Adapter) adapter);
        amazingRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        amazingRecycler.setAdapter(withHF);
        adapter.setCheckInterface(this);
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
                if (list.size() != 0) {
                    if (allBtn.isChecked()) {
                        for (int i = 0; i < list.size(); i++) {
                            list.get(i).setChoosed(true);
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        for (int i = 0; i < list.size(); i++) {
                            list.get(i).setChoosed(false);
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
        List<User> toBeDeleteGroups = new ArrayList<User>();
        for (int i = 0; i < list.size(); i++) {
            User group = list.get(i);
            if (group.isChoosed()) {
                toBeDeleteGroups.add(group);
            }
        }

        for (User sto : toBeDeleteGroups
                ) {
            userDao.delete(sto);
        }
        list.clear();
        List<User> USerAll = userDao.loadAll();
        list.addAll(USerAll);
        withHF.notifyDataSetChanged();
    }


    @Override
    public void checkGroup(int position, boolean isChecked) {
        list.get(position).setChoosed(isChecked);
        if (isAllCheck())
            allBtn.setChecked(true);
        else
            allBtn.setChecked(false);
        adapter.notifyDataSetChanged();

    }

    private boolean isAllCheck() {
        for (User group : list) {
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

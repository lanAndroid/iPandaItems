package com.example.ipandaitems.view.personalcenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chanven.lib.cptr.recyclerview.RecyclerAdapterWithHF;
import com.example.ipandaitems.R;
import com.example.ipandaitems.base.BaseActivity;
import com.example.ipandaitems.model.greendao.DaoMaster;
import com.example.ipandaitems.model.greendao.DaoSession;
import com.example.ipandaitems.model.greendao.history;
import com.example.ipandaitems.model.greendao.historyDao;
import com.example.ipandaitems.view.livechina.DisplayActivity;
import com.example.ipandaitems.view.ui.collect.adapter.HistoryAdapter;
import com.example.ipandaitems.view.video.WebView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HistoryActivity extends BaseActivity implements HistoryAdapter.CheckInterface{


    @BindView(R.id.hstory_finish)
    ImageView hstoryFinish;
    @BindView(R.id.history_recycler)
    RecyclerView historyRecycler;
    @BindView(R.id.bianji_btn)
    CheckBox bianjiBtn;
    @BindView(R.id.all_btn)
    CheckBox allBtn;
    @BindView(R.id.delete_btn)
    Button deleteBtn;
    @BindView(R.id.histor_lin)
    LinearLayout historLin;
    private List<history> list = new ArrayList<>();
    private historyDao dao;
    private RecyclerAdapterWithHF hf;
    private HistoryAdapter adapter;
    private boolean flag = false;


    @Override
    protected int layoutID() {
        return R.layout.activity_history;
    }

    @Override
    protected void initView() throws SQLException {
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(this, "hs.db");
        DaoMaster daoMaster = new DaoMaster(openHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        dao = daoSession.getHistoryDao();

    }

    @Override
    protected void loadData() {
        historyRecycler.setLayoutManager(new LinearLayoutManager(this));

        adapter = new HistoryAdapter(this, list);
        hf = new RecyclerAdapterWithHF((RecyclerView.Adapter) adapter);
        historyRecycler.setAdapter(hf);
        adapter.setCheckInterface(this);

        initCha();
    }

    private void initCha() {
        list.clear();
        List<history> HIsALl = dao.loadAll();
        list.addAll(HIsALl);
        hf.notifyDataSetChanged();
    }

    @Override
    protected void initListener() {
        hstoryFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        hf.setOnItemClickListener(new RecyclerAdapterWithHF.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerAdapterWithHF adapter, RecyclerView.ViewHolder vh, int position) {
                if (list.get(position).getTime() == null) {
                    Intent intent = new Intent(HistoryActivity.this, DisplayActivity.class);
                    intent.putExtra("name", list.get(position).getName());
                    intent.putExtra("url", list.get(position).getUrl());
                    intent.putExtra("img", list.get(position).getImg());
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(HistoryActivity.this, WebView.class);
                    intent.putExtra("name", list.get(position).getName());
                    intent.putExtra("url", list.get(position).getUrl());
                    intent.putExtra("img", list.get(position).getImg());
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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
        List<history> toBeDeleteGroups = new ArrayList<history>();
        for (int i = 0; i < list.size(); i++) {
            history group = list.get(i);
            if (group.isChoosed()) {
                toBeDeleteGroups.add(group);
            }
        }

        for (history sto : toBeDeleteGroups
                ) {
            dao.delete(sto);
        }
            initCha();
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
        for (history group : list) {
            if (!group.isChoosed())
                return false;
        }
        return true;
    }


}

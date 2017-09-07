package com.example.ipandaitems.view.navigation;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.ipandaitems.R;
import com.example.ipandaitems.base.BaseActivity;
import com.example.ipandaitems.model.entry.originalbean;
import com.example.ipandaitems.model.greendao.DaoMaster;
import com.example.ipandaitems.model.greendao.DaoSession;
import com.example.ipandaitems.model.greendao.history;
import com.example.ipandaitems.model.greendao.historyDao;
import com.example.ipandaitems.presenter.OriPresenterImpl;
import com.example.ipandaitems.view.video.WebView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Optional;

public class OrinGinalActivity extends BaseActivity implements originalIView, View.OnClickListener {


    @BindView(R.id.oringinal_finish)
    ImageView oringinalFinish;
    @BindView(R.id.original_recycler)
    RecyclerView originalRecycler;
    List<originalbean.InteractiveBean> list = new ArrayList<>();
    private historyDao dao;

    @Optional
    @Override
    protected int layoutID() {
        return R.layout.activity_orin_ginal;
    }

    @Override
    protected void initView() throws SQLException {
        ButterKnife.bind(this);
        OriPresenterImpl oriPresenter = new OriPresenterImpl(this);
        oriPresenter.OriGinalGet();
    }

    @Override
    protected void loadData() {
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(this, "hs.db");
        DaoMaster daoMaster = new DaoMaster(openHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        dao = daoSession.getHistoryDao();
    }

    @Override
    protected void initListener() {
        oringinalFinish.setOnClickListener(this);
    }

    @Override
    public void succeed(originalbean originalbean) {
        list.addAll(originalbean.getInteractive());
        originalRecycler.setLayoutManager(new LinearLayoutManager(this));
        OriginalAdapter adapter = new OriginalAdapter(this, list);
        originalRecycler.setAdapter(adapter);
        adapter.SetOnItemCLick(new OriginalAdapter.OnItemCLick() {
            @Override
            public void OnClick(View v, int position) {
                history history = dao.queryBuilder().where(historyDao.Properties.Name.eq(list.get(position).getTitle())).build().unique();
                if (history == null) {
                    dao.insert(new history(null, list.get(position).getImage(), list.get(position).getUrl(), "", list.get(position).getTitle()));
                }

                Intent intent = new Intent(OrinGinalActivity.this, WebView.class);
                intent.putExtra("name", list.get(position).getTitle());
                intent.putExtra("url", list.get(position).getUrl());
                startActivity(intent);
            }
        });
    }

    @Override
    public void Failure() {

    }


    @Override
    public void onClick(View view) {
        finish();
    }
}

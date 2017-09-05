package com.example.ipandaitems.view;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.ipandaitems.R;
import com.example.ipandaitems.base.BaseActivity;
import com.example.ipandaitems.model.entry.originalbean;
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

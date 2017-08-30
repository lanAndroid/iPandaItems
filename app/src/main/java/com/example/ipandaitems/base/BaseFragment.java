package com.example.ipandaitems.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import io.vov.vitamio.Vitamio;

/**
 * Created by xiaogang on 2017/6/20.
 */

public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Vitamio.isInitialized(getActivity());
        return inflater.inflate(layoutID(), null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initView(view);
        loadData();
        initListener();
    }

    public void setParmars(Bundle bundle) {

//        this.bundle = bundle;
    }

    protected abstract int layoutID();

    protected abstract void initView(View view);

    protected abstract void loadData();

    protected abstract void initListener();

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            onHidden();
        } else {
            setTitleBar();
            onShow();
        }

    }

    public void onShow() {
        loadData();
    }

    public void onHidden() {
    }

    public void setTitleBar() {
    }

}


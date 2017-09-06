package com.example.ipandaitems.base;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
    //判断时间是否有网络
    public Boolean isConnected(){
        //获取手机所有连接管理类（wifi,net等）
        ConnectivityManager conn= (ConnectivityManager) getActivity().getSystemService(getActivity().CONNECTIVITY_SERVICE);
        if (conn!=null) {
            //获取网络连接管理的对象
            NetworkInfo activeNetworkInfo = conn.getActiveNetworkInfo();
            if (activeNetworkInfo!=null&&activeNetworkInfo.isConnected()){
                //判断网络是否是连接的
                if (activeNetworkInfo.getState()== NetworkInfo.State.CONNECTED){
                    return true;
                }
            }
        }
        return false;
    }
}


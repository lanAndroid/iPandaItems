package com.example.ipandaitems.presenter.homepresenter;


import android.util.Log;

import com.example.ipandaitems.model.ModelImpl;
import com.example.ipandaitems.model.entry.HomeBean;
import com.example.ipandaitems.view.home.HomeFragment;
import com.example.ipandaitems.view.home.IHomeFragment;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class HomePresenterImpl implements HomeIPresenter {
   IHomeFragment mIhomeFragment;

    ModelImpl model;



    public HomePresenterImpl(HomeFragment mIhomeFragment) {
        this.mIhomeFragment = mIhomeFragment;
    }

    @Override
    public void getHomeBean() {
        model = new ModelImpl();
        model.RequestHomeGet(new Observer<HomeBean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull HomeBean homeBean) {
                Log.e("TAG", "传值了");
                mIhomeFragment.gethomebean(homeBean);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }
}

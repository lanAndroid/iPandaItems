package com.example.ipandaitems.presenter.homepresenter;


import android.util.Log;

import com.example.ipandaitems.model.ModelImpl;
import com.example.ipandaitems.model.entry.home.HomeBean;
import com.example.ipandaitems.model.entry.home.HomeMarvellBean;
import com.example.ipandaitems.model.entry.home.HomeRollingBean;
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
        model = new ModelImpl();
    }

    @Override
    public void getHomeBean() {

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

    @Override
    public void getHomeMarvellBean() {

        model.RequestHomeMarvellGet(new Observer<HomeMarvellBean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull HomeMarvellBean homeMarvellBean) {
                mIhomeFragment.gethomeMarvellbean(homeMarvellBean);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getHomeRollingBean() {

        model.RequestHomeRollingGet(new Observer<HomeRollingBean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull HomeRollingBean homeRollingBean) {
                mIhomeFragment.gethomeRollingbean(homeRollingBean);
                Log.e("TAG", homeRollingBean.toString());
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

package com.example.ipandaitems.presenter.homepresenter;


import com.example.ipandaitems.model.Callback;
import com.example.ipandaitems.model.entry.home.HomeBean;
import com.example.ipandaitems.model.entry.home.HomeMarvellBean;
import com.example.ipandaitems.model.entry.home.HomeRollingBean;
import com.example.ipandaitems.model.home.HomeModelImpl;
import com.example.ipandaitems.view.home.HomeFragment;
import com.example.ipandaitems.view.home.IHomeFragment;

public class HomePresenterImpl implements HomeIPresenter {
    IHomeFragment mIhomeFragment;

    HomeModelImpl model;


    public HomePresenterImpl(HomeFragment mIhomeFragment) {
        this.mIhomeFragment = mIhomeFragment;
        model = new HomeModelImpl();
    }

    @Override
    public void getHomeBean() {

        model.RequestHomeGet(new Callback<HomeBean>() {
            @Override
            public void succeed(HomeBean homeBean) {
                mIhomeFragment.gethomebean(homeBean);
            }

            @Override
            public void nothing(String str) {

            }
        });

    }

    @Override
    public void getHomeMarvellBean() {

        model.RequestHomeMarvellGet(new Callback<HomeMarvellBean>() {
            @Override
            public void succeed(HomeMarvellBean homeMarvellBean) {
                mIhomeFragment.gethomeMarvellbean(homeMarvellBean);
            }

            @Override
            public void nothing(String str) {

            }
        });
    }

    @Override
    public void getHomeRollingBean() {

        model.RequestHomeRollingGet(new Callback<HomeRollingBean>() {
            @Override
            public void succeed(HomeRollingBean homeRollingBean) {
                mIhomeFragment.gethomeRollingbean(homeRollingBean);
            }

            @Override
            public void nothing(String str) {

            }
        });
    }
}

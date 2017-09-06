package com.example.ipandaitems.presenter.homepresenter;


import com.example.ipandaitems.model.Callback;
import com.example.ipandaitems.model.entry.home.HomeBean;
import com.example.ipandaitems.model.entry.home.HomeChianVideo;
import com.example.ipandaitems.model.entry.home.HomeMarvellBean;
import com.example.ipandaitems.model.entry.home.HomePandanVideo;
import com.example.ipandaitems.model.entry.home.HomeRollVideo;
import com.example.ipandaitems.model.entry.home.HomeRollingBean;
import com.example.ipandaitems.model.home.HomeModelImpl;
import com.example.ipandaitems.view.home.IHomeFragment;
public class HomePresenterImpl implements HomeIPresenter {
   IHomeFragment mIhomeFragment;

    HomeModelImpl model;



    public HomePresenterImpl(IHomeFragment mIhomeFragment) {
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
                             }

        );

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



    @Override
    public void setHomeBannerVideoURl(String url) {
        model.RequestHomeBanner(url, new Callback<HomeRollVideo>() {
            @Override
            public void succeed(HomeRollVideo homeRollVideo) {
                mIhomeFragment.gethomebannerVido(homeRollVideo);
            }

            @Override
            public void nothing(String str) {

            }
        });
    }

    @Override
    public void setHomeBrodcastVideoURl(String url) {
        model.Requestgethomebroadcastvideo(url, new Callback<HomeRollVideo>() {
            @Override
            public void succeed(HomeRollVideo homeRollVideo) {
                mIhomeFragment.gethomebrodcastVido(homeRollVideo);
            }

            @Override
            public void nothing(String str) {

            }
        });
    }

    @Override
    public void setHomeBrodcastTwoVideoURl(String url) {
            model.Requestgethomebroadcasttwovideo(url, new Callback<HomeRollVideo>() {
                @Override
                public void succeed(HomeRollVideo homeRollVideo) {
                    mIhomeFragment.gethomebrodcasttwoVido(homeRollVideo);
                }

                @Override
                public void nothing(String str) {

                }
            });
    }

    @Override
    public void setHomePandanVideo(String url) {

        model.RequestHomePandanVideoGet(url, new Callback<HomePandanVideo>() {
            @Override
            public void succeed(HomePandanVideo homePandanVideo) {
                mIhomeFragment.gethomePandanVideo(homePandanVideo);
            }

            @Override
            public void nothing(String str) {

            }
        });

    }

    @Override
    public void setHomeChianVideo(String url) {
            model.RequestHomeChianVoidGet(url, new Callback<HomeChianVideo>() {
                @Override
                public void succeed(HomeChianVideo homeChianVideo) {
                    mIhomeFragment.gethomeChianVideo(homeChianVideo);
                }

                @Override
                public void nothing(String str) {

                }
            });
    }

    @Override
    public void setHomeRollVideo(String url) {
        model.RequestHomeRollingGet(url, new Callback<HomeRollVideo>() {
            @Override
            public void succeed(HomeRollVideo homeRollVideo) {
                mIhomeFragment.gethomeRollingVido(homeRollVideo);
            }

            @Override
            public void nothing(String str) {

            }
        });
    }

    @Override
    public void setHomeRollVideoURl(String url) {

    }
}

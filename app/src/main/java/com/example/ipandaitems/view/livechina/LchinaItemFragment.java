package com.example.ipandaitems.view.livechina;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.ipandaitems.R;
import com.example.ipandaitems.base.BaseFragment;
import com.example.ipandaitems.model.entry.livechina.livechinavideobean;
import com.example.ipandaitems.presenter.livepresenter.LivePresenterImpl;
import com.example.ipandaitems.view.livechina.adapter.LiveChinaTabItemAdapter;
import com.example.ipandaitems.view.livechina.assist.XListView;
import com.example.ipandaitems.view.livechina.entity.LiveChinaAllTablist;
import com.example.ipandaitems.view.livechina.entity.LiveChinaBean;
import com.example.ipandaitems.view.livechina.entity.LiveChinaTabItem;
import com.example.ipandaitems.view.livechina.entity.LiveChineItem;

import java.util.LinkedList;
import java.util.List;

import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * 直播中国每个item页
 *
 * @author wuguicheng
 */
public class LchinaItemFragment extends BaseFragment implements OnClickListener, XListView.IXListViewListener, Ilivechinaview, LiveChinaTabItemAdapter.LiveChinaItemClickImple {

    //获取列表
    private static final int GET_CHINA_ITEM_LIST = 1;
    private XListView mXListView;
    private LiveChinaTabItemAdapter mLiveChinaTabItemAdapter;
    private List<LiveChineItem> mLiveChineItems;
    private LiveChineItem liveChineItem;
    //是否初始化view
    private boolean isInitView = false;
    //是否已经将pullrefreshview动态添加进去了
    private boolean isInitPullView = false;


    public LiveChinaTabItem liveChinaTabItem;
    //在tab中的位置
    private int mPosition;


    String urlString = "http://asp.cntv.lxdns.com/asp/hls/850/0303000a/3/default/29978c0b08964a59a927b90836dd7485/850.m3u8";


    //移动的播放器
    private RelativeLayout scrollView;
    private FrameLayout live_cont_layout;
    //播放器控制器
    private VideoView videoView;

    //无网络
    View notNetImg;

    //是否第一次获取到数据
    private boolean isFirstGetData = true;

    //视频集合
    private String title;
    private View views;

    private MediaController controller;
    private CheckBox video_check;
    private String usrl;


    @Override
    protected int layoutID() {
        return R.layout.fragment_live_china_item;
    }

    @Override
    protected void initView(View view) {
        views = view;
        isInitView = true;
        if (getUserVisibleHint()) {
            setUserVisibleHint(true);
        }

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {

    }


    private void initView() {
        if (getActivity() == null) {
            return;
        }
        notNetImg = views.findViewById(R.id.live_china_item_not_net);
        notNetImg.setOnClickListener(this);
        live_cont_layout = (FrameLayout) views.findViewById(R.id.live_cont_layout);
        mXListView = (XListView) View.inflate(getActivity(), R.layout.live_china_item_xlistview, null);

        mXListView.setXListViewListener(this);
        mXListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case SCROLL_STATE_TOUCH_SCROLL:
                        break;
                    case SCROLL_STATE_IDLE:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                if ((mLiveChinaTabItemAdapter.getPlayPostion() < firstVisibleItem
                        || mLiveChinaTabItemAdapter.getPlayPostion() > mXListView.getLastVisiblePosition())
                        ) {
                    destoryPlay();
                }
            }
        });

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (isInitView) {
                //第二个条目之后的预加载
                if (!isInitPullView) {
                    initView();
                    initData();
                }
            }
        }
    }

    private void initData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            mPosition = bundle.getInt("positon", -1);
            liveChinaTabItem = (LiveChinaTabItem) bundle.getSerializable("chananelUrl");

            Log.i("aaaa", liveChinaTabItem.title);
            Log.i("aaaa", liveChinaTabItem.url);
            mLiveChineItems = new LinkedList<LiveChineItem>();
            mLiveChinaTabItemAdapter = new LiveChinaTabItemAdapter(getActivity(), mLiveChineItems, liveChinaTabItem.title.toString());
            mLiveChinaTabItemAdapter.setLiveChinaItemClickImple(this);
            mXListView.setAdapter(mLiveChinaTabItemAdapter);
            mXListView.setPullLoadEnable(false);
            mXListView.setPullRefreshEnable(true);
            isInitPullView = true;
            ((RelativeLayout) views).addView(mXListView, 0);

            if (!TextUtils.isEmpty(liveChinaTabItem.url)) {
                if (isConnected()) {
                    getHttpDataTabList();

                } else {
                    notNetImg.setVisibility(View.VISIBLE);
                }
            } else {

                notNetImg.setVisibility(View.VISIBLE);
            }

            LivePresenterImpl presenter = new LivePresenterImpl(this);
            presenter.chinacontent(liveChinaTabItem.url);
        }
    }

    private void getHttpDataTabList() {
        LivePresenterImpl presenter = new LivePresenterImpl(this);
    }

    @Override
    public void onRefresh() {
        if (liveChinaTabItem != null) {
            getHttpDataTabList();
        }

    }

    @Override
    public void onLoadMore() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (getUserVisibleHint()) {
            if (mLiveChinaTabItemAdapter != null) {
                if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    LiveFragment parentFragment = (LiveFragment) getParentFragment();
                    parentFragment.setChinaTabViewViewsible(View.GONE);
                    if (scrollView == null) {
                        return;
                    }

                    ViewGroup p = (ViewGroup) scrollView.getParent();
                    if (p != null) {
                        p.removeView(scrollView);
                    }
                    live_cont_layout.addView(scrollView);
                    mXListView.setVisibility(View.GONE);
                } else {
                    LiveFragment parentFragment = (LiveFragment) getParentFragment();
                    parentFragment.setChinaTabViewViewsible(View.VISIBLE);
                    mXListView.setVisibility(View.VISIBLE);

                    if (scrollView != null) {
                        ViewGroup p = (ViewGroup) scrollView.getParent();
                        if (p != null) {
                            p.removeView(scrollView);
                        }
                        mLiveChinaTabItemAdapter.getcurrentPlayParentLayout().addView(scrollView);
                    }
                }
            }
        }
    }


    public boolean isFirstScroll = true;

    /**
     * 切换tab的时候销毁视频
     */
    public synchronized void destoryPlay() {
        if (videoView != null && mLiveChinaTabItemAdapter.getcurrentPlayParentLayout() != null) {
            videoView.pause();
            videoView.stopPlayback();
            videoView = null;
            mLiveChinaTabItemAdapter.getcurrentPlayParentLayout().removeView(scrollView);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.live_china_item_not_net:
                if (!TextUtils.isEmpty(liveChinaTabItem.url)) {
                    if (isConnected()) {
                        getHttpDataTabList();
                    }
                } else {
                }
                break;
        }

    }


    public boolean onKeyDownBack() {
        if (videoView != null) {
            return true;
        }
        return false;
    }


    @Override
    public void succeed(LiveChinaAllTablist livechinaBean) {

    }

    @Override
    public void succeedcontent(LiveChinaBean chinaBean) {
        if (getActivity() == null) {
            return;
        }
        if (notNetImg.getVisibility() == View.VISIBLE) {
            notNetImg.setVisibility(View.GONE);
        }

        //   LiveChinaBean liveChinaBean = (LiveChinaBean) chinaBean;
        mLiveChineItems.clear();
        mLiveChineItems.addAll(chinaBean.live);
        mLiveChinaTabItemAdapter.notifyDataSetChanged();
        if (!isFirstGetData) {
            destoryPlay();
        }
        mXListView.stopRefresh();
        mXListView.stopLoadMore();
    }


    @Override
    public void succeedvideo(final livechinavideobean livechinavideobean) {
        usrl = livechinavideobean.getHls_url().getHls4() + livechinavideobean.getFlv_cdn_info().getCdn_code();
        Log.e("-------------->", usrl);
        MediaController controller = new MediaController(getActivity());
        controller.setVisibility(View.GONE);
        videoView.setMediaController(controller);
        videoView.setVideoURI(Uri.parse(usrl));
        videoView.start();


        video_check.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DisplayActivity.class);
                intent.putExtra("url", usrl);
                intent.putExtra("name", liveChineItem.title);
                intent.putExtra("img", liveChineItem.image);
                startActivity(intent);
                videoView.stopPlayback();
            }

        });

    }

    @Override
    public void Failure() {
        Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_LONG).show();
    }

    @Override
    public void initViewLocation(final LiveChineItem liveChineItem, RelativeLayout relativeLayout) {
        this.liveChineItem = liveChineItem;
        LivePresenterImpl presenter = new LivePresenterImpl(this);
        presenter.chinavideo("http://vdn.live.cntv.cn/api2/live.do?channel=pa://cctv_p2p_hd" + liveChineItem.id + "&client=androidapp");

        Toast.makeText(getActivity(), "ccccc", Toast.LENGTH_SHORT).show();
        if (videoView != null) {
            videoView.pause();
            ViewGroup p = (ViewGroup) scrollView.getParent();
            if (p != null) {
                p.removeView(scrollView);
            }
            relativeLayout.addView(scrollView);
        } else {
            if (scrollView == null) {
                scrollView = (RelativeLayout) View.inflate(getActivity(), R.layout.giraffe_player_live, null);
                relativeLayout.addView(scrollView);
            } else {
                ViewGroup p = (ViewGroup) scrollView.getParent();
                if (p != null) {
                    p.removeView(scrollView);
                }
            }
            videoView = scrollView.findViewById(R.id.video_view);
            video_check = scrollView.findViewById(R.id.player_btn);
        }
    }

    @Override
    public void doFirstLoad(LiveChineItem liveChineItem, RelativeLayout live_play_layout) {

    }


    @Override
    public void onPause() {
        super.onPause();
        if (videoView != null) {
            videoView.pause();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (videoView != null) {
            videoView.stopPlayback();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            if (videoView != null) {
                videoView.resume();
            }
        }
    }

}

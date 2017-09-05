package com.example.ipandaitems.view.livechina;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.ipandaitems.R;
import com.example.ipandaitems.base.BaseFragment;
import com.example.ipandaitems.model.entry.livechina.livechinaBean;
import com.example.ipandaitems.model.entry.livechina.livechinavideobean;
import com.example.ipandaitems.model.greendao.DBInterface;
import com.example.ipandaitems.model.greendao.DaoMaster;
import com.example.ipandaitems.model.greendao.DaoSession;
import com.example.ipandaitems.model.greendao.LiveChinaChannelEntity;
import com.example.ipandaitems.model.greendao.LiveChinaChannelEntityDao;
import com.example.ipandaitems.presenter.livepresenter.LivePresenterImpl;
import com.example.ipandaitems.view.livechina.activity.LiveChinaSelectChannelActivity;
import com.example.ipandaitems.view.livechina.entity.LiveChinaAllTablist;
import com.example.ipandaitems.view.livechina.entity.LiveChinaBean;
import com.example.ipandaitems.view.livechina.entity.LiveChinaTabItem;
import com.gridsum.mobiledissector.MobileAppTracker;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by 张豫耀 on 2017/8/23.
 */

public class LiveFragment extends BaseFragment implements Ilivechinaview, View.OnClickListener {


    private static final int FOR_ACTIVIY_RESULT = 2;
    LinearLayout livechinaLinear;
    TabLayout livechinaTab;
    ViewPager liveChinaViewPager;
    private ImageView liveChinaAddChannel;
    Unbinder unbinder;
    private PopupWindow popupWindow;
    private Button pop_btn;

    private List<String> list3 = new ArrayList<>();
    private ArrayList<String> list = new ArrayList<>();
    private List<String> list2 = new ArrayList<>();
    List<Fragment> fragmentList = new ArrayList<>();
    List<String> titleList = new ArrayList<>();
    private FrameLayout mainFragmentLayout;
    private ImageView liveChannelImg;
    View notNetImg;
    // 包括未定义的频道和订阅的频道
    private LiveChinaAllTablist allTablist;

    private DBInterface dbInterface = DBInterface.getInstance();

    LchinaTabView liveChinaTabView;
    boolean sss = false;
    private List<livechinaBean.AlllistBean> chinaBeanList = new ArrayList<>();
    private Button bianji;
    private LiveChinaChannelEntityDao dao;


    @Override
    protected int layoutID() {
        return R.layout.fragment_live_china;
    }

    private void initData() {
        if (isConnected()) {
            getInitData();
        } else {
            notNetImg.setVisibility(View.VISIBLE);
        }
    }

    protected void initView(View view) {
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(getActivity(), "dao.db");
        DaoMaster daoMaster = new DaoMaster(openHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        dao = daoSession.getLiveChinaChannelEntityDao();

        unbinder = ButterKnife.bind(this, view);
        mainFragmentLayout = (FrameLayout) view
                .findViewById(R.id.live_china_main_fragment);
        notNetImg = view.findViewById(R.id.live_china_item_not_net);
        notNetImg.setOnClickListener(this);
        initData();
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {

    }

    private void getInitData() {
        LivePresenterImpl presenter = new LivePresenterImpl(this);
        presenter.chinaget();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void succeed(LiveChinaAllTablist livechinaBean) {
        if (getActivity() == null) {
            return;
        }

        if (notNetImg.getVisibility() == View.VISIBLE) {
            notNetImg.setVisibility(View.GONE);
        }

        allTablist = (LiveChinaAllTablist) livechinaBean;

        if (allTablist != null && allTablist.alllist.size() > 0
                && allTablist.tablist.size() > 0) {
            try {
                // 查询数据库
                List<LiveChinaChannelEntity> findAll = dao.loadAll();
                if (findAll != null && findAll.size() > 0) {
                    allTablist.tablist = new LinkedList<LiveChinaTabItem>();
                    for (int i = 0; i < findAll.size(); i++) {
                        LiveChinaChannelEntity liveChinaTabItemDb = findAll
                                .get(i);
                        LiveChinaTabItem liveChinaTabItem = new LiveChinaTabItem();
                        liveChinaTabItem.order = liveChinaTabItemDb
                                .getOrder();
                        liveChinaTabItem.title = liveChinaTabItemDb
                                .getTitle();
                        liveChinaTabItem.type = liveChinaTabItemDb
                                .getType();
                        liveChinaTabItem.url = liveChinaTabItemDb
                                .getUrl();
                        allTablist.tablist
                                .add(liveChinaTabItem);
                    }
                    liveChinaTabView = new LchinaTabView(
                            LiveFragment.this, allTablist);
                    liveChinaTabView.initData();
                    mainFragmentLayout.addView(
                            liveChinaTabView.getContentView(),
                            0);
                    liveChannelImg = (ImageView) (liveChinaTabView
                            .getContentView())
                            .findViewById(R.id.live_china_add_channel);
                    // loding_progress.setVisibility(View.GONE);
                    liveChannelImg
                            .setOnClickListener(LiveFragment.this);
                } else {
                    // 第一次安装软件 未存到数据库内
                    liveChinaTabView = new LchinaTabView(
                            LiveFragment.this, allTablist);
                    liveChinaTabView.initData();
                    mainFragmentLayout.addView(
                            liveChinaTabView.getContentView(),
                            0);
                    liveChannelImg = (ImageView) (liveChinaTabView
                            .getContentView())
                            .findViewById(R.id.live_china_add_channel);
                    // loding_progress.setVisibility(View.GONE);
                    liveChannelImg
                            .setOnClickListener(LiveFragment.this);

                    for (int i = 0; i < allTablist.tablist
                            .size(); i++) {
                        LiveChinaTabItem liveChinaTabItem = allTablist.tablist
                                .get(i);

                        LiveChinaChannelEntity liveChinaTabItemDb = new LiveChinaChannelEntity();
                        // liveChinaTabItemDb.setOrder(liveChinaTabItem.order);
                        liveChinaTabItemDb.setOrder(Integer
                                .toString(i + 1));
                        liveChinaTabItemDb
                                .setTitle(liveChinaTabItem.title);
                        liveChinaTabItemDb
                                .setType(liveChinaTabItem.type);
                        liveChinaTabItemDb
                                .setUrl(liveChinaTabItem.url);

                        // 增加行数据
                        dao.insert(liveChinaTabItemDb);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void succeedcontent(LiveChinaBean livechinacontentbean) {

    }



    @Override
    public void succeedvideo(livechinavideobean livechinavideobean) {

    }


    @Override
    public void Failure() {
        Toast.makeText(getActivity(), "网络请求失败", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.live_china_add_channel:
                destoryPlay();
                //统计
                MobileAppTracker.trackEvent("更多", "", "直播中国", 0, null, "", getContext());
                MobileAppTracker.setPolicy(MobileAppTracker.POLICY_INTIME);
                Log.e("统计", "事件名称:" + "更多" + "***事件类别:" + "直播中国导航栏" + "***事件标签:" + "直播中国" + "***类型:" + "null");
                Intent intent = new Intent(getActivity(),
                        LiveChinaSelectChannelActivity.class);

                // 重新生成一个对象
                LiveChinaAllTablist liveChinaAllTablist = new LiveChinaAllTablist();
                liveChinaAllTablist.alllist = new LinkedList<LiveChinaTabItem>();
                liveChinaAllTablist.tablist = new LinkedList<LiveChinaTabItem>();
                liveChinaAllTablist.alllist.addAll(allTablist.alllist);
                liveChinaAllTablist.tablist.addAll(allTablist.tablist);

                intent.putExtra("LiveChinaAllTablist", liveChinaAllTablist);
                startActivityForResult(intent, FOR_ACTIVIY_RESULT);
                break;
            case R.id.live_china_item_not_net:
                if (isConnected()) {
                    getInitData();
                }

        }

    }

    public void destoryPlay() {
        if (liveChinaTabView != null) {
            liveChinaTabView.destoryPLay();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        // super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FOR_ACTIVIY_RESULT) {
            if (resultCode == Activity.RESULT_OK) {
                LiveChinaAllTablist liveChinaAllTablist = (LiveChinaAllTablist) data
                        .getExtras()
                        .getSerializable(
                                LiveChinaSelectChannelActivity.RESUTL_TABLIST_STRING);
                reloadTab(liveChinaAllTablist);
            }
        }
    }

    public void reloadTab(LiveChinaAllTablist allTablist) {
        this.allTablist.tablist = allTablist.tablist;
        mainFragmentLayout.removeViewAt(0);

        liveChinaTabView = new LchinaTabView(LiveFragment.this,
                allTablist);
        liveChinaTabView.initData();
        mainFragmentLayout.addView(liveChinaTabView.getContentView(), 0);
        liveChannelImg = (ImageView) (liveChinaTabView.getContentView())
                .findViewById(R.id.live_china_add_channel);
        // loding_progress.setVisibility(View.GONE);
        liveChannelImg.setOnClickListener(LiveFragment.this);

        try {
            // 删除表格数据
            dao.deleteAll();
            List<LiveChinaTabItem> tablist = allTablist.tablist;
            for (int i = 0; i < tablist.size(); i++) {
                LiveChinaTabItem liveChinaTabItem = tablist.get(i);

                LiveChinaChannelEntity liveChinaChannelEntity = new LiveChinaChannelEntity();
                // liveChinaTabItemDb.setOrder(liveChinaTabItem.order);
                liveChinaChannelEntity.setOrder(Integer.toString(i + 1));
                liveChinaChannelEntity.setTitle(liveChinaTabItem.title);
                liveChinaChannelEntity.setType(liveChinaTabItem.type);
                liveChinaChannelEntity.setUrl(liveChinaTabItem.url);

                // 增加行数据
                dao.insert(liveChinaChannelEntity);
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public void setChinaTabViewViewsible(int visible) {
        liveChinaTabView.getContentView()
                .findViewById(R.id.live_china_tab_layout)
                .setVisibility(View.VISIBLE);
    }
}

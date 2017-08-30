package com.example.ipandaitems.view.livechina;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArraySet;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.ipandaitems.R;
import com.example.ipandaitems.base.BaseFragment;
import com.example.ipandaitems.model.entry.livechina.livechinaBean;
import com.example.ipandaitems.model.entry.livechina.livechinacontentbean;
import com.example.ipandaitems.model.entry.livechina.livechinavideobean;
import com.example.ipandaitems.presenter.livepresenter.LivePresenterImpl;
import com.example.ipandaitems.view.livechina.adapter.CFragmentPagerAdapter;
import com.example.ipandaitems.view.livechina.assist.MyGridLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by 张豫耀 on 2017/8/23.
 */

public class LiveFragment extends BaseFragment implements Ilivechinaview, View.OnClickListener {


    LinearLayout livechinaLinear;
    TabLayout livechinaTab;
    ViewPager liveChinaViewPager;
    private ImageView liveChinaAddChannel;
    Unbinder unbinder;
    private PopupWindow popupWindow;
    private Button pop_btn;
    private MyGridLayout grid1;
    private MyGridLayout grid2;
    private List<String> list3 = new ArrayList<>();
    private ArrayList<String> list = new ArrayList<>();
    private List<String> list2 = new ArrayList<>();
    List<Fragment> fragmentList = new ArrayList<>();
    List<String> titleList = new ArrayList<>();
    boolean sss = false;
    private List<livechinaBean.AlllistBean> chinaBeanList = new ArrayList<>();
    CFragmentPagerAdapter adapter;
    private Button bianji;

    @Override
    protected int layoutID() {
        return R.layout.live_fragment;
    }

    @Override
    protected void initView(View view) {
        liveChinaAddChannel = view.findViewById(R.id.live_china_add_channel);
        livechinaLinear = view.findViewById(R.id.livechina_linear);
        livechinaTab = view.findViewById(R.id.livechina_tab);
        liveChinaViewPager = view.findViewById(R.id.live_china_viewPager);
        liveChinaAddChannel.setOnClickListener(this);
        LivePresenterImpl livePresenter = new LivePresenterImpl(this);
        livePresenter.chinaget();
    }

    @Override
    protected void loadData() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.livechina_element, null);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0xffffff));
        pop_btn = view.findViewById(R.id.live_chinnal_select_channel_cancel);
        grid1 = view.findViewById(R.id.userGridView);
        grid2 = view.findViewById(R.id.otherGridView);
        bianji = view.findViewById(R.id.live_china_select_channel_bianji);

    }

    @Override
    protected void initListener() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private void initpop() {

        bianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bianji.getText().toString().equals("编辑")) {
                    bianji.setText("完成");
                } else if (bianji.getText().toString().equals("完成")) {
                    bianji.setText("编辑");
                }

            }
        });
        grid1.setGridLayoutItemDrageAble(true);
        list.addAll(titleList);
        grid1.addItems(list);

        //下Gridview 添加item
        grid2.setGridLayoutItemDrageAble(false);
        for (String str1 : list3) {
            if (!list.contains(str1)) {
                // 打印出list2没有b,d
                list2.add(str1);
            }
        }
        grid2.addItems(list2);

        grid1.setOnItemSelectListener(new MyGridLayout.OnItemSelectListener() {
            @Override
            public void onItemSelect(String indexString) {
                list.remove(indexString);
                if (!list2.contains(indexString)) {
                    list2.add(indexString);
                    grid2.addTvItem(indexString);
                }
            }
        });
        grid2.setOnItemSelectListener(new MyGridLayout.OnItemSelectListener() {
            @Override
            public void onItemSelect(String indexString) {

                list2.remove(indexString);
                if (!list.contains(indexString)) {
                    list.add(indexString);
//                    grid1.addItems(list);
                    grid1.addTvItem(indexString);

                }


            }


        });


        pop_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                initss();
            }
        });
    }

    private void initss() {
        SharedPreferences sp = getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("asd", "2");
        Set<String> set = new ArraySet<String>();
        set.addAll(list);
        editor.putStringSet("slist", set);
        editor.commit();


        if (sss) {
            if (!getContext().getSharedPreferences("data", Context.MODE_PRIVATE).getString("asd", "").equals("")) {

                initload();

                adapter.notifyDataSetChanged();

            }
        }
        if (list.size() > 0) {
            list.clear();
            list2.clear();
            grid1.removeAllViews();
            grid2.removeAllViews();
        }
    }


    @Override
    public void succeed(livechinaBean livechinaBean) {

        chinaBeanList.addAll(livechinaBean.getAlllist());
        for (int i = 0; i < livechinaBean.getAlllist().size(); i++) {
            list3.add(livechinaBean.getAlllist().get(i).getTitle());
        }

        if (getContext().getSharedPreferences("data", Context.MODE_PRIVATE).getString("asd", "").equals("")) {
            for (int i = 0; i < livechinaBean.getTablist().size(); i++) {
                Log.e("------------------->", livechinaBean.getTablist().get(i).getUrl());
                fragmentList.add(new LiveChinaFragment(livechinaBean.getTablist().get(i).getUrl()));
                titleList.add(livechinaBean.getTablist().get(i).getTitle());
            }

        } else {

            initload();
        }

        adapter = new CFragmentPagerAdapter(getChildFragmentManager(), fragmentList, titleList);
        liveChinaViewPager.setAdapter(adapter);
        livechinaTab.setupWithViewPager(liveChinaViewPager);
        livechinaTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        sss = true;
    }

    @Override
    public void succeedcontent(livechinacontentbean livechinacontentbean) {

    }

    @Override
    public void succeedvideo(livechinavideobean livechinavideobean) {

    }

    private void initload() {
        Set<String> setlist = new ArraySet<>();
        setlist.addAll(list3);
        titleList.clear();
        fragmentList.clear();

        Set<String> titleSet = getContext().getSharedPreferences("data", Context.MODE_PRIVATE).getStringSet("slist", setlist);
        titleList.addAll(titleSet);
        for (int i = 0; i < titleList.size(); i++) {
            for (int j = 0; j < chinaBeanList.size(); j++) {
                if (titleList.get(i).equals(chinaBeanList.get(j).getTitle())) {
                    fragmentList.add(new LiveChinaFragment(chinaBeanList.get(j).getUrl()));
                }
            }

        }
    }

    @Override
    public void Failure() {
        Toast.makeText(getActivity(), "网络请求失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        popupWindow.showAtLocation(livechinaLinear, Gravity.NO_GRAVITY, 0, 0);
        initpop();
    }
}

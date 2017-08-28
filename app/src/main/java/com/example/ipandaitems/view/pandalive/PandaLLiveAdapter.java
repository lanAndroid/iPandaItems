package com.example.ipandaitems.view.pandalive;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * @name yanantian
 * @motto 莫羡他人谢语花, 腹有诗书气自华
 * @E-mail 1173568715@qq.com
 * @WX 15978622391
 */

public class PandaLLiveAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> list;
    String[] ss = {"直播", "精彩一刻", "当熊不让", "超萌滚滚秀", "熊猫档案", "熊猫TOP榜", "熊猫那些事儿", "特别节目", "原创新闻"};

    public PandaLLiveAdapter(FragmentManager fm, ArrayList<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return ss[position];
    }
}

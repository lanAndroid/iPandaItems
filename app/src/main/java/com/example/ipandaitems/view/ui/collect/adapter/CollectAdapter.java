package com.example.ipandaitems.view.ui.collect.adapter;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;

import com.example.ipandaitems.BuildConfig;

import java.util.List;

/**
 * Created by 张豫耀 on 2017/9/4.
 */

public class CollectAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mCurTransaction = null;
    private Fragment mCurrentPrimaryItem = null;
    List<String> strlist;

    public CollectAdapter(FragmentManager mFragmentManager, List<Fragment> list, List<String> strlist) {
        super(mFragmentManager);
        this.mFragmentManager = mFragmentManager;
        this.list = list;
        this.strlist = strlist;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return strlist.get(position);
    }

    private final String makeFragmentName(int viewId, long id) {
        return "android:switcher:" + viewId + ":" + id;
    }

    @Override
    public void startUpdate(ViewGroup container) {
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (mCurTransaction == null) {
            // 创建新事务
            mCurTransaction = mFragmentManager.beginTransaction();
        }

        // 获取单项的Id
        final long itemId = getItemId(position);

        // 根据View的Id和单项Id生成名称
        String name = makeFragmentName(container.getId(), itemId);
        // 根据生成的名称获取FragmentManager中的Fragment
        Fragment fragment = mFragmentManager.findFragmentByTag(name);
        if (fragment != null) {
            if (BuildConfig.DEBUG)
                // 如果Fragment已被添加到FragmentManager中,则只需要附着到Activity
                mCurTransaction.attach(fragment);
        } else {
            // 如果Fragment未被添加到FragmentManager中,则先获取,再添加到Activity中
            fragment = getItem(position);
            if (BuildConfig.DEBUG)
                mCurTransaction.add(container.getId(), fragment, makeFragmentName(container.getId(), itemId));
        }
        // 非当前主要项,需要隐藏相关的菜单及信息
        if (fragment != mCurrentPrimaryItem) {
            fragment.setMenuVisibility(false);
            fragment.setUserVisibleHint(false);
        }

        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (mCurTransaction == null) {
            // 创建新事务
            mCurTransaction = mFragmentManager.beginTransaction();
        }
        if (BuildConfig.DEBUG)
            // 将Fragment移出UI,但并未从FragmentManager中移除
            mCurTransaction.detach((Fragment) object);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        Fragment fragment = (Fragment) object;
        if (fragment != mCurrentPrimaryItem) {
            // 主要项切换,相关菜单及信息进行切换
            if (mCurrentPrimaryItem != null) {
                mCurrentPrimaryItem.setMenuVisibility(false);
                mCurrentPrimaryItem.setUserVisibleHint(false);
            }
            if (fragment != null) {
                fragment.setMenuVisibility(true);
                fragment.setUserVisibleHint(true);
            }
            mCurrentPrimaryItem = fragment;
        }
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        if (mCurTransaction != null) {
            // 提交事务
            mCurTransaction.commitAllowingStateLoss();
            mCurTransaction = null;
            // 立即运行等待中事务
            mFragmentManager.executePendingTransactions();
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return ((Fragment) object).getView() == view;
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }


}

package com.example.ipandaitems.utils;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.ipandaitems.App;
import com.example.ipandaitems.base.BaseFragment;


/**
 * Created by YC on 2017/4/12.
 */

public class FragmentBuilder {

    private BaseFragment lastFragment;
    private FragmentManager fragmentManager;
    private BaseFragment fragment;
    private FragmentTransaction fragmentTransaction;
    private String simpleName;

    private FragmentBuilder() {
        init();
    }

    private static FragmentBuilder fragmentBuilder;

    public static synchronized FragmentBuilder getInstance() {
        if (fragmentBuilder == null) {
            fragmentBuilder = new FragmentBuilder();
        }
        return fragmentBuilder;
    }

    private void init() {
        fragmentManager = App.baseActivity.getSupportFragmentManager();
    }

    public FragmentBuilder start(Class<? extends BaseFragment> BaseClass, int ViewID) {
        fragmentTransaction = fragmentManager.beginTransaction();
        simpleName = BaseClass.getSimpleName();
        fragment = (BaseFragment) fragmentManager.findFragmentByTag(simpleName);
        if (fragment == null) {
            try {
                fragment = BaseClass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            fragmentTransaction.add(ViewID, fragment, simpleName);
        }
        if (App.baseFragment != null) {
            fragmentTransaction.hide(App.baseFragment);
        }
        fragmentTransaction.show(fragment);
        fragmentTransaction.addToBackStack(simpleName);
        return this;
    }

    public FragmentBuilder setParms(Bundle bundle) {
        fragment.setParmars(bundle);
        return this;
    }


    public BaseFragment builder() {
        App.baseFragment = fragment;
        fragmentTransaction.commit();
        return fragment;
    }

    public BaseFragment getLastFragment() {
        return lastFragment;
    }

    public void setLastFragment(BaseFragment lastFragment) {
        this.lastFragment = lastFragment;
    }

    public static void clean() {
        fragmentBuilder = null;
    }
}

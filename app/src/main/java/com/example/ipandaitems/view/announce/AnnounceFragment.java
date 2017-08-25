package com.example.ipandaitems.view.announce;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chanven.lib.cptr.PtrClassicDefaultHeader;
import com.chanven.lib.cptr.PtrFrameLayout;
import com.chanven.lib.cptr.PtrHandler;
import com.example.ipandaitems.R;
import com.example.ipandaitems.base.BaseFragment;
import com.example.ipandaitems.model.entry.AnnBean;
import com.example.ipandaitems.presenter.annpresenter.AnnIPresenter;
import com.example.ipandaitems.presenter.annpresenter.AnnIPresenterImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 张豫耀 on 2017/8/23.
 */

public class AnnounceFragment extends BaseFragment implements AnnView {

    Unbinder unbinder;
    @BindView(R.id.Imagev)
    ImageView Imagev;
    @BindView(R.id.recy)
    RecyclerView recy;
    @BindView(R.id.ptr)
    PtrFrameLayout ptr;
    private AnnIPresenter annIPresenter;

    @Override
    protected int layoutID() {
        return R.layout.announce_fragment;
    }

    @Override
    protected void initView(View view) {
        annIPresenter = new AnnIPresenterImpl(this);
        annIPresenter.annGet();
    }


    @Override
    protected void loadData() {
        //1.默认经典头布局
       PtrClassicDefaultHeader defaultHeader = new PtrClassicDefaultHeader(getActivity());
//        给Ptr添加头布局
        ptr.setHeaderView(defaultHeader);
//        使头布局的状态和刷新状态同步
        ptr.addPtrUIHandler(defaultHeader);
        ptr.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                if (!(content instanceof ImageView)&&!(content instanceof RecyclerView)) {
                    return true;
                }else {
                    return false;
                }

            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

            }
        });

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

    @Override
    public void onSuccess(AnnBean annBean) {
        List<AnnBean.BigImgBean> bigImg = annBean.getBigImg();
        List<AnnBean.ListBean> list = annBean.getList();
        for (int i = 0; i < bigImg.size(); i++) {
            String image = bigImg.get(i).getImage();
            Glide.with(getActivity()).load(image).into(Imagev);
        }
        recy.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        AnnMyadapter adapter = new AnnMyadapter(getActivity(), list);
        recy.setAdapter(adapter);
    }

    @Override
    public void onError(String error) {

    }
}

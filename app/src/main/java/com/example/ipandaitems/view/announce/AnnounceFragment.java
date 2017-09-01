package com.example.ipandaitems.view.announce;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chanven.lib.cptr.recyclerview.RecyclerAdapterWithHF;
import com.example.ipandaitems.R;
import com.example.ipandaitems.base.BaseFragment;
import com.example.ipandaitems.model.entry.AnnBean;
import com.example.ipandaitems.model.entry.PanadaBean;
import com.example.ipandaitems.model.entry.VideoBeanr;
import com.example.ipandaitems.presenter.annpresenter.AnnIPresenter;
import com.example.ipandaitems.presenter.annpresenter.AnnIPresenterImpl;
import com.example.ipandaitems.utils.UrlUtils;
import com.example.ipandaitems.view.announce.annadapter.AnnMyadapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 张豫耀 on 2017/8/23.
 */

public class AnnounceFragment extends BaseFragment implements AnnView {

    Unbinder unbinder;
    @BindView(R.id.recy)
    XRecyclerView recy;

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
        final List<AnnBean.BigImgBean> bigImg = annBean.getBigImg();
        final List<AnnBean.ListBean> list = annBean.getList();
        View view = View.inflate(getActivity(), R.layout.video_image, null);
        ImageView image = view.findViewById(R.id.Imagev);
        TextView tv = view.findViewById(R.id.ImagevText);
        for (int i = 0; i < bigImg.size(); i++) {
            tv.setText(bigImg.get(i).getTitle());
            String imageurl = bigImg.get(i).getImage();
            Glide.with(getActivity()).load(imageurl).into(image);
            final String url = bigImg.get(i).getUrl();
//            Imagev.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(getActivity(), PanadaVideo.class);
//                    intent.putExtra("path", url);
//                    startActivity(intent);
//                }
//            });
        }
        recy.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        AnnMyadapter adapter = new AnnMyadapter(getActivity(), list);
        RecyclerAdapterWithHF myadapter = new RecyclerAdapterWithHF((RecyclerView.Adapter) adapter);
        recy.setAdapter(myadapter);
        //Recycle点击事件
        myadapter.setOnItemClickListener(new RecyclerAdapterWithHF.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerAdapterWithHF adapter, RecyclerView.ViewHolder vh, int position) {
                list.get(position).getUrl();
                String id = list.get(position).getId();
               // &n=7&serviceId=panda&o=desc&of=time&p=1
                System.out.println(id+"++++++++++++++++++");
                String sid = bigImg.get(0).getPid();
                Intent intent = new Intent(getActivity(), PanadaTop.class);
                intent.putExtra("id",id);
                intent.putExtra("sid",sid);
                startActivity(intent);
            }
        });
        recy.addHeaderView(view);
    }

    @Override
    public void onError(String error) {
        System.out.println(error);
    }

    @Override
    public void panadaSuccess(PanadaBean panadaBean) {

    }

    @Override
    public void videoSuccess(VideoBeanr videoBeanr) {

    }
}

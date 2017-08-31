package com.example.ipandaitems.view.video;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.ipandaitems.model.entry.TopBean;
import com.example.ipandaitems.model.entry.TopListBean;
import com.example.ipandaitems.presenter.videopresenter.VideoIPresenter;
import com.example.ipandaitems.presenter.videopresenter.VideoPresenterImpl;
import com.example.ipandaitems.view.WebViewActivity;
import com.example.ipandaitems.view.video.adapter.TopListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @username tian
 * @motto 莫羡他人谢语花, 腹有诗书气自华
 * @time 2017/8/24 15:05
 * 熊猫直播
 */

public class VideoFragment extends BaseFragment implements VideoInfo {
    @BindView(R.id.Imagev)
    ImageView Imagev;
    @BindView(R.id.ImagevText)
    TextView ImagevText;
    Unbinder unbinder;
    @BindView(R.id.xrecy)
    RecyclerView xrecy;
    private VideoIPresenter videoIPresenter;


    @Override
    protected int layoutID() {
        return R.layout.video_fragment;
    }

    @Override
    protected void initView(View view) {
     videoIPresenter = new VideoPresenterImpl(this);
        videoIPresenter.top();
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
    public void onSuccess(TopBean dataBean) {
        TopBean.DataBean data = dataBean.getData();
        List<TopBean.DataBean.BigImgBean> bigImg = data.getBigImg();
        for (int i = 0; i < bigImg.size(); i++) {
            String image = bigImg.get(i).getImage();
            String title = bigImg.get(i).getTitle();
            ImagevText.setText(title);
            Glide.with(getActivity()).load(image).into(Imagev);
        }
        String listurl = data.getListurl();
        videoIPresenter.topList(listurl);
//        Imagev.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(getActivity(), PanadaVideo.class);
//                startActivity(intent);
//            }
//        });
    }

    @Override
    public void onError(String e) {

    }

    @Override
    public void topListSuccess(TopListBean topListBean) {
//        final List<TopListBean.ListBean> list = topListBean.getList();
//        xrecy.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
//        TopListAdapter adapter = new TopListAdapter(getActivity(), list);
//        RecyclerAdapterWithHF myadapter = new RecyclerAdapterWithHF((RecyclerView.Adapter) adapter);
//        xrecy.setAdapter(myadapter);
//        myadapter.setOnItemClickListener(new RecyclerAdapterWithHF.OnItemClickListener() {
//            @Override
//            public void onItemClick(RecyclerAdapterWithHF adapter, RecyclerView.ViewHolder vh, int position) {
//                Intent intent = new Intent(getActivity(), WebViewActivity.class);
//                String url = list.get(position).getUrl();
//                intent.putExtra("url", url);
//                startActivity(intent);
//            }
//        });
//        //禁止recycleview滑动
//        xrecy.setNestedScrollingEnabled(false);
    }

    @Override
    public void topListError(String e) {

    }
}

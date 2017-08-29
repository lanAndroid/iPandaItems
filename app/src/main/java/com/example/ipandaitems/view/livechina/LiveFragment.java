package com.example.ipandaitems.view.livechina;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.ipandaitems.R;
import com.example.ipandaitems.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;



/**
 * Created by 张豫耀 on 2017/8/23.
 */

public class LiveFragment extends BaseFragment {
    @BindView(R.id.live_china_add_channel)
    ImageView liveChinaAddChannel;
    Unbinder unbinder;
    @BindView(R.id.livechina_linear)
    LinearLayout livechinaLinear;


    @Override
    protected int layoutID() {
        return R.layout.live_fragment;
    }

    @Override
    protected void initView(View view) {

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

    @OnClick(R.id.live_china_add_channel)
    public void onViewClicked() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.livechina_element, null);
        final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0xffffff));
        popupWindow.showAtLocation(livechinaLinear, Gravity.NO_GRAVITY, 0, 0);
        Button button = (Button) view.findViewById(R.id.live_chinnal_select_channel_cancel);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
    }


}

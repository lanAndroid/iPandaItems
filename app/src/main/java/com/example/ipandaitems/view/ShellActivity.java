package com.example.ipandaitems.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.ipandaitems.R;
import com.example.ipandaitems.base.BaseActivity;
import com.example.ipandaitems.base.BaseFragment;
import com.example.ipandaitems.utils.FragmentBuilder;
import com.example.ipandaitems.view.announce.AnnounceFragment;
import com.example.ipandaitems.view.home.HomeFragment;
import com.example.ipandaitems.view.livechina.LiveFragment;
import com.example.ipandaitems.view.pandalive.PandaLiveFragment;
import com.example.ipandaitems.view.video.VideoFragment;

import java.sql.SQLException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShellActivity extends BaseActivity {

    @BindView(R.id.home_btn)
    RadioButton homeBtn;
    @BindView(R.id.streaming_btn)
    RadioButton streamingBtn;
    @BindView(R.id.video_btn)
    RadioButton videoBtn;
    @BindView(R.id.announce_btn)
    RadioButton announceBtn;
    @BindView(R.id.live_btn)
    RadioButton liveBtn;
    @BindView(R.id.shell_tv)
    TextView shellTv;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.shell_log)
    ImageView shellLog;
    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_inter)
    TextView titleInter;


    @Override
    protected int layoutID() {
        return R.layout.activity_shell;
    }

    @Override
    protected void initView() throws SQLException {
    }

    @Override
    protected void loadData() {
        FragmentBuilder.getInstance().start(HomeFragment.class, R.id.overall_frame).builder();
        //     FragmentBuilder.getInstance().builder();
    }

    @Override
    protected void initListener() {

    }


    @OnClick({R.id.home_btn, R.id.streaming_btn, R.id.video_btn, R.id.announce_btn, R.id.live_btn, R.id.shell_log,R.id.title_inter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_btn:
                titleLeft.setVisibility(View.VISIBLE);
                titleInter.setVisibility(View.VISIBLE);
                FragmentBuilder.getInstance().start(HomeFragment.class, R.id.overall_frame).builder();
                shellTv.setText("");
                break;
            case R.id.streaming_btn:
                titleLeft.setVisibility(View.GONE);
                titleInter.setVisibility(View.GONE);
                FragmentBuilder.getInstance().start(PandaLiveFragment.class, R.id.overall_frame).builder();
                shellTv.setText("熊猫直播");
                //   FragmentBuilder.getInstance().builder();
                break;
            case R.id.video_btn:
                titleLeft.setVisibility(View.GONE);
                titleInter.setVisibility(View.GONE);
                FragmentBuilder.getInstance().start(VideoFragment.class, R.id.overall_frame).builder();
                shellTv.setText("滚滚视频");
                //    FragmentBuilder.getInstance().builder();
                break;
            case R.id.announce_btn:
                titleLeft.setVisibility(View.GONE);
                titleInter.setVisibility(View.GONE);
                FragmentBuilder.getInstance().start(AnnounceFragment.class, R.id.overall_frame).builder();

                shellTv.setText("熊猫播报");
                //     FragmentBuilder.getInstance().builder();
                break;
            case R.id.live_btn:
                titleLeft.setVisibility(View.GONE);
                titleInter.setVisibility(View.GONE);
                FragmentBuilder.getInstance().start(LiveFragment.class, R.id.overall_frame).builder();
                shellTv.setText("直播中国");
                //    FragmentBuilder.getInstance().builder();
                break;
            case R.id.shell_log:
                startActivity(new Intent(ShellActivity.this, LogActivity.class));
                break;
            case R.id.title_inter:
                startActivity(new Intent(ShellActivity.this, OrinGinalActivity.class));
                break;

        }
        //    FragmentBuilder.getInstance().builder();
    }

    @Override
    public void onBackPressed() {

        FragmentManager manager = getSupportFragmentManager();
        FragmentManager.BackStackEntry entry = manager.getBackStackEntryAt(manager.getBackStackEntryCount() - 1);
        String name = entry.getName();
        if ("HomeFragment".equals(name) || "PandaLiveFragment".equals(name)
                || "VideoFragment".equals(name) || "AnnounceFragment".equals(name) || "LiveFragment".equals(name)) {
            Process.killProcess(Process.myPid());
            System.exit(0);
        } else {
            manager.popBackStackImmediate();
            String fragmentName = manager.getBackStackEntryAt(manager.getBackStackEntryCount() - 1).getName();
            BaseFragment fragment = (BaseFragment) manager.findFragmentByTag(fragmentName);
            FragmentBuilder.getInstance().setLastFragment(fragment);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick()
    public void onViewClicked() {

    }
}

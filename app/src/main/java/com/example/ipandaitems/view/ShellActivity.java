package com.example.ipandaitems.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.FrameLayout;
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
import com.example.ipandaitems.view.personalcenter.LogActivity;
import com.example.ipandaitems.view.video.VideoFragment;

import java.sql.SQLException;

import butterknife.OnClick;
import butterknife.Optional;

public class ShellActivity extends BaseActivity {



    private TextView title_left;
    private TextView title_inter;
    private TextView shell_tv;
    private FrameLayout overall_frame;
    private RadioButton home_btn;
    private RadioButton streaming_btn;
    private RadioButton video_btn;
    private RadioButton announce_btn;
    private RadioButton live_btn;
    private RadioGroup radioGroup;
    private ImageView shell_log;

    @Override
    protected int layoutID() {
        return R.layout.activity_shell;
    }

    @Override
    protected void initView() throws SQLException {
        title_left = (TextView) findViewById(R.id.title_left);
        title_inter = (TextView) findViewById(R.id.title_inter);
        shell_tv = (TextView) findViewById(R.id.shell_tv);
        overall_frame = (FrameLayout) findViewById(R.id.overall_frame);
        home_btn = (RadioButton) findViewById(R.id.home_btn);
        streaming_btn = (RadioButton) findViewById(R.id.streaming_btn);
        video_btn = (RadioButton) findViewById(R.id.video_btn);
        announce_btn = (RadioButton) findViewById(R.id.announce_btn);
    }

    @Override
    protected void loadData() {
        FragmentBuilder.getInstance().start(HomeFragment.class, R.id.overall_frame).builder();
        //     FragmentBuilder.getInstance().builder();

    }

    @Override
    protected void initListener() {

    }

    @Optional
    @OnClick({R.id.home_btn, R.id.streaming_btn, R.id.video_btn, R.id.announce_btn, R.id.live_btn, R.id.shell_log, R.id.title_inter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_btn:
                title_left.setVisibility(View.VISIBLE);
                title_inter.setVisibility(View.VISIBLE);
                FragmentBuilder.getInstance().start(HomeFragment.class, R.id.overall_frame).builder();
                shell_tv.setText("");
                break;
            case R.id.streaming_btn:
                title_left.setVisibility(View.GONE);
                title_inter.setVisibility(View.GONE);
                FragmentBuilder.getInstance().start(PandaLiveFragment.class, R.id.overall_frame).builder();
                shell_tv.setText("熊猫直播");
                //   FragmentBuilder.getInstance().builder();
                break;
            case R.id.video_btn:
                title_left.setVisibility(View.GONE);
                title_inter.setVisibility(View.GONE);
                FragmentBuilder.getInstance().start(VideoFragment.class, R.id.overall_frame).builder();
                shell_tv.setText("滚滚视频");
                //    FragmentBuilder.getInstance().builder();
                break;
            case R.id.announce_btn:
                title_left.setVisibility(View.GONE);
                title_inter.setVisibility(View.GONE);
                FragmentBuilder.getInstance().start(AnnounceFragment.class, R.id.overall_frame).builder();

                shell_tv.setText("熊猫播报");
                //     FragmentBuilder.getInstance().builder();
                break;
            case R.id.live_btn:
                title_left.setVisibility(View.GONE);
                title_inter.setVisibility(View.GONE);
                FragmentBuilder.getInstance().start(LiveFragment.class, R.id.overall_frame).builder();
                shell_tv.setText("直播中国");
                //    FragmentBuilder.getInstance().builder();
                break;
            case R.id.shell_log:
                startActivity(new Intent(ShellActivity.this, LogActivity.class));
                break;
            case R.id.title_inter:
                startActivity(new Intent(ShellActivity.this,com.example.ipandaitems.view.navigation.OrinGinalActivity.class));
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

    @OnClick()
    public void onViewClicked() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) and run LayoutCreator again
    }
}

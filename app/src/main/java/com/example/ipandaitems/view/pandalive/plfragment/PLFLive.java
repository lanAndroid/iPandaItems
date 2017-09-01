package com.example.ipandaitems.view.pandalive.plfragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.ipandaitems.R;
import com.example.ipandaitems.base.BaseFragment;
import com.example.ipandaitems.model.entry.pandalive.PLHome;
import com.example.ipandaitems.model.entry.pandalive.PLLive;
import com.example.ipandaitems.model.entry.pandalive.PLVideo;
import com.example.ipandaitems.presenter.pandalivepresenter.plfragment.PPLLives;
import com.example.ipandaitems.view.pandalive.adapter.PL1AdapterLive;
import com.example.ipandaitems.view.pandalive.view.MyGridView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

import static com.example.ipandaitems.R.id.pl_live_abstract;


/**
 * @name yanantian
 * @motto 莫羡他人谢语花, 腹有诗书气自华
 * @E-mail 1173568715@qq.com
 * @WX 15978622391
 */

public class PLFLive extends BaseFragment implements PLFLiveView {


    @BindView(R.id.pl_live_video)
    VideoView plLiveVideo;
    @BindView(R.id.pl_live_name)
    TextView plLiveName;
    @BindView(pl_live_abstract)
    ImageView plLiveAbstract;
    @BindView(R.id.pl_live_content)
    TextView plLiveContent;
    @BindView(R.id.pl_live_duoshijiao)
    TextView plLiveDuoshijiao;
    @BindView(R.id.pl_live_kanliao)
    TextView plLiveKanliao;
    @BindView(R.id.pl_live_duoshijiao_biao)
    TextView plLiveDuoshijiaoBiao;
    @BindView(R.id.pl_live_kanliao_biao)
    TextView plLiveKanliaoBiao;
    @BindView(R.id.pl_live_chat_edit)
    EditText plLiveChatEdit;
    @BindView(R.id.pl_live_chat_but)
    Button plLiveChatBut;
    @BindView(R.id.pl_live_chat_listview)
    ListView plLiveChatListview;
    @BindView(R.id.pl_live_chat)
    LinearLayout plLiveChat;
    @BindView(R.id.pl_live_scroll)
    ScrollView plLiveScroll;
    Unbinder unbinder;
    @BindView(R.id.pl_live_multi_grid)
    MyGridView plLiveMultiGrid;
    private boolean boo = false;
    private PL1AdapterLive live;
    private List<PLLive.ListBean> list;
    private String path = "http://baobab.wdjcdn.com/145076769089714.mp4";

    @Override
    protected int layoutID() {
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        Window window = getActivity().getWindow();
        window.setFlags(flag, 600);
        return R.layout.pl_live;
    }

    @Override
    protected void initView(View view) {
        PPLLives pp = new PPLLives(this);
        pp.getViews();
        pp.getLives();
        pp.getVideo();

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void getDatas(PLHome plHome) {
        plLiveName.setText(plHome.getLive().get(0).getTitle());
        plLiveContent.setText(plHome.getLive().get(0).getBrief());
        plLiveDuoshijiao.setText(plHome.getBookmark().getMultiple().get(0).getTitle());
        plLiveKanliao.setText(plHome.getBookmark().getWatchTalk().get(0).getTitle());
    }

    @Override
    public void getDataLive(PLLive plLive) {
        list = plLive.getList();
        live = new PL1AdapterLive(getActivity(), list);
        plLiveMultiGrid.setAdapter(live);
    }

    @Override
    public void getVideos(PLVideo plVideo) {
        String ss = plVideo.getHls_url().getHls4() + plVideo.getFlv_cdn_info().getCdn_code();
        if (Vitamio.isInitialized(getContext())) {
            plLiveVideo.setVideoPath(ss);
            MediaController controller = new MediaController(getContext());
            plLiveVideo.setMediaController(controller);
            plLiveVideo.start();
        }



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

    @OnClick({pl_live_abstract, R.id.pl_live_duoshijiao, R.id.pl_live_kanliao, R.id.pl_live_chat_but})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case pl_live_abstract:
                if (boo == true) {
                    plLiveAbstract.setImageResource(R.drawable.live_china_detail_down);
                    plLiveContent.setVisibility(View.GONE);
                    boo = false;
                } else {
                    plLiveAbstract.setImageResource(R.drawable.live_china_detail_up);
                    plLiveContent.setVisibility(View.VISIBLE);
                    boo = true;
                }
                break;
            case R.id.pl_live_duoshijiao:
                plLiveDuoshijiao.setTextColor(getActivity().getResources().getColor(R.color.tianse));
                plLiveDuoshijiaoBiao.setBackgroundColor(getResources().getColor(R.color.tianse));
                plLiveKanliao.setTextColor(Color.BLACK);
                plLiveKanliaoBiao.setBackgroundColor(0x00000000);
                plLiveMultiGrid.setVisibility(View.VISIBLE);
                plLiveChat.setVisibility(View.GONE);
                break;
            case R.id.pl_live_kanliao:
                plLiveKanliao.setTextColor(getActivity().getResources().getColor(R.color.tianse));
                plLiveKanliaoBiao.setBackgroundColor(getResources().getColor(R.color.tianse));
                plLiveDuoshijiao.setTextColor(Color.BLACK);
                plLiveDuoshijiaoBiao.setBackgroundColor(0x00000000);
                plLiveMultiGrid.setVisibility(View.GONE);
                plLiveChat.setVisibility(View.VISIBLE);
                break;
            case R.id.pl_live_chat_but:

                break;
        }
    }
}

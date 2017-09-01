package com.example.ipandaitems.view.livechina.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.ipandaitems.R;
import com.example.ipandaitems.model.entry.livechina.livechinaBean;
import com.example.ipandaitems.model.entry.livechina.livechinacontentbean;
import com.example.ipandaitems.model.entry.livechina.livechinavideobean;
import com.example.ipandaitems.presenter.livepresenter.LivePresenterImpl;
import com.example.ipandaitems.view.livechina.Ilivechinaview;

import java.util.List;
import java.util.Map;

import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by 张豫耀 on 2017/8/29.
 */

public class LiveChinaZhiboItemAdapter extends RecyclerView.Adapter<LiveChinaZhiboItemAdapter.ViewHolder> implements Ilivechinaview {
    private Context context;
    List<livechinacontentbean.LiveBean> list;
    int s = 0;
    private livechinavideobean flv;
    int a = 0;
    private Map<String, String> map;
    private String url;

    public LiveChinaZhiboItemAdapter(Context context, List<livechinacontentbean.LiveBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_livechina_item, parent, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.title.setText(list.get(position).getTitle());
        holder.brief.setText(list.get(position).getBrief());
        Log.e("--------------->", list.get(position).getBrief());
        Glide.with(context).load(list.get(position).getImage()).asBitmap()
                .thumbnail(0.01f)//降低分辨率
                .placeholder(R.drawable._no_img)//未加载是背景图
                .error(R.color.colorAccent)//图片加载错误是背景图
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
        holder.linview.setVisibility(View.GONE);
        holder.jiazai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (s) {
                    case 1:
                        s = 0;
                        holder.linview.setVisibility(View.VISIBLE);
                        holder.jiazai.setImageResource(R.drawable.live_china_detail_up);
                        break;
                    case 0:
                        s = 1;
                        holder.linview.setVisibility(View.GONE);
                        holder.jiazai.setImageResource(R.drawable.live_china_detail_down);

                        break;
                }
            }
        });

        LivePresenterImpl presenter = new LivePresenterImpl(this);
        presenter.chinavideo("http://vdn.live.cntv.cn/api2/live.do?channel=pa://cctv_p2p_hd" + list.get(position).getId() + "&client=androidapp");

        Log.e("Tag", "http://vdn.live.cntv.cn/api2/live.do?channel=pa://cctv_p2p_hd" + list.get(position).getId() + "&client=androidapp");
        // holder.videoview_top.start();
        holder.bo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  holder.videoview_top.stopPlayback();
                notifyItemChanged(holder.getLayoutPosition() - 1);
                Log.d("LiveChinaZhiboItemAdapt", "a:" + a);

                a = position;
                if (position == holder.getLayoutPosition() - 1) {
                    Toast.makeText(context, "播放", Toast.LENGTH_SHORT).show();
                    if (Vitamio.isInitialized(context)) {
                        holder.imageView.setVisibility(View.GONE);
                        holder.bo.setVisibility(View.GONE);
                        Log.e("TAg", url);
                        MediaController controller = new MediaController(context);
                        holder.videoview_top.setVideoURI(Uri.parse(url));
                        holder.videoview_top.setMediaController(controller);
                        // holder.videoview_top.requestFocus();
                        holder.videoview_top.start();
                    }

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void succeed(livechinaBean livechinaBean) {

    }

    @Override
    public void succeedcontent(livechinacontentbean livechinacontentbean) {

    }

    @Override
    public void succeedvideo(livechinavideobean livechinavideobean) {

        url = livechinavideobean.getHls_url().getHls1() + livechinavideobean.getFlv_cdn_info().getCdn_code();

    }

    @Override
    public void Failure() {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, brief;
        ImageView bo;
        ImageView imageView;
        ImageView jiazai;
        VideoView videoview_top;
        LinearLayout linview;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.lc_fi_title);
            brief = (TextView) itemView.findViewById(R.id.lc_fi_brief);
            bo = (ImageView) itemView.findViewById(R.id.lc_fi_bo);
            imageView = (ImageView) itemView.findViewById(R.id.lc_fi_image);
            jiazai = (ImageView) itemView.findViewById(R.id.lc_fi_jiazai);
            videoview_top = (VideoView) itemView.findViewById(R.id.lc_videoview);
            linview = (LinearLayout) itemView.findViewById(R.id.lc_fi_view);
        }
    }
}

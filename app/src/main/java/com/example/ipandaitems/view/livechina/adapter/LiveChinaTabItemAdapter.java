package com.example.ipandaitems.view.livechina.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ipandaitems.R;
import com.example.ipandaitems.model.entry.livechina.livechinavideobean;
import com.example.ipandaitems.utils.ComonUtils;
import com.example.ipandaitems.view.livechina.Ilivechinaview;
import com.example.ipandaitems.view.livechina.entity.LiveChinaAllTablist;
import com.example.ipandaitems.view.livechina.entity.LiveChinaBean;
import com.example.ipandaitems.view.livechina.entity.LiveChineItem;
import com.gridsum.mobiledissector.MobileAppTracker;

import java.util.List;

import io.vov.vitamio.widget.VideoView;


public class LiveChinaTabItemAdapter extends SimpleBaseAdapter<LiveChineItem> implements Ilivechinaview {

    private final String mtitle;
    private LayoutInflater mInflater;
    private static final String DETAIL_VISIBLE = "visible";
    private static final String DETAIL_GONE = "gone";

    private int[] display;
    public int adjusted_h;


    private int playPositon = -1;


    private boolean isLive = true;

    private boolean isScale = true;

    private RelativeLayout currentPlayLayoutParent;
    private RelativeLayout scrollView;
    //点击事件回调
    private LiveChinaItemClickImple liveChinaItemClickImple;
    private List<LiveChineItem> list;
    private boolean isFirstLoad = true;
    private Context mContext;

    public LiveChinaTabItemAdapter(Context context, List<LiveChineItem> list, String titile) {
        super(context, list);
        this.list = list;
        mtitle = titile;
        this.mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        display = ComonUtils.getDisplay((Activity) context);
        //小窗口的比例
        float ratio = (float) 16 / 9;
        adjusted_h = (int) Math.ceil((float) display[0] / ratio);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_live_china_tab, parent, false);
            holder = new ViewHolder();

            holder.titleTextView = (TextView) convertView.findViewById(R.id.live_china_tab_item_title);
            holder.videoImg = (ImageView) convertView.findViewById(R.id.live_china_tab_item_img);
            holder.btnPlay = (ImageView) convertView.findViewById(R.id.btn_play);

            holder.jianjiebtn = (RelativeLayout) convertView.findViewById(R.id.live_china_tab_item_jianjie_btn);
            holder.jianjieDetail = (TextView) convertView.findViewById(R.id.live_china_tab_item_jianjie_detail);
            holder.jianImg = (TextView) convertView.findViewById(R.id.live_china_tab_item_jianjie_img);
            holder.live_play_layout = (RelativeLayout) convertView.findViewById(R.id.play_layout);
            holder.play_content_view = (LinearLayout) convertView.findViewById(R.id.play_content_view);
            holder.livechina_jianjie_underline = convertView.findViewById(R.id.livechina_jianjie_underline);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        LayoutParams imgLayoutParams = holder.videoImg.getLayoutParams();
        imgLayoutParams.height = adjusted_h;
        holder.videoImg.setLayoutParams(imgLayoutParams);

        holder.jianjiebtn.setTag(DETAIL_GONE);
        holder.jianjieDetail.setVisibility(View.GONE);
        holder.livechina_jianjie_underline.setVisibility(View.GONE);
        holder.jianImg.setBackgroundResource(R.drawable.live_china_detail_down);
        holder.jianjieDetail.setText(list.get(position).brief);


        holder.jianjiebtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String tag = (String) v.getTag();
                //统计
                MobileAppTracker.trackEvent("直播中国" + list.get(position).title + "图文", "", mtitle.toString(), 0, list.get(position).id, "图文浏览", context);
                MobileAppTracker.setPolicy(MobileAppTracker.POLICY_INTIME);
                Log.e("统计", "事件名称:" + list.get(position).title + "***事件类别:" + "列表" + "***事件标签:" + "直播中国*" + mtitle.toString() + "***类型:" + "图文浏览" + "***ID" + list.get(position).id);

                View detailView = v.findViewById(R.id.live_china_tab_item_jianjie_detail);
                View detailImg = v.findViewById(R.id.live_china_tab_item_jianjie_img);
                View detailunderline = v.findViewById(R.id.livechina_jianjie_underline);
                if (tag.equals(DETAIL_VISIBLE)) {
                    detailView.setVisibility(View.GONE);
                    detailunderline.setVisibility(View.GONE);
                    v.setTag(DETAIL_GONE);
                    detailImg.setBackgroundResource(R.drawable.live_china_detail_down);
                } else if (tag.equals(DETAIL_GONE)) {
                    detailView.setVisibility(View.VISIBLE);
                    detailunderline.setVisibility(View.VISIBLE);
                    v.setTag(DETAIL_VISIBLE);
                    detailImg.setBackgroundResource(R.drawable.live_china_detail_up);
                }
            }
        });


        if (position == 0 && isFirstLoad) {
            if (list.get(position).id == null) {
                Toast.makeText(context, "没有视频", Toast.LENGTH_SHORT).show();
            } else {

                liveChinaItemClickImple.doFirstLoad(list.get(position), holder.live_play_layout);
                isFirstLoad = false;
                //统计
                MobileAppTracker.trackEvent("直播中国" + list.get(position).title + "视频", "", mtitle.toString(), 0, list.get(position).id, "视频观看", context);
                MobileAppTracker.setPolicy(MobileAppTracker.POLICY_INTIME);
                Log.e("统计", "事件名称:" + list.get(position).title + "***事件类别:" + "列表" + "***事件标签:" + "直播中国*" + mtitle.toString() + "***类型:" + "视频播放" + "***ID" + list.get(position).id);
            }

        }

        holder.btnPlay.setTag(holder.live_play_layout);
        holder.btnPlay.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                //统计
                MobileAppTracker.trackEvent("直播中国" + list.get(position).title + "视频", "", mtitle.toString(), 0, list.get(position).id, "视频观看", context);
                MobileAppTracker.setPolicy(MobileAppTracker.POLICY_INTIME);
                Log.e("统计", "事件名称:" + list.get(position).title + "***事件类别:" + "列表" + "***事件标签:" + "直播中国*" + mtitle.toString() + "***类型:" + "视频播放" + "***ID" + list.get(position).id);
                playPositon = position + 1;
                RelativeLayout tag = (RelativeLayout) v.getTag();
                currentPlayLayoutParent = tag;
                Log.e("TAG", list.get(position).title + mtitle.toString() + list.get(position).id);
                if (list.get(position).id == null) {
                    Toast.makeText(context, "没有视频", Toast.LENGTH_SHORT).show();
                    return;
                }

                    liveChinaItemClickImple.initViewLocation(list.get(position), tag);

                }

        });

        holder.titleTextView.setText(list.get(position).title);
        holder.videoImg.setScaleType(ScaleType.FIT_XY);

        Glide.with(mContext).load(list.get(position).image).asBitmap().placeholder(R.drawable._no_img).error(R.drawable._no_img).into(holder.videoImg);
        return convertView;
    }

    class ViewHolder {
        TextView titleTextView, jianjieDetail, jianImg;
        RelativeLayout jianjiebtn;
        ImageView videoImg;
        ImageView btnPlay;
        RelativeLayout live_play_layout;
        LinearLayout play_content_view;
        View livechina_jianjie_underline;
         VideoView  videoView;
    }

    public int getPlayPostion() {
        return playPositon;
    }

    public RelativeLayout getcurrentPlayParentLayout() {
        return currentPlayLayoutParent;
    }

    public void setcurrentPlayLayoutParent(RelativeLayout currentPlayLayoutParent) {
        this.currentPlayLayoutParent = currentPlayLayoutParent;
    }

    public void setplayPositon(int playPositon) {
        this.playPositon = playPositon;
    }

    @Override
    public void succeed(LiveChinaAllTablist livechinaBean) {

    }

    @Override
    public void succeedcontent(LiveChinaBean livechinacontentbean) {

    }

    @Override
    public void succeedvideo(livechinavideobean livechinavideobean) {

    }

    @Override
    public void Failure() {

    }


    public interface LiveChinaItemClickImple {
        void initViewLocation(LiveChineItem liveChineItem, RelativeLayout relativeLayout);

        void doFirstLoad(LiveChineItem liveChineItem, RelativeLayout live_play_layout);
    }


    public void setLiveChinaItemClickImple(LiveChinaItemClickImple liveChinaItemClickImple) {
        this.liveChinaItemClickImple = liveChinaItemClickImple;
    }

}

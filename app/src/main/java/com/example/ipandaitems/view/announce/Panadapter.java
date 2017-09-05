package com.example.ipandaitems.view.announce;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ipandaitems.R;
import com.example.ipandaitems.model.entry.PanadaBean;

import java.util.List;

/**
 * Created by 1 on 2017/8/31.
 */

class Panadapter extends RecyclerView.Adapter<Panadapter.ViewHolder>{

    private PanadaTop panadaTop;
    private List<PanadaBean.VideoBean> video;
    private ViewHolder viewHolder;

    public Panadapter(PanadaTop panadaTop, List<PanadaBean.VideoBean> video) {
        this.panadaTop=panadaTop;
        this.video=video;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==1){
            View view = View.inflate(panadaTop, R.layout.topitem, null);
            viewHolder = new ViewHolder(view);
        }else {
            View view = View.inflate(panadaTop, R.layout.annbutton, null);
            viewHolder=new ViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return 1;
        }else {
            return 2;
        }

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String img = video.get(position).getImg();
        if (holder.image!=null){
            Glide.with(panadaTop).load(img).into(holder.image);
        }
        if (holder.listname!=null){
            holder.listname.setVisibility(View.GONE);
        }
        if (holder.listContent!=null){
            holder.listContent.setText(video.get(position).getT());
           holder.listContent.setMaxLines(2);
            holder.listContent.setEllipsize(TextUtils.TruncateAt.END);
        }
        if (holder.listtitle!=null){
            holder.listtitle.setText("高清直播");
        }
        if (holder.video_Length!=null){
            holder.video_Length.setText(video.get(position).getLen());
        }
    }

    @Override
    public int getItemCount() {
        return video.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView listtitle, listContent,listname,video_Length;
        ImageView image;
        public ViewHolder(View itemView) {
            super(itemView);
            listtitle=itemView.findViewById(R.id.listtitle);
            listContent=itemView.findViewById(R.id.list_Content);
            listname=itemView.findViewById(R.id.list_Name);
            image=itemView.findViewById(R.id.list_Image);
            video_Length=itemView.findViewById(R.id.video_Length);
        }
    }
}

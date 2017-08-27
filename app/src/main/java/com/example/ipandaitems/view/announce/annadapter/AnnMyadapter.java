package com.example.ipandaitems.view.announce.annadapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ipandaitems.R;
import com.example.ipandaitems.model.entry.AnnBean;

import java.util.List;

/**
 * Created by 1 on 2017/8/24.
 */

public class AnnMyadapter extends RecyclerView.Adapter<AnnMyadapter.ViewHolder>{
    private FragmentActivity activity;
    private List<AnnBean.ListBean> list;

    public AnnMyadapter(FragmentActivity activity, List<AnnBean.ListBean> list) {
        this.list=list;
        this.activity=activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(activity, R.layout.annbutton, null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (holder.image!=null){
            Glide.with(activity).load(list.get(position).getImage()).into(holder.image);
        }
        if (holder.text!=null){
            holder.text.setText(list.get(position).getTitle());
        }
        if (holder.text1!=null){
            holder.text1.setText(list.get(position).getBrief());
        }
        if (holder.video_length!=null){
            holder.video_length.setText(list.get(position).getVideoLength());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text,text1,video_length;
        public ViewHolder(View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.list_Image);
            text=itemView.findViewById(R.id.list_Name);
            text1=itemView.findViewById(R.id.list_Content);
            video_length=itemView.findViewById(R.id.video_Length);
        }
    }

}

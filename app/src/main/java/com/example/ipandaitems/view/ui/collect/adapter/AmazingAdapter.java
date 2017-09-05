package com.example.ipandaitems.view.ui.collect.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ipandaitems.R;
import com.example.ipandaitems.model.greendao.User;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by 张豫耀 on 2017/9/4.
 */

public class AmazingAdapter extends RecyclerView.Adapter<AmazingAdapter.ViewHolder> {
    private FragmentActivity activity;
    private List<User> list;

    public AmazingAdapter(FragmentActivity activity, List<User> list) {
        this.list = list;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(activity, R.layout.annbutton, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (holder.image != null) {
            Glide.with(activity).load(list.get(position).getImg()).into(holder.image);
        }
        if (holder.text != null) {
            holder.text.setText(list.get(position).getName());
        }
        if (holder.text1 != null) {
            SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String date = sDateFormat.format(new java.util.Date());
            holder.text1.setText(date);
        }
        if (holder.video_length != null) {
            holder.video_length.setText(list.get(position).getTime());
        }
        if (list.get(position).getTime()== null) {
            holder.video_length.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text, text1, video_length;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.list_Image);
            text = (TextView) itemView.findViewById(R.id.list_Name);
            text1 = (TextView) itemView.findViewById(R.id.list_Content);
            video_length = (TextView) itemView.findViewById(R.id.video_Length);
        }
    }

}

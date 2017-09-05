package com.example.ipandaitems.view.ui.collect.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ipandaitems.R;
import com.example.ipandaitems.model.greendao.Data;

import java.util.List;

/**
 * Created by 张豫耀 on 2017/9/4.
 */

public class SoLiveAdapter extends RecyclerView.Adapter<SoLiveAdapter.ViewHolder> {
    private Context context;
    private List<Data> list;

    public SoLiveAdapter(Context context, List<Data> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.toplist, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder.image != null) {
            Glide.with(context).load(list.get(position).getImg()).into(holder.image);
        }
        if (holder.content != null) {

        }
        if (holder.title != null) {
            holder.title.setText(list.get(position).getName());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title, content;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.toplistImg);
            title = itemView.findViewById(R.id.toplistTitle);
            content = itemView.findViewById(R.id.toplistContent);
        }
    }
}

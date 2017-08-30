package com.example.ipandaitems.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ipandaitems.R;
import com.example.ipandaitems.model.entry.originalbean;

import java.util.List;

/**
 * Created by 张豫耀 on 2017/8/29.
 */

class OriginalAdapter extends RecyclerView.Adapter<OriginalAdapter.ViewHolder> {
    List<originalbean.InteractiveBean> list;
    private Context context;
    private OnItemCLick onItemCLick;

    public OriginalAdapter(Context context, List<originalbean.InteractiveBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.original_item, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tv.setText(list.get(position).getTitle());
        Glide.with(context).load(list.get(position).getImage()).into(holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemCLick.OnClick(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.origlinal_tv);
            img = itemView.findViewById(R.id.origlinal_img);
        }
    }

    public void SetOnItemCLick(OnItemCLick onItemCLick) {
        this.onItemCLick = onItemCLick;
    }

    public interface OnItemCLick {
        void OnClick(View v, int position);
    }
}

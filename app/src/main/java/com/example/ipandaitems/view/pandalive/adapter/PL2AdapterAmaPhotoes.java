package com.example.ipandaitems.view.pandalive.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ipandaitems.R;
import com.example.ipandaitems.model.entry.pandalive.PLAmaPhotoes;

import java.util.List;

/**
 * @name yanantian
 * @motto 莫羡他人谢语花, 腹有诗书气自华
 * @E-mail 1173568715@qq.com
 * @WX 15978622391
 */

public class PL2AdapterAmaPhotoes extends RecyclerView.Adapter {
    private List<PLAmaPhotoes.VideoBean> list;
    private Context context;

    public PL2AdapterAmaPhotoes(List<PLAmaPhotoes.VideoBean> list, FragmentActivity activity) {
        this.list = list;
        this.context = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pl_recycler_item, null);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyHolder mh = (MyHolder) holder;
        mh.name.setText(list.get(position).getT());
        mh.time.setText(list.get(position).getPtime());
        Glide.with(context).load(list.get(position).getImg()).into(mh.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

   static class MyHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView img;
        TextView time;

        public MyHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.pl_recycler_item_name);
            img = itemView.findViewById(R.id.pl_recycler_item_img);
            time = itemView.findViewById(R.id.pl_recycler_item_time);
        }
    }


}

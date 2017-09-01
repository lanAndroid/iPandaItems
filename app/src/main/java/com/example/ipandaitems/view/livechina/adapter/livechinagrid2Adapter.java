package com.example.ipandaitems.view.livechina.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ipandaitems.R;

import java.util.List;

/**
 * Created by 张豫耀 on 2017/8/31.
 */

public class livechinagrid2Adapter extends RecyclerView.Adapter<livechinagrid2Adapter.ViewHolder> {
    private Context context;
    private List<String> list;

    public livechinagrid2Adapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.livechina_grid_item2, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvtitle.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvtitle;

        public ViewHolder(View itemView) {
            super(itemView);
            tvtitle = itemView.findViewById(R.id.tv_title2);
        }
    }
}

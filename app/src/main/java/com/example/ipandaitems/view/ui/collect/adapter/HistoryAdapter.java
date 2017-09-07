package com.example.ipandaitems.view.ui.collect.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ipandaitems.R;
import com.example.ipandaitems.model.greendao.history;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by 张豫耀 on 2017/9/4.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private boolean isShow = false;
    private CheckInterface checkInterface;
    private Context activity;

    private List<history> list;


    public void setCheckInterface(CheckInterface checkInterface) {
        this.checkInterface = checkInterface;
    }

    public void isShow(boolean flag) {
        isShow = flag;
        notifyDataSetChanged();
    }

    public interface CheckInterface {
        /**
         * 组选框状态改变触发的事件
         *
         * @param position  元素位置
         * @param isChecked 元素选中与否
         */
        void checkGroup(int position, boolean isChecked);
    }


    public HistoryAdapter(Context activity, List<history> list) {
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
        if (list.get(position).getTime() == null) {
            holder.video_length.setVisibility(View.GONE);
        }
        final history history = list.get(position);
        boolean choosed = history.isChoosed();
        if (choosed) {
            holder.checkBox.setChecked(true);
        } else {
            holder.checkBox.setChecked(false);
        }
        if (isShow) {
            holder.checkBox.setVisibility(View.VISIBLE);
        } else {
            holder.checkBox.setVisibility(View.GONE);
        }
        holder.checkBox.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        history.setChoosed(((CheckBox) v).isChecked());
                        checkInterface.checkGroup(position, ((CheckBox) v).isChecked());//向外暴露接口

                    }
                }
        );
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text, text1, video_length;
        CheckBox checkBox;

        public ViewHolder(View itemView) {
            super(itemView);

            checkBox = itemView.findViewById(R.id.checkBox);
            image = (ImageView) itemView.findViewById(R.id.list_Image);
            text = (TextView) itemView.findViewById(R.id.list_Name);
            text1 = (TextView) itemView.findViewById(R.id.list_Content);
            video_length = (TextView) itemView.findViewById(R.id.video_Length);
        }
    }



}

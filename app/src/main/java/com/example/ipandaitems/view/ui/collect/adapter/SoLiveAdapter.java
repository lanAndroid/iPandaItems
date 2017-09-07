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
import com.example.ipandaitems.model.greendao.Data;

import java.util.List;

/**
 * Created by 张豫耀 on 2017/9/4.
 */

public class SoLiveAdapter extends RecyclerView.Adapter<SoLiveAdapter.ViewHolder> {
    private boolean isShow = false;
    private CheckInterface checkInterface;
    private Context context;
    private List<Data> list;

    public SoLiveAdapter(Context context, List<Data> list) {
        this.context = context;
        this.list = list;
    }
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

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.toplist, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (holder.image != null) {
            Glide.with(context).load(list.get(position).getImg()).into(holder.image);
        }
        if (holder.content != null) {

        }
        if (holder.title != null) {
            holder.title.setText(list.get(position).getName());
        }
        final Data history = list.get(position);
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

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title, content;
        CheckBox checkBox;

        public ViewHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);
            image = itemView.findViewById(R.id.toplistImg);
            title = itemView.findViewById(R.id.toplistTitle);
            content = itemView.findViewById(R.id.toplistContent);
        }
    }
}

package com.example.ipandaitems.view.video.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ipandaitems.R;
import com.example.ipandaitems.model.entry.TopListBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 1 on 2017/8/29.
 */

public class TopListAdapter extends RecyclerView.Adapter<TopListAdapter.ViewHolder>{
private FragmentActivity activity;
private List<TopListBean.ListBean> list;
public TopListAdapter(FragmentActivity activity,List<TopListBean.ListBean>list){
        this.activity=activity;
        this.list=list;
        }

@Override
public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view=View.inflate(activity,R.layout.toplist,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
        }

@Override
public void onBindViewHolder(ViewHolder holder,int position){
        if(holder.image!=null){
        Glide.with(activity).load(list.get(position).getPicurl()).into(holder.image);
        }
        if(holder.content!=null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            String format = sdf.format(list.get(position).getFocus_date());
            holder.content.setText(format);
        }
        if(holder.title!=null){
        holder.title.setText(list.get(position).getTitle());
        }
        }

@Override
public int getItemCount(){
        return list.size();
        }


public class ViewHolder extends RecyclerView.ViewHolder {
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

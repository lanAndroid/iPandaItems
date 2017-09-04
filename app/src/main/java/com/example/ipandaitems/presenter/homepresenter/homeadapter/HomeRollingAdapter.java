package com.example.ipandaitems.presenter.homepresenter.homeadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ipandaitems.R;
import com.example.ipandaitems.model.entry.home.HomeRollingBean;

import java.util.List;

/**
 * Created by xiaogang on 2017/8/25.
 */

public class HomeRollingAdapter extends RecyclerView.Adapter{
    Context mContext;
    List<HomeRollingBean.ListBean> listBeanList;
    OnClicks onClicks;

    public HomeRollingAdapter(Context mContext, List<HomeRollingBean.ListBean> listBeanList) {
        this.mContext = mContext;
        this.listBeanList = listBeanList;
    }

    public interface OnClicks{
        void SetOnItemClick(viewholder view, int position);
    }
    public void SetClicks(OnClicks onClicks){
       this.onClicks=onClicks;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.home_list_rolling_item,parent,false);


        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final viewholder vi= (viewholder) holder;
        Glide.with(mContext).load(listBeanList.get(position).getImage()).into(vi.imageView);
        vi.timetv.setText(listBeanList.get(position).getDaytime());
        vi.titletv.setText(listBeanList.get(position).getTitle());
        vi.secondtv.setText(listBeanList.get(position).getVideoLength());
        vi.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = vi.getAdapterPosition();
                if(onClicks!=null){}
                onClicks.SetOnItemClick(vi,adapterPosition);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listBeanList.size();
    }

    class  viewholder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView secondtv;
        TextView titletv;
        TextView timetv;


        public viewholder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.home_list_roling_iv);
            secondtv= (TextView) itemView.findViewById(R.id.home_list_roling_secondtv);
            titletv= (TextView) itemView.findViewById(R.id.home_list_roling_titletv);
            timetv= (TextView) itemView.findViewById(R.id.home_list_roling_timetv);
        }
    }
}

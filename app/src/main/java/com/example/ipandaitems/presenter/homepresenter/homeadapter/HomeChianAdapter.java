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
import com.example.ipandaitems.model.entry.home.HomeBean;

import java.util.List;

/**
 * Created by xiaogang on 2017/8/25.
 */

public class HomeChianAdapter extends RecyclerView.Adapter{
    List<HomeBean.DataBean.ChinaliveBean.ListBeanX> chinaliveBeen;
    Context mContext;
    OnClicks onClicks;

    public HomeChianAdapter(List<HomeBean.DataBean.ChinaliveBean.ListBeanX> chinaliveBeen, Context mContext) {
        this.chinaliveBeen = chinaliveBeen;
        this.mContext = mContext;
    }
        public  interface  OnClicks{
            void OnItemClick(viewholder view, int position);
        }
        public void SetOnItemClick(OnClicks onClicks){
            this.onClicks=onClicks;
        }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.home_grid_lifechian_item,parent,false);

        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            final viewholder vi= (viewholder) holder;
          Glide.with(mContext).load(chinaliveBeen.get(position).getImage()).into(vi.imageView);
          vi.titletv.setText(chinaliveBeen.get(position).getTitle());
            vi.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int adapterPosition = vi.getAdapterPosition();
                   onClicks.OnItemClick(vi,adapterPosition);
                }
            });
    }

    @Override
    public int getItemCount() {
        return chinaliveBeen.size();
    }
    class viewholder extends RecyclerView.ViewHolder{
            ImageView imageView;
        TextView titletv;
        public viewholder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.home_grid_lifechian_iv);
            titletv= (TextView) itemView.findViewById(R.id.home_grid_lifechian_titletv);
        }
    }

}

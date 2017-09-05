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
import com.example.ipandaitems.model.entry.home.HomeMarvellBean;

import java.util.List;

/**
 * Created by xiaogang on 2017/8/25.
 */

public class MarvellousAdapter extends RecyclerView.Adapter{
    private Context mContext;
    List<HomeMarvellBean.ListBean> listBeanList;
    onClicks clicks;



    public MarvellousAdapter(Context mContext, List<HomeMarvellBean.ListBean> listBeanList) {
        this.mContext = mContext;
        this.listBeanList = listBeanList;
    }
    //回调监听
   public interface  onClicks{
        void  onItemClick(viewholder view, int position);
    }
    public void SetOnClicks(onClicks clicks) {
        this.clicks = clicks;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.home_grid_marvellous_item,parent,false);



        return new viewholder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final viewholder vi= (viewholder) holder;



        Glide.with(mContext).load(listBeanList.get(position).getImage()).into(vi.iv);
        vi.tiemtv.setText(listBeanList.get(position).getDaytime());
        vi.secondtv.setText(listBeanList.get(position).getVideoLength());
        vi.title.setText(listBeanList.get(position).getTitle());
        vi.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(clicks!=null){
                    int adapterPosition = vi.getAdapterPosition();
                    clicks.onItemClick(vi,adapterPosition);

                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return listBeanList.size();
    }
    class  viewholder extends RecyclerView.ViewHolder{
      private   ImageView iv;
        private  TextView tiemtv;
        private TextView secondtv;
        private  TextView title;


        public viewholder(View itemView) {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.home_grid_marvellous_title);
            iv= (ImageView) itemView.findViewById(R.id.home_grid_marvellous_iv);
            tiemtv= (TextView) itemView.findViewById(R.id.home_grid_marvellous_time);
            secondtv= (TextView) itemView.findViewById(R.id.home_grid_marvellous_second);
        }
    }
}

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

public class LivebroadAdapter extends RecyclerView.Adapter{
       private Context mContext;
    List<HomeBean.DataBean.PandaliveBean.ListBean> pandaliveBeen;

    public LivebroadAdapter(Context context, List<HomeBean.DataBean.PandaliveBean.ListBean> pandaliveBeen) {
        this.mContext = context;
        this.pandaliveBeen = pandaliveBeen;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.home_grid_livebroad_item,parent,false);


        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        viewholder vi= (viewholder) holder;
        vi.textView.setText(pandaliveBeen.get(position).getTitle());
        Glide.with(mContext).load(pandaliveBeen.get(position).getImage()).into(vi.imageView);

    }

    @Override
    public int getItemCount() {
        return pandaliveBeen.size();
    }
    class viewholder extends RecyclerView.ViewHolder{
            ImageView imageView;
            TextView textView;
        public viewholder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.home_grid_livebroad_iv);
            textView= (TextView) itemView.findViewById(R.id.home_grid_livebroad_tv);

        }
    }
}

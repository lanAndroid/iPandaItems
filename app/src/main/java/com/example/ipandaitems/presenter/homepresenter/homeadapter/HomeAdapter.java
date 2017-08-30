package com.example.ipandaitems.presenter.homepresenter.homeadapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ipandaitems.R;
import com.example.ipandaitems.model.entry.home.HomeBean;
import com.example.ipandaitems.model.entry.home.HomeMarvellBean;
import com.example.ipandaitems.model.entry.home.HomeRollingBean;

import java.util.List;

/**
 * Created by xiaogang on 2017/8/25.
 */

public class HomeAdapter extends RecyclerView.Adapter{
    Context mContext;
    List<HomeBean.DataBean.ChinaliveBean.ListBeanX> chinaliveBeen;
    List<HomeRollingBean.ListBean> rollinglist;
    List<HomeBean.DataBean.PandaliveBean.ListBean> pandaliveBeen;
    List<HomeMarvellBean.ListBean> MarvellList;


    public HomeAdapter(Context context, List<HomeBean.DataBean.ChinaliveBean.ListBeanX> chinaliveBeen,
                       List<HomeRollingBean.ListBean> rollinglist,
                       List<HomeBean.DataBean.PandaliveBean.ListBean> pandaliveBeen,
                       List<HomeMarvellBean.ListBean> marvellList) {
        this.mContext = context;
        this.chinaliveBeen = chinaliveBeen;
        this.rollinglist = rollinglist;
        this.pandaliveBeen = pandaliveBeen;
        MarvellList = marvellList;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return 0;
        }else if(position==1){
            return 1;
        }else if(position==2){
            return 2;
        }else {
            return 3;
        }

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;


        if(viewType==0)
        { view = LayoutInflater.from(mContext).inflate(R.layout.home_gridview_item, parent, false);
            return new Livebroadview(view);}
        else  if(viewType==1)
        {view = LayoutInflater.from(mContext).inflate(R.layout.home_gridviewtwo_item, parent, false);
            return new Marvellousview(view);}
        else  if(viewType==2)
        {view = LayoutInflater.from(mContext).inflate(R.layout.home_list_roliing, parent, false);
            return new Rollingview(view);}
        else if(viewType==3)
        { view = LayoutInflater.from(mContext).inflate(R.layout.home_grid_lifechina, parent, false);
            return new LifeChianview(view);}







            return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
       if(holder instanceof Livebroadview){
           Livebroadview livebroadview= (Livebroadview) holder;
           GridLayoutManager manager=new GridLayoutManager(mContext,3,GridLayoutManager.VERTICAL,false);

           livebroadview.Live_rv.setNestedScrollingEnabled(false);

           livebroadview.Live_rv.setLayoutManager(manager);
           LivebroadAdapter livebroadAdapter=new LivebroadAdapter(mContext,pandaliveBeen);
           livebroadview.Live_rv.setAdapter(livebroadAdapter);



       }
       else
         if(holder instanceof Marvellousview){
           Marvellousview marvellousview= (Marvellousview) holder;
           GridLayoutManager manager=new GridLayoutManager(mContext,2,GridLayoutManager.VERTICAL,false);

             marvellousview.Marve_rv.setNestedScrollingEnabled(false);

           marvellousview.Marve_rv.setLayoutManager(manager);

           MarvellousAdapter marvellousAdapter=new MarvellousAdapter(mContext,MarvellList);
           marvellousview.Marve_rv.setAdapter(marvellousAdapter);
       }
        if(holder instanceof Rollingview){
           Rollingview rollingview= (Rollingview) holder;
           RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);

            rollingview.rollingrv.setNestedScrollingEnabled(false);

           rollingview.rollingrv.setLayoutManager(layoutManager);
            HomeRollingAdapter rollingAdapter=new HomeRollingAdapter(mContext,rollinglist);
           rollingview.rollingrv.setAdapter(rollingAdapter);
       }
         if(holder instanceof LifeChianview){
           LifeChianview lifeChianview= (LifeChianview) holder;

           GridLayoutManager manager=new GridLayoutManager(mContext,3,GridLayoutManager.VERTICAL,false);

             lifeChianview.chian_rv.setNestedScrollingEnabled(false);
           lifeChianview.chian_rv.setLayoutManager(manager);
           HomeChianAdapter chianAdapter=new HomeChianAdapter(chinaliveBeen,mContext);
           lifeChianview.chian_rv.setAdapter(chianAdapter);
       }

    }

    @Override
    public int getItemCount() {

        return 4;
    }


    class Livebroadview extends RecyclerView.ViewHolder{
        RecyclerView Live_rv;

         public Livebroadview(View itemView) {
            super(itemView);
             Live_rv= (RecyclerView) itemView.findViewById(R.id.home_grid_livebroad_rv);
        }
    }

    class Marvellousview extends RecyclerView.ViewHolder{
        RecyclerView Marve_rv;
        public Marvellousview(View itemView) {
            super(itemView);
            Marve_rv= (RecyclerView) itemView.findViewById(R.id.home_grid_Marvellous_rv);
        }
    }

    class Rollingview extends RecyclerView.ViewHolder{
        RecyclerView rollingrv;
        public Rollingview(View itemView) {
            super(itemView);
            rollingrv= (RecyclerView) itemView.findViewById(R.id.home_list_roling_rv);
        }
    }

    class LifeChianview extends RecyclerView.ViewHolder{
        RecyclerView chian_rv;
        public LifeChianview(View itemView) {
            super(itemView);
            chian_rv= (RecyclerView) itemView.findViewById(R.id.home_lifechina_rv);

        }
    }
}

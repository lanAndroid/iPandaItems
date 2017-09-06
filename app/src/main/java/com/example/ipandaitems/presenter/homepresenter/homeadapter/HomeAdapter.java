package com.example.ipandaitems.presenter.homepresenter.homeadapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ipandaitems.R;
import com.example.ipandaitems.model.entry.home.HomeBean;
import com.example.ipandaitems.model.entry.home.HomeChianVideo;
import com.example.ipandaitems.model.entry.home.HomeMarvellBean;
import com.example.ipandaitems.model.entry.home.HomePandanVideo;
import com.example.ipandaitems.model.entry.home.HomeRollVideo;
import com.example.ipandaitems.model.entry.home.HomeRollingBean;
import com.example.ipandaitems.presenter.homepresenter.HomePresenterImpl;
import com.example.ipandaitems.view.home.HomeVoid;
import com.example.ipandaitems.view.home.IHomeFragment;

import java.util.List;

/**
 * Created by xiaogang on 2017/8/25.
 */

public class HomeAdapter extends RecyclerView.Adapter implements IHomeFragment{
    Context mContext;
    List<HomeBean.DataBean.ChinaliveBean.ListBeanX> chinaliveBeen;
    List<HomeRollingBean.ListBean> rollinglist;
    List<HomeBean.DataBean.PandaliveBean.ListBean> pandaliveBeen;
    List<HomeMarvellBean.ListBean> MarvellList;

  public static   String Roll_url="http://115.182.35.91/api/getVideoInfoForCBox.do?pid=";
    private String roll_vid;
    String  marvell_vid;
    private String Zhibo_url;
    private HomePresenterImpl homePresenter;
    private String roll_url_video;
    private HomeRollingAdapter rollingAdapter;
    private String marvell_url_video;
    private String pandan_flv2_url;
    private String chian_flv2_url;
    String chian_id;
    private String china_url;
    private String homePanda_url;


    public HomeAdapter(Context context, List<HomeBean.DataBean.ChinaliveBean.ListBeanX> chinaliveBeen,
                       List<HomeRollingBean.ListBean> rollinglist,
                       List<HomeBean.DataBean.PandaliveBean.ListBean> pandaliveBeen,
                       List<HomeMarvellBean.ListBean> marvellList) {
        this.mContext = context;
        this.chinaliveBeen = chinaliveBeen;
        this.rollinglist = rollinglist;
        this.pandaliveBeen = pandaliveBeen;
        MarvellList = marvellList;


        homePresenter = new HomePresenterImpl(this);




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
           //直播秀场的item
           //直播秀场视频地址
//           public static final String ZHIBO1 = "http://vdn.live.cntv.cn/api2/live.do?channel=pa://cctv_p2p_hdipanda&client=androidapp";
           Livebroadview livebroadview= (Livebroadview) holder;
           final GridLayoutManager manager=new GridLayoutManager(mContext,3,GridLayoutManager.VERTICAL,false);

           livebroadview.Live_rv.setNestedScrollingEnabled(false);

           livebroadview.Live_rv.setLayoutManager(manager);
           LivebroadAdapter livebroadAdapter=new LivebroadAdapter(mContext,pandaliveBeen);
           livebroadview.Live_rv.setAdapter(livebroadAdapter);
           final HomePresenterImpl    homePresenter111 = new HomePresenterImpl(this);

           livebroadAdapter.SetOnItemClick(new LivebroadAdapter.OnClicks() {
               @Override
               public void OnItemClicks(LivebroadAdapter.viewholder view, int position) {
                   Toast.makeText(mContext, "视频地址"+pandaliveBeen.get(position).getVid(), Toast.LENGTH_SHORT).show();
                   String HomePanda_id = pandaliveBeen.get(position).getId();
                   homePanda_url = "http://vdn.live.cntv.cn/api2/live.do?channel=pa://cctv_p2p_hd"+HomePanda_id+"&client=androidapp";
                   homePresenter111.setHomePandanVideo(homePanda_url);


               }
           });



       }
       else

           //精彩一刻的item
         if(holder instanceof Marvellousview){
           Marvellousview marvellousview= (Marvellousview) holder;
           GridLayoutManager manager=new GridLayoutManager(mContext,2,GridLayoutManager.VERTICAL,false);

             marvellousview.Marve_rv.setNestedScrollingEnabled(false);

           marvellousview.Marve_rv.setLayoutManager(manager);

           MarvellousAdapter marvellousAdapter=new MarvellousAdapter(mContext,MarvellList);
           marvellousview.Marve_rv.setAdapter(marvellousAdapter);

             final HomePresenterImpl    homePresenter2 = new HomePresenterImpl(this);


             marvellousAdapter.SetOnClicks(new MarvellousAdapter.onClicks() {
                 @Override
                 public void onItemClick(MarvellousAdapter.viewholder view, int position) {
                     Toast.makeText(mContext, "敬请期待"+MarvellList.get(position).getTitle(), Toast.LENGTH_SHORT).show();

                   marvell_vid =MarvellList.get(position).getPid();

                     homePresenter2.setHomeRollVideo(Roll_url+marvell_vid);

                     Log.e("----------视频的Josn地址", Roll_url+marvell_vid);
                     Log.e("----------地址",marvell_vid);

                 }
             });

       }
        if(holder instanceof Rollingview){


            //滚滚视频item
            //精彩一刻视频地址
            Rollingview rollingview= (Rollingview) holder;
           RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
            rollingview.rollingrv.setNestedScrollingEnabled(false);
            rollingview.rollingrv.setLayoutManager(layoutManager);
            rollingAdapter = new HomeRollingAdapter(mContext,rollinglist);
           rollingview.rollingrv.setAdapter(rollingAdapter);


            final HomePresenterImpl    homePresenter1 = new HomePresenterImpl(this);
            rollingAdapter.SetClicks(new HomeRollingAdapter.OnClicks() {
                @Override
                public void SetOnItemClick(HomeRollingAdapter.viewholder view, int position) {
                    Toast.makeText(mContext, "这是"+"地址", Toast.LENGTH_SHORT).show();

                    roll_vid =rollinglist.get(position).getPid();

                    homePresenter1.setHomeRollVideo(Roll_url+roll_vid);

                    Log.e("----------视频的Josn地址", Roll_url+roll_vid);
                    Log.e("----------地址",roll_vid);



                }
            });

       }
         if(holder instanceof LifeChianview){
             //直播中国item
           LifeChianview lifeChianview= (LifeChianview) holder;

           final GridLayoutManager manager=new GridLayoutManager(mContext,3,GridLayoutManager.VERTICAL,false);

             lifeChianview.chian_rv.setNestedScrollingEnabled(false);
           lifeChianview.chian_rv.setLayoutManager(manager);
           HomeChianAdapter chianAdapter=new HomeChianAdapter(chinaliveBeen,mContext);
           lifeChianview.chian_rv.setAdapter(chianAdapter);
             final HomePresenterImpl    homePresenter11 = new HomePresenterImpl(this);
             chianAdapter.SetOnItemClick(new HomeChianAdapter.OnClicks() {
                 @Override
                 public void OnItemClick(HomeChianAdapter.viewholder view, int position) {
                     Toast.makeText(mContext, "跳啊", Toast.LENGTH_SHORT).show();
                   chian_id = chinaliveBeen.get(position).getId();
                     String HomeChian_url="http://vdn.live.cntv.cn/api2/live.do?channel=pa://cctv_p2p_hd"+chian_id+"&client=androidapp";
                     homePresenter11.setHomePandanVideo(HomeChian_url);

                 }
             });
       }

    }

    @Override
    public int getItemCount() {

        return 4;

    }

    @Override
    public void gethomebean(HomeBean homeBean) {


    }

    @Override
    public void gethomeMarvellbean(HomeMarvellBean homeMarvellBean) {

    }

    @Override
    public void gethomeRollingbean(HomeRollingBean homeRollingBean) {

    }

//获取到熊猫直播的视频地址
    @Override
    public void gethomePandanVideo(HomePandanVideo homePandanVideo) {
        pandan_flv2_url = homePandanVideo.getFlv_url().getFlv2();

        Intent intent=new Intent(mContext, HomeVoid.class);
        Log.e("跳转之前确认下地址",pandan_flv2_url+"控？？");
        intent.putExtra("url",pandan_flv2_url);
        mContext.startActivity(intent);


    }
//首页直播中国的地址
    @Override
    public void gethomeChianVideo(HomeChianVideo homeChianVideo) {

        String chian_flv2_url = homeChianVideo.getFlv_url().getFlv2();


        Intent intent1=new Intent(mContext, HomeVoid.class);
        Log.e("跳转之前确认下地址",chian_flv2_url+"控？？");
        intent1.putExtra("url",chian_flv2_url);
        mContext.startActivity(intent1);
    }


    @Override
    public void gethomeRollingVido(HomeRollVideo homeRollVideo) {

        roll_url_video = homeRollVideo.getVideo().getChapters().get(0).getUrl();

        Log.e("==========",roll_url_video+"空的?");
        Intent intent=new Intent(mContext, HomeVoid.class);
        Log.e("跳转之前确认下地址",roll_url_video+"控？？");
        intent.putExtra("url",roll_url_video);
        mContext.startActivity(intent);
    }

    @Override
    public void gethomemarvellVido(HomeRollVideo homeRollVideo) {
        marvell_url_video = homeRollVideo.getVideo().getChapters().get(0).getUrl();


        Log.e("==========",marvell_url_video+"空的?");
        Intent intent=new Intent(mContext, HomeVoid.class);
        Log.e("跳转之前确认下地址",marvell_url_video+"控？？");
        intent.putExtra("url",marvell_url_video);
        mContext.startActivity(intent);
    }

    @Override
    public void gethomebannerVido(HomeRollVideo homeRollVideo) {

    }

    @Override
    public void gethomebrodcastVido(HomeRollVideo homeRollVideo) {

    }

    @Override
    public void gethomebrodcasttwoVido(HomeRollVideo homeRollVideo) {

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

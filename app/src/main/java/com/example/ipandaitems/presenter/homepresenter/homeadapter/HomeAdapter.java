//package com.example.ipandaitems.presenter.homepresenter.homeadapter;
//
//import android.content.Context;
//import android.content.Intent;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Toast;
//
//import com.example.ipandaitems.R;
//import com.example.ipandaitems.model.entry.home.HomeBean;
//import com.example.ipandaitems.model.entry.home.HomeMarvellBean;
//import com.example.ipandaitems.model.entry.home.HomeRollVideo;
//import com.example.ipandaitems.model.entry.home.HomeRollingBean;
//import com.example.ipandaitems.model.entry.home.HomeZhiBoVideoBean;
//import com.example.ipandaitems.presenter.homepresenter.HomePresenterImpl;
//import com.example.ipandaitems.view.home.HomeVoid;
//import com.example.ipandaitems.view.home.IHomeFragment;
//
//import java.util.List;
//
///**
// * Created by xiaogang on 2017/8/25.
// */
//
//public class HomeAdapter extends RecyclerView.Adapter implements IHomeFragment{
//    Context mContext;
//    List<HomeBean.DataBean.ChinaliveBean.ListBeanX> chinaliveBeen;
//    List<HomeRollingBean.ListBean> rollinglist;
//    List<HomeBean.DataBean.PandaliveBean.ListBean> pandaliveBeen;
//    List<HomeMarvellBean.ListBean> MarvellList;
//
//    String Roll_url="http://115.182.35.91/api/getVideoInfoForCBox.do?pid=";
//    private String roll_vid;
//    private String Zhibo_url;
//    private HomePresenterImpl homePresenter;
//    private String roll_url_video;
//    private HomeRollingAdapter rollingAdapter;
//
//
//    public HomeAdapter(Context context, List<HomeBean.DataBean.ChinaliveBean.ListBeanX> chinaliveBeen,
//                       List<HomeRollingBean.ListBean> rollinglist,
//                       List<HomeBean.DataBean.PandaliveBean.ListBean> pandaliveBeen,
//                       List<HomeMarvellBean.ListBean> marvellList) {
//        this.mContext = context;
//        this.chinaliveBeen = chinaliveBeen;
//        this.rollinglist = rollinglist;
//        this.pandaliveBeen = pandaliveBeen;
//        MarvellList = marvellList;
//
//
//        homePresenter = new HomePresenterImpl(this);
//
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        if(position==0){
//            return 0;
//        }else if(position==1){
//            return 1;
//        }else if(position==2){
//            return 2;
//        }else {
//            return 3;
//        }
//
//    }
//
//
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view ;
//
//
//        if(viewType==0)
//        { view = LayoutInflater.from(mContext).inflate(R.layout.home_gridview_item, parent, false);
//            return new Livebroadview(view);}
//        else  if(viewType==1)
//        {view = LayoutInflater.from(mContext).inflate(R.layout.home_gridviewtwo_item, parent, false);
//            return new Marvellousview(view);}
//        else  if(viewType==2)
//        {view = LayoutInflater.from(mContext).inflate(R.layout.home_list_roliing, parent, false);
//            return new Rollingview(view);}
//        else if(viewType==3)
//        { view = LayoutInflater.from(mContext).inflate(R.layout.home_grid_lifechina, parent, false);
//            return new LifeChianview(view);}
//
//        return null;
//    }
//
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//       if(holder instanceof Livebroadview){
//           //直播秀场的item
//           //直播秀场视频地址
////           public static final String ZHIBO1 = "http://vdn.live.cntv.cn/api2/live.do?channel=pa://cctv_p2p_hdipanda&client=androidapp";
//           Livebroadview livebroadview= (Livebroadview) holder;
//           final GridLayoutManager manager=new GridLayoutManager(mContext,3,GridLayoutManager.VERTICAL,false);
//
//           livebroadview.Live_rv.setNestedScrollingEnabled(false);
//
//           livebroadview.Live_rv.setLayoutManager(manager);
//           LivebroadAdapter livebroadAdapter=new LivebroadAdapter(mContext,pandaliveBeen);
//           livebroadview.Live_rv.setAdapter(livebroadAdapter);
//           livebroadAdapter.SetOnItemClick(new LivebroadAdapter.OnClicks() {
//               @Override
//               public void OnItemClicks(LivebroadAdapter.viewholder view, int position) {
//                   Toast.makeText(mContext, "视频地址"+pandaliveBeen.get(position).getVid(), Toast.LENGTH_SHORT).show();
//               }
//           });
//
//
//
//
//
//
//
//
//           livebroadAdapter.SetOnItemClick(new LivebroadAdapter.OnClicks() {
//               @Override
//               public void OnItemClicks(LivebroadAdapter.viewholder view, int position) {
//                   Toast.makeText(mContext, "视频地址"+pandaliveBeen.get(position).getVid(), Toast.LENGTH_SHORT).show();
//
//
//
//               }
//           });
//
//
//
//
//
//
//
//
//           livebroadAdapter.SetOnItemClick(new LivebroadAdapter.OnClicks() {
//               @Override
//               public void OnItemClicks(LivebroadAdapter.viewholder view, int position) {
//                   Toast.makeText(mContext, "视频地址"+pandaliveBeen.get(position).getVid(), Toast.LENGTH_SHORT).show();
//
//
//
//               }
//           });
//
//
//
//       }
//       else
//
//           //精彩一刻的item
//         if(holder instanceof Marvellousview){
//           Marvellousview marvellousview= (Marvellousview) holder;
//           GridLayoutManager manager=new GridLayoutManager(mContext,2,GridLayoutManager.VERTICAL,false);
//
//             marvellousview.Marve_rv.setNestedScrollingEnabled(false);
//
//           marvellousview.Marve_rv.setLayoutManager(manager);
//
//           MarvellousAdapter marvellousAdapter=new MarvellousAdapter(mContext,MarvellList);
//           marvellousview.Marve_rv.setAdapter(marvellousAdapter);
//             marvellousAdapter.SetOnClicks(new MarvellousAdapter.onClicks() {
//                 @Override
//                 public void onItemClick(MarvellousAdapter.viewholder view, int position) {
//                     Toast.makeText(mContext, "敬请期待"+MarvellList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
//<<<<<<< .merge_file_a11960
//<<<<<<< .merge_file_a07460
//=======
//
//
//
//>>>>>>> .merge_file_a01912
//=======
//
//
//
//>>>>>>> .merge_file_a06516
//                 }
//             });
//
//       }
//        if(holder instanceof Rollingview){
//
//
//            //滚滚视频item
//            //精彩一刻视频地址
//            Rollingview rollingview= (Rollingview) holder;
//           RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
//
//            rollingview.rollingrv.setNestedScrollingEnabled(false);
//
//           rollingview.rollingrv.setLayoutManager(layoutManager);
//            rollingAdapter = new HomeRollingAdapter(mContext,rollinglist);
//           rollingview.rollingrv.setAdapter(rollingAdapter);
//            rollingAdapter.SetClicks(new HomeRollingAdapter.OnClicks() {
//                @Override
//                public void SetOnItemClick(HomeRollingAdapter.viewholder view, int position) {
//                    Toast.makeText(mContext, "这是"+rollinglist.get(position).getPid()+"地址", Toast.LENGTH_SHORT).show();
//
//
//            final HomePresenterImpl    homePresenter1 = new HomePresenterImpl(this);
//            rollingAdapter.SetClicks(new HomeRollingAdapter.OnClicks() {
//                @Override
//                public void SetOnItemClick(HomeRollingAdapter.viewholder view, int position) {
//                    Toast.makeText(mContext, "这是"+"地址", Toast.LENGTH_SHORT).show();
//
//                    roll_vid =rollinglist.get(position).getPid();
//
//                    homePresenter1.setHomeRollVideo(Roll_url+roll_vid);
//
//                    Log.e("----------视频的Josn地址", Roll_url+roll_vid);
//                    Log.e("----------地址",roll_vid);
//
//
//
//                }
//            });
//
//       }
//         if(holder instanceof LifeChianview){
//             //直播中国item
//           LifeChianview lifeChianview= (LifeChianview) holder;
//
//           final GridLayoutManager manager=new GridLayoutManager(mContext,3,GridLayoutManager.VERTICAL,false);
//
//             lifeChianview.chian_rv.setNestedScrollingEnabled(false);
//           lifeChianview.chian_rv.setLayoutManager(manager);
//           HomeChianAdapter chianAdapter=new HomeChianAdapter(chinaliveBeen,mContext);
//           lifeChianview.chian_rv.setAdapter(chianAdapter);
//             chianAdapter.SetOnItemClick(new HomeChianAdapter.OnClicks() {
//                 @Override
//                 public void OnItemClick(HomeChianAdapter.viewholder view, int position) {
//                     Toast.makeText(mContext, "这是"+chinaliveBeen.get(position).getVid()+"地址", Toast.LENGTH_SHORT).show();
//                     Toast.makeText(mContext, "跳啊", Toast.LENGTH_SHORT).show();
//                     Toast.makeText(mContext, "跳啊", Toast.LENGTH_SHORT).show();
//                 }
//             });
//       }
//
//    }
//
//    @Override
//    public int getItemCount() {
//
//        return 4;
//    }
//
//    @Override
//    public void gethomebean(HomeBean homeBean) {
//
//
//    }
//
//    @Override
//    public void gethomeMarvellbean(HomeMarvellBean homeMarvellBean) {
//
//    }
//
//    @Override
//    public void gethomeRollingbean(HomeRollingBean homeRollingBean) {
//
//    }
//
//    @Override
//    public void gethomeViodbean(HomeZhiBoVideoBean homeVideoBean) {
//         Zhibo_url = homeVideoBean.getVideo().getChapters2().get(2).getUrl();
//
//
//    }
//
//    @Override
//    public void gethomeRollingVido(HomeRollVideo homeRollVideo) {
//
//        roll_url_video = homeRollVideo.getVideo().getChapters().get(0).getUrl();
//
//        Log.e("==========",roll_url_video+"空的?");
//        Intent intent=new Intent(mContext, HomeVoid.class);
//        Log.e("跳转之前确认下地址",roll_url_video+"控？？");
//        intent.putExtra("url",roll_url_video);
//        mContext.startActivity(intent);
//    }
//
//
//    class Livebroadview extends RecyclerView.ViewHolder{
//        RecyclerView Live_rv;
//
//         public Livebroadview(View itemView) {
//            super(itemView);
//             Live_rv= (RecyclerView) itemView.findViewById(R.id.home_grid_livebroad_rv);
//        }
//    }
//
//    class Marvellousview extends RecyclerView.ViewHolder{
//        RecyclerView Marve_rv;
//        public Marvellousview(View itemView) {
//            super(itemView);
//            Marve_rv= (RecyclerView) itemView.findViewById(R.id.home_grid_Marvellous_rv);
//        }
//    }
//
//    class Rollingview extends RecyclerView.ViewHolder{
//        RecyclerView rollingrv;
//        public Rollingview(View itemView) {
//            super(itemView);
//            rollingrv= (RecyclerView) itemView.findViewById(R.id.home_list_roling_rv);
//        }
//    }
//
//    class LifeChianview extends RecyclerView.ViewHolder{
//        RecyclerView chian_rv;
//        public LifeChianview(View itemView) {
//            super(itemView);
//            chian_rv= (RecyclerView) itemView.findViewById(R.id.home_lifechina_rv);
//
//        }
//    }
//}

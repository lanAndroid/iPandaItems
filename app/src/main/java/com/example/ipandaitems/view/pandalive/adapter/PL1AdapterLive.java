package com.example.ipandaitems.view.pandalive.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ipandaitems.R;
import com.example.ipandaitems.model.entry.pandalive.PLLive;

import java.util.List;

/**
 * @name yanantian
 * @motto 莫羡他人谢语花, 腹有诗书气自华
 * @E-mail 1173568715@qq.com
 * @WX 15978622391
 */

public class PL1AdapterLive extends BaseAdapter {
    private Context context;
    private List<PLLive.ListBean> list;

    public PL1AdapterLive(FragmentActivity activity, List<PLLive.ListBean> list) {
        this.context = activity;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyHolder mh;
        if (view == null) {
            mh = new MyHolder();
            view = LayoutInflater.from(context).inflate(R.layout.pl_live_multi_item, null);
            mh.img = view.findViewById(R.id.pl_live_multi_item_img);
            mh.tv = view.findViewById(R.id.pl_live_multi_item_name);
            view.setTag(mh);
        } else {
            mh = (MyHolder) view.getTag();
        }
        mh.tv.setText(list.get(i).getTitle());
        Glide.with(context).load(list.get(i).getImage()).into(mh.img);
        return view;
    }


    class MyHolder {
        ImageView img;
        TextView tv;
    }


}

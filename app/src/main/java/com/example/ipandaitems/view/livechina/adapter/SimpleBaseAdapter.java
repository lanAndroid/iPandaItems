package com.example.ipandaitems.view.livechina.adapter;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Adapter基类
 * 
 */
public abstract class SimpleBaseAdapter<T> extends BaseAdapter {

	public Context context;
	
	public List<T> list;
	

	public SimpleBaseAdapter(Context context, List<T> list) {
		this.context = context;
		this.list = list;

		

	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	


}

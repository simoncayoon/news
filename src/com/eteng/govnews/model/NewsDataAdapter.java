package com.eteng.govnews.model;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.eteng.govnews.R;

public class NewsDataAdapter extends BaseAdapter {

	private ArrayList<NewsInfo> listData;
	LayoutInflater inflater;
	
	public NewsDataAdapter(Context ctx, ArrayList<NewsInfo> data){
		this.listData = data;
		inflater = (LayoutInflater) ctx
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		return listData.size();
	}

	@Override
	public Object getItem(int position) {
		return listData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater
					.inflate(R.layout.news_list_item_layout, null);

			holder.vTitle = (TextView) convertView
					.findViewById(R.id.news_summary_title);
			holder.vDate = (TextView) convertView
					.findViewById(R.id.news_summary_date);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		NewsInfo info = listData.get(position);
		holder.vTitle.setText(info.getNewsTitle());
		holder.vTitle.setText(info.getNewsDate());

		return convertView;
	}

	class ViewHolder{
		public TextView vTitle, vDate;
	}
}

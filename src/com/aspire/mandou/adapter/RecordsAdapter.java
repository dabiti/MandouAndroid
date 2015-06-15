package com.aspire.mandou.adapter;

import java.util.List;
import java.util.Map;

import com.example.mandou.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RecordsAdapter extends BaseAdapter{
	private List<Map<String, Object>> mContentDataList;
	private LayoutInflater mInflater;
	
	public RecordsAdapter(Context context,List<Map<String, Object>> contentData){
		mContentDataList = contentData;
		mInflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		return mContentDataList.size();
	}

	@Override
	public Object getItem(int arg0) {
		return mContentDataList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		ViewHolder viewHolder;
		if(arg1 == null){
			arg1 = mInflater.inflate(R.layout.records_content_item, null);
			viewHolder = new ViewHolder();
			viewHolder.contentLabel = (TextView) arg1.findViewById(R.id.content_label);
			viewHolder.contentTypeImage = (ImageView) arg1.findViewById(R.id.content_type_image);
			viewHolder.contentTyptLabel = (TextView) arg1.findViewById(R.id.content_type_label);
			viewHolder.contentNoteLabel = (TextView) arg1.findViewById(R.id.content_note_label);
			viewHolder.titleDateLabel = (TextView) arg1.findViewById(R.id.records_title_date);
			viewHolder.recordsLabel = (TextView) arg1.findViewById(R.id.records_label);
			arg1.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) arg1.getTag();
		}
		
		if(!mContentDataList.get(arg0).get("date").equals("empty")){
			viewHolder.titleDateLabel.setVisibility(View.VISIBLE);
			viewHolder.titleDateLabel.setText((String)mContentDataList.get(arg0).get("date"));
		}else{
			viewHolder.titleDateLabel.setVisibility(View.GONE);
		}
		String contentLabel = "您今天于"+(String)mContentDataList.get(arg0).get("contentTime")+"分易捷购消费积分，价值"+(String)mContentDataList.get(arg0).get("contentValue")+"元。";
		viewHolder.recordsLabel.setText((String)mContentDataList.get(arg0).get("records"));
		viewHolder.contentLabel.setText(contentLabel);
		viewHolder.contentNoteLabel.setText((String)mContentDataList.get(arg0).get("note"));
		viewHolder.contentTyptLabel.setText((String)mContentDataList.get(arg0).get("cellType"));
		
		
		return arg1;
	}
	
	public static class ViewHolder{
		TextView contentTyptLabel;
		ImageView contentTypeImage;
		TextView recordsLabel;
		TextView contentLabel;
		TextView contentNoteLabel;
		TextView titleDateLabel;
	}
}

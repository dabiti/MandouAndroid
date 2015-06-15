package com.aspire.mandou.adapter;

import java.util.List;
import java.util.Map;

import com.aspire.mandou.adapter.RecordsAdapter.ViewHolder;
import com.example.mandou.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ElectronicVoucherAdapter extends BaseAdapter{
	private List<Map<String,Object>> mData;
	private LayoutInflater mInflater;
	public ElectronicVoucherAdapter(List<Map<String,Object>> data,Context context){
		mData = data;
		mInflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public Object getItem(int arg0) {
		return mData.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		ViewHolder viewHolder;
		if(arg1 == null){
			arg1 = mInflater.inflate(R.layout.electronic_voucher_item, null);
			viewHolder = new ViewHolder();
			viewHolder.DingDanHao = (TextView) arg1.findViewById(R.id.ding_dan_hao);
			viewHolder.MianeShu = (TextView) arg1.findViewById(R.id.mian_e_shu);
			viewHolder.ShangPinIcon = (ImageView) arg1.findViewById(R.id.shang_pin_icon);
			viewHolder.ShangpinMingCheng = (TextView) arg1.findViewById(R.id.shang_pin_ming_cheng);
			viewHolder.isPayed = (ImageView) arg1.findViewById(R.id.zhi_fu_button);
			arg1.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) arg1.getTag();
		}
		
		viewHolder.DingDanHao.setText((String)mData.get(arg0).get("dingdanhao"));
		viewHolder.MianeShu.setText((String)mData.get(arg0).get("miane"));
		viewHolder.ShangpinMingCheng.setText((String)mData.get(arg0).get("shangpinming"));
		return arg1;
	}
	
	public static class ViewHolder{
		TextView DingDanHao;
		TextView ShangpinMingCheng;
		TextView MianeShu;
		ImageView ShangPinIcon;
		ImageView isPayed;
	}
}

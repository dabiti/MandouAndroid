package com.aspire.mandou.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.aspire.mandou.adapter.ElectronicVoucherAdapter;
import com.example.mandou.R;

public class ElectronicVoucherFragment extends Fragment{
	private ListView eListView;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.electronic_voucher, container,false);
		eListView = (ListView) view.findViewById(R.id.dian_zi_quan_listview);
		List<Map<String,Object>> mData = getData();
		ElectronicVoucherAdapter evAdapter = new ElectronicVoucherAdapter(mData,this.getActivity());
		eListView.setAdapter(evAdapter);
		return view;
	}
	
	private List<Map<String,Object>> getData(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("dingdanhao","YH103991820");
		map.put("shangpinming", "华为萨拉快点讲法律考试");
		map.put("miane","999");
		list.add(map);
		
		map = new HashMap<String,Object>();
		map.put("dingdanhao","YH103991820");
		map.put("shangpinming", "华为萨拉快点讲法律考试");
		map.put("miane","999");
		list.add(map);
		
		map = new HashMap<String,Object>();
		map.put("dingdanhao","YH103991820");
		map.put("shangpinming", "华为萨拉快点讲法律考试");
		map.put("miane","999");
		list.add(map);
		
		map = new HashMap<String,Object>();
		map.put("dingdanhao","YH103991820");
		map.put("shangpinming", "华为萨拉快点讲法律考试");
		map.put("miane","999");
		list.add(map);
		return list;
	}
}

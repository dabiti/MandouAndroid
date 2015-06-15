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

import com.aspire.mandou.adapter.RecordsAdapter;
import com.example.mandou.R;

/**
 * 消费记录
 */
public class RecordsConsumptionFragment extends Fragment{
	private ListView listView;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.records_consumption, container,false);
		listView = (ListView) view.findViewById(R.id.records_consumption_listview);
		List<Map<String,Object>> mData = this.getData();
		RecordsAdapter recordsAdapter = new RecordsAdapter(this.getActivity(),mData);
		listView.setAdapter(recordsAdapter);
		return view;
	}

	private List<Map<String,Object>> getData(){
		List<Map<String, Object>> contentDataList = new ArrayList<Map<String, Object>>();
		Map<String,Object> contentDataMap = new HashMap<String,Object>();
		contentDataMap.put("date", "2015.06.02");
		contentDataMap.put("cellType","加油充值卡");// 0:加油卡 1:易洁苟 2:充值
		contentDataMap.put("records","123123.123");
		contentDataMap.put("contentTime","12:28");
		contentDataMap.put("contentValue","998");
		contentDataMap.put("note","xxxxxxxxx");
		contentDataList.add(contentDataMap);
		
		contentDataMap = new HashMap<String,Object>();
		contentDataMap.put("date", "empty");
		contentDataMap.put("cellType","易捷购");// 0:加油卡 1:易洁苟 2:充值
		contentDataMap.put("records","123123.123");
		contentDataMap.put("contentTime","12:28");
		contentDataMap.put("contentValue","998");
		contentDataMap.put("note","xxxxxxxxx");
		contentDataList.add(contentDataMap);
		
		contentDataMap = new HashMap<String,Object>();
		contentDataMap.put("date", "empty");
		contentDataMap.put("cellType","充值");// 0:加油卡 1:易洁苟 2:充值
		contentDataMap.put("records","123123.123");
		contentDataMap.put("contentTime","12:28");
		contentDataMap.put("contentValue","998");
		contentDataMap.put("note","xxxxxxxxx");
		contentDataList.add(contentDataMap);
		
		contentDataMap = new HashMap<String,Object>();
		contentDataMap.put("date", "2015.05.27");
		contentDataMap.put("cellType","加油充值卡");// 0:加油卡 1:易洁苟 2:充值
		contentDataMap.put("records","123123.123");
		contentDataMap.put("contentTime","12:28");
		contentDataMap.put("contentValue","998");
		contentDataMap.put("note","xxxxxxxxx");
		contentDataList.add(contentDataMap);
		
		contentDataMap = new HashMap<String,Object>();
		contentDataMap.put("date", "empty");
		contentDataMap.put("cellType","加油充值卡");// 0:加油卡 1:易洁苟 2:充值
		contentDataMap.put("records","123123.123");
		contentDataMap.put("contentTime","12:28");
		contentDataMap.put("contentValue","998");
		contentDataMap.put("note","xxxxxxxxx");
		contentDataList.add(contentDataMap);
		
		contentDataMap = new HashMap<String,Object>();
		contentDataMap.put("date", "empty");
		contentDataMap.put("cellType","加油充值卡");// 0:加油卡 1:易洁苟 2:充值
		contentDataMap.put("records","123123.123");
		contentDataMap.put("contentTime","12:28");
		contentDataMap.put("contentValue","998");
		contentDataMap.put("note","xxxxxxxxx");
		contentDataList.add(contentDataMap);
		
		contentDataMap = new HashMap<String,Object>();
		contentDataMap.put("date", "empty");
		contentDataMap.put("cellType","加油充值卡");// 0:加油卡 1:易洁苟 2:充值
		contentDataMap.put("records","123123.123");
		contentDataMap.put("contentTime","12:28");
		contentDataMap.put("contentValue","998");
		contentDataMap.put("note","xxxxxxxxx");
		contentDataList.add(contentDataMap);
		
		contentDataMap = new HashMap<String,Object>();
		contentDataMap.put("date", "2015.05.27");
		contentDataMap.put("cellType","加油充值卡");// 0:加油卡 1:易洁苟 2:充值
		contentDataMap.put("records","123123.123");
		contentDataMap.put("contentTime","12:28");
		contentDataMap.put("contentValue","998");
		contentDataMap.put("note","xxxxxxxxx");
		contentDataList.add(contentDataMap);
		
		contentDataMap = new HashMap<String,Object>();
		contentDataMap.put("date", "empty");
		contentDataMap.put("cellType","加油充值卡");// 0:加油卡 1:易洁苟 2:充值
		contentDataMap.put("records","123123.123");
		contentDataMap.put("contentTime","12:28");
		contentDataMap.put("contentValue","998");
		contentDataMap.put("note","xxxxxxxxx");
		contentDataList.add(contentDataMap);
		
		contentDataMap = new HashMap<String,Object>();
		contentDataMap.put("date", "empty");
		contentDataMap.put("cellType","加油充值卡");// 0:加油卡 1:易洁苟 2:充值
		contentDataMap.put("records","123123.123");
		contentDataMap.put("contentTime","12:28");
		contentDataMap.put("contentValue","998");
		contentDataMap.put("note","xxxxxxxxx");
		contentDataList.add(contentDataMap);
		
		contentDataMap = new HashMap<String,Object>();
		contentDataMap.put("date", "empty");
		contentDataMap.put("cellType","加油充值卡");// 0:加油卡 1:易洁苟 2:充值
		contentDataMap.put("records","123123.123");
		contentDataMap.put("contentTime","12:28");
		contentDataMap.put("contentValue","998");
		contentDataMap.put("note","xxxxxxxxx");
		contentDataList.add(contentDataMap);
		
		contentDataMap = new HashMap<String,Object>();
		contentDataMap.put("date", "empty");
		contentDataMap.put("cellType","加油充值卡");// 0:加油卡 1:易洁苟 2:充值
		contentDataMap.put("records","123123.123");
		contentDataMap.put("contentTime","12:28");
		contentDataMap.put("contentValue","998");
		contentDataMap.put("note","xxxxxxxxx");
		contentDataList.add(contentDataMap);
		
		contentDataMap = new HashMap<String,Object>();
		contentDataMap.put("date", "2015.05.27");
		contentDataMap.put("cellType","加油充值卡");// 0:加油卡 1:易洁苟 2:充值
		contentDataMap.put("records","123123.123");
		contentDataMap.put("contentTime","12:28");
		contentDataMap.put("contentValue","998");
		contentDataMap.put("note","xxxxxxxxx");
		contentDataList.add(contentDataMap);
		
		return contentDataList;
	}
	
}

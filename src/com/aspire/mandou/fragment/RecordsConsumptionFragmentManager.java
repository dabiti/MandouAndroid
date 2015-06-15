package com.aspire.mandou.fragment;

import com.example.mandou.R;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class RecordsConsumptionFragmentManager extends Fragment implements OnClickListener{
	private LinearLayout recordsConsumption;// 消费记录
	private LinearLayout electronicVoucher;// 电子券
	private ElectronicVoucherFragment electronicVoucherFragment;
	private RecordsConsumptionFragment recordsConsumptionFragment;
    private FragmentManager fragmentManager;
    
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.records_consumption_fragment_manager, container,false);
		init(view);
		return view;
	}

	private void init(View view){
		recordsConsumption = (LinearLayout) view.findViewById(R.id.xiao_fei_ji_lu);
		recordsConsumption.setOnClickListener(this);
		electronicVoucher = (LinearLayout) view.findViewById(R.id.dian_zi_quan);
		electronicVoucher.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View arg0) {
		fragmentManager = getFragmentManager();
		FragmentTransaction ft = fragmentManager.beginTransaction();
		switch(arg0.getId()){
		case R.id.xiao_fei_ji_lu:			
			if(recordsConsumptionFragment == null)
				recordsConsumptionFragment = new RecordsConsumptionFragment();
			ft.replace(R.id.rcfmanager,recordsConsumptionFragment);
			ft.commit();
			break;
		case R.id.dian_zi_quan:
			if(electronicVoucherFragment == null) 
				electronicVoucherFragment = new ElectronicVoucherFragment();
			ft.replace(R.id.rcfmanager, electronicVoucherFragment);
			ft.commit();
			break;
		}
	}
}

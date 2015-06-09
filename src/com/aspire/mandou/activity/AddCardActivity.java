package com.aspire.mandou.activity;

import java.io.IOException;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.aspire.mandou.activity.base.BaseActivity;
import com.aspire.mandou.entity.BizException;
import com.aspire.mandou.entity.ResultData;
import com.aspire.mandou.framework.widget.MyToast;
import com.aspire.mandou.util.IntentUtil;
import com.aspire.mandou.util.MyAsyncTask;
import com.aspire.mandou.util.StringUtil;
import com.aspire.mandou.util.UIUtil;
import com.aspire.mandou.webservice.ServiceException;
import com.example.mandou.R;
import com.neweggcn.lib.json.JsonParseException;


public class AddCardActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		putContentView(R.layout.my_card_add_layout,R.string.my_purse_op_bind);
		
	}
	
	public void onClickSave(View view){
		EditText cardNumEditText = (EditText)findViewById(R.id.card_add_num);
		EditText idCardEditText = (EditText)findViewById(R.id.card_add_idcard);
		EditText nameEditText = (EditText)findViewById(R.id.card_add_name);
		EditText phoneEditText = (EditText)findViewById(R.id.card_add_phone);
		
		final String cardNumString = cardNumEditText.getText().toString();
		final String idCardString = idCardEditText.getText().toString();
		final String nameString = nameEditText.getText().toString();
		final String phoneString = phoneEditText.getText().toString();
		
		if(StringUtil.isEmpty(cardNumString)){
			MyToast.show(this, getString(R.string.card_add_num_tips));
			return;
		}
		if(StringUtil.isEmpty(idCardString)){
			MyToast.show(this, getString(R.string.card_add_idcard_tips));
			return;
		}
		if(!StringUtil.isIdentityNo(idCardString)){
			MyToast.show(this, getString(R.string.card_add_idcard_error));
			return;
		}
		if(StringUtil.isEmpty(nameString)){
			MyToast.show(this, getString(R.string.card_add_name_tips));
			return;
		}
		if(StringUtil.isEmpty(phoneString)){
			MyToast.show(this, getString(R.string.card_add_phone_tips));
			return;
		}
		if(!StringUtil.isPhone(phoneString)){
			MyToast.show(this, getString(R.string.card_add_phone_error));
			return;
		}
		

		UIUtil.hideSoftInput(phoneEditText);
		showLoading(R.string.loading_message_tip);
//		new MyAsyncTask<ResultData<CardBindRequest>>(this) {
//
//			@Override
//			public ResultData<CardBindRequest> callService()
//					throws IOException, JsonParseException, BizException,
//					ServiceException {
//				CardBindRequest request = new CardBindRequest();
//				request.setCardNo(cardNumString);
//				request.setCardOwnerCellPhone(phoneString);
//				request.setCardOwnerName(nameString);
//				request.setIDNumber(idCardString);
//				request.setBankSysNo(10);
//				return new MyAccountService_Part2().bindCreditCard(request);
//			}
//
//			@Override
//			public void onLoaded(ResultData<CardBindRequest> result)
//					throws Exception {
//				closeLoading();
//				
//				if (result.isSuccess()) {
//					MyToast.show(getApplicationContext(), result.getMessage());
//					IntentUtil.redirectToNextActivity(AddCardActivity.this, MyPurseActivity.class);
//					AddCardActivity.this.finish();
//				}else{
//					showError(result);
//				}
//			}
//
//		}.execute();
	}
}

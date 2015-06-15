package com.aspire.mandou.activity;

import java.io.IOException;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.aspire.mandou.activity.base.BaseActivity;
import com.aspire.mandou.entity.BizException;
import com.aspire.mandou.entity.ResultData;
import com.aspire.mandou.framework.widget.MyToast;
import com.aspire.mandou.framework.widget.NavigationHelper;
import com.aspire.mandou.listener.CountDownTimerListener;
import com.aspire.mandou.util.CountDownTimerUtil;
import com.aspire.mandou.util.IntentUtil;
import com.aspire.mandou.util.MyAsyncTask;
import com.aspire.mandou.util.StringUtil;
import com.aspire.mandou.util.UIUtil;
import com.aspire.mandou.webservice.MyAccountService;
import com.aspire.mandou.webservice.MyAccountService_Part2;
import com.aspire.mandou.webservice.ServiceException;
import com.example.mandou.R;
import com.neweggcn.lib.json.JsonParseException;

public class MyPhoneVerifyActivity extends BaseActivity {
	private EditText mPhoneEditText;
	private EditText mCodeEditText;
	private Button mGetValidateCodeButton;
	private CountDownTimerUtil mCountDownTimerUtil;
	private int mSequenceCode=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		putContentView(R.layout.my_info_phone_verify_layout, R.string.my_acc_phone,
				NavigationHelper.NONE);
		
		initViews();
	}

	private void initViews(){
		mPhoneEditText = (EditText)findViewById(R.id.my_verify_phone);
		mCodeEditText= (EditText)findViewById(R.id.my_verify_code);
		mGetValidateCodeButton = (Button)findViewById(R.id.btn_send_code);
		
		String phone= getIntent().getStringExtra("phone");
		mPhoneEditText.setText(phone);
	}
	
	public void onClickSave(View view){
		final String phone= mPhoneEditText.getText().toString().trim();
		if(StringUtil.isEmpty(phone)){
			MyToast.show(this, getString(R.string.my_verify_phone));
			return;
		}
		
		final String code= mCodeEditText.getText().toString().trim();
		if(StringUtil.isEmpty(code)){
			MyToast.show(this, getString(R.string.card_add_vc_tips));
			return;
		}

		UIUtil.hideSoftInput(mPhoneEditText);
		showLoading(R.string.loading_message_tip);
		new MyAsyncTask<ResultData<Integer>>(this){

			@Override
			public ResultData<Integer> callService() throws IOException,
					JsonParseException, BizException, ServiceException {
				return new MyAccountService_Part2().validatePhone(mSequenceCode, phone, code);
			}

			@Override
			public void onLoaded(ResultData<Integer> result) throws Exception {
				closeLoading();
				if(result.isSuccess()){
					showMsg("手机验证成功。");
//					IntentUtil.redirectToMainActivity(MyPhoneVerifyActivity.this, MyInfoActivity.class, 1);
				}
				else{
					if (!StringUtil.isEmpty(result.getMessage())) {
						showMsg(result.getMessage());
//						IntentUtil.redirectToMainActivity(MyPhoneVerifyActivity.this, MyInfoActivity.class, 2);
					}
					else{
						showMsg("手机验证失败，请稍后再试。");
					}
				}
			}
		}.executeTask();
	}
	
	//请求验证码
	public void onClickGetValidateCode(View view) {
		final String mobile=mPhoneEditText.getText().toString();
		if (StringUtil.isPhone(mobile)) {
			((InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(mPhoneEditText.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
			if (mCountDownTimerUtil!=null) {
				mCountDownTimerUtil.cancel();
				mCountDownTimerUtil=null;
			}
			showLoading(R.string.loading_message_tip);
			new MyAsyncTask<ResultData<Integer>>(this){

				@Override
				public ResultData<Integer> callService() throws IOException,
						JsonParseException, BizException, ServiceException {
					return new MyAccountService().sendSMSValidateCode(mobile);
				}

				@Override
				public void onLoaded(ResultData<Integer> result) throws Exception {
					closeLoading();
					if (result.isSuccess()) {
						mSequenceCode=result.getData();
						mGetValidateCodeButton.setEnabled(false);
						mGetValidateCodeButton.setBackgroundColor(getResources().getColor(R.color.black_10));
						mCountDownTimerUtil=new CountDownTimerUtil(120000, 1000,new CountDownTimerListener() {
							
							@Override
							public void onTick(long millisUntilFinished) {
								mGetValidateCodeButton.setText(String.valueOf(millisUntilFinished/1000)+"秒");
							}
							
							@Override
							public void onFinish() {
								mGetValidateCodeButton.setEnabled(true);
								mGetValidateCodeButton.setBackgroundColor(getResources().getColor(R.color.my_color_yellow));
								mGetValidateCodeButton.setText(R.string.my_purse_op_get_validate);
							}
						});
						mCountDownTimerUtil.start();
						showMsg(R.string.my_phone_code_send, String.valueOf(mSequenceCode));
					}else {
						if (!StringUtil.isEmpty(result.getMessage())) {
							showMsg(result.getMessage());
						}
					}
				}
			}.executeTask();
		}else {
			showMsg(R.string.my_verify_phone_error);
		}
	}
	
	
}

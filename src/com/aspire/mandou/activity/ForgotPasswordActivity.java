package com.aspire.mandou.activity;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspire.mandou.activity.base.BaseActivity;
import com.aspire.mandou.entity.BizException;
import com.aspire.mandou.entity.ResultData;
import com.aspire.mandou.framework.widget.MyToast;
import com.aspire.mandou.listener.CountDownTimerListener;
import com.aspire.mandou.util.CountDownTimerUtil;
import com.aspire.mandou.util.MyAsyncTask;
import com.aspire.mandou.util.StringUtil;
import com.aspire.mandou.util.UIUtil;
import com.aspire.mandou.webservice.MyAccountService;
import com.aspire.mandou.webservice.ServiceException;
import com.example.mandou.R;
import com.neweggcn.lib.json.JsonParseException;

public class ForgotPasswordActivity extends BaseActivity implements OnClickListener {
	private ImageView mRegisterPhoneImage;
	private TextView mRegisterPhoneTextView;
	private View mRegisterPhoneSelectedView;
	private View mRegisterPhoneView;
	private ImageView mRegisterEmailImage;
	private TextView mRegisterEmailTextView;
	private View mRegisterEmailSelectedView;
	private View mRegisterEmailView;
	
	private ImageView mRegisterPhoneValidatedImageView;
	
	private EditText mRegisterCustomerIdEditText;
	private EditText mRegisterPasswordEditText;
	private EditText mRegisterRePasswordEditText;
	private EditText mRegisterPhoneEditText;
	private EditText mRegisterEmailEditText;
	private EditText mRegisterPhoneCodeEditText;
	private LinearLayout mRegosterSendPhoneCodeLinearLayout;
	private LinearLayout mRegisterSendPhoneCodeTimeLinearLayout;
	private TextView mRegisterSendPhoneCodeTimeTextView;
	private LinearLayout mRegosterSendEmailCodeLinearLayout;
	private LinearLayout mRegisterSendEmailCodeTimeLinearLayout;
	private TextView mRegisterSendEmailCodeTimeTextView;
	private LinearLayout mRegisterPhoneCodeLayout;
	private LinearLayout mRegisterEmailCodeLayout;
	
	private int mSelectedTabIndex=R.id.register_phone_framelayout;
	private CountDownTimerUtil mCountDownTimerUtilPhone;
	private CountDownTimerUtil mCountDownTimerUtilEmail;
	private int mSequenceCodePhone;
	private int mSequenceCodeEmail;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		putContentView(R.layout.forgot_password_layout, R.string.forgot_password_title);
		init();
	}
	
	private void init() {
		findView();
	}
	
	private void findView() {
		mRegisterPhoneImage=(ImageView)findViewById(R.id.register_phone_image);
		mRegisterPhoneTextView=(TextView)findViewById(R.id.register_phone_textview);
		mRegisterPhoneSelectedView=findViewById(R.id.register_phone_line_selected_view);
		mRegisterPhoneView=findViewById(R.id.register_phone_line_view);
		mRegisterEmailImage=(ImageView)findViewById(R.id.register_email_image);
		mRegisterEmailTextView=(TextView)findViewById(R.id.register_email_textview);
		mRegisterEmailSelectedView=findViewById(R.id.register_email_line_selected_view);
		mRegisterEmailView=findViewById(R.id.register_email_line_view);
		
		mRegisterPhoneValidatedImageView=(ImageView)findViewById(R.id.register_phone_validated_imageview);
		
		mRegisterCustomerIdEditText=(EditText)findViewById(R.id.register_customer_id_edittext);
		mRegisterPasswordEditText=(EditText)findViewById(R.id.register_password_edittext);
		mRegisterRePasswordEditText=(EditText)findViewById(R.id.register_repassword_edittext);
		mRegisterPhoneEditText=(EditText)findViewById(R.id.register_phone_edittext);
		mRegisterEmailEditText=(EditText)findViewById(R.id.register_email_edittext);
		mRegisterPhoneCodeEditText=(EditText)findViewById(R.id.register_phone_code_edittext);
		
		mRegosterSendPhoneCodeLinearLayout=(LinearLayout)findViewById(R.id.register_send_phone_code_linearlayout);
		mRegisterSendPhoneCodeTimeLinearLayout=(LinearLayout)findViewById(R.id.register_send_phone_code_time_linearlayout);
		mRegisterSendPhoneCodeTimeTextView=(TextView)findViewById(R.id.register_send_phone_code_time_textview);
		mRegosterSendEmailCodeLinearLayout=(LinearLayout)findViewById(R.id.register_send_email_code_linearlayout);
		mRegisterSendEmailCodeTimeLinearLayout=(LinearLayout)findViewById(R.id.register_send_email_code_time_linearlayout);
		mRegisterSendEmailCodeTimeTextView=(TextView)findViewById(R.id.register_send_email_code_time_textview);
		mRegisterPhoneCodeLayout=(LinearLayout)findViewById(R.id.register_phone_code_linearlayout);
		mRegisterEmailCodeLayout=(LinearLayout)findViewById(R.id.register_email_code_linearlayout);
		
		findViewById(R.id.register_phone_framelayout).setOnClickListener(this);
		findViewById(R.id.register_email_framelayout).setOnClickListener(this);
		findViewById(R.id.register_button).setOnClickListener(this);
		mRegosterSendPhoneCodeLinearLayout.setOnClickListener(this);
		mRegosterSendEmailCodeLinearLayout.setOnClickListener(this);
	}
	
	private void setSelectedView(int selectedId) {
		//email 
		if (mSelectedTabIndex!=selectedId) {
			mSelectedTabIndex=selectedId;
			if (selectedId==R.id.register_phone_framelayout) {
				mRegisterPhoneCodeLayout.setVisibility(View.VISIBLE);
				mRegisterPhoneValidatedImageView.setImageResource(R.drawable.phone);
				mRegisterPhoneEditText.setVisibility(View.VISIBLE);
				mRegisterPhoneImage.setImageResource(R.drawable.phone_press);
				mRegisterPhoneTextView.setTextColor(getResources().getColor(R.color.menu_gray));
				mRegisterPhoneSelectedView.setVisibility(View.VISIBLE);
				mRegisterPhoneView.setVisibility(View.GONE);
			}else {
				mRegisterPhoneCodeLayout.setVisibility(View.GONE);
				mRegisterPhoneEditText.setVisibility(View.GONE);
				mRegisterPhoneImage.setImageResource(R.drawable.phone_selector);
				mRegisterPhoneTextView.setTextColor(getResources().getColor(R.color.menu_light_gray));
				mRegisterPhoneSelectedView.setVisibility(View.GONE);
				mRegisterPhoneView.setVisibility(View.VISIBLE);
			}
			if (selectedId==R.id.register_email_framelayout) {
				mRegisterEmailCodeLayout.setVisibility(View.VISIBLE);
				mRegisterEmailEditText.setVisibility(View.VISIBLE);
				mRegisterPhoneValidatedImageView.setImageResource(R.drawable.email);
				mRegisterEmailImage.setImageResource(R.drawable.email_press);
				mRegisterEmailTextView.setTextColor(getResources().getColor(R.color.menu_gray));
				mRegisterEmailSelectedView.setVisibility(View.VISIBLE);
				mRegisterEmailView.setVisibility(View.GONE);
			}else {
				mRegisterEmailCodeLayout.setVisibility(View.GONE);
				mRegisterEmailEditText.setVisibility(View.GONE);
				mRegisterEmailImage.setImageResource(R.drawable.email_selector);
				mRegisterEmailTextView.setTextColor(getResources().getColor(R.color.menu_light_gray));
				mRegisterEmailSelectedView.setVisibility(View.GONE);
				mRegisterEmailView.setVisibility(View.VISIBLE);
			}
			
			//清空输入框
			mRegisterPhoneCodeEditText.setText("");
			mRegisterPasswordEditText.setText("");
			mRegisterRePasswordEditText.setText("");
		}
	}
	
	private void sendValidatedCode(){
		final String customerId=getEditText(mRegisterCustomerIdEditText);
		final String phone=getEditText(mRegisterPhoneEditText);
		final String email=getEditText(mRegisterEmailEditText);
		if (checkValidated(phone,email,customerId)) {
			if (mSelectedTabIndex==R.id.register_phone_framelayout){
				sendValidatedCodePhone(customerId,phone);
			}else if (mSelectedTabIndex==R.id.register_email_framelayout) {
				sendValidatedCodeEmail(customerId,email);
			}
		}
	}
	
	private void sendValidatedCodePhone(final String customerId,final String phone) {
		if (mCountDownTimerUtilPhone!=null) {
			mCountDownTimerUtilPhone.cancel();
			mCountDownTimerUtilPhone=null;
		}
		UIUtil.hideSoftInput(mRegisterRePasswordEditText);
		showLoading(R.string.loading_message_tip);
		new MyAsyncTask<ResultData<Integer>>(ForgotPasswordActivity.this){

			@Override
			public ResultData<Integer> callService() throws IOException,
					JsonParseException, BizException, ServiceException {
				return new MyAccountService().SendMobileVerifyCode4GetBackPassword(phone,customerId);
			}

			@Override
			public void onLoaded(ResultData<Integer> result) throws Exception {
				closeLoading();
				if (result!=null&&result.getCode()==0&&result.isSuccess()) {
					mSequenceCodePhone=result.getData();
					mRegosterSendPhoneCodeLinearLayout.setVisibility(View.GONE);
					mRegisterSendPhoneCodeTimeLinearLayout.setVisibility(View.VISIBLE);
					mCountDownTimerUtilPhone=new CountDownTimerUtil(120000, 1000,new CountDownTimerListener() {
						
						@Override
						public void onTick(long millisUntilFinished) {
							mRegisterSendPhoneCodeTimeTextView.setText(String.valueOf(millisUntilFinished/1000)+"秒");
						}
						
						@Override
						public void onFinish() {
							mRegisterSendPhoneCodeTimeLinearLayout.setVisibility(View.GONE);
							mRegosterSendPhoneCodeLinearLayout.setVisibility(View.VISIBLE);
						}
					});
					mCountDownTimerUtilPhone.start();
					showMsg(R.string.my_phone_code_send, String.valueOf(mSequenceCodePhone));
				}else {
					if (!StringUtil.isEmpty(result.getMessage())) {
						MyToast.show(ForgotPasswordActivity.this, result.getMessage());
					}
				}
			}
		}.executeTask();
	}
	
	private void sendValidatedCodeEmail(final String customerId,final String email) {
		if (mCountDownTimerUtilEmail!=null) {
			mCountDownTimerUtilEmail.cancel();
			mCountDownTimerUtilEmail=null;
		}
		showLoading(R.string.loading_message_tip);
		new MyAsyncTask<ResultData<Integer>>(ForgotPasswordActivity.this){

			@Override
			public ResultData<Integer> callService() throws IOException,
					JsonParseException, BizException, ServiceException {
				return new MyAccountService().SendEmailVerifyCode4GetBackPassword(email,customerId);
			}

			@Override
			public void onLoaded(ResultData<Integer> result) throws Exception {
				closeLoading();
				if (result!=null&&result.getCode()==0&&result.isSuccess()) {
					mSequenceCodeEmail=result.getData();
					mRegosterSendEmailCodeLinearLayout.setVisibility(View.GONE);
					mRegisterSendEmailCodeTimeLinearLayout.setVisibility(View.VISIBLE);
					mCountDownTimerUtilEmail=new CountDownTimerUtil(120000, 1000,new CountDownTimerListener() {
						
						@Override
						public void onTick(long millisUntilFinished) {
							mRegisterSendEmailCodeTimeTextView.setText(String.valueOf(millisUntilFinished/1000)+"秒");
						}
						
						@Override
						public void onFinish() {
							mRegisterSendEmailCodeTimeLinearLayout.setVisibility(View.GONE);
							mRegosterSendEmailCodeLinearLayout.setVisibility(View.VISIBLE);
						}
					});
					mCountDownTimerUtilEmail.start();
					showMsg(R.string.my_email_code_send, String.valueOf(mSequenceCodeEmail));
				}else {
					if (!StringUtil.isEmpty(result.getMessage())) {
						MyToast.show(ForgotPasswordActivity.this, result.getMessage());
					}
				}
			}
		}.executeTask();
	}
	
	private boolean checkValidated(String mobile,String email,String customerId) {
		if (StringUtil.isEmpty(customerId)) {
			setError(mRegisterCustomerIdEditText,R.string.myaccount_register_customer_id_tip);
			return false;
		}
		if (mSelectedTabIndex==R.id.register_phone_framelayout) {
			if (StringUtil.isEmpty(mobile)) {
				setError(mRegisterPhoneEditText,R.string.myaccount_register_phone_code_tip);
				return false;
			}
			if (!StringUtil.isPhone(mobile)) {
				setError(mRegisterPhoneEditText,R.string.my_verify_phone_error);
				return false;
			}
		}else if (mSelectedTabIndex==R.id.register_email_framelayout) {
			if (StringUtil.isEmpty(email)) {
				setError(mRegisterEmailEditText,R.string.myaccount_register_email_address_tip);
				return false;
			}if (!StringUtil.isEmail(email)) {
				setError(mRegisterEmailEditText,R.string.my_verify_email_error);
				return false;
			}
		}
		
		return true;
	}
	
	private void setError(EditText editText,int resourcesId) {
		editText.setError(Html.fromHtml("<font color=#434343>"+ ForgotPasswordActivity.this.getResources().getString(resourcesId)+ "</color>"));
		editText.requestFocus();
	}
	
	private String getEditText(EditText editText) {
		if (editText!=null&&editText.getText()!=null&&editText.getText().toString()!=null) {
			return editText.getText().toString().trim();
		}
		return null;
	}
	
	private boolean validation(String customerId,String email,String phone,String password,String rePassword,String validatedCode){
		if (StringUtil.isEmpty(customerId)) {
			setError(mRegisterCustomerIdEditText, R.string.myaccount_register_customer_id_tip);
			return false;
		}
		
		if (mSelectedTabIndex==R.id.register_phone_framelayout) {
			if (!StringUtil.isPhone(phone)) {
				setError(mRegisterPhoneEditText, R.string.myaccount_register_phone_code_tip);
				return false;
			}
		}else if (mSelectedTabIndex==R.id.register_email_framelayout) {
			if (!StringUtil.isEmail(email)) {
				setError(mRegisterEmailEditText, R.string.myaccount_register_email_address_tip);
				return false;
			}
		}
		if (StringUtil.isEmpty(validatedCode)) {
			setError(mRegisterPhoneCodeEditText, R.string.myaccount_register_phone_validated_code_tip);
			return false;
		}else {
			if (validatedCode.length()!=6) {
				setError(mRegisterPhoneCodeEditText, R.string.myaccount_register_phone_validated_code_length_tip);
				return false;
			}
		}
		
		if (StringUtil.isEmpty(password)) {
			setError(mRegisterPasswordEditText, R.string.myaccount_register_password_tip);
			return false;
		}
		if (StringUtil.isEmpty(rePassword)) {
			setError(mRegisterRePasswordEditText, R.string.myaccount_register_repassword_tip);
			return false;
		}
		if (!password.equals(rePassword)) {
			setError(mRegisterPasswordEditText, R.string.myaccount_register_password_two_tip);
			return false;
		}
		
		
		return true;
	}
	
	private void submit() {
		final String customerId=getEditText(mRegisterCustomerIdEditText);
		final String phone=getEditText(mRegisterPhoneEditText);
		final String email=getEditText(mRegisterEmailEditText);
		final String passored=getEditText(mRegisterPasswordEditText);
		final String rePassored=getEditText(mRegisterRePasswordEditText);
		final String validatedCode=getEditText(mRegisterPhoneCodeEditText);
		
		if (validation(customerId,email,phone,passored,rePassored,validatedCode)) {
			showLoading(R.string.loading_message_tip);
			new MyAsyncTask<ResultData<Integer>>(ForgotPasswordActivity.this){

				@Override
				public ResultData<Integer> callService() throws IOException,
						JsonParseException, BizException, ServiceException {
					if (mSelectedTabIndex==R.id.register_phone_framelayout) {
						return new MyAccountService().UpdatePasswordWithMobileVerifyCode(customerId,phone,passored,validatedCode,mSequenceCodePhone);
					}else{
						return new MyAccountService().UpdatePasswordWithEmailVerifyCode(customerId,email,passored,validatedCode,mSequenceCodeEmail);
					}
				}

				@Override
				public void onLoaded(ResultData<Integer> result) throws Exception {
					closeLoading();
					if (result!=null&&result.getCode()==0&&result.isSuccess()) {
						if (!StringUtil.isEmpty(result.getMessage())) {
							MyToast.show(ForgotPasswordActivity.this, result.getMessage());
						}
						ForgotPasswordActivity.this.finish();
					}else {
						if (!StringUtil.isEmpty(result.getMessage())) {
							MyToast.show(ForgotPasswordActivity.this, result.getMessage());
						}
					}
				}
			}.executeTask();
		}
	}
		
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.register_phone_framelayout:
			setSelectedView(R.id.register_phone_framelayout);
			break;
		case R.id.register_email_framelayout:
			setSelectedView(R.id.register_email_framelayout);
			break;
		case R.id.register_send_phone_code_linearlayout:
		case R.id.register_send_email_code_linearlayout:
			sendValidatedCode();
			break;
		case R.id.register_button:
			submit();
			break;
		default:
			break;
		}
	}
}

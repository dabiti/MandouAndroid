package com.aspire.mandou.activity;

import java.io.IOException;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspire.mandou.activity.base.BaseActivity;
import com.aspire.mandou.entity.BizException;
import com.aspire.mandou.entity.ResultData;
import com.aspire.mandou.entity.customer.CustomerInfo;
import com.aspire.mandou.entity.customer.RegisterInfo;
import com.aspire.mandou.framework.widget.MyToast;
import com.aspire.mandou.listener.CheckLoginListener;
import com.aspire.mandou.listener.CountDownTimerListener;
import com.aspire.mandou.util.CountDownTimerUtil;
import com.aspire.mandou.util.CustomerUtil;
import com.aspire.mandou.util.IntentUtil;
import com.aspire.mandou.util.LoginStackUtil;
import com.aspire.mandou.util.MyAsyncTask;
import com.aspire.mandou.util.StringUtil;
import com.aspire.mandou.webservice.MyAccountService;
import com.aspire.mandou.webservice.ServiceException;
import com.example.mandou.R;
import com.neweggcn.lib.json.JsonParseException;

public class RegisterActivity extends BaseActivity implements OnClickListener {
	private ImageView mRegisterPhoneImage;
	private TextView mRegisterPhoneTextView;
	private View mRegisterPhoneSelectedView;
	private View mRegisterPhoneView;
	private ImageView mRegisterEmailImage;
	private TextView mRegisterEmailTextView;
	private View mRegisterEmailSelectedView;
	private View mRegisterEmailView;
	
	private EditText mRegisterCustomerIdEditText;
	private EditText mRegisterPasswordEditText;
	private EditText mRegisterRePasswordEditText;
	private EditText mRegisterPhoneEditText;
	private LinearLayout mRegosterSendPhoneCodeLinearLayout;
	private LinearLayout mRegisterSendPhoneCodeTimeLinearLayout;
	private TextView mRegisterSendPhoneCodeTimeTextView;
	private EditText mRegisterPhoneCodeEditText;
	private EditText mRegisterEmailEditText;
	private CheckBox mRegisterAgreementCheckBox;
	private LinearLayout mRegisterPhoneCodeLayout;
	private LinearLayout mRegisterEmailCodeLayout;
	private LinearLayout mRegosterSendEmailCodeLinearLayout;
	private LinearLayout mRegisterSendEmailCodeTimeLinearLayout;
	private TextView mRegisterSendEmailCodeTimeTextView;
	private ImageView mRegisterPhoneValidatedImageView;
	
	private int mSelectedTabIndex=R.id.register_phone_framelayout;
	private CountDownTimerUtil mCountDownTimerUtilPhone;
	private CountDownTimerUtil mCountDownTimerUtilEmail;
	private int mSequenceCodePhone=0;
	private int mSequenceCodeEmail=0;
	private CheckLoginListener mCheckLoginListener;
	private Bundle mCheckLoginParamsBundle;
	private boolean mIsExit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		putContentView(R.layout.register_layout, R.string.myacount_register_title_label);
		getIntentData();
		findView();
	}
	
	private void getIntentData() {
		mCheckLoginListener = getIntent().getParcelableExtra(CustomerUtil.INTENT_ONLOGIN_SUCCESSFULLY_CALLBACK);
		mCheckLoginParamsBundle = getIntent().getBundleExtra(CustomerUtil.INTENT_ONLOGIN_CALLBACK_PARAMS);
		mIsExit= getIntent().getBooleanExtra(CustomerUtil.INTENT_ONLOGIN_IS_EXIT_KEY, true);
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
		
		mRegisterCustomerIdEditText=(EditText)findViewById(R.id.register_customer_id_edittext);
		mRegisterPasswordEditText=(EditText)findViewById(R.id.register_password_edittext);
		mRegisterRePasswordEditText=(EditText)findViewById(R.id.register_repassword_edittext);
		mRegisterPhoneEditText=(EditText)findViewById(R.id.register_phone_edittext);
		mRegosterSendPhoneCodeLinearLayout=(LinearLayout)findViewById(R.id.register_send_phone_code_linearlayout);
		mRegisterSendPhoneCodeTimeLinearLayout=(LinearLayout)findViewById(R.id.register_send_phone_code_time_linearlayout);
		mRegisterSendPhoneCodeTimeTextView=(TextView)findViewById(R.id.register_send_phone_code_time_textview);
		mRegisterPhoneCodeEditText=(EditText)findViewById(R.id.register_phone_code_edittext);
		mRegisterEmailEditText=(EditText)findViewById(R.id.register_email_edittext);
		mRegisterAgreementCheckBox=(CheckBox)findViewById(R.id.register_agreement_checkbox);
		mRegosterSendEmailCodeLinearLayout=(LinearLayout)findViewById(R.id.register_send_email_code_linearlayout);
		mRegisterSendEmailCodeTimeLinearLayout=(LinearLayout)findViewById(R.id.register_send_email_code_time_linearlayout);
		mRegisterSendEmailCodeTimeTextView=(TextView)findViewById(R.id.register_send_email_code_time_textview);
		mRegisterPhoneCodeLayout=(LinearLayout)findViewById(R.id.register_phone_code_linearlayout);
		mRegisterEmailCodeLayout=(LinearLayout)findViewById(R.id.register_email_code_linearlayout);
		mRegisterPhoneValidatedImageView=(ImageView)findViewById(R.id.register_phone_validated_imageview);
		
		findViewById(R.id.register_phone_framelayout).setOnClickListener(this);
		findViewById(R.id.register_email_framelayout).setOnClickListener(this);
		mRegosterSendPhoneCodeLinearLayout.setOnClickListener(this);
		mRegosterSendEmailCodeLinearLayout.setOnClickListener(this);
		findViewById(R.id.register_button).setOnClickListener(this);
		findViewById(R.id.register_agreement_textview).setOnClickListener(this);
	}
	
	private void setSelectedView(int selectedId) {
		if (mSelectedTabIndex!=selectedId) {
			mSelectedTabIndex=selectedId;
			if (selectedId==R.id.register_phone_framelayout) {
				mRegisterPhoneValidatedImageView.setImageResource(R.drawable.phone);
				mRegisterPhoneCodeLayout.setVisibility(View.VISIBLE);
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
				mRegisterPhoneValidatedImageView.setImageResource(R.drawable.email);
				mRegisterEmailCodeLayout.setVisibility(View.VISIBLE);
				mRegisterEmailEditText.setVisibility(View.VISIBLE);
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
		}
	}
	
	private void register() {
		hiddenKeyboard();
		final RegisterInfo registerInfo = getRegisterInfo();
		if (validation(registerInfo)) {
			showLoading(R.string.loading_message_tip);
			new MyAsyncTask<ResultData<CustomerInfo>>(RegisterActivity.this){

				@Override
				public ResultData<CustomerInfo> callService() throws IOException,
						JsonParseException, BizException, ServiceException {
					return new MyAccountService().register(registerInfo);
				}

				@Override
				public void onLoaded(ResultData<CustomerInfo> result) throws Exception {
					closeLoading();
					if (result!=null&&result.getCode()==0&&result.isSuccess()) {
						if (mSelectedTabIndex==R.id.register_phone_framelayout) {
							success(result.getData(),registerInfo.getMobile());
						}else if (mSelectedTabIndex==R.id.register_email_framelayout) {
							success(result.getData(),registerInfo.getCustomerID());
						}
					}else {
						if (!StringUtil.isEmpty(result.getMessage())) {
							MyToast.show(RegisterActivity.this, result.getMessage());
						}
					}
				}
			}.executeTask();
		}
	}
	
	private void success(CustomerInfo customerInfo,String account) {
		customerInfo.setIsRemember(true);
		CustomerUtil.cacheCustomerInfo(customerInfo);
		CustomerUtil.cacheLoginAccount(account);
		if (mCheckLoginListener!=null) {
			mCheckLoginListener.OnLogined(customerInfo, mCheckLoginParamsBundle);
		}else {
//			IntentUtil.redirectToNextActivity(RegisterActivity.this, MyPurseActivity.class);
		}
		RegisterActivity.this.finish();
	}
	
	private boolean validation(RegisterInfo registerInfo){
		
		if (StringUtil.isEmpty(registerInfo.getCustomerID())) {
			setError(mRegisterCustomerIdEditText, R.string.myaccount_register_customer_id_tip);
			return false;
		}
		if (StringUtil.isEmpty(registerInfo.getPassword())) {
			setError(mRegisterPasswordEditText, R.string.myaccount_register_password_tip);
			return false;
		}
		if (StringUtil.isEmpty(registerInfo.getRePassword())) {
			setError(mRegisterRePasswordEditText, R.string.myaccount_register_repassword_tip);
			return false;
		}
		if (!registerInfo.getPassword().equals(registerInfo.getRePassword())) {
			setError(mRegisterPasswordEditText, R.string.myaccount_register_password_two_tip);
			return false;
		}
		if (mSelectedTabIndex==R.id.register_phone_framelayout) {
			if (!StringUtil.isPhone(registerInfo.getMobile())) {
				setError(mRegisterPhoneEditText, R.string.myaccount_register_phone_code_tip);
				return false;
			}
		}else if (mSelectedTabIndex==R.id.register_email_framelayout) {
			if (!StringUtil.isEmail(registerInfo.getEmail())) {
				setError(mRegisterEmailEditText, R.string.myaccount_register_email_address_tip);
				return false;
			}
		}
		if (StringUtil.isEmpty(registerInfo.getValidatedCode())) {
			setError(mRegisterPhoneCodeEditText, R.string.myaccount_register_phone_validated_code_tip);
			return false;
		}else {
			if (registerInfo.getValidatedCode().length()!=6) {
				setError(mRegisterPhoneCodeEditText, R.string.myaccount_register_phone_validated_code_length_tip);
				return false;
			}
		}
		if (!mRegisterAgreementCheckBox.isChecked()) {
			MyToast.show(RegisterActivity.this, "请同意注册协议");
			return false;
		}
		
		return true;
	}
	
	private void setError(EditText editText,int resourcesId) {
		editText.setText(null);
		editText.setError(Html.fromHtml("<font color=#434343>"+ RegisterActivity.this.getResources().getString(resourcesId)+ "</color>"));
		editText.requestFocus();
	}
	
	private String getEditText(EditText editText) {
		if (editText!=null&&editText.getText()!=null&&editText.getText().toString()!=null) {
			return editText.getText().toString().trim();
		}
		return null;
	}
	
	private void sendPhoneValidatedCode() {
		hiddenKeyboard();
		final String mobile=getEditText(mRegisterPhoneEditText);
		if (StringUtil.isPhone(mobile)) {
			((InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(mRegisterPhoneEditText.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
			if (mCountDownTimerUtilPhone!=null) {
				mCountDownTimerUtilPhone.cancel();
				mCountDownTimerUtilPhone=null;
			}
			showLoading(R.string.loading_message_tip);
			new MyAsyncTask<ResultData<Integer>>(RegisterActivity.this){

				@Override
				public ResultData<Integer> callService() throws IOException,
						JsonParseException, BizException, ServiceException {
					return new MyAccountService().sendSMSValidateCode(mobile);
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
							MyToast.show(RegisterActivity.this, result.getMessage());
						}
					}
				}
			}.executeTask();
		}else {
			setError(mRegisterPhoneEditText,R.string.myaccount_register_phone_code_tip);
		}
	}
	
	private void sendEmailValidatedCode() {
		hiddenKeyboard();
		final String email=getEditText(mRegisterEmailEditText);
		if (StringUtil.isEmail(email)) {
			if (mCountDownTimerUtilEmail!=null) {
				mCountDownTimerUtilEmail.cancel();
				mCountDownTimerUtilEmail=null;
			}
			showLoading(R.string.loading_message_tip);
			new MyAsyncTask<ResultData<Integer>>(RegisterActivity.this){

				@Override
				public ResultData<Integer> callService() throws IOException,
						JsonParseException, BizException, ServiceException {
					return new MyAccountService().sendEmailValidateCode(email);
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
							MyToast.show(RegisterActivity.this, result.getMessage());
						}
					}
				}
			}.executeTask();
		}else {
			setError(mRegisterEmailEditText,R.string.myaccount_register_email_address_tip);
		}
	}
	
	private RegisterInfo getRegisterInfo() {
		RegisterInfo registerInfo=new RegisterInfo();
		registerInfo.setCustomerID(getEditText(mRegisterCustomerIdEditText));
		registerInfo.setPassword(getEditText(mRegisterPasswordEditText));
		registerInfo.setRePassword(getEditText(mRegisterRePasswordEditText));
		registerInfo.setGender(0);
		registerInfo.setEmail(getEditText(mRegisterEmailEditText));
		registerInfo.setValidatedCode(getEditText(mRegisterPhoneCodeEditText));
		registerInfo.setMobile(getEditText(mRegisterPhoneEditText));
		registerInfo.setCustomerType(0);
		if (mSelectedTabIndex==R.id.register_phone_framelayout) {
			registerInfo.setRegisterType(0);
			registerInfo.setSequenceCode(mSequenceCodePhone);
			registerInfo.setEmail("");
		}else if (mSelectedTabIndex==R.id.register_email_framelayout) {
			registerInfo.setRegisterType(1);
			registerInfo.setSequenceCode(mSequenceCodeEmail);
			registerInfo.setMobile("");
		}
		
		return registerInfo;
	}
	
	private void hiddenKeyboard() {
		InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(mRegisterPhoneCodeEditText.getWindowToken(), 0);
	}
	
	@Override
	public void onBackPressed() {
		LoginStackUtil.clearAll();
		Bundle bundle=new Bundle();
		bundle.putParcelable(CustomerUtil.INTENT_ONLOGIN_SUCCESSFULLY_CALLBACK, mCheckLoginListener);
		bundle.putBundle(CustomerUtil.INTENT_ONLOGIN_CALLBACK_PARAMS, mCheckLoginParamsBundle);
		bundle.putBoolean(CustomerUtil.INTENT_ONLOGIN_IS_EXIT_KEY, mIsExit);
		IntentUtil.redirectToNextActivity(RegisterActivity.this, LoginActivity.class,bundle);
		RegisterActivity.this.finish();
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
			sendPhoneValidatedCode();
			break;
		case R.id.register_send_email_code_linearlayout:
			sendEmailValidatedCode();
			break;
		case R.id.register_button:
			register();
			break;
		case R.id.register_agreement_textview:
//			IntentUtil.redirectToNextActivity(RegisterActivity.this, RegisterAgreementActivity.class);
			break;
		default:
			break;
		}
	}
}

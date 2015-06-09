package com.aspire.mandou.activity;

import java.io.IOException;
import com.aspire.mandou.entity.BizException;
import com.aspire.mandou.entity.ResultData;
import com.aspire.mandou.entity.customer.CustomerInfo;
import com.aspire.mandou.framework.widget.MyToast;
import com.aspire.mandou.listener.CheckLoginListener;
import com.aspire.mandou.util.CustomerUtil;
import com.aspire.mandou.util.DialogUtil;
import com.aspire.mandou.util.IntentUtil;
import com.aspire.mandou.util.LoginStackUtil;
import com.aspire.mandou.util.MyAsyncTask;
import com.aspire.mandou.util.StringUtil;
import com.aspire.mandou.webservice.MyAccountService;
import com.aspire.mandou.webservice.ServiceException;
import com.example.mandou.R;
import com.neweggcn.lib.json.JsonParseException;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

public class LoginActivity extends Activity implements OnClickListener {
	
	private EditText mUserNameEditText;
	private EditText mUserPasswordEditText;
	private CheckBox mRememberCheckbox;
	private ImageView mHeadImageView;
	private ProgressDialog mLoadingDialog;
	
	private CheckLoginListener mCheckLoginListener;
	private Bundle mCheckLoginParamsBundle;
	private boolean mIsExit=true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);
		init();
	}

	private void findView() {
		findViewById(R.id.myaccount_login_button_login).setOnClickListener(this);
		findViewById(R.id.myaccount_login_button_register).setOnClickListener(this);
		mUserNameEditText=(EditText)findViewById(R.id.myaccount_login_editext_username);
		mUserPasswordEditText=(EditText)findViewById(R.id.myaccount_login_editext_userpassword);
		mRememberCheckbox=(CheckBox)findViewById(R.id.myaccount_login_checkbox_remember);
		mHeadImageView=(ImageView)findViewById(R.id.login_head_imageview);
		
		findViewById(R.id.login_friends_linearlayout).setOnClickListener(this);
		findViewById(R.id.login_findkey_linearlayout).setOnClickListener(this);
		findViewById(R.id.login_qq_linearlayout).setOnClickListener(this);
		findViewById(R.id.login_weibo_linearlayout).setOnClickListener(this);
	}
	
	private void getIntentData() {
		mCheckLoginListener = getIntent().getParcelableExtra(CustomerUtil.INTENT_ONLOGIN_SUCCESSFULLY_CALLBACK);
		mCheckLoginParamsBundle = getIntent().getBundleExtra(CustomerUtil.INTENT_ONLOGIN_CALLBACK_PARAMS);
		mIsExit=getIntent().getBooleanExtra(CustomerUtil.INTENT_ONLOGIN_IS_EXIT_KEY, true);
	}
	
	private void init() {
		getIntentData();
		findView();
	}
	
	private void setHeadImage() {
		Bitmap bitmap=CustomerUtil.getHeadImage();
		if (bitmap!=null) {
			mHeadImageView.setImageBitmap(bitmap);
		}
	}
	
	private void setUserName() {
		String userName=CustomerUtil.getLoginAccount();
		if (!StringUtil.isEmpty(userName)) {
			mUserNameEditText.setText(CustomerUtil.getLoginAccount());
			mUserPasswordEditText.requestFocus();
		}
	}
	
	private void login() {
		login(getEditText(mUserNameEditText), getEditText(mUserPasswordEditText));
	}
	
	private void login(final String userName,final String password) {
		InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(mUserPasswordEditText.getWindowToken(), 0);
		if (validation(userName,password)) {
			showLoading(R.string.myaccount_login_logining);
			new MyAsyncTask<ResultData<CustomerInfo>>(LoginActivity.this){

				@Override
				public ResultData<CustomerInfo> callService() throws IOException,
						JsonParseException, BizException, ServiceException {
					return new MyAccountService().login(userName, password);
				}

				@Override
				public void onLoaded(ResultData<CustomerInfo> result) throws Exception {
					closeLoading();
					if (result!=null&&result.getCode()==0&&result.isSuccess()) {
						success(result.getData(),userName);
					}else {
						if (!StringUtil.isEmpty(result.getMessage())) {
							MyToast.show(LoginActivity.this, result.getMessage());
						}
					}
				}
			}.executeTask();
		}
	}
	
	private boolean validation(String userName,String password) {
		if (StringUtil.isEmpty(userName)) {
			setError(mUserNameEditText,R.string.myaccount_login_username_error_empty_tip);
			return false;
		}
		if (StringUtil.isEmpty(password)) {
			setError(mUserPasswordEditText,R.string.myaccount_login_userpassword_error_empty_tip);
			
			return false;
		}
		return true;
	}

	private void setError(EditText editText,int resourcesId) {
		editText.setText(null);
		editText.setError(Html.fromHtml("<font color=#434343>"+ LoginActivity.this.getResources().getString(resourcesId)+ "</color>"));
		editText.requestFocus();
	}
	
	private void success(CustomerInfo customerInfo,String userName) {
		customerInfo.setIsRemember(mRememberCheckbox.isChecked());
		CustomerUtil.cacheCustomerInfo(customerInfo);
		CustomerUtil.cacheLoginAccount(userName);
		if (mCheckLoginListener!=null) {
			mCheckLoginListener.OnLogined(customerInfo, mCheckLoginParamsBundle);
		}else {
//			IntentUtil.redirectToNextActivity(LoginActivity.this, MyPurseActivity.class);
		}
		if(CustomerUtil.getIsVisitor()){
			LoginActivity.this.finish();
		}else {
			LoginStackUtil.addActivity(LoginActivity.this);
		}
	}
	
	private String getEditText(EditText editText) {
		if (editText!=null&&editText.getText()!=null) {
			String value=editText.getText().toString();
			if (value!=null) {
				return value.trim();
			}
		}
		return "";
	}
	
	@Override
	protected void onResume() {
		super.onResume();
//		setHeadImage();
		setUserName();
		mUserPasswordEditText.setText(null);
	}
	
	@Override
	public void onBackPressed() {
		if (mIsExit) {
//			ExitAppUtil.exit(LoginActivity.this);
		}else {
			
			super.onBackPressed();
		}
	}
	
	public void showLoading(String tips) {
		closeLoading();
		try {
			if (mLoadingDialog!=null&& mLoadingDialog.isShowing()) {
				mLoadingDialog.dismiss();
			}
			mLoadingDialog = DialogUtil.getProgressDialog(this, tips);
			mLoadingDialog.show();
		} catch (Exception e) {
		}
	}
	
	public void showLoading(int id){
		showLoading(this.getResources().getString(id));
	}

	public void closeLoading() {

		try {

			if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
				mLoadingDialog.dismiss();
			}
		} catch (Exception e) {
		}
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.myaccount_login_button_login:
//			login();
			IntentUtil.redirectToNextActivity(LoginActivity.this, MainActivity.class);
			break;
		case R.id.myaccount_login_button_register:
			Bundle bundle=new Bundle();
			bundle.putParcelable(CustomerUtil.INTENT_ONLOGIN_SUCCESSFULLY_CALLBACK, mCheckLoginListener);
			bundle.putBundle(CustomerUtil.INTENT_ONLOGIN_CALLBACK_PARAMS, mCheckLoginParamsBundle);
			bundle.putBoolean(CustomerUtil.INTENT_ONLOGIN_IS_EXIT_KEY, mIsExit);
			IntentUtil.redirectToNextActivity(LoginActivity.this, RegisterActivity.class,bundle);
			if(CustomerUtil.getIsVisitor()){
				LoginActivity.this.finish();
			}else {
				LoginStackUtil.addActivity(LoginActivity.this);
			}
			break;
		case R.id.login_friends_linearlayout:
			CustomerUtil.cacheIsVisitorLogin(true);
			IntentUtil.redirectToNextActivity(LoginActivity.this, MainActivity.class);
			LoginActivity.this.finish();
			break;
		case R.id.login_findkey_linearlayout:
			IntentUtil.redirectToNextActivity(LoginActivity.this, ForgotPasswordActivity.class);
			break;
		case R.id.login_qq_linearlayout:
			MyToast.show(LoginActivity.this, "敬请期待！");
		case R.id.login_weibo_linearlayout:
			MyToast.show(LoginActivity.this, "敬请期待！");
			break;
		default:
			break;
		}
	}
	
}

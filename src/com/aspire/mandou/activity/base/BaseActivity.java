package com.aspire.mandou.activity.base;

import com.aspire.mandou.entity.ResultData;
import com.aspire.mandou.framework.widget.CustomTitleManager;
import com.aspire.mandou.framework.widget.MyToast;
import com.aspire.mandou.framework.widget.NavigationHelper;
import com.aspire.mandou.util.DialogUtil;
import com.aspire.mandou.util.IntentUtil;
import com.aspire.mandou.util.StringUtil;
import com.example.mandou.R;

import android.R.string;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

/**
 * This Class include Title Bar ,Navigation Bar and some other Broadcast
 * Receiver components.
 * 
 */
public class BaseActivity extends Activity {

	private CustomTitleManager mCustomTitleManager;
	private NavigationHelper mNavigationHelper;


	// Default selected tab is NavigationHelper.DEFAULT
	private int mCurrentSelectedTab = NavigationHelper.DEFAULT;
	private ProgressDialog mLoadingDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		mNavigationHelper = new NavigationHelper(this);
	}

	/**
	 * Set content view with Title bar and Navigation bar
	 * 
	 * @param layoutId
	 *            Page Content Layout
	 * @param pageTitle
	 *            Page Title
	 * @param tab
	 *            The Current Selected Tab,
	 *            NavigationHelper.HOME,NavigationHelper.SEARCH
	 *            NavigationHelper.CATEGORY,NavigationHelper.CART
	 *            NavigationHelper.MORE
	 * 
	 */
	public void putContentView(int layoutId, String pageTitle, int tab) {

		Boolean noTitle = pageTitle == null || pageTitle.equals("");
		mCustomTitleManager = new CustomTitleManager(this, noTitle);
		setContentView(layoutId);
		if (!noTitle) {
			mCustomTitleManager.setUp();
			mCustomTitleManager.setTitle(pageTitle);
		}

		mCurrentSelectedTab = tab;
		if (mCurrentSelectedTab != NavigationHelper.NONE) {

			mNavigationHelper.setNavigationActionEvent();
			mNavigationHelper.setSelectedNavigationTab(mCurrentSelectedTab);
		} else {
		}
	}

	/**
	 * Set content view
	 * 
	 * @param layoutId
	 * @param pageTitle
	 * @param tab
	 *            NavigationHelper.HOME,NavigationHelper.SEARCH
	 *            NavigationHelper.CATEGORY,NavigationHelper.CART
	 *            NavigationHelper.MORE
	 * 
	 */
	public void putContentView(int layoutId, int pageTitle, int tab) {

		Boolean noTitle = pageTitle <= 0;

		mCustomTitleManager = new CustomTitleManager(this, noTitle);
		setContentView(layoutId);
		if (!noTitle) {
			mCustomTitleManager.setUp();
			mCustomTitleManager.setTitle(pageTitle);
		}

		mCurrentSelectedTab = tab;

		if (mCurrentSelectedTab != NavigationHelper.NONE) {

			mNavigationHelper.setNavigationActionEvent();
			mNavigationHelper.setSelectedNavigationTab(mCurrentSelectedTab);
		} else {
		}
	}

	public void putContentView(int layoutId, String pageTitle) {

		putContentView(layoutId, pageTitle, NavigationHelper.DEFAULT);
	}

	public void putContentView(int layoutId, int pageTitle) {

		putContentView(layoutId, pageTitle, NavigationHelper.DEFAULT);
	}

	public void setPageTitle(int pageTitle) {

		if (mCustomTitleManager != null) {
			mCustomTitleManager.setTitle(pageTitle);
		}
	}

	public void setPageTitle(String pageTitle) {

		if (mCustomTitleManager != null) {
			mCustomTitleManager.setTitle(pageTitle);
		}
	}

	public void showBackButton() {
		if (mCustomTitleManager != null) {
			mCustomTitleManager.showBackButton(true);
		}
	}

	public LinearLayout showRightNormalButton(String title,
			View.OnClickListener listener) {
		if (mCustomTitleManager != null) {
			return mCustomTitleManager.showRightNormalButton(true, title,
					listener);
		}
		return null;
	}

	public LinearLayout showRightIconButton(int drawable,
			View.OnClickListener listener) {
		if (mCustomTitleManager != null) {
			return mCustomTitleManager.showRightIconButton(true, drawable,
					listener);
		}
		return null;
	}

	public ImageButton getRightIconButton() {
		if (mCustomTitleManager != null) {
			return mCustomTitleManager.getRightIconButton();
		}
		return null;
	}



	@Override
	protected void onRestart() {
		super.onRestart();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
//		if (VersionUtil.getInstance().IsUpdate()) {
//			IntentUtil.redirectToNextActivity(this, VersionActivity.class);
//		}
		super.onResume();
	}

	@Override
	protected void onDestroy() {

		super.onDestroy();
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

	public void showError(ResultData<?> resultData){
		if(resultData.isSuccess()){
			return;
		}
		String errorMsg = resultData.getMessage();
		if(!StringUtil.isEmpty(errorMsg)){
			MyToast.show(this, errorMsg);
		}
		else{
			errorMsg = getString(R.string.common_error_message);
			MyToast.show(this, errorMsg);
		}
	}
	
	public void showMsg(String msg){
		MyToast.show(this, msg);
	}
	
	public void showMsg(int resid){
		String msg= getString(resid);
		MyToast.show(this, msg);
	}
	
	public void showMsg(int resid, Object... params){
		String msg= getString(resid, params);
		MyToast.show(this, msg);
	}
}
package com.aspire.mandou.framework.widget;

import com.aspire.mandou.activity.LoginActivity;
import com.aspire.mandou.entity.customer.CustomerInfo;
import com.aspire.mandou.util.CustomerUtil;
import com.example.mandou.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NavigationHelper {

	public static final int HOME = 10;
	public static final int SEARCH = 11;
	public static final int CATEGORY = 12;
	public static final int CART = 13;
	public static final int MORE = 14;
	/**
	 * the page has navigation bar ,but no tab selected.
	 */
	public static final int DEFAULT = 0;
	/**
	 * the page has no navigation bar.
	 */
	public static final int NONE = -1;
	
	private Activity mActivity;
		
	private int mItemTextColor;
	private int mItemTextPressedColor;
	private int mItemLinePressedColor;;
	private int mItemLineDefaultColor;
	
	private Button mHomeActionView;
	private Button mSearchActionView;
	private Button mCategoryActionView;
	private Button mCartActionView;
	private Button mMoreActionView;
	
	private Button mPreviousSelectedActionView;
	
	public NavigationHelper(Activity activity){
		
		mActivity = activity;
	}

	public void setNavigationActionEvent(){
		mItemTextColor = mActivity.getResources().getColor(R.color.menu_gray);
		mItemTextPressedColor = mActivity.getResources().getColor(R.color.btn_orange);
		mItemLinePressedColor = mActivity.getResources().getColor(R.color.transparent);
		mItemLineDefaultColor = mActivity.getResources().getColor(R.color.transparent);
		
		mHomeActionView = (Button)mActivity.findViewById(R.id.navigation_bar_item_home);
		mSearchActionView = (Button)mActivity.findViewById(R.id.navigation_bar_item_search);
		mCategoryActionView = (Button)mActivity.findViewById(R.id.navigation_bar_item_category);
		mCartActionView = (Button)mActivity.findViewById(R.id.navigation_bar_item_cart);
		mMoreActionView = (Button)mActivity.findViewById(R.id.navigation_bar_item_more);
		
		ActionEventResponser actionEventResponser = new ActionEventResponser(mActivity);
		
		if (mHomeActionView != null) {	
			mHomeActionView.setOnClickListener(actionEventResponser);
		}
		if (mSearchActionView != null) {			
			mSearchActionView.setOnClickListener(actionEventResponser);
		}
		if (mCategoryActionView != null) {	
			mCategoryActionView.setOnClickListener(actionEventResponser);
		}
		if (mCartActionView != null) {	
			mCartActionView.setOnClickListener(actionEventResponser);
		}
		if (mMoreActionView != null) {
			mMoreActionView.setOnClickListener(actionEventResponser);
		}
	}
	
	/**
	 * Set current selected navigation tab
	 * 
	 * @param tab 
	 *   NavigationHelper.HOME,NavigationHelper.CATEGORY,
	 *   NavigationHelper.SEARCH,NavigationHelper.CART
	 *   NavigationHelper.MORE,NavigationHelper.DEFAULT
	 */
	public void setSelectedNavigationTab(int tab){
		
		restoreViewState();
		
		switch (tab) {
		
			case HOME:
				
				mActivity.findViewById(R.id.navigation_bar_item_home_line).setBackgroundColor(mItemLinePressedColor);
				
				mHomeActionView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.navigation_bar_home_hover, 0, 0);
				mHomeActionView.setTextColor(mItemTextPressedColor);
				mHomeActionView.setBackgroundResource(R.color.transparent);
				mHomeActionView.setClickable(false);
				break;
			case SEARCH:
	
				mActivity.findViewById(R.id.navigation_bar_item_search_line).setBackgroundColor(mItemLinePressedColor);
				
				mSearchActionView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.navigation_bar_search_hover, 0, 0);
				mSearchActionView.setTextColor(mItemTextPressedColor);
				mSearchActionView.setBackgroundResource(R.color.transparent);
				mSearchActionView.setClickable(false);
				break;
			case CATEGORY:
				
				mActivity.findViewById(R.id.navigation_bar_item_category_line).setBackgroundColor(mItemLinePressedColor);
				
				mCategoryActionView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.navigation_bar_category_hover, 0, 0);
				mCategoryActionView.setTextColor(mItemTextPressedColor);
				mCategoryActionView.setBackgroundResource(R.color.transparent);
				mCategoryActionView.setClickable(false);
				break;
			case CART:
				
				mActivity.findViewById(R.id.navigation_bar_item_cart_line).setBackgroundColor(mItemLinePressedColor);
				
				mCartActionView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.navigation_bar_cart_hover, 0, 0);
				mCartActionView.setTextColor(mItemTextPressedColor);
				mCartActionView.setBackgroundResource(R.color.transparent);
				mCartActionView.setClickable(false);
				break;
			case MORE:
				
				mActivity.findViewById(R.id.navigation_bar_item_more_line).setBackgroundColor(mItemLinePressedColor);
				
				mMoreActionView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.navigation_bar_more_hover, 0, 0);
				mMoreActionView.setTextColor(mItemTextPressedColor);
				mMoreActionView.setBackgroundResource(R.color.transparent);
				mMoreActionView.setClickable(false);
				break;
			default:
				break;
		}
	}
	
	private void restoreViewState(){
		
		if (mPreviousSelectedActionView != null) {
			
			switch (mPreviousSelectedActionView.getId()) {
			
			case R.id.navigation_bar_item_home:
				mActivity.findViewById(R.id.navigation_bar_item_home_line).setBackgroundColor(mItemLineDefaultColor);

				mPreviousSelectedActionView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.navigation_bar_home_normal, 0, 0);
				mPreviousSelectedActionView.setTextColor(mItemTextColor);
				mPreviousSelectedActionView.setBackgroundResource(R.color.transparent);
				break;

			case R.id.navigation_bar_item_search:
				mActivity.findViewById(R.id.navigation_bar_item_search_line).setBackgroundColor(mItemLineDefaultColor);
				
				mPreviousSelectedActionView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.navigation_bar_search_normal, 0, 0);
				mPreviousSelectedActionView.setTextColor(mItemTextColor);
				mPreviousSelectedActionView.setBackgroundResource(R.color.transparent);
				
				break;
			case R.id.navigation_bar_item_category:
				mActivity.findViewById(R.id.navigation_bar_item_category_line).setBackgroundColor(mItemLineDefaultColor);

				mPreviousSelectedActionView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.navigation_bar_category_normal, 0, 0);
				mPreviousSelectedActionView.setTextColor(mItemTextColor);
				mPreviousSelectedActionView.setBackgroundResource(R.color.transparent);
				
				break;
			case R.id.navigation_bar_item_cart:
				mActivity.findViewById(R.id.navigation_bar_item_cart_line).setBackgroundColor(mItemLineDefaultColor);
	
				mPreviousSelectedActionView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.navigation_bar_cart_normal, 0, 0);
				mPreviousSelectedActionView.setTextColor(mItemTextColor);
				mPreviousSelectedActionView.setBackgroundResource(R.color.transparent);
				
				break;
			case R.id.navigation_bar_item_more:
				mActivity.findViewById(R.id.navigation_bar_item_more_line).setBackgroundColor(mItemLineDefaultColor);
			
				mPreviousSelectedActionView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.navigation_bar_more_normal, 0, 0);
				mPreviousSelectedActionView.setTextColor(mItemTextColor);
				mPreviousSelectedActionView.setBackgroundResource(R.color.transparent);
				
				break;
			}
		}	
	}
	private class ActionEventResponser implements View.OnClickListener{
		
		private Activity mActivity;
	
		public ActionEventResponser(Activity mActivity){
			
			this.mActivity = mActivity;	
		}
		
		@Override
		public void onClick(View v) {
			
			Button button = (Button)v;
			restoreViewState();
			mPreviousSelectedActionView = button;
			
			switch (v.getId()) {
			
			case R.id.navigation_bar_item_home:
//				checkLogin(R.id.navigation_bar_item_home);
				break;
			case R.id.navigation_bar_item_search:
//				redirect(MostBargainActivity.class, Intent.FLAG_ACTIVITY_NO_ANIMATION, 0, 0);
				break;
			case R.id.navigation_bar_item_category:
//				redirect(ProductListActivity.class, Intent.FLAG_ACTIVITY_NO_ANIMATION, 0, 0);
				break;
			case R.id.navigation_bar_item_cart:
//				redirect(HelpActivity.class, Intent.FLAG_ACTIVITY_NO_ANIMATION, 0, 0);			
				break;
			case R.id.navigation_bar_item_more:
//				checkLogin(R.id.navigation_bar_item_more);
				break;
			}
		}
		
//		private void checkLogin(final int itemId) {
//			CustomerUtil.checkLogin(mActivity, LoginActivity.class, new OnLoginListener(new LoginCallback(){
//
//				@Override
//				public void OnLogined(CustomerInfo customer, Bundle bundle) {
//					switch (itemId) {
//					case R.id.navigation_bar_item_home:
//						redirect(MyPurseActivity.class, Intent.FLAG_ACTIVITY_NO_ANIMATION, 0, 0);
//						break;
//					case R.id.navigation_bar_item_more:
//						redirect(MyAccountActivity.class, Intent.FLAG_ACTIVITY_NO_ANIMATION, 0, 0);	
//						break;
//					default:
//						break;
//					}
//				}
//			}));
//		}
		
		/**
		 * redirect to target page
		 * 
		 * @param targetActivity
		 * 			target page
		 * @param flags
		 * 			for activity self parameter
		 * @param enterAnim
		 * 			0 if no need anim
		 * @param exitAnim
		 * 			0 if no need anim
		 */
		@SuppressWarnings("rawtypes")
		private void redirect(Class targetActivity,int flags,int enterAnim,int exitAnim){
			
			Intent intent = new Intent(mActivity,targetActivity);
			intent.setFlags(flags);
			mActivity.startActivity(intent);
			mActivity.overridePendingTransition(enterAnim, exitAnim);
		}
	}
}

package com.aspire.mandou.framework.widget;

import com.example.mandou.R;

import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomTitleManager {

	private Activity mActivity;

	private TextView mTitleTextView;
	private Boolean mNoTitle;
	private ImageButton mBackButton;
	private LinearLayout mRightNormalButtonLayout;
	private LinearLayout mRightIconButtonLayout;
	private ImageButton mRightIconImageButton;

	public CustomTitleManager(Activity activity, Boolean noTitle) {

		mActivity = activity;
		mNoTitle = noTitle;
		if (noTitle) {
			mActivity.requestWindowFeature(Window.FEATURE_NO_TITLE);
		} else {
			mActivity.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		}

	}

	public void setUp() {

		if (!mNoTitle) {
			mActivity.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
					R.layout.custom_title);

			mTitleTextView = (TextView) mActivity
					.findViewById(R.id.activity_title);
			mBackButton = (ImageButton)mActivity.findViewById(R.id.btn_back);
			mRightNormalButtonLayout =(LinearLayout)mActivity.findViewById(R.id.btn_right_normal);
			mRightIconButtonLayout =(LinearLayout)mActivity.findViewById(R.id.btn_right_icon);
			mRightIconImageButton =(ImageButton)mActivity.findViewById(R.id.btn_right_icon_btn);
		}
	}

	public void setTitle(String title) {

		if (mTitleTextView != null) {
			mTitleTextView.setText(title);
		}
	}

	public void setTitle(int title) {

		if (mTitleTextView != null) {
			mTitleTextView.setText(title);
		}
	}

	public void showBackButton(Boolean visible) {
		if(mBackButton != null){
			mBackButton.setVisibility(visible?View.VISIBLE:View.GONE);
		}
	}

	public LinearLayout showRightNormalButton(Boolean visible,String title,View.OnClickListener listener) {
		if(mRightNormalButtonLayout != null){
			mRightNormalButtonLayout.setVisibility(visible?View.VISIBLE:View.GONE);
			Button btnButton=(Button)mRightNormalButtonLayout.findViewById(R.id.btn_right_normal_btn);
			btnButton.setText(title);
			btnButton.setOnClickListener(listener);
		}
		return mRightNormalButtonLayout;
	}

	public LinearLayout showRightIconButton(Boolean visible,int drawable,View.OnClickListener listener) {
		if(mRightIconImageButton != null){
			mRightIconButtonLayout.setVisibility(visible?View.VISIBLE:View.GONE);
			mRightIconImageButton.setImageDrawable(mActivity.getResources().getDrawable(drawable));
			mRightIconImageButton.setOnClickListener(listener);
		}
		
		return mRightIconButtonLayout;
	}
	
	public ImageButton getRightIconButton() {
		return mRightIconImageButton;
	}
}

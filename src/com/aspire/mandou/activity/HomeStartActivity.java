package com.aspire.mandou.activity;


import com.aspire.mandou.listener.CountDownTimerListener;
import com.aspire.mandou.util.CountDownTimerUtil;
import com.aspire.mandou.util.CustomerUtil;
import com.aspire.mandou.util.IntentUtil;
import com.aspire.mandou.util.LoginStackUtil;
import com.example.mandou.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
public class HomeStartActivity extends Activity {
	
	private View mContent;
	private CountDownTimerUtil mCountDownTimerUtil;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mContent = new View(this);
		mContent.setBackgroundResource(R.drawable.start);
		setContentView(mContent);
		init();
	}
	
	private void init() {
//		VersionUtil.getInstance().checkVersionUpdate();
//		ExitAppUtil.isRemember();
		CustomerUtil.clearAuthenTick();
		CustomerUtil.cacheIsVisitorLogin(false);
		LoginStackUtil.clearAll();
		setCountDownTimer();
		mCountDownTimerUtil.start();
	}
	private void setCountDownTimer() {
		mCountDownTimerUtil=new CountDownTimerUtil(2000,1000,new CountDownTimerListener(){

			@Override
			public void onFinish() {
				IntentUtil.redirectToNextActivity(HomeStartActivity.this, LoginActivity.class);
				finish();
			}

			@Override
			public void onTick(long millisUntilFinished) {
				
			}
		});
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Bitmap bitmap = ((BitmapDrawable) mContent.getBackground()).getBitmap();
		bitmap = null;
		mContent = null;
		if (mCountDownTimerUtil!=null) {
			mCountDownTimerUtil.cancel();
			mCountDownTimerUtil=null;
		}
		System.gc();
	}
}

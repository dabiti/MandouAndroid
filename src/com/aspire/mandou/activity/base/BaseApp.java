package com.aspire.mandou.activity.base;

import com.aspire.mandou.framework.cache.MyFileCache;
import com.aspire.mandou.framework.http.BetterHttp;
import com.aspire.mandou.util.BitmapCacheUtil;
import com.aspire.mandou.util.ImageUrlUtil;
import com.example.mandou.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import android.app.Application;
import android.content.Context;

public class BaseApp extends Application  {
	public static String staticUnionCustomerNameKey="staticUnionCustomerNameKey";
	public static String staticDeviceIDKey="staticDeviceIDKey";
	
	private static String mUserAgent;

	private static BaseApp instance;

	private static int mNum=0;

	@Override
	public void onCreate() {
		super.onCreate();
		
		if (mNum==0) {
			instance = this;
			
			initImageLoader(this);
			MyFileCache.install(getApplicationContext());

			mUserAgent = getResources().getString(R.string.user_agent);

			setupBetterHttp();
			
			ImageUrlUtil.setContext(this);
			BitmapCacheUtil.setCacheRoot(this);
			
			mNum=mNum+1;
		}
		
	}

	public static BaseApp instance() {
		return instance;
	}
	
	@Override
	public void onTerminate() {
		super.onTerminate();
	}

	protected String getHighResolutionKey() {
		return "AndroidImgV1";
	}

	public static String getUserAgent() {
		return mUserAgent;
	}

	private void setupBetterHttp() {
		BetterHttp.setContext(this);
		BetterHttp.setupHttpClient();
		BetterHttp.setUserAgent(mUserAgent);
		String highResolutionKey = getHighResolutionKey();
		if (highResolutionKey != null) {
			BetterHttp.setDefaultHeader("X-HighResolution", highResolutionKey);
		}
		BetterHttp.enableGZIPEncoding();
	}

	private static void initImageLoader(Context context) {
		 ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
			.threadPriority(Thread.NORM_PRIORITY - 2)
			.denyCacheImageMultipleSizesInMemory()
			.discCacheFileNameGenerator(new Md5FileNameGenerator())
			.tasksProcessingOrder(QueueProcessingType.LIFO)
			.enableLogging() // Not necessary in common
			.build();
		 // Initialize ImageLoader with configuration.  
		 ImageLoader.getInstance().init(config);  
	}
}
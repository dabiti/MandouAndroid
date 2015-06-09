package com.aspire.mandou.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.aspire.mandou.framework.http.BetterHttp;
import com.aspire.mandou.framework.http.BetterHttpRequest;
import com.aspire.mandou.framework.http.BetterHttpResponse;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class BitmapCacheUtil {
	private static final int BITMAP_CAHCE_UTIL_WHAT=1;
	private static BitmapCacheUtil mBitmapCacheUtil;
	private Handler mHandler;
	private static String mCacheRoot;
	
	public BitmapCacheUtil() {
		setHandler();
	}
	
	public static void setCacheRoot(Context context){
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			mCacheRoot= Environment.getExternalStorageDirectory().getPath()+"/";
		}else {
			mCacheRoot= context.getCacheDir().getPath()+"/";
		}
	}
	
	public static BitmapCacheUtil getInstance(){
		if (mBitmapCacheUtil==null) {
			mBitmapCacheUtil=new BitmapCacheUtil();
		}
		
		return mBitmapCacheUtil;
	}
	
	private void setHandler() {
		mHandler=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case BITMAP_CAHCE_UTIL_WHAT:
					BitmapCacheData cacheData=(BitmapCacheData)msg.obj;
					if (cacheData!=null&&cacheData.getBitmap()!=null) {
						if (cacheData.getIsRound()) {
							Bitmap bitmap=BitmapUtil.toRoundBitmap(cacheData.getBitmap());
							putBitmap(cacheData.getPath(), bitmap,true);
						}else {
							putBitmap(cacheData.getPath(), cacheData.getBitmap(),false);
						}
					}
					break;
				default:
					break;
				}
			}
		};
	}
	
	public void execute(final String path){
		execute(path,false);
	}
	
	public void execute(final String path,final boolean isRound) {
		if (path!=null&&!"".equals(path.trim())) {
			Bitmap bitmap=getBitmap(path.trim());
			if (bitmap==null) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							BetterHttpRequest request = BetterHttp.get(path);
							BetterHttpResponse response = request.send();
							int statusCode = response.getStatusCode();

							if (statusCode==200) {
								InputStream inputStream = response.getResponseBody();
		                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream); 
		                        if (bitmap!=null) {
		                        	BitmapCacheData cacheData=new BitmapCacheData();
		                        	cacheData.setPath(path.trim());
		                        	cacheData.setBitmap(bitmap);
		                        	cacheData.setIsRound(isRound);
		                        	Message msg=new Message();
				                    msg.what=BITMAP_CAHCE_UTIL_WHAT;
				                    msg.obj=cacheData;
				                    mHandler.sendMessage(msg);
								}
							}
						} catch (MalformedURLException e1) {
						}catch (IOException e) {
						}catch(Exception e2){
						}
						
					}
				}).start();
			}
		}
	}
		
	public void removeBitmap(final String path) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				String fileName=getFileName(path);
				if (fileName!=null&&!"".equals(fileName)){
					File file = new File(mCacheRoot, fileName);
					if (!file.delete()) {
						file.deleteOnExit();
					}
				}
			}
		}).start();
	}
	
	public void putBitmap(String path,Bitmap bitmap,boolean isRound) {
		String fileName=getFileName(path);
		if (fileName!=null&&!"".equals(fileName)&&bitmap!=null) {
			File file = new File(mCacheRoot, fileName);
			FileOutputStream stream = null;
			ObjectOutputStream objectStream = null;
			try {
				file.createNewFile();
				stream = new FileOutputStream(file);
				if (isRound) {
					bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
				}else {
					bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
				}
			} catch (FileNotFoundException ex) {
			} catch (IOException ex) {
			} finally {
				if (objectStream != null) {
					try {
						objectStream.close();
					} catch (IOException ex) {
					}
				}
				if (stream != null) {
					try {
						stream.close();
					} catch (IOException ex) {
					}
				}
			}
		}
	}
	
	public Bitmap getBitmap(String path) {
		String fileName=getFileName(path);
		if (fileName!=null&&!"".equals(fileName)) {
			File file = new File(mCacheRoot,fileName);
			if (file.exists()) {
				FileInputStream stream = null;
				try {
					stream = new FileInputStream(file);
					Bitmap bitmap=BitmapFactory.decodeStream(stream);
					return  bitmap;
				} catch (FileNotFoundException ex) {
				} catch (IOException ex) {
				}finally {
					if (stream != null) {
						try {
							stream.close();
						} catch (IOException ex) {
						}
					}
				}
			}
		}
	
		return null;
	}
	
	private String getFileName(String path) {
		if (path!=null&&!"".equals(path.trim())) {
			File file=new File(path.trim());
			String fileName=file.getName();
			return fileName!=null?fileName.trim().toLowerCase():null;
		}
		
		return null;
	}
	
	public class BitmapCacheData{
		private String mPath;
		private Bitmap mBitmap;
		private boolean mIsRound;
		public String getPath() {
			return mPath;
		}
		public void setPath(String path) {
			this.mPath = path;
		}
		public Bitmap getBitmap() {
			return mBitmap;
		}
		public void setBitmap(Bitmap bitmap) {
			this.mBitmap = bitmap;
		}
		public boolean getIsRound() {
			return mIsRound;
		}
		public void setIsRound(boolean isRound) {
			this.mIsRound = isRound;
		}
	}
}

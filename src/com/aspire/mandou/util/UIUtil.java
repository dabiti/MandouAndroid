package com.aspire.mandou.util;

import com.aspire.mandou.activity.base.BaseApp;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class UIUtil {
	
	/**
	 * 隐藏软键盘
	 */
	public static void hideSoftInput(EditText editText) {
		((InputMethodManager)BaseApp.instance().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(editText.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
	}
}

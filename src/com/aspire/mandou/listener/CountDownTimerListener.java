package com.aspire.mandou.listener;

public interface CountDownTimerListener {
	void onFinish();
	void onTick(long millisUntilFinished);
}

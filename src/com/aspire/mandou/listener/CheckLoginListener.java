package com.aspire.mandou.listener;

import com.aspire.mandou.entity.customer.CustomerInfo;
import android.os.Bundle;
import android.os.Parcelable;

public interface CheckLoginListener extends Parcelable {
	public void OnLogined(CustomerInfo customer, Bundle bundle);
}

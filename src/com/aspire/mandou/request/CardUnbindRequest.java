package com.aspire.mandou.request;

import java.io.Serializable;

import com.neweggcn.lib.json.annotations.SerializedName;

public class CardUnbindRequest implements Serializable {
	
	private static final long serialVersionUID = 7642268056234799898L;

	@SerializedName("CreditCardSysNo")
	private int CardSysNo;
	
	@SerializedName("CreditCardNo")
	private String CardNo;

	public int getCardSysNo() {
		return CardSysNo;
	}

	public void setCardSysNo(int cardSysNo) {
		CardSysNo = cardSysNo;
	}

	public String getCardNo() {
		return CardNo;
	}

	public void setCardNo(String cardNo) {
		CardNo = cardNo;
	}
	
	
}

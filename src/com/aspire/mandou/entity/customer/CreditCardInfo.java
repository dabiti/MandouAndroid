package com.aspire.mandou.entity.customer;

import com.neweggcn.lib.json.annotations.SerializedName;

public class CreditCardInfo {
	@SerializedName("SysNo")
	public int SysNo;
	@SerializedName("BankName")
	public String BankName;
	@SerializedName("CardNumber")
	public String CardNumber;

	public int getSysNo() {
		return SysNo;
	}

	public void setSysNo(int sysNo) {
		SysNo = sysNo;
	}

	public String getBankName() {
		return BankName;
	}

	public void setBankName(String bankName) {
		BankName = bankName;
	}

	public String getCardNumber() {
		return CardNumber;
	}

	public void setCardNumber(String cardNumber) {
		CardNumber = cardNumber;
	}

}

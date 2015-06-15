package com.aspire.mandou.request;

import java.io.Serializable;

import com.neweggcn.lib.json.annotations.SerializedName;

public class CardBindRequest implements Serializable {
	private static final long serialVersionUID = 6955657844320924159L;
	
	@SerializedName("BankSysNo")
	private int BankSysNo;
	
	@SerializedName("CardNo")
	private String CardNo;
	
	@SerializedName("IDNumber")
	private String IDNumber;
	
	@SerializedName("CardOwnerName")
	private String CardOwnerName;
	
	@SerializedName("CardOwnerCellPhone")
	private String CardOwnerCellPhone;
	
	public int getBankSysNo() {
		return BankSysNo;
	}

	public void setBankSysNo(int bankSysNo) {
		BankSysNo = bankSysNo;
	}

	public String getCardNo() {
		return CardNo;
	}

	public void setCardNo(String cardNo) {
		CardNo = cardNo;
	}

	public String getIDNumber() {
		return IDNumber;
	}

	public void setIDNumber(String iDNumber) {
		IDNumber = iDNumber;
	}

	public String getCardOwnerName() {
		return CardOwnerName;
	}

	public void setCardOwnerName(String cardOwnerName) {
		CardOwnerName = cardOwnerName;
	}

	public String getCardOwnerCellPhone() {
		return CardOwnerCellPhone;
	}

	public void setCardOwnerCellPhone(String cardOwnerCellPhone) {
		CardOwnerCellPhone = cardOwnerCellPhone;
	}
	
	
}

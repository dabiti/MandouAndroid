package com.aspire.mandou.entity.customer;

import java.io.Serializable;

import com.neweggcn.lib.json.annotations.SerializedName;

public class CustomerInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5328321004877325242L;
	
	@SerializedName("ImageUrl")
	private String ImageUrl;
	@SerializedName("CustomerName")
	private String CustomerName;
	@SerializedName("NickName")
	private String NickName;
	@SerializedName("Mobile")
	private String Mobile;
	@SerializedName("IsMobileConfirmed")
	private boolean IsMobileConfirmed;
	@SerializedName("Email")
	private String Email;
	@SerializedName("IsEmailConfirmed")
	private boolean IsEmailConfirmed;
	@SerializedName("HavePayPassword")
	private boolean HavePayPassword;
	@SerializedName("IsRemember")
	private boolean IsRemember;
	public String getImageUrl() {
		return ImageUrl;
	}
	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}
	public String getCustomerName() {
		return CustomerName;
	}
	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}
	public String getNickName() {
		return NickName;
	}
	public void setNickName(String nickName) {
		NickName = nickName;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public boolean getIsMobileConfirmed() {
		return IsMobileConfirmed;
	}
	public void setIsMobileConfirmed(boolean isMobileConfirmed) {
		IsMobileConfirmed = isMobileConfirmed;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public boolean getIsEmailConfirmed() {
		return IsEmailConfirmed;
	}
	public void setIsEmailConfirmed(boolean isEmailConfirmed) {
		IsEmailConfirmed = isEmailConfirmed;
	}
	public boolean isHavePayPassword() {
		return HavePayPassword;
	}
	public void setHavePayPassword(boolean havePayPassword) {
		HavePayPassword = havePayPassword;
	}
	public boolean getIsRemember() {
		return IsRemember;
	}
	public void setIsRemember(boolean isRemember) {
		IsRemember = isRemember;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}

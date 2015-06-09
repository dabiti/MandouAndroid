package com.aspire.mandou.entity.customer;

import java.io.Serializable;

import com.neweggcn.lib.json.annotations.SerializedName;

public class RegisterInfo implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 3716483904354251106L;

	@SerializedName("CustomerID")
	private String CustomerID ;
	@SerializedName("Password")
    private String Password ;
	@SerializedName("RePassword")
    private String RePassword ;
	@SerializedName("CustomerName")
    private String CustomerName ;
	@SerializedName("Gender")
    private int Gender ;
	@SerializedName("Email")
    private String Email ;
	@SerializedName("ValidatedCode")
    private String ValidatedCode ;
	@SerializedName("SequenceCode")
    private int SequenceCode ;
	@SerializedName("Mobile")
    private String Mobile ;
	@SerializedName("RegisterType")
    private int RegisterType ;
	private int CustomerType;
	public String getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getRePassword() {
		return RePassword;
	}
	public void setRePassword(String rePassword) {
		RePassword = rePassword;
	}
	public String getCustomerName() {
		return CustomerName;
	}
	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}
	public int getGender() {
		return Gender;
	}
	public void setGender(int gender) {
		Gender = gender;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	/**
	 * 验证码
	 * @return
	 */
	public String getValidatedCode() {
		return ValidatedCode;
	}
	/**
	 * 验证码
	 * @param validatedCode
	 */
	public void setValidatedCode(String validatedCode) {
		ValidatedCode = validatedCode;
	}
	/**
	 * 短信顺序码
	 * @return
	 */
	public int getSequenceCode() {
		return SequenceCode;
	}
	/**
	 * 短信顺序码
	 * @param sequenceCode
	 */
	public void setSequenceCode(int sequenceCode) {
		SequenceCode = sequenceCode;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	/**
	 * 注册类型 0-手机 1-邮箱
	 * @return
	 */
	public int getRegisterType() {
		return RegisterType;
	}
	/**
	 * 注册类型 0-手机 1-邮箱
	 * @param registerType
	 */
	public void setRegisterType(int registerType) {
		RegisterType = registerType;
	}
	public int getCustomerType() {
		return CustomerType;
	}
	public void setCustomerType(int customerType) {
		CustomerType = customerType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}

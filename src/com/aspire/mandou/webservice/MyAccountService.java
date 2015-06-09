package com.aspire.mandou.webservice;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URLEncoder;

import android.net.Uri;

import com.neweggcn.lib.json.Gson;
import com.neweggcn.lib.json.reflect.TypeToken;
import com.aspire.mandou.entity.ResultData;
import com.aspire.mandou.entity.customer.CustomerInfo;
import com.aspire.mandou.entity.customer.RegisterInfo;

public class MyAccountService extends BaseService {
	
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ServiceException
	 */
	public ResultData<CustomerInfo> login(String username, String password) throws MalformedURLException, IOException, ServiceException{
		Uri.Builder b = Uri.parse(RESTFUL_SERVICE_HOST_SSL).buildUpon();
		b.path("Sinopec_Oil/test.do");
		String url = b.build().toString();
		username = URLEncoder.encode(username, "utf-8");
		password = URLEncoder.encode(password, "utf-8");
		
		String json=loginPost(url, "CustomerID="+username+"&Password="+password);
		Type type = new TypeToken<ResultData<CustomerInfo>>() {}.getType();
		
		ResultData<CustomerInfo> resultData=new Gson().fromJson(json, type);
		
		return resultData;
	}
	
	
	/**
	 * 发送验证码
	 * @param mobile
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ServiceException
	 */
	public ResultData<Integer> sendSMSValidateCode(String mobile) throws MalformedURLException, IOException, ServiceException{
		Uri.Builder b = Uri.parse(RESTFUL_SERVICE_HOST_SSL).buildUpon();
		b.path("Api/Customer/SendSMSValidateCode");
		b.appendQueryParameter("mobile", mobile);
		String url = b.build().toString();
		
		String json=read(url);
		Type type = new TypeToken<ResultData<Integer>>() {}.getType();
		return new Gson().fromJson(json, type);
	}
	
	/**
	 * 发送邮箱验证码
	 * @param mobile
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ServiceException
	 */
	public ResultData<Integer> sendEmailValidateCode(String email) throws MalformedURLException, IOException, ServiceException{
		Uri.Builder b = Uri.parse(RESTFUL_SERVICE_HOST_SSL).buildUpon();
		b.path("Api/Customer/SendEmailValidateCode");
		b.appendQueryParameter("email", email);
		String url = b.build().toString();
		
		String json=read(url);
		Type type = new TypeToken<ResultData<Integer>>() {}.getType();
		return new Gson().fromJson(json, type);
	}
	
	/**
	 * 注册
	 * @param info
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ServiceException
	 */
	public ResultData<CustomerInfo> register(RegisterInfo info) throws MalformedURLException, IOException, ServiceException{
		Uri.Builder b = Uri.parse(RESTFUL_SERVICE_HOST_SSL).buildUpon();
		b.path("Sinopec_Oil/register.do");
		String url = b.build().toString();
		
		Gson gson=new Gson();
		String json=createRegister(url, info.toString());
		Type type = new TypeToken<ResultData<CustomerInfo>>() {}.getType();
		return new Gson().fromJson(json, type);
	}
	
	
	/**
	 * 发送找回密码手机验证码
	 * @param mobile
	 * @param customerId
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ServiceException
	 */
	public ResultData<Integer> SendMobileVerifyCode4GetBackPassword(String mobile,String customerId) throws MalformedURLException, IOException, ServiceException{
		Uri.Builder b = Uri.parse(RESTFUL_SERVICE_HOST_SSL).buildUpon();
		b.path("Api/Customer/SendMobileVerifyCode4GetBackPassword");
		b.appendQueryParameter("customerid", customerId);
		b.appendQueryParameter("mobile", mobile);
		String url = b.build().toString();
		
		String json=read(url);
		Type type = new TypeToken<ResultData<Integer>>() {}.getType();
		return new Gson().fromJson(json, type);
	}
	
	/**
	 * 发送找回密码邮箱验证码
	 * @param mobile
	 * @param customerId
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ServiceException
	 */
	public ResultData<Integer> SendEmailVerifyCode4GetBackPassword(String email,String customerId) throws MalformedURLException, IOException, ServiceException{
		Uri.Builder b = Uri.parse(RESTFUL_SERVICE_HOST_SSL).buildUpon();
		b.path("Api/Customer/SendEmailVerifyCode4GetBackPassword");
		b.appendQueryParameter("customerid", customerId);
		b.appendQueryParameter("email", email);
		String url = b.build().toString();
		
		String json=read(url);
		Type type = new TypeToken<ResultData<Integer>>() {}.getType();
		return new Gson().fromJson(json, type);
	}
	
	/**
	 * 手机修改密码
	 * @param customerId
	 * @param phone
	 * @param password
	 * @param validatedCode
	 * @param sequenceCode
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ServiceException
	 */
	public ResultData<Integer> UpdatePasswordWithMobileVerifyCode(String customerId,String phone,String password,String validatedCode,int sequenceCode) throws MalformedURLException, IOException, ServiceException{
		Uri.Builder b = Uri.parse(RESTFUL_SERVICE_HOST_SSL).buildUpon();
		b.path("Api/Customer/UpdatePasswordWithMobileVerifyCode");
		b.appendQueryParameter("sequenceCode",String.valueOf(sequenceCode));
		b.appendQueryParameter("mobile", phone);
		b.appendQueryParameter("validatedcode", validatedCode);
		b.appendQueryParameter("customerid", customerId);
		b.appendQueryParameter("newpassword", password);
		String url = b.build().toString();
		
		String json=read(url);
		Type type = new TypeToken<ResultData<Integer>>() {}.getType();
		return new Gson().fromJson(json, type);
	}
	
	/**
	 * 邮箱修改密码
	 * @param customerId
	 * @param email
	 * @param password
	 * @param validatedCode
	 * @param sequenceCode
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws ServiceException
	 */
	public ResultData<Integer> UpdatePasswordWithEmailVerifyCode(String customerId,String email,String password,String validatedCode,int sequenceCode) throws MalformedURLException, IOException, ServiceException{
		Uri.Builder b = Uri.parse(RESTFUL_SERVICE_HOST_SSL).buildUpon();
		b.path("Api/Customer/UpdatePasswordWithEmailVerifyCode");
		b.appendQueryParameter("sequenceCode",String.valueOf(sequenceCode));
		b.appendQueryParameter("email", email);
		b.appendQueryParameter("validatedcode", validatedCode);
		b.appendQueryParameter("customerid", customerId);
		b.appendQueryParameter("newpassword", password);
		String url = b.build().toString();
		
		String json=read(url);
		Type type = new TypeToken<ResultData<Integer>>() {}.getType();
		return new Gson().fromJson(json, type);
	}
}

package com.aspire.mandou.webservice;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URLEncoder;

import android.net.Uri;

import com.aspire.mandou.entity.ResultData;
import com.aspire.mandou.entity.customer.CustomerInfo;
import com.aspire.mandou.request.CardBindRequest;
import com.neweggcn.lib.json.Gson;
import com.neweggcn.lib.json.reflect.TypeToken;

public class MyAccountService_Part2 extends BaseService {
	/**
	 * 我的帐户信息
	 */
	public ResultData<CustomerInfo> getMyInfo() throws MalformedURLException,
			IOException, ServiceException {
		Uri.Builder b = Uri.parse(RESTFUL_SERVICE_HOST_SSL).buildUpon();
		b.path("Api/Customer/UserInfo");

		String url = b.build().toString();
		Gson gson = new Gson();
		String jsonString = read(url);
		Type type = new TypeToken<ResultData<CustomerInfo>>() {
		}.getType();

		ResultData<CustomerInfo> resultData = gson.fromJson(jsonString, type);

		return resultData;
	}

//	/**
//	 * 我的-积分交易记录
//	 */
//	public ResultData<MyTransactionList> getMyTranctionList(int pageIndex,
//			int pageSize) throws MalformedURLException, IOException,
//			ServiceException {
//		Uri.Builder b = Uri.parse(RESTFUL_SERVICE_HOST_SSL).buildUpon();
//		b.path("Api/Customer/QueryTransactionRecords");
//		b.appendQueryParameter("pageIndex", String.valueOf(pageIndex));
//		b.appendQueryParameter("pageSize", String.valueOf(pageSize));
//
//		String url = b.build().toString();
//		Gson gson = new Gson();
//		String jsonString = read(url);
//		Type type = new TypeToken<ResultData<MyTransactionList>>() {
//		}.getType();
//
//		ResultData<MyTransactionList> resultData = gson.fromJson(jsonString,
//				type);
//
//		return resultData;
//	}
//
//	/**
//	 * 合作商户列表
//	 */
//	public ResultData<MyPartnerList> getMyPartnerList()
//			throws MalformedURLException, IOException, ServiceException {
//		Uri.Builder b = Uri.parse(RESTFUL_SERVICE_HOST_SSL).buildUpon();
//		b.path("Api/PayType/List");
//
//		String url = b.build().toString();
//
//		String json = read(url);
//		Type type = new TypeToken<ResultData<MyPartnerList>>() {
//		}.getType();
//		return new Gson().fromJson(json, type);
//	}
//
//	/**
//	 * 修改登录密码
//	 * 
//	 * @param info
//	 */
//	public ResultData<Integer> modifyLoginPwd(ModifyLoginPwdRequest info)
//			throws MalformedURLException, IOException, ServiceException {
//		Uri.Builder b = Uri.parse(RESTFUL_SERVICE_HOST_SSL).buildUpon();
//		b.path("Api/Customer/UpdatePassword");
//		String url = b.build().toString();
//
//		Gson gson = new Gson();
//		String json = create(url, gson.toJson(info));
//		Type type = new TypeToken<ResultData<Integer>>() {
//		}.getType();
//		return new Gson().fromJson(json, type);
//	}
//
//	/**
//	 * 修改交易密码
//	 * 
//	 * @param info
//	 */
//	public ResultData<Integer> modifyTradePwd(ModifyTradePwdRequest info)
//			throws MalformedURLException, IOException, ServiceException {
//		Uri.Builder b = Uri.parse(RESTFUL_SERVICE_HOST_SSL).buildUpon();
//		b.path("Api/Customer/UpdatePayPassword");
//		String url = b.build().toString();
//
//		Gson gson = new Gson();
//		String json = create(url, gson.toJson(info));
//		Type type = new TypeToken<ResultData<Integer>>() {
//		}.getType();
//		return new Gson().fromJson(json, type);
//	}
//
//	/**
//	 * 上传文件到文件服务器。
//	 */
//	public ResultData<String> upload(String filePath) throws Exception {
//
//		Uri.Builder b = Uri.parse(RESTFUL_SERVICE_HOST_SSL).buildUpon();
//		b.path("Api/Customer/UploadAvatar");
//		String url = b.build().toString();
//		MultipartEntity entity = new MultipartEntity(
//				HttpMultipartMode.BROWSER_COMPATIBLE);
//
//		entity.addPart("action", new StringBody(""));
//		entity.addPart("filekey", new StringBody("uploadfile"));
//		entity.addPart("uploadfile", new FileBody(new File(filePath)));
//
//		String jsonString = create(url, entity);
//
//		Type type = new TypeToken<ResultData<String>>() {
//		}.getType();
//		Gson gson = new Gson();
//		return gson.fromJson(jsonString, type);
//	}
//	
	/**
	 * 绑定信用卡
	 * 
	 */
	public ResultData<CardBindRequest> bindCreditCard(CardBindRequest info)
			throws MalformedURLException, IOException, ServiceException {
		Uri.Builder b = Uri.parse(RESTFUL_SERVICE_HOST_SSL).buildUpon();
		b.path("Api/Customer/BindCustomerCreditCardInfo");
		String url = b.build().toString();

		Gson gson = new Gson();
		String json = create(url, gson.toJson(info));
		Type type = new TypeToken<ResultData<CardBindRequest>>() {
		}.getType();
		return new Gson().fromJson(json, type);
	}
//	
//	/**
//	 * 解绑信用卡
//	 * 
//	 */
//	public ResultData<Integer> bindCreditCard(CardUnbindRequest info)
//			throws MalformedURLException, IOException, ServiceException {
//		Uri.Builder b = Uri.parse(RESTFUL_SERVICE_HOST_SSL).buildUpon();
//		b.path("Api/Customer/UnBindCustomerCreditCardInfo");
//		String url = b.build().toString();
//
//		Gson gson = new Gson();
//		String json = create(url, gson.toJson(info));
//		Type type = new TypeToken<ResultData<Integer>>() {
//		}.getType();
//		return new Gson().fromJson(json, type);
//	}
//
//	/**
//	 * 更新用户昵称
//	 * 
//	 */
//	public ResultData<Integer> updateNickName(String nickName)
//			throws MalformedURLException, IOException, ServiceException {
//		Uri.Builder b = Uri.parse(RESTFUL_SERVICE_HOST_SSL).buildUpon();
//		b.path("Api/Customer/UpdateNickName");
//		b.appendQueryParameter("nickname", nickName);
//		String url = b.build().toString();
//
//		String json = read(url);
//		Type type = new TypeToken<ResultData<Integer>>() {
//		}.getType();
//		return new Gson().fromJson(json, type);
//	}
//	
	/**
	 * 手机验证
	 * 
	 */
	public ResultData<Integer> validatePhone(int sequenceCode, String mobile, String validatedCode)
			throws MalformedURLException, IOException, ServiceException {
		Uri.Builder b = Uri.parse(RESTFUL_SERVICE_HOST_SSL).buildUpon();
		b.path("Api/Customer/ValidateCustomerMobile");
		b.appendQueryParameter("sequenceCode", String.valueOf(sequenceCode));
		b.appendQueryParameter("mobile", mobile);
		b.appendQueryParameter("validatedCode", validatedCode);
		String url = b.build().toString();

		String json = read(url);
		Type type = new TypeToken<ResultData<Integer>>() {
		}.getType();
		return new Gson().fromJson(json, type);
	}
//	
//	/**
//	 * 邮箱验证
//	 * 
//	 */
//	public ResultData<Integer> validateEmail(int sequenceCode, String email, String validatedCode)
//			throws MalformedURLException, IOException, ServiceException {
//		Uri.Builder b = Uri.parse(RESTFUL_SERVICE_HOST_SSL).buildUpon();
//		b.path("Api/Customer/ValidateEmail");
//		b.appendQueryParameter("sequenceCode", String.valueOf(sequenceCode));
//		b.appendQueryParameter("email", email);
//		b.appendQueryParameter("validatedCode", validatedCode);
//		String url = b.build().toString();
//
//		String json = read(url);
//		Type type = new TypeToken<ResultData<Integer>>() {
//		}.getType();
//		return new Gson().fromJson(json, type);
//	}
	
	/**
	 * 发送邮箱验证码
	 */
	public ResultData<Integer> sendEmailValidateCode(String email) throws MalformedURLException, IOException, ServiceException{
		Uri.Builder b = Uri.parse(RESTFUL_SERVICE_HOST_SSL).buildUpon();
		b.path("Api/Customer/SendValidateCustomerEmail");
		b.appendQueryParameter("Email", email);
		String url = b.build().toString();
		
		String json=read(url);
		Type type = new TypeToken<ResultData<Integer>>() {}.getType();
		return new Gson().fromJson(json, type);
	}
	
	/**
	 * 找回支付密碼
	 */
	public ResultData<Integer> getBackPayPassword(int isByEmail,String password,String validatedCode,int sequenceCode) throws MalformedURLException, IOException, ServiceException{
		Uri.Builder b = Uri.parse(RESTFUL_SERVICE_HOST_SSL).buildUpon();
		b.path("Api/Customer/UpdatePayPasswordWithVerifyCode");
		b.appendQueryParameter("sequenceCode",String.valueOf(sequenceCode));
		b.appendQueryParameter("validatedCode", validatedCode);
		b.appendQueryParameter("isemail", String.valueOf(isByEmail));
		b.appendQueryParameter("newpassword", password);
		String url = b.build().toString();
		
		String json=read(url);
		Type type = new TypeToken<ResultData<Integer>>() {}.getType();
		return new Gson().fromJson(json, type);
	}
	
	/**
	 * 提交反馈
	 */
	public ResultData<Integer> createFeedback(String content) throws MalformedURLException, IOException, ServiceException{
		Uri.Builder b = Uri.parse(RESTFUL_SERVICE_HOST_SSL).buildUpon();
		b.path("Api/Customer/CreateFeedBack");
		String url = b.build().toString();
		
		String json=create(url,"Content="+URLEncoder.encode(content, "utf-8"),true);
		Type type = new TypeToken<ResultData<Integer>>() {}.getType();
		return new Gson().fromJson(json, type);
	}
}

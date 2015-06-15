package com.aspire.mandou.webservice;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.util.List;

import android.net.Uri;

import com.aspire.mandou.entity.ResultData;
import com.aspire.mandou.entity.customer.CreditCardInfo;
import com.aspire.mandou.request.CardBindRequest;
import com.aspire.mandou.request.CardUnbindRequest;
import com.neweggcn.lib.json.Gson;
import com.neweggcn.lib.json.reflect.TypeToken;

public class WalletService extends BaseService {

	/**
	 * 满兜钱包-银行卡列表
	 */
	public ResultData<List<CreditCardInfo>> getMyCardList()
			throws MalformedURLException, IOException, ServiceException {
		Uri.Builder b = Uri.parse(RESTFUL_SERVICE_HOST_SSL).buildUpon();
		b.path("Api/Customer/GetCustomerBindCreditCardList");

		String url = b.build().toString();

		String jsonString = create(url, "");
		Type type = new TypeToken<ResultData<List<CreditCardInfo>>>() {
		}.getType();

		ResultData<List<CreditCardInfo>> resultData = new Gson().fromJson(
				jsonString, type);

		return resultData;
	}
	
//	/**
//	 * 满兜钱包-电子券列表
//	 */
//	public ResultData<OrderListInfo> getOrderListInfo(OrderFilter orderFilter) throws MalformedURLException, IOException, ServiceException{
//		Uri.Builder b = Uri.parse(RESTFUL_SERVICE_HOST_SSL).buildUpon();
//		b.path("Api/Order/OrderList");
//		
//		String url = b.build().toString();
//		Gson gson = new Gson();
//		String jsonString = create(url,gson.toJson(orderFilter));
//		Type type = new TypeToken<ResultData<OrderListInfo>>() {}.getType();
//		
//		ResultData<OrderListInfo> resultData=gson.fromJson(jsonString, type);
//		
//		return resultData;
//	}
//	
//	/**
//	 * 订单详情
//	 */
//	public ResultData<OrderDetailInfo> getOrderDetail(int sysNo) throws MalformedURLException, IOException, ServiceException{
//		Uri.Builder b = Uri.parse(RESTFUL_SERVICE_HOST_WWW).buildUpon();
//		b.path("Api/Order/OrderDetail");
//		b.appendQueryParameter("sosysno",String.valueOf(sysNo));
//		
//		String url = b.build().toString();
//		
//		String jsonString = read(url);
//		Type type = new TypeToken<ResultData<OrderDetailInfo>>() {}.getType();
//		
//		ResultData<OrderDetailInfo> resultData=new Gson().fromJson(jsonString, type);
//		
//		return resultData;
//	}
//	
//	/**
//	 * 满兜钱包-获取各银行积分数
//	 */
//	public ResultData<List<CreditCardPointResult>> getCardPointList(CreditCardPointFilter filter) throws MalformedURLException, IOException, ServiceException{
//		Uri.Builder b = Uri.parse(RESTFUL_SERVICE_HOST_SSL).buildUpon();
//		b.path("Api/Customer/GetCustomerBindCreditCardPointList");
//		
//		String url = b.build().toString();
//		Gson gson = new Gson();
//		String jsonString = create(url,gson.toJson(filter));
//		Type type = new TypeToken<ResultData<List<CreditCardPointResult>>>() {}.getType();
//		
//		ResultData<List<CreditCardPointResult>> resultData=gson.fromJson(jsonString, type);
//		
//		return resultData;
//	}
//	
	/**
	 * 满兜钱包-绑定银行
	 */
	public ResultData<CardBindRequest> bindCard(CardBindRequest req) throws MalformedURLException, IOException, ServiceException{
		Uri.Builder b = Uri.parse(RESTFUL_SERVICE_HOST_SSL).buildUpon();
		b.path("Api/Customer/BindCustomerCreditCardInfo");
		
		String url = b.build().toString();
		Gson gson = new Gson();
		String jsonString = create(url,gson.toJson(req));
		Type type = new TypeToken<ResultData<CardBindRequest>>() {}.getType();
		
		ResultData<CardBindRequest> resultData=gson.fromJson(jsonString, type);
		
		return resultData;
	}
	
	/**
	 * 满兜钱包-解绑银行
	 */
	public ResultData<CardUnbindRequest> unbindCard(CardUnbindRequest req) throws MalformedURLException, IOException, ServiceException{
		Uri.Builder b = Uri.parse(RESTFUL_SERVICE_HOST_SSL).buildUpon();
		b.path("Api/Customer/UnBindCustomerCreditCardInfo");
		
		String url = b.build().toString();
		Gson gson = new Gson();
		String jsonString = create(url,gson.toJson(req));
		Type type = new TypeToken<ResultData<CardUnbindRequest>>() {}.getType();
		
		ResultData<CardUnbindRequest> resultData=gson.fromJson(jsonString, type);
		
		return resultData;
	}
}

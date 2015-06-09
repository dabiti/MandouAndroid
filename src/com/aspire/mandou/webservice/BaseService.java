package com.aspire.mandou.webservice;

import java.io.IOException;
import java.net.MalformedURLException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;

import com.aspire.mandou.framework.http.BetterHttp;
import com.aspire.mandou.framework.http.BetterHttpRequest;
import com.aspire.mandou.framework.http.BetterHttpResponse;
import com.aspire.mandou.framework.http.JsonEntity;
import com.aspire.mandou.util.CustomerUtil;

public class BaseService {

	public static final String SUCCESS_CODE = "000";
	private static final String XNEWEGGAPPID_KEY="x-newegg-app-id";
	private static final String XNEWEGGAPPID_VALUE="test.com";

	public static final int EnvironmentType_LANTestRelease = 0;
	public static final int EnvironmentType_WWWTestRelease = 1;
	public static final int EnvironmentType_ProductionRelease = 2;
	
	public static final int CURRENTEN_ENVIRONMENT_TYPE = EnvironmentType_ProductionRelease;
	protected static final String URL_EXTENSION = ".egg";
	public static String RESTFUL_SERVICE_HOST_WWW = null;
	public static String RESTFUL_SERVICE_HOST_SSL = null;

	static {
		switch (CURRENTEN_ENVIRONMENT_TYPE) {

		case EnvironmentType_LANTestRelease:
			RESTFUL_SERVICE_HOST_WWW = "http://127.0.0.1:57838/";
			RESTFUL_SERVICE_HOST_SSL = "http://127.0.0.1:57838/";
			break;
		case EnvironmentType_WWWTestRelease: 	
			RESTFUL_SERVICE_HOST_WWW = "http://10.16.83.111:8099/";
			RESTFUL_SERVICE_HOST_SSL = "http://10.16.83.111:8099/";
			break;
		case EnvironmentType_ProductionRelease:
			RESTFUL_SERVICE_HOST_WWW = "http://192.168.12.100:8080/";
			RESTFUL_SERVICE_HOST_SSL = "http://192.168.12.100:8080/";
			break;
		}
	}

	protected static String read(String urlString) throws MalformedURLException, IOException, ServiceException {
		BetterHttpRequest request = BetterHttp.get(urlString);
		addAuthKeyHeader(request.unwrap());
		BetterHttpResponse response = request.send();

		int statusCode = response.getStatusCode();

		if (isSuccessful(statusCode)) {
			return response.getResponseBodyAsString();
		}

		throw new ServiceException(statusCode);
	}

	protected static String create(String urlString, String bodyString) throws MalformedURLException, IOException,
			ServiceException {
		return create(urlString, bodyString, false);
	}

	protected static String create(String urlString, String bodyString, boolean isForm) throws MalformedURLException,
			IOException, ServiceException {

		HttpEntity entity = null;
		BetterHttpRequest request = null;

		if (isForm) {
			// TODO: 研究下看是否可以用UrlEncodedFormEntity
			StringEntity stringEntity = new StringEntity(bodyString);
			stringEntity.setContentType("application/x-www-form-urlencoded");
			entity = stringEntity;
			request = BetterHttp.post(urlString, entity);
			request.unwrap().addHeader("Content-type", "application/x-www-form-urlencoded");
		} else {

			entity = new JsonEntity(bodyString);
			request = BetterHttp.post(urlString, entity);
		}
		addAuthKeyHeader(request.unwrap());
		BetterHttpResponse response = request.send();

		int statusCode = response.getStatusCode();

		if (isSuccessful(statusCode)) {
			return response.getResponseBodyAsString();
		}

		throw new ServiceException(statusCode);
	}
	
	protected static String create(String urlString, HttpEntity entity) throws MalformedURLException,
		IOException, ServiceException {
	
		BetterHttpRequest request = BetterHttp.post(urlString, entity);
		addAuthKeyHeader(request.unwrap());
		BetterHttpResponse response = request.send();
		
		int statusCode = response.getStatusCode();
		
		if (isSuccessful(statusCode)) {
			return response.getResponseBodyAsString();
		}
		
		throw new ServiceException(statusCode);
	}
	
	protected static String createRegister(String urlString, String bodyString) throws MalformedURLException,
		IOException, ServiceException {
		
		HttpEntity entity = null;
		BetterHttpRequest request = null;

		request = BetterHttp.post(urlString, entity);
		
		addAuthKeyHeader(request.unwrap());
		BetterHttpResponse response = request.send();

		int statusCode = response.getStatusCode();

		if (isSuccessful(statusCode)) {
			Header header=response.unwrap().getFirstHeader("Set-Cookie");
			if (header != null) {
				CustomerUtil.cacheAuthenTick(header.getValue());
			}
			return response.getResponseBodyAsString();
		}

		throw new ServiceException(statusCode);
	}

	protected static String loginPost(String urlString, String bodyString) throws MalformedURLException, IOException,
			ServiceException {
		HttpEntity entity = null;
		BetterHttpRequest request = null;
		
		StringEntity stringEntity = new StringEntity(bodyString);

		stringEntity.setContentType("application/x-www-form-urlencoded");
		entity = stringEntity;

		request = BetterHttp.post(urlString, entity);
		request.unwrap().addHeader(XNEWEGGAPPID_KEY, XNEWEGGAPPID_VALUE);
		BetterHttpResponse response = request.send();

		int statusCode = response.getStatusCode();
		if (isSuccessful(statusCode)) {
			Header header=response.unwrap().getFirstHeader("Set-Cookie");
			if (header != null) {
				CustomerUtil.cacheAuthenTick(header.getValue());
			}
			return response.getResponseBodyAsString();
		}

		throw new ServiceException(statusCode);
	}

	protected static String update(String urlString, String bodyString) throws MalformedURLException, IOException,
			ServiceException {
		return update(urlString, bodyString, false);
	}

	protected static String update(String urlString, String bodyString, boolean isForm) throws MalformedURLException,
			IOException, ServiceException {
		// TODO:
		// 虽然update和create都是post，但是他们有个标记:"connection.addRequestProperty("X-OP", "Update");"
		// 还是不一样，所以还需评估在这里是否可以直接调用create方法
		return create(urlString, bodyString, isForm);
	}

	protected static String delete(String urlString) throws MalformedURLException, IOException, ServiceException {
		BetterHttpRequest request = BetterHttp.delete(urlString);
		addAuthKeyHeader(request.unwrap());
		BetterHttpResponse response = request.send();

		int statusCode = response.getStatusCode();

		if (isSuccessful(statusCode)) {
			return response.getResponseBodyAsString();
		}

		throw new ServiceException(statusCode);
	}

	private static boolean isSuccessful(int statusCode) {
		return statusCode >= 200 && statusCode < 300;
	}

	private static void addAuthKeyHeader(HttpUriRequest request) {
		String authKey=CustomerUtil.getAuthenTick();
		if (authKey != null && authKey.length() > 0) {
			request.addHeader("Cookie", authKey);
		}
		request.addHeader(XNEWEGGAPPID_KEY, XNEWEGGAPPID_VALUE);
	}
}

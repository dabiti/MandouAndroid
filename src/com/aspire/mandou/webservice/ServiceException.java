package com.aspire.mandou.webservice;

/**
 * �Զ�����쳣����װHTTP��Response Code���ж��Ƿ���˻��ǿͻ����쳣��
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = -3693672283333003372L;

	private int responseCode;

	public ServiceException(int responseCode) {
		super();
		this.responseCode = responseCode;
	}

	/**
	 * ��ȡHTTP��Response Code��
	 * 
	 * @return HTTP��Response Code
	 */
	public int getResponseCode() {
		return responseCode;
	}

	/**
	 * ��ȡ��ǰ�Ƿ��Ƿ���˴���
	 * 
	 * @return {@code true}�����5XX����֮{@code false}��
	 */
	public boolean isServerError() {
		return responseCode >= 500 && responseCode < 600;
	}

	/**
	 * ��ȡ��ǰ�Ƿ��ǿͻ��˴���
	 * 
	 * @return {@code true}�����4XX����֮{@code false}��
	 */
	public boolean isClientError() {
		return responseCode >= 400 && responseCode < 500;
	}
}
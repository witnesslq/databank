/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay) 
 */
package com.yeepay.g3.app.databank.utils.result;

/**
 * <p>Title: Json 消息</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-3-21 下午3:55
 */
public class JsonMessageResult {

	private String status;

	private String message;

	public JsonMessageResult(String message) {
		this.status = "OK";
		this.message = message;
	}

	public JsonMessageResult(String status, String message) {
		this.status = status;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

}

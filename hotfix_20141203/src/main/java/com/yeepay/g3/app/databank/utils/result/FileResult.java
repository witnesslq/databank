/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.utils.result;

/**
 * <p>Title: </p>
 * <p>Description: 描述</p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-10-30 下午8:11
 */
public class FileResult {

	private String targetFileName;

	private long size;

	public FileResult() {
		// No thing
	}

	public FileResult(String targetFileName, long size) {
		this.targetFileName = targetFileName;
		this.size = size;
	}

	public String getTargetFileName() {
		return targetFileName;
	}

	public long getSize() {
		return size;
	}

}

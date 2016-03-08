/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.helper.stat;

import com.google.common.collect.Lists;
import com.yeepay.g3.utils.common.json.JSONUtils;

import java.util.List;

/**
 * <p>Title: Chart 专用数据视图</p>
 * <p>Description: 描述</p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-8-7 下午12:12
 */
public class DoubleDataView {

	private String type;

	private String name;

	private List<List<Double>> data = Lists.newArrayList();

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<List<Double>> getData() {
		return data;
	}

	public void setData(List<List<Double>> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return JSONUtils.toJsonString(this);
	}

}

/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.helper.stat;

import com.google.common.collect.Maps;
import com.yeepay.g3.utils.common.json.JSONUtils;

import java.util.List;
import java.util.Map;

/**
 * <p>Title: Chart 专用数据视图</p>
 * <p>Description: 描述</p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-8-7 下午12:12
 */
public class LongDataView {

	private Map<String, List<Object>> data = Maps.newHashMap();

	private Long maxValue;

	public Map<String, List<Object>> getData() {
		return data;
	}

	public void setData(Map<String, List<Object>> data) {
		this.data = data;
	}

	public Long getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Long maxValue) {
		this.maxValue = maxValue;
	}

	@Override
	public String toString() {
		return JSONUtils.toJsonString(this);
	}

}

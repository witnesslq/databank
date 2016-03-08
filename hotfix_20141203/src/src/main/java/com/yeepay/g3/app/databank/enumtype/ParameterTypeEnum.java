/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.enumtype;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * <p>Title: 参数类型枚举</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-4-7 下午17:59
 */
public enum ParameterTypeEnum {

	DATE("DATE", "Date"),
	LIST("LIST", "List"),
	QUERY("QUERY", "Query"),
	TEXT("TEXT", "Text"),
	SUB("SUB", "SubReport"),
	BOOL("BOOL", "Boolean"),
	OTHER("OTHER", "自定义");

	private static Map<String, ParameterTypeEnum> valueMap = Maps.newHashMap();

	private String value;
	private String displayName;

	static {
		for (ParameterTypeEnum item : ParameterTypeEnum.values()) {
			valueMap.put(item.value, item);
		}
	}

	ParameterTypeEnum(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	public static ParameterTypeEnum parse(String value) {
		return valueMap.get(value);
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}

}

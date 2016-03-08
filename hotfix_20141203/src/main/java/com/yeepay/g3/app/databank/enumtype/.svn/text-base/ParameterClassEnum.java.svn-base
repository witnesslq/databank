/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.enumtype;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * <p>Title: 类类型枚举</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-4-7 下午17:59
 */
public enum ParameterClassEnum {

	STRING("STRING", "java.lang.String"),
	DOUBLE("DOUBLE", "java.lang.Double"),
	INTEGER("INTEGER", "java.lang.Integer"),
	LONG("LONG", "java.lang.Long"),
	BOOLEAN("BOOLEAN", "java.lang.Boolean"),
	BIGDEC("BIGDEC", "java.math.BigDecimal"),
	DATEU("DATEU", "java.util.Date"),
	DATES("DATES", "java.sql.Date"),
	TIMESTAMP("TIMESTAMP", "java.sql.Timestamp");

	private static Map<String, ParameterClassEnum> valueMap = Maps.newHashMap();

	private String value;
	private String displayName;

	static {
		for (ParameterClassEnum item : ParameterClassEnum.values()) {
			valueMap.put(item.value, item);
		}
	}

	ParameterClassEnum(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	public static ParameterClassEnum parse(String value) {
		return valueMap.get(value);
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}

}

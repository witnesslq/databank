/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.enumtype;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * <p>Title: 数据库类型枚举</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-4-3 下午13:51
 */
public enum DatabaseTypeEnum {

	DB2("DB2", "DB2"),
	MYSQL("MYSQL", "MySQL"),
	H2("H2", "H2");

	private static Map<String, DatabaseTypeEnum> valueMap = Maps.newHashMap();

	private String value;
	private String displayName;

	static {
		for (DatabaseTypeEnum item : DatabaseTypeEnum.values()) {
			valueMap.put(item.value, item);
		}
	}

	DatabaseTypeEnum(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	public static DatabaseTypeEnum parse(String value) {
		return valueMap.get(value);
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}

	public static Map<String, DatabaseTypeEnum> getValueMap() {
		return valueMap;
	}

}

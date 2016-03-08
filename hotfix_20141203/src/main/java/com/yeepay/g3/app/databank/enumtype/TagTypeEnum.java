/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.enumtype;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * <p>Title: 标签类型枚举</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-4-8 下午14:43
 */
public enum TagTypeEnum {

	LINE("LINE", "产品线"),
	SYS("SYS", "系统内置"),
	USER("USER", "用户自定义");

	private static Map<String, TagTypeEnum> valueMap = Maps.newHashMap();

	private String value;
	private String displayName;

	static {
		for (TagTypeEnum item : TagTypeEnum.values()) {
			valueMap.put(item.value, item);
		}
	}

	TagTypeEnum(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}

	public static TagTypeEnum parse(String value) {
		return valueMap.get(value);
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}

}

/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.enumtype;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * <p>Title: 日志级别枚举</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-4-11 上午11:04
 */
public enum LogLevelEnum {

	VERIFY("VERIFY", "验证 SQL", 10),
	SELECT("SELECT", "查询记录", 5),
	DOWN("DOWN", "下载", 1),
	UPDATE("UPDATE", "更新记录", 2),
	INSERT("INSERT", "插入记录", 2),
	DELETE("DELETE", "删除记录", 2),
	VERIFY_C("VERIFY_C", "验证数据源", 10),
    CREATE_DT("CREATE_DT", "创建动态表单", 10), // 数据报表
    EXECUTE_DT("EXECUTE_DT", "执行动态表单", 2),// 数据报表
	CREATE_T("CREATE_T", "创建表", 1),
	DROP_T("DROP_T", "删除表", 1);

	private static Map<String, LogLevelEnum> valueMap = Maps.newHashMap();

	private String value;
	private String displayName;
	private int baseScore;

	static {
		for (LogLevelEnum item : LogLevelEnum.values()) {
			valueMap.put(item.value, item);
		}
	}

	LogLevelEnum(String value, String displayName, int baseScore) {
		this.value = value;
		this.displayName = displayName;
		this.baseScore = baseScore;
	}

	public static LogLevelEnum parse(String value) {
		return valueMap.get(value);
	}

	public String getValue() {
		return value;
	}

	public String getDisplayName() {
		return displayName;
	}

	public int getBaseScore() {
		return baseScore;
	}

	public static Map<String, LogLevelEnum> getValueMap() {
		return valueMap;
	}

}

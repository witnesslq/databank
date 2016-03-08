package com.yeepay.g3.app.databank.enumtype;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * title: 事务隔离级别<br/>
 * description: 描述<br/>
 * Copyright: Copyright (c)2014<br/>
 * Company: 易宝支付(YeePay)<br/>
 *
 * @author baitao.ji
 * @version 1.0.0
 * @since 15/1/8 10:54
 */
public enum IsolationTypeEnum {

//    RR("RR", "可重复读"),
//    RS("RS", "读稳定性"),
    CS("CS", "游标稳定性"),
    UR("UR", "未提交读");

    private static Map<String, IsolationTypeEnum> valueMap = Maps.newHashMap();

    private String value;
    private String displayName;

    static {
        for (IsolationTypeEnum item : IsolationTypeEnum.values()) {
            valueMap.put(item.value, item);
        }
    }

    IsolationTypeEnum(String value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }

    public static IsolationTypeEnum parse(String value) {
        return valueMap.get(value);
    }

    public String getValue() {
        return value;
    }

    public String getDisplayName() {
        return displayName;
    }

}

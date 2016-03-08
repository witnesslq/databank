package com.yeepay.g3.app.databank.enumtype;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by hgs on 15/12/16.
 */
public enum ApplyStatusEnum {

    APPLY_REVIEW("APPLY_REVIEW", "审核中"),
    APPLY_SUCCESS("APPLY_SUCCESS", "申请成功"),
    APPLY_FAILURE("APPLY_FAILURE", "申请失败");

    private static Map<String, ApplyStatusEnum> valueMap = Maps.newHashMap();

    private String value;
    private String displayName;

    static {
        for (ApplyStatusEnum item : ApplyStatusEnum.values()) {
            valueMap.put(item.value, item);
        }
    }

    ApplyStatusEnum(String value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }

    public static ApplyStatusEnum parse(String value) {
        return valueMap.get(value);
    }

    public String getValue() {
        return value;
    }

    public String getDisplayName() {
        return displayName;
    }
    public static Map<String, ApplyStatusEnum> getValueMap() {
        return valueMap;
    }
}

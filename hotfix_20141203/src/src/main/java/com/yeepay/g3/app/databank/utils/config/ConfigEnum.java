/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.utils.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * <p>Title: 配置信息</p>
 * <p>Description: 描述</p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 14-5-28 18:35
 */
public enum ConfigEnum {

    DATABANK_INTERRUPT_LOGID("DATABANK_INTERRUPT_LOGID", Lists.newArrayList()),

    DATABANK_FREESPACE_LIMIT("DATABANK_FREESPACE_LIMIT", 5L),

    DATABANK_QUERY_STMT_TIMEOUT("DATABANK_QUERY_STMT_TIMEOUT", 30 * 1000L),
    DATABANK_QUERY_STMT_MAXROWS("DATABANK_QUERY_STMT_MAXROWS", 1000L),

    DATABANK_DOWN_STMT_TIMEOUT("DATABANK_DOWN_STMT_TIMEOUT", 60 * 1000L),
    DATABANK_DOWN_STMT_MAXROWS("DATABANK_DOWN_STMT_MAX_ROWS", 500000L);

    private static Map<String, ConfigEnum> valueMap = Maps.newHashMap();

    private String configKey;
    private Object defaultValue;

    static {
        for (ConfigEnum item : ConfigEnum.values()) {
            valueMap.put(item.configKey, item);
        }
    }

    ConfigEnum(String configKey, Object defaultValue) {
        this.configKey = configKey;
        this.defaultValue = defaultValue;
    }

    public String getConfigKey() {
        return configKey;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

}

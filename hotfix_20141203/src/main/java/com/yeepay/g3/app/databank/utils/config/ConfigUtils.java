/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.utils.config;

import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.config.ConfigParam;
import com.yeepay.g3.utils.config.ConfigParamGroup;
import com.yeepay.g3.utils.config.ConfigurationUtils;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: 统一配置工具类二次封装</p>
 * <p>Description: 描述</p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 14-4-18 18:12
 */
public final class ConfigUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigUtils.class);

    public static final String CONFIG_TYPE_TEXT_RESOURCES = "config_type_text_resources";

    private ConfigUtils() {
        // Utility classes should not have a public or default constructor.
    }

    /**
     * 获取产品层配置参数
     *
     * @param configKey 参数key(含默认值)
     * @return 参数值
     */
    public static Object getAppConfigParam(ConfigEnum configKey) {
        return getConfigParam(ConfigurationUtils.CONFIG_TYPE_APP, configKey);
    }

    /**
     * 获取子系统配置参数
     *
     * @param configKey 参数key(含默认值)
     * @return 参数值
     */
    public static Object getSysConfigParam(ConfigEnum configKey) {
        return getConfigParam(ConfigurationUtils.CONFIG_TYPE_SYS, configKey);
    }

    /**
     * 获取配置参数
     *
     * @param configType 参数的分类
     * @param configKey  参数key(含默认值)
     * @return 参数值
     */
    private static Object getConfigParam(String configType, ConfigEnum configKey) {
        ConfigParam configParam = ConfigurationUtils.getConfigParam(configType, configKey.getConfigKey());
        if (null != configParam && null != configParam.getValue()) {
            return configParam.getValue();
        } else {
            return configKey.getDefaultValue();
        }
    }

    /**
     * 获取数据字典统一配置 Map
     *
     * @param configKey 配置键
     * @return 参数值
     */
    public static Map<String, String> getResourcesConfigMap(ConfigEnum configKey) {
        return getResourcesConfigMap(configKey.getConfigKey(), configKey.getDefaultValue());
    }

    @SuppressWarnings("unchecked")
    private static Map<String, String> getResourcesConfigMap(String configKey, Object defaultValue) {
        ConfigParamGroup configParamGroup = ConfigurationUtils.getConfigParamGroup(CONFIG_TYPE_TEXT_RESOURCES);
        ConfigParam configParam = configParamGroup.getConfig(configKey);
        if (null != configParam && null != configParam.getValue()) {
            Object value = configParam.getValue();
            if (value instanceof Map) {
                return (Map<String, String>) value;
            } else if (value instanceof String && ((String) value).indexOf('@') == 0) {
                String key = (String) value;
                return getResourcesConfigMap(key.substring(1, key.length()), defaultValue);
            }
        }
        return (Map<String, String>) defaultValue;
    }

    /**
     * 获取数据字典统一配置 List
     *
     * @param configKey 配置键
     * @return 参数值
     */
    public static List<String> getResourcesConfigList(ConfigEnum configKey) {
        return getResourcesConfigList(configKey.getConfigKey(), configKey.getDefaultValue());
    }

    @SuppressWarnings("unchecked")
    private static List<String> getResourcesConfigList(String configKey, Object defaultValue) {
        ConfigParamGroup configParamGroup = ConfigurationUtils.getConfigParamGroup(CONFIG_TYPE_TEXT_RESOURCES);
        ConfigParam configParam = configParamGroup.getConfig(configKey);
        if (null != configParam && null != configParam.getValue()) {
            Object value = configParam.getValue();
            if (value instanceof List) {
                return (List<String>) value;
            } else if (value instanceof String && ((String) value).indexOf('@') == 0) {
                String key = (String) value;
                return getResourcesConfigList(key.substring(1, key.length()), defaultValue);
            }
        }
        return (List<String>) defaultValue;
    }

    /**
     * 从统一配置获取超时阈值
     *
     * @param key          统一配置key
     * @param defaultValue 默认值
     * @return
     */
    public static long loadTimeoutThreshold(String key, long defaultValue) {
        long threshold = defaultValue;
        try {
            Object object = ConfigurationUtils.getSysConfigParam(key).getValue();
            if (object instanceof Long) {
                threshold = (Long) object;
            } else {
                LOGGER.warn("统一配置调用 {} 失败, 启用默认设置：{}", key, defaultValue);
            }
        } catch (Exception e) {
            LOGGER.warn("统一配置调用 " + key + " 失败", e);
        }
        return threshold;
    }

    /**
     * 从统一配置获取开关
     *
     * @param key          统一配置key
     * @param defaultValue 默认值
     * @return
     */
    public static boolean loadSwitch(String key, boolean defaultValue) {
        boolean value = defaultValue;
        try {
            Object object = ConfigurationUtils.getSysConfigParam(key).getValue();
            if (object instanceof Boolean) {
                value = (Boolean) object;
            } else {
                LOGGER.warn("统一配置调用 {} 失败, 启用默认设置：{}", key, defaultValue);
            }
        } catch (Exception e) {
            LOGGER.warn("统一配置调用 " + key + " 失败", e);
        }
        return value;
    }

    @SuppressWarnings("unchecked")
    public static void needInterrupt(Long logId, Statement statement) throws SQLException {
        List<String> needInterruptLogId = (List<String>) ConfigUtils.getAppConfigParam(ConfigEnum.DATABANK_INTERRUPT_LOGID);
        if (needInterruptLogId.contains(logId)) {
            LOGGER.warn("需要中断当前 SQL 的执行, logId:{}", logId);
            statement.cancel();
        }
    }

}

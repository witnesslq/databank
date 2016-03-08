package com.yeepay.g3.app.databank.database;

import com.google.common.collect.Maps;
import com.yeepay.g3.app.databank.entity.DataBankSource;
import com.yeepay.g3.app.databank.enumtype.DatabaseTypeEnum;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * title: 数据库元信息提供者 工厂类<br/>
 * description: 描述<br/>
 * Copyright: Copyright (c)2014<br/>
 * Company: 易宝支付(YeePay)<br/>
 *
 * @author baitao.ji
 * @version 1.0.0
 * @since 16/1/29 00:03
 */
public class MetaInfoProviderFactory {

    private static Map<DatabaseTypeEnum, MetaInfoProvider> metaInfoProviderMap = Maps.newConcurrentMap();

    public static MetaInfoProvider getMetaInfoProvider(DatabaseTypeEnum databaseType) {
        return metaInfoProviderMap.get(databaseType);
    }

    public static void register(MetaInfoProvider metaInfoProvider) {
        metaInfoProviderMap.put(metaInfoProvider.getDatabaseType(), metaInfoProvider);
    }

    public static TreeMap<String, String> loadSchema(DataBankSource dataBankSource) throws Exception {
        return getMetaInfoProvider(dataBankSource.getDbType()).loadSchema(dataBankSource);
    }

    public static List<String> loadSchemaAndTable(DataBankSource dataBankSource) throws Exception {
        return getMetaInfoProvider(dataBankSource.getDbType()).loadSchemaAndTable(dataBankSource);
    }

    public static List<String> loadSchemaAndTableColumn(DataBankSource dataBankSource, String schemaName, String tableName) throws Exception {
        return getMetaInfoProvider(dataBankSource.getDbType()).loadSchemaAndTableColumn(dataBankSource, schemaName, tableName);
    }

    public static List<String> loadSchemaAndTableColumn(DataBankSource dataBankSource, String[] schemaAndTables) throws Exception {
        return getMetaInfoProvider(dataBankSource.getDbType()).loadSchemaAndTableColumn(dataBankSource, schemaAndTables);
    }

    public static List<String> loadTableIndex(DataBankSource dataBankSource, String schemaName, String tableName) throws Exception {
        return getMetaInfoProvider(dataBankSource.getDbType()).loadTableIndex(dataBankSource, schemaName, tableName);
    }

}

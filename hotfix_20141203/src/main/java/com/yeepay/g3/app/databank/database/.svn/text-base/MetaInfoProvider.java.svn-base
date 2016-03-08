package com.yeepay.g3.app.databank.database;

import com.yeepay.g3.app.databank.entity.DataBankSource;
import com.yeepay.g3.app.databank.enumtype.DatabaseTypeEnum;

import java.util.List;
import java.util.TreeMap;

/**
 * title: 数据库元信息提供者 接口<br/>
 * description: 描述<br/>
 * Copyright: Copyright (c)2014<br/>
 * Company: 易宝支付(YeePay)<br/>
 *
 * @author baitao.ji
 * @version 1.0.0
 * @since 16/1/28 16:09
 */
public interface MetaInfoProvider {

    /**
     * 加载指定数据源的所有Schema
     *
     * @param dataBankSource 数据源
     * @return
     * @throws Exception
     */
    TreeMap<String, String> loadSchema(DataBankSource dataBankSource) throws Exception;

    /**
     * 加载指定数据源的所有表名
     *
     * @param dataBankSource 数据源
     * @return
     * @throws Exception
     */
    List<String> loadSchemaAndTable(DataBankSource dataBankSource) throws Exception;

    /**
     * 加载指定数据源中指定表下的所有列
     *
     * @param dataBankSource 数据源
     * @return schemaName.tableName.columnName
     * @throws Exception
     */
    List<String> loadSchemaAndTableColumn(DataBankSource dataBankSource, String schemaName, String tableName) throws Exception;

    /**
     * 获取多个schema和表中的所有列
     *
     * @param dataBankSource
     * @param schemaAndTables
     * @return schemaName.tableName.columnName
     * @throws Exception
     */
    List<String> loadSchemaAndTableColumn(DataBankSource dataBankSource, String[] schemaAndTables) throws Exception;

    /**
     * 加载指定数据源的所有表名
     *
     * @param dataBankSource 数据源
     * @param schemaName     Schema
     * @param tableName      表名
     * @return
     * @throws Exception
     */
    List<String> loadTableIndex(DataBankSource dataBankSource, String schemaName, String tableName) throws Exception;

    DatabaseTypeEnum getDatabaseType();

}

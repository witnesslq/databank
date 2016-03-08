package com.yeepay.g3.app.databank.database;

import com.yeepay.g3.app.databank.SpringTransactionalTests;
import com.yeepay.g3.app.databank.data.datasource.DataBankSourceData;
import com.yeepay.g3.app.databank.entity.DataBankSource;
import com.yeepay.g3.app.databank.enumtype.DatabaseTypeEnum;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.TreeMap;

import static org.junit.Assert.assertTrue;

/**
 * title: <br/>
 * description: 描述<br/>
 * Copyright: Copyright (c)2014<br/>
 * Company: 易宝支付(YeePay)<br/>
 *
 * @author baitao.ji
 * @version 1.0.0
 * @since 16/1/29 15:59
 */
public class MetaInfoProviderTest extends SpringTransactionalTests {

    @Autowired
    private MetaInfoProvider DB2MetaInfoProvider;

    @Autowired
    private MetaInfoProvider mySQLMetaInfoProvider;

    private DataBankSource DB2DataBankSource;

    private DataBankSource mySQLDataBankSource;

    @Before
    public void setUp() throws Exception {
        DB2DataBankSource = DataBankSourceData.getRandom(DatabaseTypeEnum.DB2);
        mySQLDataBankSource = DataBankSourceData.getRandom(DatabaseTypeEnum.MYSQL);
    }

    @Test
    public void testLoadSchemaForDB2() throws Exception {
        TreeMap<String, String> schemaMap = DB2MetaInfoProvider.loadSchema(DB2DataBankSource);
        assertTrue(schemaMap.containsKey("AUTH2"));
        assertTrue(schemaMap.containsValue("AUTH2"));
    }

    @Test
    public void testLoadSchemaForMySQL() throws Exception {
        TreeMap<String, String> schemaMap = mySQLMetaInfoProvider.loadSchema(mySQLDataBankSource);
        assertTrue(schemaMap.containsKey("information_schema"));
        assertTrue(schemaMap.containsValue("information_schema"));
    }

    @Test
    public void testLoadSchemaAndTable() throws Exception {

    }

    @Test
    public void testLoadSchemaAndTableColumnForDB2() throws Exception {
        String[] schemaAndTables = new String[]{"AUTH2.TBL_INTERFACE"};
        List<String> columns = DB2MetaInfoProvider.loadSchemaAndTableColumn(DB2DataBankSource, schemaAndTables);
        assertTrue(columns.contains("AUTH2.TBL_INTERFACE.AUTH_INTERFACE_CODE"));
    }

    @Test
    public void testLoadSchemaAndTableColumnForMySQL() throws Exception {
        String[] schemaAndTables = new String[]{"mysql.column_stats"};
        List<String> columns = mySQLMetaInfoProvider.loadSchemaAndTableColumn(mySQLDataBankSource, schemaAndTables);
        assertTrue(columns.contains("mysql.column_stats.table_name"));
    }

    @Test
    public void testLoadTableIndexForDB2() throws Exception {
        List<String> columns = DB2MetaInfoProvider.loadTableIndex(DB2DataBankSource, "AUTH2", "TBL_INTERFACE");
        assertTrue(columns.contains("AUTH2,TBL_INTERFACE,AVAILABLE,D,0"));
    }

    @Ignore
    @Test
    public void testLoadTableIndexForMySQL() throws Exception {
        List<String> columns = mySQLMetaInfoProvider.loadTableIndex(mySQLDataBankSource, "mysql", "column_stats");
        assertTrue(columns.contains("mysql.column_stats.table_name"));
    }

}
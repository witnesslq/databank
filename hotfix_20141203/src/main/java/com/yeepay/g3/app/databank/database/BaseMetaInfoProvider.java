package com.yeepay.g3.app.databank.database;

import com.yeepay.g3.app.databank.entity.DataBankSource;
import com.yeepay.g3.app.databank.helper.DBHelper;
import com.yeepay.g3.utils.cache.config.LocalCache;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 * title: 数据库元信息提供者 基类<br/>
 * description: 描述<br/>
 * Copyright: Copyright (c)2014<br/>
 * Company: 易宝支付(YeePay)<br/>
 *
 * @author baitao.ji
 * @version 1.0.0
 * @since 16/1/28 16:23
 */
public abstract class BaseMetaInfoProvider implements MetaInfoProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseMetaInfoProvider.class);

    @LocalCache(timeToIdleSeconds = 3600, timeToLiveSeconds = 86400)
    @Override
    public TreeMap<String, String> loadSchema(DataBankSource dataBankSource) throws Exception {
        TreeMap<String, String> tableNameList;
        Connection dbConn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            dbConn = DBHelper.getConn(dataBankSource);
            stmt = dbConn.createStatement();
            //stmt.setQueryTimeout(10);
            rs = stmt.executeQuery(getSchemaSQL());
            tableNameList = new TreeMap<String, String>();
            tableNameList.put("", "未指定");
            while (rs.next()) {
                tableNameList.put(rs.getString(1), rs.getString(1));
            }
        } catch (Exception e) {
            throw new RuntimeException("无法读取数据源信息，请与 DBA 取得联系！");
        } finally {
            DBHelper.close(rs, stmt, dbConn);
        }
        return tableNameList;
    }

    protected abstract String getSchemaSQL();

    @Override
    public List<String> loadSchemaAndTable(DataBankSource dataBankSource) throws Exception {
        List<String> tableNameList;
        Connection dbConn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            dbConn = DBHelper.getConn(dataBankSource);
            stmt = dbConn.createStatement();
            //stmt.setQueryTimeout(10);
            rs = stmt.executeQuery(getSchemaAndTableSQL());
            tableNameList = new ArrayList<String>();
            while (rs.next()) {
                tableNameList.add(rs.getString(1));
            }
        } catch (Exception e) {
            throw new RuntimeException("无法读取数据源信息，请与 DBA 取得联系！");
        } finally {
            DBHelper.close(rs, stmt, dbConn);
        }
        return tableNameList;
    }

    protected abstract String getSchemaAndTableSQL();

    public List<String> loadSchemaAndTableColumn(DataBankSource dataBankSource, final String schemaName, final String tableName) throws Exception {
        return loadSchemaAndTableColumn(dataBankSource, new ArrayList<SchemaTableTuple>() {{
            new SchemaTableTuple(schemaName, tableName);
        }});
    }

    public List<String> loadSchemaAndTableColumn(DataBankSource dataBankSource, String[] schemaAndTables) throws Exception {
        if (null == schemaAndTables || schemaAndTables.length == 0) {
            throw new RuntimeException("无法读取数据源信息，请与 DBA 取得联系！");
        }

        List<SchemaTableTuple> tableColumnNameLists = new LinkedList<SchemaTableTuple>();
        for (String str : schemaAndTables) {
            //获取表名和schema名
            String schemaName = StringUtils.substringBefore(str, ".");
            String tableName = StringUtils.substringAfterLast(str, ".");
            tableColumnNameLists.add(new SchemaTableTuple(schemaName, tableName));
        }
        return loadSchemaAndTableColumn(dataBankSource, tableColumnNameLists);
    }

    public List<String> loadSchemaAndTableColumn(DataBankSource dataBankSource, List<SchemaTableTuple> schemaAndTables) throws Exception {
        List<String> tableNameList;
        // 尝试连接
        Connection dbConn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            dbConn = DBHelper.getConn(dataBankSource);
            stmt = dbConn.createStatement();
            //stmt.setQueryTimeout(10);
            rs = stmt.executeQuery(getSchemaAndTableColumnSQL(schemaAndTables));
            tableNameList = new ArrayList<String>();
            while (rs.next()) {
                tableNameList.add(rs.getString(1).trim());
            }
        } catch (Exception e) {
            LOGGER.warn("无法读取数据源信息", e);
            throw new RuntimeException("无法读取数据源信息，请与 DBA 取得联系！");
        } finally {
            DBHelper.close(rs, stmt, dbConn);
        }
        return tableNameList;
    }

    protected abstract String getSchemaAndTableColumnSQL(List<SchemaTableTuple> schemaAndTables);

    public List<String> loadTableIndex(DataBankSource dataBankSource, String schemaName, String tableName) throws Exception {
        List<String> tableNameList;
        // 尝试连接
        Connection dbConn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            dbConn = DBHelper.getConn(dataBankSource);
            stmt = dbConn.createStatement();
            //stmt.setQueryTimeout(10);
            rs = stmt.executeQuery(getTableIndexSQL(schemaName, tableName));
            tableNameList = new ArrayList<String>();
            while (rs.next()) {
                String column = rs.getString(3);
                tableNameList.add(rs.getString(1).trim() + "," + rs.getString(2) + "," + column.substring(1, column.length()) + "," + rs.getString(4) + "," + rs.getString(5));
            }
        } catch (Exception e) {
            LOGGER.warn("无法读取数据源信息", e);
            throw new RuntimeException("无法读取数据源信息，请与 DBA 取得联系！");
        } finally {
            DBHelper.close(rs, stmt, dbConn);
        }
        return tableNameList;
    }

    protected abstract String getTableIndexSQL(String schemaName, String tableName);

}

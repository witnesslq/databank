/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.helper;

import com.yeepay.g3.app.databank.biz.eventhandler.EventNameEnum;
import com.yeepay.g3.app.databank.entity.DataBankSource;
import com.yeepay.g3.app.databank.entity.SQLStatusEntity;
import com.yeepay.g3.app.databank.enumtype.CacheKeyEnum;
import com.yeepay.g3.app.databank.enumtype.LogLevelEnum;
import com.yeepay.g3.app.databank.helper.file.OutputHelperContext;
import com.yeepay.g3.app.databank.service.DefaultEhcacheManager;
import com.yeepay.g3.app.databank.service.LogManager;
import com.yeepay.g3.app.databank.service.SQLStatusManager;
import com.yeepay.g3.app.databank.utils.config.ConfigEnum;
import com.yeepay.g3.app.databank.utils.config.ConfigUtils;
import com.yeepay.g3.app.databank.utils.result.FileResult;
import com.yeepay.g3.app.databank.utils.result.QueryResult;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.event.ext.BaseEventUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * <p>Title: 数据库操作 Helper</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-4-2 下午6:07
 */
@Component
public class DBHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(DBHelper.class);

    private LogManager logManager;

    private static String reportPath;

    @Autowired
    private QueryHelper queryHelper;

    @Autowired
    private OutputHelperContext outputHelperContext;

    @Autowired
    private DefaultEhcacheManager ehcacheManager;

    @Autowired
    private SQLStatusManager sqlStatusManager;

//	private MapDataSourceLookup dataSourceLookup;
    //SingleConnectionDataSource

    /**
     * 获取数据库驱动类
     *
     * @param dbType 数据库类型
     * @return 数据库驱动类
     */
    private String getDriverName(String dbType) throws Exception {
        return "com.yeepay.g3.app.databank.utils.log4jdbc.DriverSpy";
    }

    public Connection getConn(DataBankSource dataBankSource) throws Exception {
        return getConn(dataBankSource, null);
    }

    public Connection getConn(DataBankSource dataBankSource, String schema) throws Exception {
        String dbType = dataBankSource.getDbType().getValue();
        String connStr = dataBankSource.getConnStr();
        String username = dataBankSource.getUsername();
        String password = dataBankSource.getPassword();

        // 基本数据校验
        if (StringUtils.isBlank(connStr) || StringUtils.isBlank(dbType) || StringUtils.isBlank(username)) {
            throw new Exception("必填项不能为空.");
        }

        // TODO 仅支持 DB2
        if (null != schema && !"".equals(schema) && !"未指定".equals(schema)) {
            connStr += ":currentSchema=" + schema + ";";
        }

        // 获取驱动
        String driverName = getDriverName(dbType);

        // 尝试加载数据库驱动
        try {
            Class.forName(driverName).newInstance();
        } catch (Exception ex) {
            LOGGER.warn("驱动加载失败. connStr:{}", connStr);
            throw new Exception("驱动加载失败.");
        }

        // 尝试连接
        try {
            return DriverManager.getConnection(connStr, username, password);
        } catch (SQLException e) {
            LOGGER.warn("连接数据库失败. connStr:{} username:{} password:{}", connStr, username, password);
            throw e;
        }
    }

    /**
     * 校验数据库连接是否正确
     *
     * @param loginName 用户登录名
     * @param ds        数据源对象
     */
    public void verifyConn(String loginName, DataBankSource ds) throws Exception {
        String sql;
        // 查找合适的数据库连通性 SQL
        // refence: http://stackoverflow.com/questions/10684244/dbcp-validationquery-for-different-databases
        String dbType = ds.getDbType().getValue();
        if ("db2".equalsIgnoreCase(dbType)) {
            sql = "select 1 from sysibm.sysdummy1";
        } else if ("mysql".equalsIgnoreCase(dbType)) {
            sql = "select 1";
        } else if ("h2".equalsIgnoreCase(dbType)) {
            sql = "select 1";
        } else if ("oracle".equalsIgnoreCase(dbType)) {
            sql = "select 1 from dual";
        } else if ("hsqldb".equalsIgnoreCase(dbType)) {
            sql = "select 1 from INFORMATION_SCHEMA.SYSTEM_USERS";
        } else if ("postgresql".equalsIgnoreCase(dbType)) {
            sql = "select version();";
        } else if ("derby".equalsIgnoreCase(dbType)) {
            sql = "values 1";
        } else if ("microsoft".equalsIgnoreCase(dbType)) {
            sql = "sql select 1";
        } else {
            throw new Exception("不支持的数据库类型.");
        }

        QueryResult queryResult = new QueryResult(loginName, LogLevelEnum.VERIFY_C, sql);

        // 验证数据库可否查询
        queryHelper.executeSelect(ds, queryResult);
    }

    /**
     * 加载指定数据源的所有Schema
     *
     * @param dataBankSource 数据源
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public TreeMap<String, String> loadSchema(DataBankSource dataBankSource) throws Exception {
        // 已缓存
        String key = CacheKeyEnum.DS_SCHEMA + ":" + dataBankSource.getId().toString();
        TreeMap<String, String> tableNameList = (TreeMap<String, String>) ehcacheManager.get(key);
        if (null == tableNameList || 0 == tableNameList.size()) {
            String querySchemaAndTable = "SELECT rtrim(NAME) AS nameList FROM sysibm.sysschemata s " +
                    "WHERE s.DEFINERTYPE='U' AND s.NAME NOT IN ('NULLID','SQLJ','SYSTOOLS','DB2ADMIN','DB2INST1','DB2INST2') " +
                    "ORDER BY nameList ASC";

            // 尝试连接
            try {
                Connection dbConn = getConn(dataBankSource);
                Statement stmt = dbConn.createStatement();
                //stmt.setQueryTimeout(10);
                ResultSet rs = stmt.executeQuery(querySchemaAndTable);
                tableNameList = new TreeMap();
                tableNameList.put("", "未指定");
                while (rs.next()) {
                    tableNameList.put(rs.getString(1), rs.getString(1));
                }
                ehcacheManager.put(key, tableNameList);
            } catch (Exception e) {
                throw new Exception("无法读取数据源信息，请与 DBA 取得联系！");
            }
        }
        return tableNameList;
    }

    /**
     * 加载指定数据源的所有表名
     *
     * @param dataBankSource 数据源
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<String> loadSchemaAndTable(DataBankSource dataBankSource) throws Exception {
        // 已缓存
        String key = CacheKeyEnum.DS_SCHEMA_TABLE + ":" + dataBankSource.getId().toString();
        List<String> tableNameList = (List<String>) ehcacheManager.get(key);
        if (null == tableNameList || 0 == tableNameList.size()) {
            String querySchemaAndTable = "SELECT rtrim(TABSCHEMA)||'.'||TABNAME AS nameList FROM syscat.tables t " +
                    "INNER JOIN sysibm.sysschemata s ON s.NAME=t.TABSCHEMA " +
                    "AND s.DEFINERTYPE='U' AND s.NAME NOT IN ('NULLID','SQLJ','SYSTOOLS','DB2ADMIN','DB2INST1','DB2INST2') " +
                    "ORDER BY TABSCHEMA,TABNAME";

            // 尝试连接
            try {
                Connection dbConn = getConn(dataBankSource);
                Statement stmt = dbConn.createStatement();
                //stmt.setQueryTimeout(10);
                ResultSet rs = stmt.executeQuery(querySchemaAndTable);
                tableNameList = new ArrayList<String>();
                while (rs.next()) {
                    tableNameList.add(rs.getString(1));
                }
                Collections.sort(tableNameList);
                ehcacheManager.put(key, tableNameList);
            } catch (Exception e) {
                throw new Exception("无法读取数据源信息，请与 DBA 取得联系！");
            }
        }
        return tableNameList;
    }

    /**
     * 加载指定数据源的所有表名
     *
     * @param dataBankSource 数据源
     * @param schemaName     Schema
     * @param tableName      表名
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<String> loadTableIndex(DataBankSource dataBankSource, String schemaName, String tableName) throws Exception {
        // 已缓存
        String key = CacheKeyEnum.DS_TABLE_INDEX + ":" + dataBankSource.getId().toString() + ":" + schemaName + ":" + tableName;
        List<String> tableNameList = (List<String>) ehcacheManager.get(key);
        if (null == tableNameList || 0 == tableNameList.size()) {
            String query = "SELECT TABSCHEMA,TABNAME,COLNAMES,UNIQUERULE,SYSTEM_REQUIRED FROM syscat.indexes" +
                    " WHERE TABSCHEMA = '" + schemaName + "'";
            if (null != tableName && !"".equals(tableName)) {
                query += " AND TABNAME = '" + tableName + "'";
            }
            query += " ORDER BY TABSCHEMA,TABNAME";

            // 尝试连接
            try {
                Connection dbConn = getConn(dataBankSource);
                Statement stmt = dbConn.createStatement();
                //stmt.setQueryTimeout(10);
                ResultSet rs = stmt.executeQuery(query);
                tableNameList = new ArrayList<String>();
                while (rs.next()) {
                    String column = rs.getString(3);
                    tableNameList.add(rs.getString(1).trim() + "," + rs.getString(2) + "," + column.substring(1, column.length()) + "," + rs.getString(4) + "," + rs.getString(5));
                }
                Collections.sort(tableNameList);
                ehcacheManager.put(key, tableNameList);
            } catch (Exception e) {
                LOGGER.warn("无法读取数据源信息", e);
                throw new Exception("无法读取数据源信息，请与 DBA 取得联系！");
            }
        }
        return tableNameList;
    }

    /**
     * 查询并输出文件
     *
     * @param dataBankSource 数据源
     * @param queryResult    查询结果
     * @return 下载链接
     */
    public String executeSelectToFile(DataBankSource dataBankSource, QueryResult queryResult, String type) throws Exception {
        // 先生成日志，后异步更新
        Long logId = logManager.create(dataBankSource.getId(), queryResult);

        long usedMilliSecond = 0;
        Date startDateTime = new Date();
        String targetFileName = startDateTime.getTime() + "-" + queryResult.getLoginName();
        queryResult.setStartDateTime(startDateTime);

        // 尝试连接
        Connection dbConn = getConn(dataBankSource, queryResult.getSchema());
        Statement stmt = null;

        FileResult fileResult = new FileResult();

        try {
            stmt = dbConn.createStatement();
            stmt.setMaxRows(((Long) ConfigUtils.getAppConfigParam(ConfigEnum.DATABANK_DOWN_STMT_MAXROWS)).intValue());
            stmt.setQueryTimeout(((Long) ConfigUtils.getAppConfigParam(ConfigEnum.DATABANK_DOWN_STMT_TIMEOUT)).intValue());

            long start = System.currentTimeMillis();
            ResultSet rs = stmt.executeQuery(queryResult.getActualSql());
            long end = System.currentTimeMillis();

            // 查询用时
            usedMilliSecond = end - start;

            fileResult = outputHelperContext.save(logId, rs, reportPath, targetFileName, type);

            queryResult.setSize(fileResult.getSize());
            queryResult.setUsedMilliSecond(usedMilliSecond);
            LOGGER.info("SQL 执行成功！SQL:{}", queryResult.getActualSql());
        } catch (SQLException e) {
            LOGGER.warn("SQL 执行失败！dsId:{} 原始sql:{}", dataBankSource.getId(), queryResult.getActualSql());

            SQLStatusEntity sqlStatus;
            queryResult.setStatus(e.getSQLState());
            sqlStatus = sqlStatusManager.getBySQLStatus(e.getSQLState());
            if (null != sqlStatus) {
                throw new SQLException(e.getMessage().replace("DB2 SQL Error:", sqlStatus.getExplanation() + "<br/>出错详情:<br/>"));
            } else {
                try {
                    EmailHelper.sendEmail("未知的 SQL Status", e.getMessage());
                } catch (IOException ioe) {
                    // Do nothing
                }
                throw new SQLException(e.getMessage().replaceFirst("DB2 SQL Error:", "<br/>出错详情:<br/>"));
            }
        } catch (Exception e) {
            LOGGER.warn("SQL 执行失败！dsId:{} 原始sql:{}", dataBankSource.getId(), queryResult.getActualSql());
            throw e;
        } finally {
            BaseEventUtils.sendEventNotInTransaction(EventNameEnum.UPDATE_LOG_INFO.toString(), logId, queryResult);

            // 清场
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                LOGGER.warn("无法关闭 stmt. dsId:{}", dataBankSource.getId());
            }
            try {
                if (dbConn != null)
                    dbConn.close();
            } catch (SQLException e) {
                LOGGER.warn("无法关闭 数据库连接. dsId:{}", dataBankSource.getId());
            }
        }
        return fileResult.getTargetFileName();
    }

    @Autowired
    public void setLogManager(LogManager logManager) {
        this.logManager = logManager;
    }

    @Value("#{propertiesReader['report.output.dir']}")
    public void setReportPath(String reportPath) {
        if ('/' == reportPath.charAt(reportPath.length() - 1)) {
            DBHelper.reportPath = reportPath;
        } else {
            DBHelper.reportPath = reportPath + "/";
        }
    }

}

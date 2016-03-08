/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.helper;

import com.yeepay.g3.app.databank.biz.eventhandler.EventNameEnum;
import com.yeepay.g3.app.databank.entity.DataBankSource;
import com.yeepay.g3.app.databank.entity.SQLStatusEntity;
import com.yeepay.g3.app.databank.enumtype.DatabaseTypeEnum;
import com.yeepay.g3.app.databank.enumtype.LogLevelEnum;
import com.yeepay.g3.app.databank.helper.file.OutputHelperContext;
import com.yeepay.g3.app.databank.service.LogManager;
import com.yeepay.g3.app.databank.service.SQLStatusManager;
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
    private SQLStatusManager sqlStatusManager;

    /**
     * 获取数据库驱动类
     *
     * @param dbType 数据库类型
     * @return 数据库驱动类
     */
    private static String getDriverName(DatabaseTypeEnum dbType) throws Exception {
        switch (dbType) {
            case DB2:
                return "com.ibm.db2.jcc.DB2Driver";
            case MYSQL:
                return "com.mysql.jdbc.Driver";
            default:
                throw new RuntimeException("不支持其他数据库");
        }
    }

    public static Connection getConn(DataBankSource dataBankSource) throws Exception {
        return getConn(dataBankSource, null);
    }

    public static Connection getConn(DataBankSource dataBankSource, String schema) throws Exception {
        String connStr = dataBankSource.getConnStr();
        String username = dataBankSource.getUsername();
        String password = dataBankSource.getPassword();

        // 基本数据校验
        if (StringUtils.isEmpty(connStr) || StringUtils.isEmpty(username)) {
            throw new RuntimeException("必填项不能为空.");
        }

        if (null != schema && !"".equals(schema) && !"未指定".equals(schema)) {
            switch (dataBankSource.getDbType()) {
                case DB2:
                    connStr += ":currentSchema=" + schema + ";";
                    break;
                case MYSQL:
                    if (connStr.lastIndexOf("/") > 15) {
                        connStr += schema;
                    } else {
                        connStr += "/" + schema;
                    }
                    break;
                default:
                    throw new RuntimeException("不支持其他数据库");
            }
        }

        // 获取驱动
        String driverName = getDriverName(dataBankSource.getDbType());

        // 尝试加载数据库驱动
        try {
            Class.forName(driverName).newInstance();
        } catch (Exception ex) {
            LOGGER.warn("驱动加载失败. connStr:{}", connStr);
            throw new RuntimeException("驱动加载失败.");
        }

        // 尝试连接
        try {
            return DriverManager.getConnection(connStr, username, password);
        } catch (Exception e) {
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
        switch (ds.getDbType()) {
            case DB2:
                sql = "select 1 from sysibm.sysdummy1";
                break;
            case MYSQL:
                sql = "select 1";
                break;
            default:
                throw new RuntimeException("不支持的数据库类型.");
        }

        // 验证数据库可否查询
        QueryResult queryResult = new QueryResult(loginName, LogLevelEnum.VERIFY_C, sql);
        queryResult.setSchema("");
        ds.setId(0L);
        queryHelper.executeSelect(ds, queryResult);
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
        Connection dbConn = null;
        Statement stmt = null;
        ResultSet rs = null;
        FileResult fileResult = new FileResult();

        try {
            dbConn = getConn(dataBankSource, queryResult.getSchema());
            stmt = dbConn.createStatement(); // NOSONAR

            long start = System.currentTimeMillis();
            rs = stmt.executeQuery(queryResult.getActualSql());
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
                throw new SQLException(e.getMessage().replaceFirst("DB2 SQL Error:", "<br/>出错详情:<br/>"));
            }
        } catch (Exception e) {
            LOGGER.warn("SQL 执行失败！dsId:{} 原始sql:{}", dataBankSource.getId(), queryResult.getActualSql());
            throw e;
        } finally {
            BaseEventUtils.sendEventNotInTransaction(EventNameEnum.UPDATE_LOG_INFO.toString(), logId, queryResult);
            close(rs, stmt, dbConn);
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

    public static void close(ResultSet rs, Statement stmt, Connection dbConn) {
        try {
            if (null != rs) {
                rs.close();
            }
        } catch (Exception e) {
            // Do nothing
        }
        try {
            if (null != stmt) {
                stmt.close();
            }
        } catch (Exception e) {
            // Do nothing
        }
        try {
            if (null != dbConn) {
                dbConn.close();
            }
        } catch (Exception e) {
            // Do nothing
        }
    }

}

/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.app.databank.helper;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yeepay.g3.app.databank.biz.eventhandler.EventNameEnum;
import com.yeepay.g3.app.databank.entity.DataBankSource;
import com.yeepay.g3.app.databank.entity.SQLStatusEntity;
import com.yeepay.g3.app.databank.enumtype.IsolationTypeEnum;
import com.yeepay.g3.app.databank.exception.DatabankException;
import com.yeepay.g3.app.databank.service.LogManager;
import com.yeepay.g3.app.databank.service.SQLStatusManager;
import com.yeepay.g3.app.databank.utils.config.ConfigEnum;
import com.yeepay.g3.app.databank.utils.config.ConfigUtils;
import com.yeepay.g3.app.databank.utils.result.QueryResult;
import com.yeepay.g3.app.databank.utils.sql.dialect.Dialect;
import com.yeepay.g3.core.druid.sql.ast.statement.SQLSelectStatement;
import com.yeepay.g3.core.druid.sql.parser.ParserException;
import com.yeepay.g3.core.druid.sql.parser.SQLStatementParser;
import com.yeepay.g3.core.druid.sql.visitor.SchemaStatVisitor;
import com.yeepay.g3.core.druid.stat.TableStat;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.event.ext.BaseEventUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>Title: SQL 语句处理</p>
 * <p>Description: 描述</p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-7-22 上午9:45
 */
@Component
public class QueryHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryHelper.class);

    private LogManager logManager;

    private SQLStatusManager sqlStatusManager;

    @Autowired
    private DBHelper dbHelper;

    /**
     * 查询准备
     *
     * @param dialect
     * @param permissions
     * @param originalSql
     * @param lineNum
     * @param quickMode
     * @param skipSqlCheck
     * @return
     */
    public QueryResult queryPrepare(Dialect dialect, Map<String, List<String>> permissions, String schema, String originalSql,
                                    Long lineNum, IsolationTypeEnum isolation, String quickMode, String skipSqlCheck) {
        // SQL 分析
        long start = System.currentTimeMillis();
        QueryResult queryResult = new QueryResult();
        try {
            SQLStatementParser parser = new SQLStatementParser(originalSql);
            SQLSelectStatement sqlSelectStatement = parser.parseSelect();

            SchemaStatVisitor visitor = new SchemaStatVisitor();
            sqlSelectStatement.accept(visitor);

            // 表级权限控制
            List<String> params = permissions.get("notAllow");
            Set<Map.Entry<TableStat.Name, TableStat>> table = visitor.getTables().entrySet();
            if (null != params && params.size() > 0) {
                for (String item : params) {
                    for (Map.Entry<TableStat.Name, TableStat> entry : table) {
                        // 全限定表名 || 指定Schema+表名
                        if (item.contains(".") && entry.getKey().getName().equalsIgnoreCase(item) ||
                                !item.contains(".") && (schema + "." + entry.getKey().getName()).equalsIgnoreCase(item)) {
                            throw DatabankException.NOT_ALLOW_ACCESS_TBL_EXCEPTION
                                    .newInstance("【禁止】无权访问的 Schema 和表: {0} {1}", schema, entry.getKey().getName());
                        }
                    }
                }
            } else {
                params = permissions.get("allow");
                if (null != params && params.size() > 0) {
                    for (Map.Entry<TableStat.Name, TableStat> entry : table) {
                        String item = entry.getKey().getName();
                        // 全限定表名 || 指定Schema+表名
                        if (item.contains(".") && !params.contains(item) ||
                                !item.contains(".") && !params.contains(schema + "." + entry.getKey().getName())) {
                            throw DatabankException.NOT_ALLOW_ACCESS_TBL_EXCEPTION
                                    .newInstance("【只允许】无权访问的 Schema 和表: {0} {1}", schema, entry.getKey().getName());
                        }
                    }
                }
            }

            queryResult.setTables(visitor.getTables().size());
            queryResult.setColumns(visitor.getColumns().size());
            queryResult.setConditions(visitor.getConditions().size());
            queryResult.setRelationships(visitor.getRelationships().size());
            queryResult.setOrderBy(visitor.getOrderByColumns().size());
            queryResult.setGroupBy(visitor.getGroupByColumns().size());
//			queryResult.setVariants(visitor.getVariants().size());
        } catch (ParserException e) {
            LOGGER.warn("SQL分析异常, sql=" + originalSql, e);

            // 不跳过语法检查器则正常报错
            if (null == skipSqlCheck || !"1".equals(skipSqlCheck)) {
                throw e;
            }
        }
        long end = System.currentTimeMillis();

        // SQL 优化
        originalSql = dialect.prepare(originalSql);
        if (null != lineNum && lineNum != 0) {
            originalSql = dialect.selectTopN(originalSql, Math.min(lineNum, 65535L), isolation, quickMode);
        }
        originalSql = dialect.selectOptimize(originalSql);
        queryResult.setActualSql(originalSql);
        queryResult.setUsedMilliSecond(end - start);
        return queryResult;
    }

    /**
     * 查询
     *
     * @param dataBankSource 数据源
     * @param queryResult    查询结果
     * @return 查询结果
     */
    public QueryResult executeSelect(DataBankSource dataBankSource, QueryResult queryResult) throws Exception {
        List<String> header = Lists.newArrayList();
        List<Map<String, String>> content = Lists.newArrayList();

        // 先生成日志，后异步更新
        Long logId = logManager.create(dataBankSource.getId(), queryResult);

        long usedMilliSecond = 0;
        queryResult.setStartDateTime(new Date());

        // 尝试连接
        Connection dbConn = dbHelper.getConn(dataBankSource, queryResult.getSchema());
        Statement stmt = null;
        try {
            stmt = dbConn.createStatement();
            stmt.setMaxRows(((Long) ConfigUtils.getAppConfigParam(ConfigEnum.DATABANK_QUERY_STMT_MAXROWS)).intValue());
            stmt.setQueryTimeout(((Long) ConfigUtils.getAppConfigParam(ConfigEnum.DATABANK_QUERY_STMT_TIMEOUT)).intValue());

            long start = System.currentTimeMillis();
            ResultSet rs = stmt.executeQuery(queryResult.getActualSql());
            long end = System.currentTimeMillis();

            // 查询用时
            usedMilliSecond = end - start;

            // 封装表头
            int num = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= num; ++i) {
                header.add(rs.getMetaData().getColumnLabel(i).trim());
            }

            // 封装正文
            while (rs.next()) {
                Map<String, String> raw = Maps.newHashMap();
                for (int i = 1; i <= num; ++i) {
                    String item = rs.getString(i);
                    item = null == item ? " " : StringEscapeUtils.escapeHtml4(item.trim());
                    raw.put(i - 1 + "", item);
                }
                content.add(raw);

                ConfigUtils.needInterrupt(logId, rs.getStatement());
            }
            queryResult.setHeader(header);
            queryResult.setContent(content);
            queryResult.setSize(content.size());
            queryResult.setUsedMilliSecond(usedMilliSecond);
            LOGGER.info("SQL 执行成功！SQL:{}", queryResult.getActualSql());
        } catch (SQLException e) {
            LOGGER.warn("SQL 执行失败！dsId:{} 原始sql:{}", dataBankSource.getId(), queryResult.getActualSql());

            String sqlState = e.getSQLState();
            String errMsg = e.getMessage();
            if (StringUtils.isBlank(errMsg)) {
                errMsg = "";
            }
            SQLStatusEntity sqlStatus;
            try {
                queryResult.setStatus(sqlState);
                sqlStatus = sqlStatusManager.getBySQLStatus(sqlState);
            } catch (Exception e1) {
                try {
                    EmailHelper.sendEmail("未知的 SQL Status", errMsg);
                } catch (IOException ioe) {
                    // Do nothing
                }
                throw new SQLException(errMsg.replaceFirst("DB2 SQL Error:", "出错详情:<br/>"));
            }
            throw new SQLException(errMsg.replace("DB2 SQL Error:", sqlStatus.getExplanation() + "<br/>出错详情:<br/>"));
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
        return queryResult;
    }

    @Autowired
    public void setLogManager(LogManager logManager) {
        this.logManager = logManager;
    }

    @Autowired
    public void setSqlStatusManager(SQLStatusManager sqlStatusManager) {
        this.sqlStatusManager = sqlStatusManager;
    }

}

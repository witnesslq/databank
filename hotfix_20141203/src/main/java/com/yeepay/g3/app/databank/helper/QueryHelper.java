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
import com.yeepay.g3.app.databank.enumtype.DatabaseTypeEnum;
import com.yeepay.g3.app.databank.enumtype.IsolationTypeEnum;
import com.yeepay.g3.app.databank.exception.DatabankException;
import com.yeepay.g3.app.databank.service.LogManager;
import com.yeepay.g3.app.databank.service.RoleManager;
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
import com.yeepay.g3.utils.common.CheckUtils;
import com.yeepay.g3.utils.common.log.Logger;
import com.yeepay.g3.utils.common.log.LoggerFactory;
import com.yeepay.g3.utils.event.ext.BaseEventUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

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

    @Autowired
    private RoleManager roleManager;

    /**
     * 查询准备
     *
     * @param dialect
     * @param originalSql
     * @param lineNum
     * @param quickMode
     * @param skipSqlCheck
     * @return
     */
    public QueryResult queryPrepare(Dialect dialect, String schema, String originalSql,
                                    Long lineNum, IsolationTypeEnum isolation, String quickMode, String skipSqlCheck, Long dsId, Long userId) {
        // SQL 分析
        long start = System.currentTimeMillis();
        QueryResult queryResult = new QueryResult();
        try {
            SQLStatementParser parser = new SQLStatementParser(originalSql);
            SQLSelectStatement sqlSelectStatement = parser.parseSelect();
            SchemaStatVisitor visitor = new SchemaStatVisitor();
            sqlSelectStatement.accept(visitor);

            Set<Map.Entry<TableStat.Name, TableStat>> table = visitor.getTables().entrySet();
            Set<TableStat.Column> columns = visitor.getColumns();
            //获取sql中的schema和table
            Set<String> schemaAndTables = getSchemaAndTable(table, schema);
            //获取sql中所有的schema
            Set<String> schemas = new HashSet<String>();
            for (String schemaAndTable : schemaAndTables) {
                String[] array = schemaAndTable.split("\\.");
                schemas.add(array[0].toUpperCase());
            }
            checkTableAuthority(table, userId, dsId, schemas, schema, schemaAndTables);
            checkColumnAuthority(columns, userId, dsId, schemaAndTables, schema);

            queryResult.setTables(visitor.getTables().size());
            queryResult.setColumns(visitor.getColumns().size());
            queryResult.setConditions(visitor.getConditions().size());
            queryResult.setRelationships(visitor.getRelationships().size());
            queryResult.setOrderBy(visitor.getOrderByColumns().size());
            queryResult.setGroupBy(visitor.getGroupByColumns().size());
//		queryResult.setVariants(visitor.getVariants().size());
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
     * 获取sql语句中的schema和table
     *
     * @param table
     * @param schema
     */
    public Set<String> getSchemaAndTable(Set<Map.Entry<TableStat.Name, TableStat>> table, String schema) {
        //去重复
        Set<String> schemaAndTables = new HashSet<String>();
        for (Map.Entry<TableStat.Name, TableStat> entry : table) {
            String schemaAndTable = entry.getKey().getName();
            if (!schemaAndTable.contains(".")) {
                if ("".equals(schema) || null == schema || schema.length() == 0) {
                    schemaAndTable = "DB2INST." + schemaAndTable;
                    schemaAndTables.add(schemaAndTable);
                } else {
                    schemaAndTable = schema + "." + schemaAndTable;
                }
            }
            schemaAndTables.add(schemaAndTable.toUpperCase());
        }
        return schemaAndTables;
    }


    /**
     * 检查表级权限
     *
     * @param table
     * @param userId
     * @param dsId
     * @param schemas
     * @param schema
     */
    public void checkTableAuthority(Set<Map.Entry<TableStat.Name, TableStat>> table, Long userId, Long dsId,
                                    Set<String> schemas, String schema, Set<String> schemaAndTables) {
        Map<String, List<String>> permissionTables = roleManager.getTablePermission(dsId, userId, schemas);
        Map<String, List<String>> permissionColumns = roleManager.getTableColumnPermision(dsId, userId, schemaAndTables);

        List<String> params = permissionTables.get("notAllow");
        if (!CheckUtils.isEmpty(params)) {
            for (Map.Entry<TableStat.Name, TableStat> entry : table) {
                boolean flag = false;
                String tableName = entry.getKey().getName().toUpperCase();
                if (!tableName.contains(".")) {
                    tableName = schema + "." + tableName;
                }
                //判断禁止的表中是否包含
                for (String notAllowTable : params) {
                    if (notAllowTable.indexOf(tableName) >= 0) {
                        flag = true;
                    }
                    if (flag) {
                        //包含了禁止的表，再判断有没有禁止的列
                        if (isContaniColumn(entry.getKey().getName(), permissionColumns)) {
                            continue;
                        }
                        throw DatabankException.NOT_ALLOW_ACCESS_TBL_EXCEPTION
                                .newInstance("【禁止】无权访问的 Schema 和表: {0} {1}", schema, entry.getKey().getName());
                    }
                }
            }
        }
        params = permissionTables.get("allow");
        if (!CheckUtils.isEmpty(params)) {
            if (null != params && params.size() > 0) {
                for (Map.Entry<TableStat.Name, TableStat> entry : table) {
                    String item = entry.getKey().getName();
                    boolean flag = false;
                    if (!item.contains(".")) {
                        item = schema + "." + item;
                    }
                    for (String allowTable : params) {
                        if (allowTable.indexOf(item.toUpperCase()) >= 0) {
                            flag = true;
                        }
                    }
                    if (!flag) {
                        throw DatabankException.NOT_ALLOW_ACCESS_TBL_EXCEPTION
                                .newInstance("【只允许】无权访问的 Schema 和表: {0} {1}", schema, entry.getKey().getName());
                    }
                }
            }
        }
    }

    /**
     * 判断禁止表中是否有禁止的列
     *
     * @param tableName
     * @param permissionColumns
     * @return
     */
    public boolean isContaniColumn(String tableName, Map<String, List<String>> permissionColumns) {
        List<String> columnParams = permissionColumns.get("notAllowColumn");
        if (!CheckUtils.isNull(columnParams)) {
            for (String column : columnParams) {
                if (column.contains(tableName.toUpperCase())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 检查列级权限
     *
     * @param columns
     * @param userId
     * @param dsId
     * @param schemaAndTables
     * @param schema
     */
    public void checkColumnAuthority(Set<TableStat.Column> columns, Long userId, Long dsId, Set<String> schemaAndTables, String schema) {
        Map<String, List<String>> permissionColumns = roleManager.getTableColumnPermision(dsId, userId, schemaAndTables);
        //列级权限控制
        List<String> columnParams = permissionColumns.get("notAllowColumn");
        if (!CheckUtils.isEmpty(columnParams) && !CheckUtils.isEmpty(columns)) {
            for (TableStat.Column column : columns) {
                if (column.getName().contains("*")) {
                    boolean flag = false;
                    for (String param : columnParams) {
                        if (param.indexOf(column.getTable().toUpperCase()) >= 0) {
                            flag = true;
                        }
                    }
                    if (flag) {
                        throw DatabankException.NOT_ALLOW_ACCESS_TBL_EXCEPTION
                                .newInstance("【禁止】无权访问的 Schema 和列: {0} {1}", schema, column.getName());
                    }
                }
                //获取限定列 表＋列名
                String tableColumn = column.getTable() + "." + column.getName();

                for (String notAllowColumn : columnParams) {
                    if (notAllowColumn.indexOf(tableColumn.toUpperCase()) >= 0) {
                        throw DatabankException.NOT_ALLOW_ACCESS_TBL_EXCEPTION
                                .newInstance("【禁止】无权访问的 Schema 和列: {0} {1}", schema, column.getName());
                    }
                }
            }
        }
        columnParams = permissionColumns.get("allowColumn");
        if (!CheckUtils.isEmpty(columnParams)) {
            for (TableStat.Column column : columns) {
                String columnName = column.getName();
                if (columnName.contains("*")) {
                    throw DatabankException.NOT_ALLOW_ACCESS_TBL_EXCEPTION
                            .newInstance("【只允许】无权访问的 Schema 和列: {0} {1}", schema, column.getName());
                }
                if (!columnName.contains(".")) {
                    columnName = column.getTable() + "." + columnName;
                }
                boolean flag = false;
                for (String columnP : columnParams) {
                    if (columnP.indexOf(columnName.toUpperCase()) >= 0) {
                        flag = true;
                    }
                }
                if (flag) {
                    continue;
                }
                throw DatabankException.NOT_ALLOW_ACCESS_TBL_EXCEPTION
                        .newInstance("【只允许】无权访问的 Schema 和列: {0} {1}", schema, column.getName());

            }
        }
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
        ResultSet rs = null;
        try {
            stmt = dbConn.createStatement();

            // 某些发行版本的 MySQL 不支持 Maxrows, 比如 MariaDB
            // http://cfsimplicity.com/78/the-simplicity-of-migrating-from-mysql-to-mariadb
            if (DatabaseTypeEnum.MYSQL != dataBankSource.getDbType()) {
                stmt.setMaxRows(((Long) ConfigUtils.getAppConfigParam(ConfigEnum.DATABANK_QUERY_STMT_MAXROWS)).intValue());
            }

            stmt.setQueryTimeout(((Long) ConfigUtils.getAppConfigParam(ConfigEnum.DATABANK_QUERY_STMT_TIMEOUT)).intValue());

           /* //设置查询的querytag
            String queryTag = "" + UUID.randomUUID().getMostSignificantBits();

            String setExpalin = "explain plan set querytag = '" + queryTag + "'  for  " + queryResult.getActualSql();
            stmt.execute(setExpalin);
            //保存查询标志
            queryResult.setQueryTag(queryTag);*/

            long start = System.currentTimeMillis();
            rs = stmt.executeQuery(queryResult.getActualSql());
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
                throw new SQLException(errMsg.replaceFirst("DB2 SQL Error:", "出错详情:<br/>"));
            }
            throw new SQLException(errMsg.replace("DB2 SQL Error:", sqlStatus.getExplanation() + "<br/>出错详情:<br/>"));
        } catch (Exception e) {
            LOGGER.warn("SQL 执行失败！dsId:{} 原始sql:{}", dataBankSource.getId(), queryResult.getActualSql());
            throw e;
        } finally {
            BaseEventUtils.sendEventNotInTransaction(EventNameEnum.UPDATE_LOG_INFO.toString(), logId, queryResult);
            DBHelper.close(rs, stmt, dbConn);
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

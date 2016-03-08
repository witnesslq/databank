package com.yeepay.g3.app.databank.database.dialect;

import com.yeepay.g3.app.databank.database.BaseMetaInfoProvider;
import com.yeepay.g3.app.databank.database.MetaInfoProviderFactory;
import com.yeepay.g3.app.databank.database.SchemaTableTuple;
import com.yeepay.g3.app.databank.enumtype.DatabaseTypeEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * title: DB2 数据库元信息提供者<br/>
 * description: 描述<br/>
 * Copyright: Copyright (c)2014<br/>
 * Company: 易宝支付(YeePay)<br/>
 *
 * @author baitao.ji
 * @version 1.0.0
 * @since 16/1/28 16:09
 */
@Component
@Lazy(value = false)
public class DB2MetaInfoProvider extends BaseMetaInfoProvider {

    @PostConstruct
    public void init() {
        MetaInfoProviderFactory.register(this);
    }

    @Override
    public DatabaseTypeEnum getDatabaseType() {
        return DatabaseTypeEnum.DB2;
    }

    @Override
    protected String getSchemaSQL() {
        return "SELECT rtrim(NAME) AS database FROM sysibm.sysschemata s " +
                "WHERE s.DEFINERTYPE='U' AND s.NAME NOT IN ('NULLID','SQLJ','SYSTOOLS','DB2ADMIN','DB2INST1','DB2INST2') " +
                "ORDER BY database ASC";
    }

    @Override
    protected String getSchemaAndTableSQL() {
        return "SELECT rtrim(TABSCHEMA)||'.'||TABNAME AS table_name FROM syscat.tables t " +
                "INNER JOIN sysibm.sysschemata s ON s.NAME=t.TABSCHEMA " +
                "AND s.DEFINERTYPE='U' AND s.NAME NOT IN ('NULLID','SQLJ','SYSTOOLS','DB2ADMIN','DB2INST1','DB2INST2') " +
                "ORDER BY table_name ASC";
    }

    @Override
    protected String getSchemaAndTableColumnSQL(List<SchemaTableTuple> schemaAndTables) {
        StringBuilder sb = new StringBuilder("SELECT rtrim(TABSCHEMA)||'.'||TABNAME||'.'||COLNAME AS nameList FROM syscat.columns t INNER JOIN sysibm.sysschemata s ON s.NAME=t.TABSCHEMA AND s.DEFINERTYPE='U' AND s.NAME NOT IN ('NULLID','SQLJ','SYSTOOLS','DB2ADMIN','DB2INST1','DB2INST2') WHERE 1=0 ");
        for (SchemaTableTuple item : schemaAndTables) {
            sb.append("OR (TABSCHEMA='").append(item.getSchema()).append("' AND TABNAME='").append(item.getTable()).append("') ");
        }
        sb.append("ORDER BY COLNAME,TABNAME,TABSCHEMA");
        return sb.toString();
    }

    @Override
    protected String getTableIndexSQL(String schemaName, String tableName) {
        StringBuilder sb = new StringBuilder("SELECT TABSCHEMA,TABNAME,COLNAMES,UNIQUERULE,SYSTEM_REQUIRED FROM syscat.indexes");
        sb.append(" WHERE TABSCHEMA = '").append(schemaName).append("'");
        if (StringUtils.isNotBlank(tableName)) {
            sb.append(" AND TABNAME = '").append(tableName).append("'");
        }
        sb.append(" ORDER BY TABNAME ASC");
        return sb.toString();
    }

}

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
 * title: MySQL 数据库元信息提供者<br/>
 * description: 描述<br/>
 * Copyright: Copyright (c)2014<br/>
 * Company: 易宝支付(YeePay)<br/>
 *
 * @author baitao.ji
 * @version 1.0.0
 * @since 16/1/28 16:17
 */
@Component
@Lazy(value = false)
public class MySQLMetaInfoProvider extends BaseMetaInfoProvider {

    @PostConstruct
    public void init() {
        MetaInfoProviderFactory.register(this);
    }

    @Override
    public DatabaseTypeEnum getDatabaseType() {
        return DatabaseTypeEnum.MYSQL;
    }

    @Override
    protected String getSchemaSQL() {
        return "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA";
    }

    @Override
    protected String getSchemaAndTableSQL() {
        return "SELECT SCHEMA_NAME+'.'+table_name FROM INFORMATION_SCHEMA.TABLES";
    }

    @Override
    protected String getSchemaAndTableColumnSQL(List<SchemaTableTuple> schemaAndTables) {
        StringBuilder sb = new StringBuilder("SELECT concat_ws('.',TABLE_SCHEMA,TABLE_NAME,COLUMN_NAME) FROM INFORMATION_SCHEMA.COLUMNS WHERE 0=1 ");
        for (SchemaTableTuple item : schemaAndTables) {
            sb.append("OR (TABLE_SCHEMA='").append(item.getSchema()).append("' AND TABLE_NAME='").append(item.getTable()).append("') ");
        }
        sb.append("ORDER BY TABLE_SCHEMA,TABLE_NAME,COLUMN_NAME");
        return sb.toString();
    }

    @Override
    protected String getTableIndexSQL(String schemaName, String tableName) {
        StringBuilder sb = new StringBuilder("SELECT concat_ws(',',TABLE_SCHEMA,TABLE_NAME,'D','0') FROM INFORMATION_SCHEMA.INDEX_STATISTICS");
        sb.append(" WHERE TABLE_SCHEMA = '").append(schemaName).append("'");
        if (StringUtils.isNotBlank(tableName)) {
            sb.append(" AND TABLE_NAME = '").append(tableName).append("'");
        }
        sb.append(" ORDER BY TABLE_SCHEMA,TABLE_SCHEMA,INDEX_NAME");
        return sb.toString();
    }

}

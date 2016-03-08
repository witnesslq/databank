package com.yeepay.g3.app.databank.database;

/**
 * title: Schema Table元组<br/>
 * description: 描述<br/>
 * Copyright: Copyright (c)2014<br/>
 * Company: 易宝支付(YeePay)<br/>
 *
 * @author baitao.ji
 * @version 1.0.0
 * @since 16/1/29 15:42
 */
public class SchemaTableTuple {

    private final String schema;

    private final String table;

    public SchemaTableTuple(String schema, String table) {
        this.schema = schema;
        this.table = table;
    }

    public String getSchema() {
        return schema;
    }

    public String getTable() {
        return table;
    }

}

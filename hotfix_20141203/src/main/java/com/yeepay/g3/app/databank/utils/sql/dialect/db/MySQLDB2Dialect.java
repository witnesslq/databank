package com.yeepay.g3.app.databank.utils.sql.dialect.db;

import com.yeepay.g3.app.databank.enumtype.IsolationTypeEnum;
import com.yeepay.g3.app.databank.utils.sql.dialect.AbstractDialect;
import com.yeepay.g3.core.druid.sql.SQLUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * title: <br/>
 * description: 描述<br/>
 * Copyright: Copyright (c)2014<br/>
 * Company: 易宝支付(YeePay)<br/>
 *
 * @author baitao.ji
 * @version 1.0.0
 * @since 16/1/29 17:39
 */
public class MySQLDB2Dialect extends AbstractDialect {

    @Override
    public String format(String sql) {
        return SQLUtils.format(sql, "MYSQL");
    }

    @Override
    public String selectTopN(String sql, long limit, IsolationTypeEnum isolation, String quickMode) {
        // 重新限定抓取记录数
        String upperSql = StringUtils.upperCase(sql);
        if (StringUtils.indexOf(upperSql, " LIMIT ") > 0) {
            long userLimit = Integer.parseInt(StringUtils.trim(StringUtils.substringAfter(upperSql, "LIMIT")));
            limit = Math.min(limit, userLimit);
            upperSql = StringUtils.substringBefore(upperSql, " LIMIT");
        }

        return upperSql + " LIMIT " + limit;
    }

    @Override
    public String selectOptimize(String sql) {
        return sql;
    }

}

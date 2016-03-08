/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay) 
 */
package com.yeepay.g3.app.databank.utils.sql.dialect.db;

import com.yeepay.g3.app.databank.enumtype.IsolationTypeEnum;
import com.yeepay.g3.app.databank.utils.sql.dialect.AbstractDialect;
import com.yeepay.g3.core.druid.sql.SQLUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>Title: DB2 方言</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-4-10 上午11:03
 */
public class DB2Dialect extends AbstractDialect {

    @Override
    public String format(String sql) {
        return SQLUtils.format(sql, "DB2");
    }

    @Override
    public String selectTopN(String sql, long limit, IsolationTypeEnum isolation, String quickMode) {
        // WITH UR 和 FETCH FIRST 1 ROWS ONLY 不能同时存在
        String subSql = sql.substring(sql.length() - 7, sql.length());
        if (StringUtils.equalsIgnoreCase("WITH", StringUtils.substring(subSql, -7, -3))) {
            sql = StringUtils.substring(sql, 0, -8);
        }

        // 重新限定抓取记录数
        int index, index2;
        String upperSql = sql.toUpperCase();
        if ((index = upperSql.indexOf("FETCH FIRST")) > 0 && (index2 = upperSql.indexOf("ROWS ONLY")) > 0) {
            long userLimit = Integer.parseInt(sql.substring(index + 12, index2 - 1));
            limit = Math.min(limit, userLimit);
            sql = sql.substring(0, index - 1);
        }

        if (null == quickMode || !"1".equals(quickMode)) {
            return sql + " FETCH FIRST " + limit + " ROWS ONLY WITH " + isolation.getValue();
        } else {
            return sql + " FETCH FIRST " + limit + " ROWS ONLY OPTIMIZE FOR " + limit + " ROWS WITH " + isolation.getValue();
        }
    }

    @Override
    public String selectOptimize(String sql) {
        // 在DB2中，共有四种隔离级：RS,RR,CS,UR
        // UR:UR-Uncommitted Read 未提交读, 允许脏读，不加行锁
        // 避免重复添加 WITH UR
        if ("WITH UR".equalsIgnoreCase(sql.substring(sql.length() - 7, sql.length()))) {
            return sql;
        } else {
            return sql + " WITH UR";
        }
    }

    @Override
    public boolean wall(String sql) {
        //return sql.toUpperCase().indexOf("DATABANK.") > 0;
        return true;
    }

}

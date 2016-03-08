/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay) 
 */
package com.yeepay.g3.app.databank.utils.sql.dialect;

import com.yeepay.g3.app.databank.enumtype.IsolationTypeEnum;
import com.yeepay.g3.app.databank.utils.sql.dialect.db.DB2Dialect;
import com.yeepay.g3.app.databank.utils.sql.dialect.db.MySQLDB2Dialect;

/**
 * <p>Title: 类功能描述</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-4-10 上午11:11
 */
public class AbstractDialect implements Dialect {

	private Dialect dialect;

	public AbstractDialect() {
	}

	public AbstractDialect(String dbType) {
		// 查找合适的数据库驱动
		dbType = dbType.toLowerCase();
		if ("db2".equals(dbType)) {
			this.dialect = new DB2Dialect();
		} else {
			this.dialect = new MySQLDB2Dialect();
		}
	}

	@Override
	public String prepare(String sql) {
		// 去除多余空格
		sql = sql.trim().replaceAll(" +", " ").replaceAll(" ,", ",").replaceAll(", ", ",");
		int index = sql.lastIndexOf(";");
		int index2 = sql.lastIndexOf("；");
		if (index == sql.length() - 1) {
			sql = sql.substring(0, index);
		} else if (index2 == sql.length() - 1) {
			sql = sql.substring(0, index2);
		}
		return sql;
	}

	@Override
	public String format(String sql) {
		return dialect.format(sql);
	}

	@Override
	public String selectTopN(String sql, long limit, IsolationTypeEnum isolation, String quickMode) {
		return dialect.selectTopN(sql, limit, isolation, quickMode);
	}

	@Override
	public String selectOptimize(String sql) {
		return dialect.selectOptimize(sql);
	}

	@Override
	public boolean wall(String sql) {
		return dialect.wall(sql);
	}

}

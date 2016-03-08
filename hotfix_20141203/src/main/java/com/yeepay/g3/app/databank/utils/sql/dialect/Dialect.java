/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay) 
 */
package com.yeepay.g3.app.databank.utils.sql.dialect;

import com.yeepay.g3.app.databank.enumtype.IsolationTypeEnum;

/**
 * <p>Title: 数据库方言接口</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-4-10 上午11:01
 */
public interface Dialect {

	/**
	 * 预处理校验 SQL
	 *
	 * @param sql 待处理 SQL 语句
	 * @return
	 */
	public String prepare(String sql);

	/**
	 * 格式化 SQL
	 *
	 * @param sql 待处理 SQL 语句
	 * @return
	 */
	public String format(String sql);

	/**
	 * ：返回指定个数的结果集
	 *
	 * @param sql 待处理 SQL 语句
	 * @return
	 */
	public String selectTopN(String sql, long limit, IsolationTypeEnum isolation, String quickMode);

	/**
	 * 优化 SQL
	 *
	 * @param sql 待处理 SQL 语句
	 * @return
	 */
	public String selectOptimize(String sql);

	/**
	 * 过滤 SQL 语句
	 *
	 * @param sql
	 * @return
	 */
	public boolean wall(String sql);

}

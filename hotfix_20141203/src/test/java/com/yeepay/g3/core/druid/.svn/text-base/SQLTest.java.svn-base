/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay)
 */
package com.yeepay.g3.core.druid;

import com.yeepay.g3.core.druid.sql.ast.SQLStatement;
import com.yeepay.g3.core.druid.sql.parser.SQLStatementParser;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTOutputVisitor;

import java.util.List;

/**
 * <p>Title: 功能</p>
 * <p>Description: 描述</p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-7-19 下午3:18
 */
public class SQLTest {

	private static String sql = "select * from EMP.TBL_USER where isadmin='0' group by primarydepartmentid order by userid";

	public static void main(String[] args) {
		StringBuilder out = new StringBuilder();
		SQLASTOutputVisitor visitor = new SQLASTOutputVisitor(out);
		SQLStatementParser parser = new SQLStatementParser(sql);
		List<SQLStatement> statementList = parser.parseStatementList();
		System.out.println(out.toString());
	}

}

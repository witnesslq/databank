package com.yeepay.g3.core.druid.sql;

import com.yeepay.g3.core.druid.sql.ast.SQLStatement;
import com.yeepay.g3.core.druid.sql.ast.expr.SQLAggregateExpr;
import com.yeepay.g3.core.druid.sql.ast.expr.SQLAllColumnExpr;
import com.yeepay.g3.core.druid.sql.ast.statement.*;

import java.util.List;

public class PagerUtils {

	public static String count(String sql, String dbType) {
		List<SQLStatement> stmtList = SQLUtils.parseStatements(sql, dbType);

		if (stmtList.size() != 1) {
			throw new IllegalArgumentException("sql not support count : " + sql);
		}

		SQLStatement stmt = stmtList.get(0);

		if (!(stmt instanceof SQLSelectStatement)) {
			throw new IllegalArgumentException("sql not support count : " + sql);
		}

		SQLSelectStatement selectStmt = (SQLSelectStatement) stmt;
		return count(selectStmt.getSelect(), dbType);
	}

	public static String limit(String sql, String dbType, int offset, int count) {
		List<SQLStatement> stmtList = SQLUtils.parseStatements(sql, dbType);

		if (stmtList.size() != 1) {
			throw new IllegalArgumentException("sql not support count : " + sql);
		}

		SQLStatement stmt = stmtList.get(0);

		if (!(stmt instanceof SQLSelectStatement)) {
			throw new IllegalArgumentException("sql not support count : " + sql);
		}

		SQLSelectStatement selectStmt = (SQLSelectStatement) stmt;

		return limit(selectStmt.getSelect(), dbType, offset, count);
	}

	public static String limit(SQLSelect select, String dbType, int offset, int count) {
		SQLSelectQuery query = select.getQuery();

		if (query instanceof SQLSelectQueryBlock) {
			return limitQueryBlock(select, dbType, offset, count);
		}

		throw new UnsupportedOperationException();
	}

	private static String limitQueryBlock(SQLSelect select, String dbType, int offset, int count) {
		SQLSelectQueryBlock queryBlock = (SQLSelectQueryBlock) select.getQuery();
		throw new UnsupportedOperationException();
	}

	private static String count(SQLSelect select, String dbType) {
		if (select.getOrderBy() != null) {
			select.setOrderBy(null);
		}

		SQLSelectQuery query = select.getQuery();
		clearOrderBy(query);

		if (query instanceof SQLSelectQueryBlock) {
			SQLSelectItem countItem = createCountItem(dbType);

			SQLSelectQueryBlock queryBlock = (SQLSelectQueryBlock) query;

			if (queryBlock.getGroupBy() != null && queryBlock.getGroupBy().getItems().size() > 0) {
				return createCountUseSubQuery(select, dbType);
			}

			queryBlock.getSelectList().clear();
			queryBlock.getSelectList().add(countItem);
			return SQLUtils.toSQLString(select);
		} else if (query instanceof SQLUnionQuery) {
			return createCountUseSubQuery(select, dbType);
		}

		throw new IllegalStateException();
	}

	private static String createCountUseSubQuery(SQLSelect select, String dbType) {
		SQLSelectQueryBlock countSelectQuery = createQueryBlock(dbType);

		SQLSelectItem countItem = createCountItem(dbType);
		countSelectQuery.getSelectList().add(countItem);

		countSelectQuery.setFrom(new SQLSubqueryTableSource(select));

		SQLSelect countSelect = new SQLSelect(countSelectQuery);
		SQLSelectStatement countStmt = new SQLSelectStatement(countSelect);

		return SQLUtils.toSQLString(countStmt);
	}

	private static SQLSelectQueryBlock createQueryBlock(String dbType) {
		return new SQLSelectQueryBlock();
	}

	private static SQLSelectItem createCountItem(String dbType) {
		SQLAggregateExpr countExpr;

		countExpr = new SQLAggregateExpr("COUNT");
		countExpr.getArguments().add(new SQLAllColumnExpr());

		SQLSelectItem countItem = new SQLSelectItem(countExpr);
		return countItem;
	}

	private static void clearOrderBy(SQLSelectQuery query) {
		if (query instanceof SQLSelectQueryBlock) {
			SQLSelectQueryBlock queryBlock = (SQLSelectQueryBlock) query;
			return;
		}

		if (query instanceof SQLUnionQuery) {
			SQLUnionQuery union = (SQLUnionQuery) query;
			if (union.getOrderBy() != null) {
				union.setOrderBy(null);
			}
			clearOrderBy(union.getLeft());
			clearOrderBy(union.getRight());
		}
	}
}

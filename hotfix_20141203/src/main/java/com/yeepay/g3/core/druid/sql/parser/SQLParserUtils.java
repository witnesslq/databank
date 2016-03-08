package com.yeepay.g3.core.druid.sql.parser;

public class SQLParserUtils {

	public static SQLStatementParser createSQLStatementParser(String sql, String dbType) {
		return new SQLStatementParser(sql);
	}

	public static SQLExprParser createExprParser(String sql, String dbType) {
		return new SQLExprParser(sql);
	}
}

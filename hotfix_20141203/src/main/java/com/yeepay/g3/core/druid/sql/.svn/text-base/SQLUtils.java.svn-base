package com.yeepay.g3.core.druid.sql;

import com.yeepay.g3.core.druid.sql.ast.SQLExpr;
import com.yeepay.g3.core.druid.sql.ast.SQLObject;
import com.yeepay.g3.core.druid.sql.ast.SQLStatement;
import com.yeepay.g3.core.druid.sql.parser.*;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTOutputVisitor;
import com.yeepay.g3.core.druid.sql.visitor.SchemaStatVisitor;
import com.yeepay.g3.core.druid.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SQLUtils {

	private static final Logger LOG = LoggerFactory.getLogger(SQLUtils.class);

	public static String toSQLString(SQLObject sqlObject) {
		StringBuilder out = new StringBuilder();
		sqlObject.accept(new SQLASTOutputVisitor(out));

		String sql = out.toString();
		return sql;
	}

	public static SQLExpr toSQLExpr(String sql, String dbType) {
		SQLExprParser parser = SQLParserUtils.createExprParser(sql, dbType);
		SQLExpr expr = parser.expr();

		if (parser.getLexer().token() != Token.EOF) {
			throw new ParserException("SQL 表达式不合法 : " + sql);
		}

		return expr;
	}

	public static List<SQLStatement> toStatementList(String sql, String dbType) {
		SQLStatementParser parser = SQLParserUtils.createSQLStatementParser(sql, dbType);
		return parser.parseStatementList();
	}

	public static SQLExpr toSQLExpr(String sql) {
		return toSQLExpr(sql, null);
	}

	public static String format(String sql, String dbType) {
		return format(sql, dbType, null);
	}

	public static String format(String sql, String dbType, List<Object> parameters) {
		try {
			List<SQLStatement> statementList = toStatementList(sql, dbType);

			return toSQLString(statementList, dbType, parameters);
		} catch (ParserException ex) {
			LOG.warn("format error", ex);
			return sql;
		}
	}

	public static String toSQLString(List<SQLStatement> statementList, String dbType) {
		return toSQLString(statementList, dbType, null);
	}

	public static String toSQLString(List<SQLStatement> statementList, String dbType, List<Object> parameters) {
		StringBuilder out = new StringBuilder();
		SQLASTOutputVisitor visitor = createFormatOutputVisitor(out, statementList, dbType);
		if (parameters != null) {
			visitor.setParameters(parameters);
		}

		for (SQLStatement stmt : statementList) {
			stmt.accept(visitor);
		}

		return out.toString();
	}

	public static SQLASTOutputVisitor createFormatOutputVisitor(Appendable out, List<SQLStatement> statementList,
																String dbType) {
		return new SQLASTOutputVisitor(out);
	}

	public static SchemaStatVisitor createSchemaStatVisitor(List<SQLStatement> statementList, String dbType) {
		return new SchemaStatVisitor();
	}

	public static List<SQLStatement> parseStatements(String sql, String dbType) {
		SQLStatementParser parser = SQLParserUtils.createSQLStatementParser(sql, dbType);
		List<SQLStatement> stmtList = parser.parseStatementList();
		if (parser.getLexer().token() != Token.EOF) {
			throw new RuntimeException("syntax error : " + sql);
		}
		return stmtList;
	}

	/**
	 * @param columnName
	 * @param tableAlias
	 * @param pattern    if pattern is null,it will be set {%Y-%m-%d %H:%i:%s} as mysql default value and set {yyyy-mm-dd hh24:mi:ss} as oracle default value
	 * @param dbType     {@link com.yeepay.g3.core.druid.util.JdbcConstants} if dbType is null ,it will be set the mysql as a default value
	 */
	public static String buildToDate(String columnName, String tableAlias, String pattern, String dbType) {
		StringBuilder sql = new StringBuilder();
		if (StringUtils.isEmpty(columnName))
			return "";
		sql.append("(");
		if (!StringUtils.isEmpty(tableAlias))
			sql.append(tableAlias).append(".");
		sql.append(columnName).append(",");
		sql.append("'");
		sql.append(pattern);
		sql.append("')");
		return sql.toString();
	}
}

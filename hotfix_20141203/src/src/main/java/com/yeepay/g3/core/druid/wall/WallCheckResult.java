package com.yeepay.g3.core.druid.wall;

import com.yeepay.g3.core.druid.sql.ast.SQLStatement;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class WallCheckResult {

	private final List<SQLStatement> statementList;
	private final Map<String, WallSqlTableStat> tableStats;

	private final List<Violation> violations;

	private final Map<String, WallSqlFunctionStat> functionStats;

	private final boolean syntaxError;

	private final WallSqlStat sqlStat;

	public WallCheckResult() {
		this(null);
	}

	public WallCheckResult(WallSqlStat sqlStat, List<SQLStatement> stmtList) {
		if (sqlStat != null) {
			tableStats = sqlStat.getTableStats();
			violations = sqlStat.getViolations();
			functionStats = sqlStat.getFunctionStats();
			statementList = stmtList;
			syntaxError = sqlStat.isSyntaxError();
		} else {
			tableStats = Collections.emptyMap();
			violations = Collections.emptyList();
			functionStats = Collections.emptyMap();
			statementList = stmtList;
			syntaxError = false;
		}
		this.sqlStat = sqlStat;
	}

	public WallCheckResult(WallSqlStat sqlStat) {
		this(sqlStat, Collections.<SQLStatement>emptyList());
	}

	public WallCheckResult(WallSqlStat sqlStat, List<Violation> violations, Map<String, WallSqlTableStat> tableStats,
						   Map<String, WallSqlFunctionStat> functionStats, List<SQLStatement> statementList,
						   boolean syntaxError) {
		this.sqlStat = sqlStat;
		this.tableStats = tableStats;
		this.violations = violations;
		this.functionStats = functionStats;
		this.statementList = statementList;
		this.syntaxError = syntaxError;
	}

	public List<Violation> getViolations() {
		return violations;
	}

	public List<SQLStatement> getStatementList() {
		return statementList;
	}

	public Map<String, WallSqlTableStat> getTableStats() {
		return tableStats;
	}

	public Map<String, WallSqlFunctionStat> getFunctionStats() {
		return functionStats;
	}

	public boolean isSyntaxError() {
		return syntaxError;
	}


	public WallSqlStat getSqlStat() {
		return sqlStat;
	}
}

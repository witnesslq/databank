package com.yeepay.g3.core.druid.wall;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

public class WallSqlStat {

	private volatile long executeCount;
	private volatile long effectRowCount;

	static final AtomicLongFieldUpdater<WallSqlStat> executeCountUpdater = AtomicLongFieldUpdater.newUpdater(WallSqlStat.class,
			"executeCount");
	static final AtomicLongFieldUpdater<WallSqlStat> effectRowCountUpdater = AtomicLongFieldUpdater.newUpdater(WallSqlStat.class,
			"effectRowCount");

	private final Map<String, WallSqlTableStat> tableStats;

	private final List<Violation> violations;

	private final Map<String, WallSqlFunctionStat> functionStats;

	private final boolean syntaxError;

	public WallSqlStat(Map<String, WallSqlTableStat> tableStats, Map<String, WallSqlFunctionStat> functionStats,
					   boolean syntaxError) {
		this(tableStats, functionStats, Collections.<Violation>emptyList(), syntaxError);
	}

	public WallSqlStat(Map<String, WallSqlTableStat> tableStats, Map<String, WallSqlFunctionStat> functionStats,
					   List<Violation> violations, boolean syntaxError) {
		this.violations = violations;
		this.tableStats = tableStats;
		this.functionStats = functionStats;
		this.syntaxError = syntaxError;
	}

	public long incrementAndGetExecuteCount() {
		return executeCountUpdater.incrementAndGet(this);
	}

	public long getExecuteCount() {
		return executeCount;
	}

	public long incrementAndGetEffectRowCount() {
		return effectRowCountUpdater.incrementAndGet(this);
	}

	public long addAndGetEffectRowCount(long delta) {
		return effectRowCountUpdater.addAndGet(this, delta);
	}

	public long getEffectRowCount() {
		return effectRowCount;
	}

	public Map<String, WallSqlTableStat> getTableStats() {
		return tableStats;
	}

	public Map<String, WallSqlFunctionStat> getFunctionStats() {
		return functionStats;
	}

	public List<Violation> getViolations() {
		return violations;
	}

	public boolean isSyntaxError() {
		return syntaxError;
	}
}

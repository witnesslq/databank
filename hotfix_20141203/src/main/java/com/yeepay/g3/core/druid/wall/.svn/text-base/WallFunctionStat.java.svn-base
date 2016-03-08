package com.yeepay.g3.core.druid.wall;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

public class WallFunctionStat {

	private volatile long invokeCount;
	static final AtomicLongFieldUpdater<WallFunctionStat> invokeCountUpdater = AtomicLongFieldUpdater.newUpdater(WallFunctionStat.class,
			"invokeCount");

	public long getInvokeCount() {
		return invokeCount;
	}

	public void incrementInvokeCount() {
		invokeCountUpdater.incrementAndGet(this);
	}

	public void addSqlFunctionStat(WallSqlFunctionStat sqlFunctionStat) {
		this.invokeCount += sqlFunctionStat.getInvokeCount();
	}

	public String toString() {
		return "{\"invokeCount\":" + invokeCount + "}";
	}

	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("invokeCount", invokeCount);
		return map;
	}

}

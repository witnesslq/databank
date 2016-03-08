package com.yeepay.g3.core.druid.wall;

public class WallSqlFunctionStat {

	private int invokeCount;

	public int getInvokeCount() {
		return invokeCount;
	}

	public void incrementInvokeCount() {
		this.invokeCount++;
	}

	public void addInvokeCount(int value) {
		this.invokeCount += value;
	}
}

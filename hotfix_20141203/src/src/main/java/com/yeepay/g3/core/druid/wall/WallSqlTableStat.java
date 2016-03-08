package com.yeepay.g3.core.druid.wall;


public class WallSqlTableStat {

	private int selectCount;
	private int selectIntoCount;
	private int insertCount;
	private int updateCount;
	private int deleteCount;
	private int truncateCount;
	private int createCount;
	private int alterCount;
	private int dropCount;
	private int replaceCount;
	private int showCount;

	public int getReplaceCount() {
		return replaceCount;
	}

	public int incrementReplaceCount() {
		return replaceCount++;
	}

	public void addReplaceCount(int value) {
		this.replaceCount += value;
	}

	public int getSelectCount() {
		return selectCount;
	}

	public void incrementSelectCount() {
		this.selectCount++;
	}

	public void addSelectCount(int value) {
		this.selectCount += value;
	}

	public int getSelectIntoCount() {
		return selectIntoCount;
	}

	public void incrementSelectIntoCount() {
		this.selectIntoCount++;
	}

	public void addSelectIntoCount(int value) {
		this.selectIntoCount += value;
	}

	public int getInsertCount() {
		return insertCount;
	}

	public void incrementInsertCount() {
		this.insertCount++;
	}

	public void addInsertCount(int value) {
		this.insertCount += value;
	}

	public int getUpdateCount() {
		return updateCount;
	}

	public void incrementUpdateCount() {
		this.updateCount++;
	}

	public void addUpdateCount(int value) {
		this.deleteCount += value;
	}

	public int getDeleteCount() {
		return deleteCount;
	}

	public void incrementDeleteCount() {
		this.deleteCount++;
	}

	public void addDeleteCount(int value) {
		this.deleteCount += value;
	}

	public int getTruncateCount() {
		return truncateCount;
	}

	public void incrementTruncateCount() {
		this.truncateCount++;
	}

	public void addTruncateCount(int value) {
		this.truncateCount += value;
	}

	public int getCreateCount() {
		return createCount;
	}

	public void incrementCreateCount() {
		this.createCount++;
	}

	public void addCreateCount(int value) {
		this.createCount += value;
	}

	public int getAlterCount() {
		return alterCount;
	}

	public void incrementAlterCount() {
		this.alterCount++;
	}

	public void addAlterCount(int value) {
		this.alterCount += value;
	}

	public int getDropCount() {
		return dropCount;
	}

	public void incrementDropCount() {
		this.dropCount++;
	}

	public void addDropCount(int value) {
		this.dropCount += value;
	}


	public int getShowCount() {
		return showCount;
	}

	public void incrementShowCount() {
		this.showCount++;
	}
}

package com.yeepay.g3.core.druid.wall;

import com.yeepay.g3.core.druid.support.json.JSONUtils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;


public class WallTableStat {

	private volatile long selectCount;
	private volatile long selectIntoCount;
	private volatile long insertCount;
	private volatile long updateCount;
	private volatile long deleteCount;
	private volatile long truncateCount;
	private volatile long createCount;
	private volatile long alterCount;
	private volatile long dropCount;
	private volatile long replaceCount;
	private volatile long deleteDataCount;
	private volatile long updateDataCount;
	private volatile long insertDataCount;
	private volatile long fetchRowCount;

	static final AtomicLongFieldUpdater<WallTableStat> selectCountUpdater = AtomicLongFieldUpdater.newUpdater(WallTableStat.class,
			"selectCount");
	static final AtomicLongFieldUpdater<WallTableStat> selectIntoCountUpdater = AtomicLongFieldUpdater.newUpdater(WallTableStat.class,
			"selectIntoCount");
	static final AtomicLongFieldUpdater<WallTableStat> insertCountUpdater = AtomicLongFieldUpdater.newUpdater(WallTableStat.class,
			"insertCount");
	static final AtomicLongFieldUpdater<WallTableStat> updateCountUpdater = AtomicLongFieldUpdater.newUpdater(WallTableStat.class,
			"updateCount");
	static final AtomicLongFieldUpdater<WallTableStat> deleteCountUpdater = AtomicLongFieldUpdater.newUpdater(WallTableStat.class,
			"deleteCount");
	static final AtomicLongFieldUpdater<WallTableStat> truncateCountUpdater = AtomicLongFieldUpdater.newUpdater(WallTableStat.class,
			"truncateCount");
	static final AtomicLongFieldUpdater<WallTableStat> createCountUpdater = AtomicLongFieldUpdater.newUpdater(WallTableStat.class,
			"createCount");
	static final AtomicLongFieldUpdater<WallTableStat> alterCountUpdater = AtomicLongFieldUpdater.newUpdater(WallTableStat.class,
			"alterCount");
	static final AtomicLongFieldUpdater<WallTableStat> dropCountUpdater = AtomicLongFieldUpdater.newUpdater(WallTableStat.class,
			"dropCount");
	static final AtomicLongFieldUpdater<WallTableStat> replaceCountUpdater = AtomicLongFieldUpdater.newUpdater(WallTableStat.class,
			"replaceCount");

	static final AtomicLongFieldUpdater<WallTableStat> deleteDataCountUpdater = AtomicLongFieldUpdater.newUpdater(WallTableStat.class,
			"deleteDataCount");
	static final AtomicLongFieldUpdater<WallTableStat> insertDataCountUpdater = AtomicLongFieldUpdater.newUpdater(WallTableStat.class,
			"insertDataCount");
	static final AtomicLongFieldUpdater<WallTableStat> updateDataCountUpdater = AtomicLongFieldUpdater.newUpdater(WallTableStat.class,
			"updateDataCount");

	static final AtomicLongFieldUpdater<WallTableStat> fetchRowCountUpdater = AtomicLongFieldUpdater.newUpdater(WallTableStat.class,
			"fetchRowCount");

	public WallTableStat() {

	}

	public long getSelectCount() {
		return selectCount;
	}

	public long getSelectIntoCount() {
		return selectIntoCount;
	}

	public long getInsertCount() {
		return insertCount;
	}

	public long getUpdateCount() {
		return updateCount;
	}

	public long getDeleteCount() {
		return deleteCount;
	}

	public long getTruncateCount() {
		return truncateCount;
	}

	public long getCreateCount() {
		return createCount;
	}

	public long getAlterCount() {
		return alterCount;
	}

	public long getDropCount() {
		return dropCount;
	}

	public long getReplaceCount() {
		return replaceCount;
	}

	public long getDeleteDataCount() {
		return this.deleteDataCount;
	}

	public void addDeleteDataCount(long delta) {
		deleteDataCountUpdater.addAndGet(this, delta);
	}

	public long getUpdateDataCount() {
		return this.updateDataCount;
	}

	public long getInsertDataCount() {
		return this.insertDataCount;
	}

	public void addInsertDataCount(long delta) {
		insertDataCountUpdater.addAndGet(this, delta);
	}

	public void addUpdateDataCount(long delta) {
		updateDataCountUpdater.addAndGet(this, delta);
	}

	public long getFetchRowCount() {
		return fetchRowCount;
	}

	public void addFetchRowCount(long delta) {
		fetchRowCountUpdater.addAndGet(this, delta);
	}

	public void addSqlTableStat(WallSqlTableStat stat) {
		{
			long val = stat.getSelectCount();
			if (val > 0) {
				selectCountUpdater.addAndGet(this, val);
			}
		}
		{
			long val = stat.getSelectIntoCount();
			if (val > 0) {
				selectIntoCountUpdater.addAndGet(this, val);
			}
		}
		{
			long val = stat.getInsertCount();
			if (val > 0) {
				insertCountUpdater.addAndGet(this, val);
			}
		}
		{
			long val = stat.getUpdateCount();
			if (val > 0) {
				updateCountUpdater.addAndGet(this, val);
			}
		}
		{
			long val = stat.getDeleteCount();
			if (val > 0) {
				deleteCountUpdater.addAndGet(this, val);
			}
		}
		{
			long val = stat.getAlterCount();
			if (val > 0) {
				alterCountUpdater.addAndGet(this, val);
			}
		}
		{
			long val = stat.getTruncateCount();
			if (val > 0) {
				truncateCountUpdater.addAndGet(this, val);
			}
		}
		{
			long val = stat.getCreateCount();
			if (val > 0) {
				createCountUpdater.addAndGet(this, val);
			}
		}
		{
			long val = stat.getDropCount();
			if (val > 0) {
				dropCountUpdater.addAndGet(this, val);
			}
		}
		{
			long val = stat.getReplaceCount();
			if (val > 0) {
				replaceCountUpdater.addAndGet(this, val);
			}
		}
	}

	public String toString() {

		Map<String, Object> map = toMap();

		return JSONUtils.toJSONString(map);
	}

	public Map<String, Object> toMap() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		return toMap(map);
	}

	public Map<String, Object> toMap(Map<String, Object> map) {
		if (selectCount > 0) {
			map.put("selectCount", selectCount);
		}
		if (deleteCount > 0) {
			map.put("deleteCount", deleteCount);
		}
		if (insertCount > 0) {
			map.put("insertCount", insertCount);
		}
		if (updateCount > 0) {
			map.put("updateCount", updateCount);
		}
		if (alterCount > 0) {
			map.put("alterCount", alterCount);
		}
		if (dropCount > 0) {
			map.put("dropCount", dropCount);
		}
		if (createCount > 0) {
			map.put("createCount", createCount);
		}
		if (truncateCount > 0) {
			map.put("truncateCount", truncateCount);
		}
		if (replaceCount > 0) {
			map.put("replaceCount", replaceCount);
		}
		if (deleteDataCount > 0) {
			map.put("deleteDataCount", deleteDataCount);
		}
		if (fetchRowCount > 0) {
			map.put("fetchRowCount", fetchRowCount);
		}
		if (updateDataCount > 0) {
			map.put("updateDataCount", updateDataCount);
		}
		return map;
	}
}

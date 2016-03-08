/**
 * Copyright: Copyright (c)2011
 * Company: 易宝支付(YeePay) 
 */
package com.yeepay.g3.app.databank.utils.result;

import com.google.common.collect.Lists;
import com.yeepay.g3.app.databank.enumtype.LogLevelEnum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: Json 数据库查询结果</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c)2011</p>
 * <p>Company: 易宝支付(YeePay)</p>
 *
 * @author baitao.ji
 * @version 0.1, 13-3-21 下午3:55
 */
public class QueryResult implements Serializable {

	private String loginName;

	private LogLevelEnum level = LogLevelEnum.SELECT;

	/**
	 * Schema
	 */
	private String schema;

	/**
	 * 实际执行语句
	 */
	private String actualSql;

	/**
	 * 表头
	 */
	private List<String> header = Lists.newArrayList();

	/**
	 * 数据
	 */
	private List<Map<String, String>> content = new ArrayList<Map<String, String>>();

	/**
	 * 数据条数
	 */
	private long size = 0;

	private int tables;

	private int columns;

	private int conditions;

	private int relationships;

	private int orderBy;

	private int groupBy;

//	private int variants;

	/**
	 * 执行结果
	 */
	private String status = "00000";

	/**
	 * 分析用时
	 */
	private long analysisMilliSecond = 0;

	/**
	 * 查询用时
	 */
	private long usedMilliSecond = 0;

	/**
	 * 开始查询时间
	 */
	private Date startDateTime;

	public QueryResult() {
		// Do nothing
	}

	public QueryResult(String loginName, LogLevelEnum level, String actualSql) {
		this.loginName = loginName;
		this.level = level;
		this.actualSql = actualSql;
	}

	public QueryResult(List<String> header, List<Map<String, String>> content, long usedMilliSecond, Date startDateTime) {
		this.header = header;
		this.content = content;
		this.size = content.size();
		this.usedMilliSecond = usedMilliSecond;
		this.startDateTime = startDateTime;
	}

	public QueryResult(int size, long usedMilliSecond, Date startDateTime) {
		this.size = size;
		this.usedMilliSecond = usedMilliSecond;
		this.startDateTime = startDateTime;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public LogLevelEnum getLevel() {
		return level;
	}

	public void setLevel(LogLevelEnum level) {
		this.level = level;
	}

	public List<String> getHeader() {
		return header;
	}

	public void setHeader(List<String> header) {
		this.header = header;
	}

	public List<Map<String, String>> getContent() {
		return content;
	}

	public void setContent(List<Map<String, String>> content) {
		this.content = content;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getActualSql() {
		return actualSql;
	}

	public void setActualSql(String actualSql) {
		this.actualSql = actualSql;
	}

	public long getAnalysisMilliSecond() {
		return analysisMilliSecond;
	}

	public void setAnalysisMilliSecond(long analysisMilliSecond) {
		this.analysisMilliSecond = analysisMilliSecond;
	}

	public long getUsedMilliSecond() {
		return usedMilliSecond;
	}

	public void setUsedMilliSecond(long usedMilliSecond) {
		this.usedMilliSecond = usedMilliSecond;
	}

	public Date getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTables() {
		return tables;
	}

	public void setTables(int tables) {
		this.tables = tables;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public int getConditions() {
		return conditions;
	}

	public void setConditions(int conditions) {
		this.conditions = conditions;
	}

	public int getRelationships() {
		return relationships;
	}

	public void setRelationships(int relationships) {
		this.relationships = relationships;
	}

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	public int getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(int groupBy) {
		this.groupBy = groupBy;
	}

}

package com.yeepay.g3.core.druid.sql.ast.statement;

import com.yeepay.g3.core.druid.sql.ast.SQLName;
import com.yeepay.g3.core.druid.sql.ast.SQLStatementImpl;

import java.util.ArrayList;
import java.util.List;

public class SQLCreateIndexStatement extends SQLStatementImpl implements SQLDDLStatement {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private SQLName name;

	private SQLTableSource table;

	private List<SQLSelectOrderByItem> items = new ArrayList<SQLSelectOrderByItem>();

	private String type;

	public SQLTableSource getTable() {
		return table;
	}

	public void setTable(SQLName table) {
		this.setTable(new SQLExprTableSource(table));
	}

	public void setTable(SQLTableSource table) {
		this.table = table;
	}

	public List<SQLSelectOrderByItem> getItems() {
		return items;
	}

	public void setItems(List<SQLSelectOrderByItem> items) {
		this.items = items;
	}

	public SQLName getName() {
		return name;
	}

	public void setName(SQLName name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}

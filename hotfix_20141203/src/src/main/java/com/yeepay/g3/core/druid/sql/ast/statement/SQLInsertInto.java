package com.yeepay.g3.core.druid.sql.ast.statement;

import com.yeepay.g3.core.druid.sql.ast.SQLExpr;
import com.yeepay.g3.core.druid.sql.ast.SQLName;
import com.yeepay.g3.core.druid.sql.ast.SQLObjectImpl;
import com.yeepay.g3.core.druid.sql.ast.statement.SQLInsertStatement.ValuesClause;

import java.util.ArrayList;
import java.util.List;

public abstract class SQLInsertInto extends SQLObjectImpl {

	private static final long serialVersionUID = 1L;
	protected SQLExprTableSource tableSource;

	protected final List<SQLExpr> columns = new ArrayList<SQLExpr>();
	protected ValuesClause values;
	protected SQLSelect query;

	public SQLInsertInto() {

	}

	public String getAlias() {
		return tableSource.getAlias();
	}

	public void setAlias(String alias) {
		this.tableSource.setAlias(alias);
	}

	public SQLExprTableSource getTableSource() {
		return tableSource;
	}

	public void setTableSource(SQLExprTableSource tableSource) {
		if (tableSource != null) {
			tableSource.setParent(this);
		}
		this.tableSource = tableSource;
	}

	public SQLName getTableName() {
		return (SQLName) tableSource.getExpr();
	}

	public void setTableName(SQLName tableName) {
		this.setTableSource(new SQLExprTableSource(tableName));
	}

	public void setTableSource(SQLName tableName) {
		this.setTableSource(new SQLExprTableSource(tableName));
	}

	public SQLSelect getQuery() {
		return query;
	}

	public void setQuery(SQLSelect query) {
		this.query = query;
	}

	public List<SQLExpr> getColumns() {
		return columns;
	}

	public ValuesClause getValues() {
		return values;
	}

	public void setValues(ValuesClause values) {
		this.values = values;
	}
}

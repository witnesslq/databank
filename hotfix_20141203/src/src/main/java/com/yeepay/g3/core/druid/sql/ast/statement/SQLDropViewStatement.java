package com.yeepay.g3.core.druid.sql.ast.statement;

import com.yeepay.g3.core.druid.sql.ast.SQLName;
import com.yeepay.g3.core.druid.sql.ast.SQLStatementImpl;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

import java.util.ArrayList;
import java.util.List;

public class SQLDropViewStatement extends SQLStatementImpl implements SQLDDLStatement {

	private static final long serialVersionUID = 1L;

	protected List<SQLExprTableSource> tableSources = new ArrayList<SQLExprTableSource>();

	public SQLDropViewStatement() {

	}

	public SQLDropViewStatement(SQLName name) {
		this(new SQLExprTableSource(name));
	}

	public SQLDropViewStatement(SQLExprTableSource tableSource) {
		this.tableSources.add(tableSource);
	}

	public List<SQLExprTableSource> getTableSources() {
		return tableSources;
	}

	public void setTableSources(List<SQLExprTableSource> tableSources) {
		this.tableSources = tableSources;
	}

	public void setName(SQLName name) {
		this.addTableSource(new SQLExprTableSource(name));
	}

	public void addTableSource(SQLName name) {
		this.addTableSource(new SQLExprTableSource(name));
	}

	public void addTableSource(SQLExprTableSource tableSource) {
		tableSources.add(tableSource);
	}

	@Override
	protected void accept0(SQLASTVisitor visitor) {
		if (visitor.visit(this)) {
			this.acceptChild(visitor, tableSources);
		}
		visitor.endVisit(this);
	}
}

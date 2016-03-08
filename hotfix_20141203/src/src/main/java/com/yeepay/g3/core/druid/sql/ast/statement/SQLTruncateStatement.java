package com.yeepay.g3.core.druid.sql.ast.statement;

import com.yeepay.g3.core.druid.sql.ast.SQLName;
import com.yeepay.g3.core.druid.sql.ast.SQLStatementImpl;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

import java.util.ArrayList;
import java.util.List;

public class SQLTruncateStatement extends SQLStatementImpl {

	private static final long serialVersionUID = 1L;
	protected List<SQLExprTableSource> tableSources = new ArrayList<SQLExprTableSource>(2);

	public List<SQLExprTableSource> getTableSources() {
		return tableSources;
	}

	public void setTableSources(List<SQLExprTableSource> tableSources) {
		this.tableSources = tableSources;
	}

	public void addTableSource(SQLName name) {
		SQLExprTableSource tableSource = new SQLExprTableSource(name);
		tableSource.setParent(this);
		this.tableSources.add(tableSource);
	}

	@Override
	protected void accept0(SQLASTVisitor visitor) {
		if (visitor.visit(this)) {
			acceptChild(visitor, tableSources);
		}
		visitor.endVisit(this);
	}
}

package com.yeepay.g3.core.druid.sql.ast.statement;

import com.yeepay.g3.core.druid.sql.ast.SQLName;
import com.yeepay.g3.core.druid.sql.ast.SQLStatementImpl;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

import java.util.ArrayList;
import java.util.List;

public class SQLAlterTableStatement extends SQLStatementImpl implements SQLDDLStatement {

	private static final long serialVersionUID = 1L;

	private SQLExprTableSource tableSource;
	private List<SQLAlterTableItem> items = new ArrayList<SQLAlterTableItem>();

	public List<SQLAlterTableItem> getItems() {
		return items;
	}

	public void setItems(List<SQLAlterTableItem> items) {
		this.items = items;
	}

	public SQLExprTableSource getTableSource() {
		return tableSource;
	}

	public void setTableSource(SQLExprTableSource tableSource) {
		this.tableSource = tableSource;
	}

	public SQLName getName() {
		if (getTableSource() == null) {
			return null;
		}
		return (SQLName) getTableSource().getExpr();
	}

	public void setName(SQLName name) {
		this.setTableSource(new SQLExprTableSource(name));
	}

	@Override
	protected void accept0(SQLASTVisitor visitor) {
		if (visitor.visit(this)) {
			acceptChild(visitor, getTableSource());
			acceptChild(visitor, getItems());
		}
		visitor.endVisit(this);
	}
}

package com.yeepay.g3.core.druid.sql.ast.statement;

import com.yeepay.g3.core.druid.sql.ast.SQLExpr;
import com.yeepay.g3.core.druid.sql.ast.SQLStatementImpl;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

import java.util.ArrayList;
import java.util.List;

public class SQLSetStatement extends SQLStatementImpl {

	private static final long serialVersionUID = 1L;

	private List<SQLAssignItem> items = new ArrayList<SQLAssignItem>();

	public SQLSetStatement() {
	}

	public SQLSetStatement(SQLExpr target, SQLExpr value) {
		this.items.add(new SQLAssignItem(target, value));
	}

	public List<SQLAssignItem> getItems() {
		return items;
	}

	public void setItems(List<SQLAssignItem> items) {
		this.items = items;
	}

	@Override
	protected void accept0(SQLASTVisitor visitor) {
		if (visitor.visit(this)) {
			acceptChild(visitor, this.items);
		}
		visitor.endVisit(this);
	}

	public void output(StringBuffer buf) {
		buf.append("SET ");

		for (int i = 0; i < items.size(); ++i) {
			if (i != 0) {
				buf.append(", ");
			}

			SQLAssignItem item = items.get(i);
			item.output(buf);
		}
	}
}

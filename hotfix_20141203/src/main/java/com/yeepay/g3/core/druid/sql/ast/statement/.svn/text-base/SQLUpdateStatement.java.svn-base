package com.yeepay.g3.core.druid.sql.ast.statement;

import com.yeepay.g3.core.druid.sql.ast.SQLExpr;
import com.yeepay.g3.core.druid.sql.ast.SQLName;
import com.yeepay.g3.core.druid.sql.ast.SQLStatementImpl;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

import java.util.ArrayList;
import java.util.List;

public class SQLUpdateStatement extends SQLStatementImpl {

	private static final long serialVersionUID = 1L;

	protected final List<SQLUpdateSetItem> items = new ArrayList<SQLUpdateSetItem>();
	protected SQLExpr where;

	protected SQLTableSource tableSource;

	public SQLUpdateStatement() {

	}

	public SQLTableSource getTableSource() {
		return tableSource;
	}

	public void setTableSource(SQLExpr expr) {
		this.setTableSource(new SQLExprTableSource(expr));
	}

	public void setTableSource(SQLTableSource tableSource) {
		if (tableSource != null) {
			tableSource.setParent(this);
		}
		this.tableSource = tableSource;
	}

	public SQLName getTableName() {
		if (tableSource instanceof SQLExprTableSource) {
			SQLExprTableSource exprTableSource = (SQLExprTableSource) tableSource;
			return (SQLName) exprTableSource.getExpr();
		}
		return null;
	}

	public SQLExpr getWhere() {
		return where;
	}

	public void setWhere(SQLExpr where) {
		if (where != null) {
			where.setParent(this);
		}
		this.where = where;
	}

	public List<SQLUpdateSetItem> getItems() {
		return items;
	}

	@Override
	public void output(StringBuffer buf) {
		buf.append("UPDATE ");

		this.tableSource.output(buf);

		buf.append(" SET ");
		for (int i = 0, size = items.size(); i < size; ++i) {
			if (i != 0) {
				buf.append(", ");
			}
			items.get(i).output(buf);
		}

		if (this.where != null) {
			buf.append(" WHERE ");
			this.where.output(buf);
		}
	}

	@Override
	protected void accept0(SQLASTVisitor visitor) {
		if (visitor.visit(this)) {
			acceptChild(visitor, tableSource);
			acceptChild(visitor, items);
			acceptChild(visitor, where);
		}
		visitor.endVisit(this);
	}
}

package com.yeepay.g3.core.druid.sql.ast.statement;

import com.yeepay.g3.core.druid.sql.ast.SQLExpr;
import com.yeepay.g3.core.druid.sql.ast.SQLName;
import com.yeepay.g3.core.druid.sql.ast.SQLStatementImpl;
import com.yeepay.g3.core.druid.sql.ast.expr.SQLIdentifierExpr;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

public class SQLDeleteStatement extends SQLStatementImpl {

	private static final long serialVersionUID = 1L;
	protected SQLTableSource tableSource;
	protected SQLExpr where;

	public SQLDeleteStatement() {

	}

	public SQLTableSource getTableSource() {
		return tableSource;
	}

	public SQLExprTableSource getExprTableSource() {
		return (SQLExprTableSource) getTableSource();
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
		return (SQLName) getExprTableSource().getExpr();
	}

	public void setTableName(SQLName tableName) {
		this.setTableSource(new SQLExprTableSource(tableName));
	}

	public void setTableName(String name) {
		setTableName(new SQLIdentifierExpr(name));
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

	public String getAlias() {
		return this.tableSource.getAlias();
	}

	public void setAlias(String alias) {
		this.tableSource.setAlias(alias);
	}

	@Override
	protected void accept0(SQLASTVisitor visitor) {
		if (visitor.visit(this)) {
			acceptChild(visitor, tableSource);
			acceptChild(visitor, where);
		}

		visitor.endVisit(this);
	}

}

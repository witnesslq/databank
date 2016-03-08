package com.yeepay.g3.core.druid.sql.ast.statement;

import com.yeepay.g3.core.druid.sql.ast.SQLExpr;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class SQLUnique extends SQLConstaintImpl implements SQLUniqueConstraint, SQLTableElement {

	private final List<SQLExpr> columns = new ArrayList<SQLExpr>();

	public List<SQLExpr> getColumns() {
		return columns;
	}

	@Override
	protected void accept0(SQLASTVisitor visitor) {
		if (visitor.visit(this)) {
			acceptChild(visitor, this.getName());
			acceptChild(visitor, this.getColumns());
		}
		visitor.endVisit(this);
	}

}

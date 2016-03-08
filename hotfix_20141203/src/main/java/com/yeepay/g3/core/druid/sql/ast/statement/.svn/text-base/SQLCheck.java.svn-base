package com.yeepay.g3.core.druid.sql.ast.statement;

import com.yeepay.g3.core.druid.sql.ast.SQLExpr;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

public class SQLCheck extends SQLConstaintImpl implements SQLTableElement {

	private static final long serialVersionUID = 1L;

	private SQLExpr expr;

	public SQLCheck() {

	}

	public SQLCheck(SQLExpr expr) {
		this.setExpr(expr);
	}

	public SQLExpr getExpr() {
		return expr;
	}

	public void setExpr(SQLExpr expr) {
		if (expr != null) {
			expr.setParent(this);
		}
		this.expr = expr;
	}

	@Override
	protected void accept0(SQLASTVisitor visitor) {
		if (visitor.visit(this)) {
			acceptChild(visitor, this.getName());
			acceptChild(visitor, this.getExpr());
		}
		visitor.endVisit(this);
	}


}

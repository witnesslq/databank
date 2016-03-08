package com.yeepay.g3.core.druid.sql.ast.statement;

import com.yeepay.g3.core.druid.sql.ast.SQLExpr;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

public class SQLExprTableSource extends SQLTableSourceImpl {

	private static final long serialVersionUID = 1L;

	protected SQLExpr expr;

	public SQLExprTableSource() {

	}

	public SQLExprTableSource(SQLExpr expr) {
		this.setExpr(expr);
	}

	public SQLExpr getExpr() {
		return this.expr;
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
			acceptChild(visitor, this.expr);
		}
		visitor.endVisit(this);
	}

	public void output(StringBuffer buf) {
		this.expr.output(buf);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((expr == null) ? 0 : expr.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		SQLExprTableSource other = (SQLExprTableSource) obj;
		if (expr == null) {
			if (other.expr != null) return false;
		} else if (!expr.equals(other.expr)) return false;
		return true;
	}

}

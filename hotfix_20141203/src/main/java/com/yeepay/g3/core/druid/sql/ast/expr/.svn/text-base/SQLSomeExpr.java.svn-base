package com.yeepay.g3.core.druid.sql.ast.expr;

import com.yeepay.g3.core.druid.sql.ast.SQLExprImpl;
import com.yeepay.g3.core.druid.sql.ast.statement.SQLSelect;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

public class SQLSomeExpr extends SQLExprImpl {

	private static final long serialVersionUID = 1L;
	public SQLSelect subQuery;

	public SQLSomeExpr() {

	}

	public SQLSomeExpr(SQLSelect select) {

		this.subQuery = select;
	}

	public SQLSelect getSubQuery() {
		return this.subQuery;
	}

	public void setSubQuery(SQLSelect subQuery) {
		this.subQuery = subQuery;
	}

	public void output(StringBuffer buf) {
		this.subQuery.output(buf);
	}

	@Override
	protected void accept0(SQLASTVisitor visitor) {
		if (visitor.visit(this)) {
			acceptChild(visitor, this.subQuery);
		}

		visitor.endVisit(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((subQuery == null) ? 0 : subQuery.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		SQLSomeExpr other = (SQLSomeExpr) obj;
		if (subQuery == null) {
			if (other.subQuery != null) {
				return false;
			}
		} else if (!subQuery.equals(other.subQuery)) {
			return false;
		}
		return true;
	}
}

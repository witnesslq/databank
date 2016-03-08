package com.yeepay.g3.core.druid.sql.ast.expr;

import com.yeepay.g3.core.druid.sql.ast.SQLExpr;
import com.yeepay.g3.core.druid.sql.ast.SQLExprImpl;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

import java.io.Serializable;

public class SQLNotExpr extends SQLExprImpl implements Serializable {

	private static final long serialVersionUID = 1L;
	public SQLExpr expr;

	public SQLNotExpr() {

	}

	public SQLNotExpr(SQLExpr expr) {

		this.expr = expr;
	}

	public SQLExpr getExpr() {
		return this.expr;
	}

	public void setExpr(SQLExpr expr) {
		this.expr = expr;
	}

	@Override
	public void output(StringBuffer buf) {
		buf.append(" NOT ");
		this.expr.output(buf);
	}

	protected void accept0(SQLASTVisitor visitor) {
		if (visitor.visit(this)) {
			acceptChild(visitor, this.expr);
		}

		visitor.endVisit(this);
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
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		SQLNotExpr other = (SQLNotExpr) obj;
		if (expr == null) {
			if (other.expr != null) {
				return false;
			}
		} else if (!expr.equals(other.expr)) {
			return false;
		}
		return true;
	}
}

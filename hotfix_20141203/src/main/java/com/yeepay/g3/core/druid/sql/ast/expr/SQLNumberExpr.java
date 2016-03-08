package com.yeepay.g3.core.druid.sql.ast.expr;

import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

public class SQLNumberExpr extends SQLNumericLiteralExpr {

	private static final long serialVersionUID = 1L;

	private Number number;

	public SQLNumberExpr() {

	}

	public SQLNumberExpr(Number number) {

		this.number = number;
	}

	public Number getNumber() {
		return this.number;
	}

	public void setNumber(Number number) {
		this.number = number;
	}

	public void output(StringBuffer buf) {
		buf.append(this.number.toString());
	}

	protected void accept0(SQLASTVisitor visitor) {
		visitor.visit(this);
		visitor.endVisit(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
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
		SQLNumberExpr other = (SQLNumberExpr) obj;
		if (number == null) {
			if (other.number != null) {
				return false;
			}
		} else if (!number.equals(other.number)) {
			return false;
		}
		return true;
	}
}

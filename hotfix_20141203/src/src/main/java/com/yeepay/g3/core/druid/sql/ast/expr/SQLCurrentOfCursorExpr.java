package com.yeepay.g3.core.druid.sql.ast.expr;

import com.yeepay.g3.core.druid.sql.ast.SQLExprImpl;
import com.yeepay.g3.core.druid.sql.ast.SQLName;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

public class SQLCurrentOfCursorExpr extends SQLExprImpl {

	private static final long serialVersionUID = 1L;

	private SQLName cursorName;

	public SQLCurrentOfCursorExpr() {

	}

	public SQLCurrentOfCursorExpr(SQLName cursorName) {
		this.cursorName = cursorName;
	}

	public SQLName getCursorName() {
		return cursorName;
	}

	public void setCursorName(SQLName cursorName) {
		this.cursorName = cursorName;
	}

	@Override
	public void output(StringBuffer buf) {
		buf.append("CURRENT OF ");
		cursorName.output(buf);
	}

	@Override
	protected void accept0(SQLASTVisitor visitor) {
		if (visitor.visit(this)) {
			acceptChild(visitor, this.cursorName);
		}
		visitor.endVisit(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cursorName == null) ? 0 : cursorName.hashCode());
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
		SQLCurrentOfCursorExpr other = (SQLCurrentOfCursorExpr) obj;
		if (cursorName == null) {
			if (other.cursorName != null) {
				return false;
			}
		} else if (!cursorName.equals(other.cursorName)) {
			return false;
		}
		return true;
	}

}

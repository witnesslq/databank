package com.yeepay.g3.core.druid.sql.ast.expr;

import com.yeepay.g3.core.druid.sql.ast.SQLExprImpl;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

public class SQLNullExpr extends SQLExprImpl implements SQLLiteralExpr {

	private static final long serialVersionUID = 1L;

	public SQLNullExpr() {

	}

	public void output(StringBuffer buf) {
		buf.append("NULL");
	}

	protected void accept0(SQLASTVisitor visitor) {
		visitor.visit(this);

		visitor.endVisit(this);
	}

	public int hashCode() {
		return 0;
	}

	public boolean equals(Object o) {
		return o instanceof SQLNullExpr;
	}
}

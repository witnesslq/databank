package com.yeepay.g3.core.druid.sql.ast.expr;

import com.yeepay.g3.core.druid.sql.ast.SQLExprImpl;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

public class SQLDefaultExpr extends SQLExprImpl implements SQLLiteralExpr {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean equals(Object o) {
		return o instanceof SQLDefaultExpr;
	}

	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	protected void accept0(SQLASTVisitor visitor) {
		visitor.visit(this);
		visitor.endVisit(this);
	}

	public String toString() {
		return "DEFAULT";
	}
}

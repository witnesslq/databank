package com.yeepay.g3.core.druid.sql.ast.statement;

import com.yeepay.g3.core.druid.sql.ast.SQLExpr;
import com.yeepay.g3.core.druid.sql.ast.SQLStatementImpl;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

public class SQLReleaseSavePointStatement extends SQLStatementImpl {

	private static final long serialVersionUID = 1L;
	private SQLExpr name;

	public SQLExpr getName() {
		return name;
	}

	public void setName(SQLExpr name) {
		this.name = name;
	}

	@Override
	protected void accept0(SQLASTVisitor visitor) {
		if (visitor.visit(this)) {
			acceptChild(visitor, name);
		}
		visitor.endVisit(this);
	}
}

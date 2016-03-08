package com.yeepay.g3.core.druid.sql.ast.statement;

import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

public class SQLColumnPrimaryKey extends SQLConstaintImpl implements SQLColumnConstraint {

	private static final long serialVersionUID = 1L;

	@Override
	protected void accept0(SQLASTVisitor visitor) {
		if (visitor.visit(this)) {
			acceptChild(visitor, this.getName());
		}
		visitor.endVisit(this);
	}

}

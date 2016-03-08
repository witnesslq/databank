package com.yeepay.g3.core.druid.sql.ast.statement;

import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

@SuppressWarnings("serial")
public class NotNullConstraint extends SQLConstaintImpl implements SQLColumnConstraint {

	public NotNullConstraint() {

	}

	@Override
	protected void accept0(SQLASTVisitor visitor) {
		visitor.visit(this);
		visitor.endVisit(this);
	}

}

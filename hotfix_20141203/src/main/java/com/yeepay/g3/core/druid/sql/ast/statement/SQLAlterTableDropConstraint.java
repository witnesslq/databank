package com.yeepay.g3.core.druid.sql.ast.statement;

import com.yeepay.g3.core.druid.sql.ast.SQLName;
import com.yeepay.g3.core.druid.sql.ast.SQLObjectImpl;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

public class SQLAlterTableDropConstraint extends SQLObjectImpl implements SQLAlterTableItem {

	private static final long serialVersionUID = 1L;

	private SQLName constraintName;

	@Override
	protected void accept0(SQLASTVisitor visitor) {
		if (visitor.visit(this)) {
			acceptChild(visitor, this.constraintName);
		}
		visitor.endVisit(this);
	}


	public SQLName getConstraintName() {
		return constraintName;
	}


	public void setConstraintName(SQLName constraintName) {
		this.constraintName = constraintName;
	}


}

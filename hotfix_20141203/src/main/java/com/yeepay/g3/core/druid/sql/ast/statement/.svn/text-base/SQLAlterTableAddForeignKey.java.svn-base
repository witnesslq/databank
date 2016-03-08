package com.yeepay.g3.core.druid.sql.ast.statement;

import com.yeepay.g3.core.druid.sql.ast.SQLObjectImpl;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

public class SQLAlterTableAddForeignKey extends SQLObjectImpl implements SQLAlterTableItem {

	private static final long serialVersionUID = 1L;
	private SQLForeignKeyConstraint foreignKey;

	public SQLAlterTableAddForeignKey() {

	}

	public SQLAlterTableAddForeignKey(SQLForeignKeyConstraint foreignKey) {
		this.foreignKey = foreignKey;
	}

	public SQLForeignKeyConstraint getForeignKey() {
		return foreignKey;
	}

	public void setForeignKey(SQLForeignKeyConstraint foreignKey) {
		this.foreignKey = foreignKey;
	}

	@Override
	protected void accept0(SQLASTVisitor visitor) {
		if (visitor.visit(this)) {
			acceptChild(visitor, foreignKey);
		}
		visitor.endVisit(this);
	}

}

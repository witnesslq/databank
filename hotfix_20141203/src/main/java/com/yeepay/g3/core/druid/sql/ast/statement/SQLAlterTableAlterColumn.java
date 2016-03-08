package com.yeepay.g3.core.druid.sql.ast.statement;

import com.yeepay.g3.core.druid.sql.ast.SQLObjectImpl;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

public class SQLAlterTableAlterColumn extends SQLObjectImpl implements SQLAlterTableItem {

	private static final long serialVersionUID = 1L;

	private SQLColumnDefinition column;

	@Override
	protected void accept0(SQLASTVisitor visitor) {
		if (visitor.visit(this)) {
			acceptChild(visitor, column);
		}
		visitor.endVisit(this);
	}

	public SQLColumnDefinition getColumn() {
		return column;
	}

	public void setColumn(SQLColumnDefinition column) {
		this.column = column;
	}

}

package com.yeepay.g3.core.druid.sql.ast.statement;

import com.yeepay.g3.core.druid.sql.ast.SQLName;
import com.yeepay.g3.core.druid.sql.ast.SQLObjectImpl;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

public class SQLAlterTableDropColumnItem extends SQLObjectImpl implements SQLAlterTableItem {

	private static final long serialVersionUID = 1L;
	private SQLName columnName;

	@Override
	protected void accept0(SQLASTVisitor visitor) {
		if (visitor.visit(this)) {
			acceptChild(visitor, columnName);
		}
		visitor.endVisit(this);
	}

	public SQLName getColumnName() {
		return columnName;
	}

	public void setColumnName(SQLName columnName) {
		this.columnName = columnName;
	}

}

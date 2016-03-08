package com.yeepay.g3.core.druid.sql.ast.statement;

import com.yeepay.g3.core.druid.sql.ast.SQLExpr;
import com.yeepay.g3.core.druid.sql.ast.SQLObjectImpl;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

public class SQLUpdateSetItem extends SQLObjectImpl {

	private static final long serialVersionUID = 1L;

	private SQLExpr column;
	private SQLExpr value;

	public SQLUpdateSetItem() {

	}

	public SQLExpr getColumn() {
		return column;
	}

	public void setColumn(SQLExpr column) {
		this.column = column;
	}

	public SQLExpr getValue() {
		return value;
	}

	public void setValue(SQLExpr value) {
		this.value = value;
	}

	public void output(StringBuffer buf) {
		column.output(buf);
		buf.append(" = ");
		value.output(buf);
	}

	@Override
	protected void accept0(SQLASTVisitor visitor) {
		if (visitor.visit(this)) {
			acceptChild(visitor, column);
			acceptChild(visitor, value);
		}

		visitor.endVisit(this);
	}

}

package com.yeepay.g3.core.druid.sql.ast.statement;

import com.yeepay.g3.core.druid.sql.ast.SQLStatementImpl;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

public class SQLSelectStatement extends SQLStatementImpl {

	private static final long serialVersionUID = 1L;

	protected SQLSelect select;

	public SQLSelectStatement() {

	}

	public SQLSelectStatement(SQLSelect select) {
		this.setSelect(select);
	}

	public SQLSelect getSelect() {
		return this.select;
	}

	public void setSelect(SQLSelect select) {
		if (select != null) {
			select.setParent(this);
		}
		this.select = select;
	}

	public void output(StringBuffer buf) {
		this.select.output(buf);
	}

	protected void accept0(SQLASTVisitor visitor) {
		if (visitor.visit(this)) {
			acceptChild(visitor, this.select);
		}
		visitor.endVisit(this);
	}
}

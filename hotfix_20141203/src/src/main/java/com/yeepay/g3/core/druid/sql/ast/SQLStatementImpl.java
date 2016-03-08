package com.yeepay.g3.core.druid.sql.ast;

import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

public abstract class SQLStatementImpl extends SQLObjectImpl implements SQLStatement {

	private static final long serialVersionUID = 1L;

	public SQLStatementImpl() {

	}

	public void output(StringBuffer buf) {
		buf.append(super.toString());
	}

	@Override
	protected void accept0(SQLASTVisitor visitor) {
		throw new UnsupportedOperationException(this.getClass().getName());
	}
}

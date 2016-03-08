package com.yeepay.g3.core.druid.sql.ast.statement;

import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

public class SQLSubqueryTableSource extends SQLTableSourceImpl {

	private static final long serialVersionUID = 1L;

	protected SQLSelect select;

	public SQLSubqueryTableSource() {

	}

	public SQLSubqueryTableSource(String alias) {
		super(alias);
	}

	public SQLSubqueryTableSource(SQLSelect select, String alias) {
		super(alias);
		this.setSelect(select);
	}

	public SQLSubqueryTableSource(SQLSelect select) {
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

	@Override
	protected void accept0(SQLASTVisitor visitor) {
		if (visitor.visit(this)) {
			acceptChild(visitor, select);
		}
		visitor.endVisit(this);
	}

	public void output(StringBuffer buf) {
		buf.append("(");
		this.select.output(buf);
		buf.append(")");
	}
}

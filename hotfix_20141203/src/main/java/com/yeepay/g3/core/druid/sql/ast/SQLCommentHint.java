package com.yeepay.g3.core.druid.sql.ast;

import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

public class SQLCommentHint extends SQLObjectImpl implements SQLHint {

	private static final long serialVersionUID = 1L;

	private String text;

	public SQLCommentHint() {

	}

	public SQLCommentHint(String text) {

		this.text = text;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	protected void accept0(SQLASTVisitor visitor) {
		visitor.visit(this);
		visitor.endVisit(this);
	}
}

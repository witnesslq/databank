package com.yeepay.g3.core.druid.sql.ast.statement;

import com.yeepay.g3.core.druid.sql.ast.SQLExpr;
import com.yeepay.g3.core.druid.sql.ast.SQLObjectImpl;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

import java.util.ArrayList;
import java.util.List;

public class SQLSelectGroupByClause extends SQLObjectImpl {

	private static final long serialVersionUID = 1L;

	private final List<SQLExpr> items = new ArrayList<SQLExpr>();
	private SQLExpr having;

	public SQLSelectGroupByClause() {

	}

	protected void accept0(SQLASTVisitor visitor) {
		if (visitor.visit(this)) {
			acceptChild(visitor, this.items);
			acceptChild(visitor, this.having);
		}

		visitor.endVisit(this);
	}

	public SQLExpr getHaving() {
		return this.having;
	}

	public void setHaving(SQLExpr having) {
		this.having = having;
	}

	public List<SQLExpr> getItems() {
		return this.items;
	}
}

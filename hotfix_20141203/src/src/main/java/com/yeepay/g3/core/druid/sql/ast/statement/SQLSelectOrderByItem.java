package com.yeepay.g3.core.druid.sql.ast.statement;

import com.yeepay.g3.core.druid.sql.ast.SQLExpr;
import com.yeepay.g3.core.druid.sql.ast.SQLObjectImpl;
import com.yeepay.g3.core.druid.sql.ast.SQLOrderingSpecification;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

public class SQLSelectOrderByItem extends SQLObjectImpl {

	private static final long serialVersionUID = 1L;

	protected SQLExpr expr;
	protected String collate;
	protected SQLOrderingSpecification type;

	public SQLSelectOrderByItem() {

	}

	public SQLExpr getExpr() {
		return this.expr;
	}

	public void setExpr(SQLExpr expr) {
		this.expr = expr;
	}

	public String getCollate() {
		return collate;
	}

	public void setCollate(String collate) {
		this.collate = collate;
	}

	public SQLOrderingSpecification getType() {
		return this.type;
	}

	public void setType(SQLOrderingSpecification type) {
		this.type = type;
	}

	protected void accept0(SQLASTVisitor visitor) {
		if (visitor.visit(this)) {
			acceptChild(visitor, this.expr);
		}

		visitor.endVisit(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((collate == null) ? 0 : collate.hashCode());
		result = prime * result + ((expr == null) ? 0 : expr.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		SQLSelectOrderByItem other = (SQLSelectOrderByItem) obj;
		if (collate == null) {
			if (other.collate != null) return false;
		} else if (!collate.equals(other.collate)) return false;
		if (expr == null) {
			if (other.expr != null) return false;
		} else if (!expr.equals(other.expr)) return false;
		if (type != other.type) return false;
		return true;
	}

}

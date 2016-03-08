package com.yeepay.g3.core.druid.sql.ast.statement;

import com.yeepay.g3.core.druid.sql.ast.SQLExpr;
import com.yeepay.g3.core.druid.sql.ast.SQLObjectImpl;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

public class SQLSelectItem extends SQLObjectImpl {

	private static final long serialVersionUID = 1L;

	private SQLExpr expr;
	private String alias;

	public SQLSelectItem() {

	}

	public SQLSelectItem(SQLExpr expr) {
		this(expr, null);
	}

	public SQLSelectItem(SQLExpr expr, String alias) {
		this.expr = expr;
		this.alias = alias;

		if (expr != null) {
			expr.setParent(this);
		}
	}

	public SQLExpr getExpr() {
		return this.expr;
	}

	public void setExpr(SQLExpr expr) {
		this.expr = expr;
		if (expr != null) {
			expr.setParent(this);
		}
	}

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public void output(StringBuffer buf) {
		this.expr.output(buf);
		if ((this.alias != null) && (this.alias.length() != 0)) {
			buf.append(" AS ");
			buf.append(this.alias);
		}
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
		result = prime * result + ((alias == null) ? 0 : alias.hashCode());
		result = prime * result + ((expr == null) ? 0 : expr.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		SQLSelectItem other = (SQLSelectItem) obj;
		if (alias == null) {
			if (other.alias != null) return false;
		} else if (!alias.equals(other.alias)) return false;
		if (expr == null) {
			if (other.expr != null) return false;
		} else if (!expr.equals(other.expr)) return false;
		return true;
	}

}

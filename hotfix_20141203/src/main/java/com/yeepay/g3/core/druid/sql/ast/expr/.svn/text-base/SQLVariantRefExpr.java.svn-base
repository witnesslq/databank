package com.yeepay.g3.core.druid.sql.ast.expr;

import com.yeepay.g3.core.druid.sql.ast.SQLExprImpl;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

public class SQLVariantRefExpr extends SQLExprImpl {

	private static final long serialVersionUID = 1L;

	private String name;

	private boolean global = false;

	private int index = -1;

	public SQLVariantRefExpr(String name) {
		this.name = name;
	}

	public SQLVariantRefExpr(String name, boolean global) {
		this.name = name;
		this.global = global;
	}

	public SQLVariantRefExpr() {

	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void output(StringBuffer buf) {
		buf.append(this.name);
	}

	@Override
	protected void accept0(SQLASTVisitor visitor) {
		visitor.visit(this);

		visitor.endVisit(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof SQLVariantRefExpr)) {
			return false;
		}
		SQLVariantRefExpr other = (SQLVariantRefExpr) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	public boolean isGlobal() {
		return global;
	}

	public void setGlobal(boolean global) {
		this.global = global;
	}

}

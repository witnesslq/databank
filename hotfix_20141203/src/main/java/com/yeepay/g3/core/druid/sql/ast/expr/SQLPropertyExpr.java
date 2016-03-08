package com.yeepay.g3.core.druid.sql.ast.expr;

import com.yeepay.g3.core.druid.sql.ast.SQLExpr;
import com.yeepay.g3.core.druid.sql.ast.SQLExprImpl;
import com.yeepay.g3.core.druid.sql.ast.SQLName;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

public class SQLPropertyExpr extends SQLExprImpl implements SQLName {

	private static final long serialVersionUID = 1L;

	private SQLExpr owner;
	private String name;

	public SQLPropertyExpr(SQLExpr owner, String name) {
		setOwner(owner);
		this.name = name;
	}

	public SQLPropertyExpr() {

	}

	public String getSimleName() {
		return name;
	}

	public SQLExpr getOwner() {
		return this.owner;
	}

	public void setOwner(SQLExpr owner) {
		if (owner != null) {
			owner.setParent(this);
		}
		this.owner = owner;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void output(StringBuffer buf) {
		this.owner.output(buf);
		buf.append(".");
		buf.append(this.name);
	}

	protected void accept0(SQLASTVisitor visitor) {
		if (visitor.visit(this)) {
			acceptChild(visitor, this.owner);
		}

		visitor.endVisit(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
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
		if (!(obj instanceof SQLPropertyExpr)) {
			return false;
		}
		SQLPropertyExpr other = (SQLPropertyExpr) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (owner == null) {
			if (other.owner != null) {
				return false;
			}
		} else if (!owner.equals(other.owner)) {
			return false;
		}
		return true;
	}

}

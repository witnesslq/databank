package com.yeepay.g3.core.druid.sql.ast.expr;

import com.yeepay.g3.core.druid.sql.ast.SQLExprImpl;
import com.yeepay.g3.core.druid.sql.ast.SQLName;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

public class SQLIdentifierExpr extends SQLExprImpl implements SQLName {

	private static final long serialVersionUID = -4101240977289682659L;

	private String name;

	private transient String lowerName;

	public SQLIdentifierExpr() {

	}

	public SQLIdentifierExpr(String name) {
		this.name = name;
	}

	public String getSimleName() {
		return name;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
		this.lowerName = null;
	}

	public String getLowerName() {
		if (lowerName == null && name != null) {
			lowerName = name.toLowerCase();
		}
		return lowerName;
	}

	public void setLowerName(String lowerName) {
		this.lowerName = lowerName;
	}

	public void output(StringBuffer buf) {
		buf.append(this.name);
	}

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
		if (!(obj instanceof SQLIdentifierExpr)) {
			return false;
		}
		SQLIdentifierExpr other = (SQLIdentifierExpr) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

}

package com.yeepay.g3.core.druid.sql.ast;

import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

import java.util.ArrayList;
import java.util.List;

public class SQLDataTypeImpl extends SQLObjectImpl implements SQLDataType {

	private static final long serialVersionUID = -2783296007802532452L;

	protected String name;
	protected final List<SQLExpr> arguments = new ArrayList<SQLExpr>();

	public SQLDataTypeImpl() {

	}

	public SQLDataTypeImpl(String name) {

		this.name = name;
	}

	@Override
	protected void accept0(SQLASTVisitor visitor) {
		if (visitor.visit(this)) {
			acceptChild(visitor, this.arguments);
		}

		visitor.endVisit(this);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SQLExpr> getArguments() {
		return this.arguments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arguments == null) ? 0 : arguments.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		SQLDataTypeImpl other = (SQLDataTypeImpl) obj;
		if (arguments == null) {
			if (other.arguments != null) return false;
		} else if (!arguments.equals(other.arguments)) return false;
		if (name == null) {
			if (other.name != null) return false;
		} else if (!name.equals(other.name)) return false;
		return true;
	}

}

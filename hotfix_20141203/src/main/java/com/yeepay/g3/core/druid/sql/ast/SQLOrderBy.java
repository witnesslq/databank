package com.yeepay.g3.core.druid.sql.ast;

import com.yeepay.g3.core.druid.sql.ast.statement.SQLSelectOrderByItem;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class SQLOrderBy extends SQLObjectImpl {

	protected final List<SQLSelectOrderByItem> items = new ArrayList<SQLSelectOrderByItem>();

	public SQLOrderBy() {

	}

	public List<SQLSelectOrderByItem> getItems() {
		return this.items;
	}

	protected void accept0(SQLASTVisitor visitor) {
		if (visitor.visit(this)) {
			acceptChild(visitor, this.items);
		}

		visitor.endVisit(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		SQLOrderBy other = (SQLOrderBy) obj;
		if (items == null) {
			if (other.items != null) return false;
		} else if (!items.equals(other.items)) return false;
		return true;
	}

}

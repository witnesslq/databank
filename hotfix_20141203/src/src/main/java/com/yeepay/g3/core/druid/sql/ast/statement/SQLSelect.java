package com.yeepay.g3.core.druid.sql.ast.statement;

import com.yeepay.g3.core.druid.sql.ast.SQLObjectImpl;
import com.yeepay.g3.core.druid.sql.ast.SQLOrderBy;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

public class SQLSelect extends SQLObjectImpl {

	private static final long serialVersionUID = 1L;

	protected SQLWithSubqueryClause withSubQuery;
	protected SQLSelectQuery query;
	protected SQLOrderBy orderBy;

	public SQLSelect() {

	}

	public SQLSelect(SQLSelectQuery query) {
		this.setQuery(query);
	}

	public SQLWithSubqueryClause getWithSubQuery() {
		return withSubQuery;
	}

	public void setWithSubQuery(SQLWithSubqueryClause withSubQuery) {
		this.withSubQuery = withSubQuery;
	}

	public SQLSelectQuery getQuery() {
		return this.query;
	}

	public void setQuery(SQLSelectQuery query) {
		if (query != null) {
			query.setParent(this);
		}
		this.query = query;
	}

	public SQLOrderBy getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(SQLOrderBy orderBy) {
		this.orderBy = orderBy;
	}

	protected void accept0(SQLASTVisitor visitor) {
		if (visitor.visit(this)) {
			acceptChild(visitor, this.query);
			acceptChild(visitor, this.orderBy);
		}

		visitor.endVisit(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderBy == null) ? 0 : orderBy.hashCode());
		result = prime * result + ((query == null) ? 0 : query.hashCode());
		result = prime * result + ((withSubQuery == null) ? 0 : withSubQuery.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		SQLSelect other = (SQLSelect) obj;
		if (orderBy == null) {
			if (other.orderBy != null) return false;
		} else if (!orderBy.equals(other.orderBy)) return false;
		if (query == null) {
			if (other.query != null) return false;
		} else if (!query.equals(other.query)) return false;
		if (withSubQuery == null) {
			if (other.withSubQuery != null) return false;
		} else if (!withSubQuery.equals(other.withSubQuery)) return false;
		return true;
	}

}

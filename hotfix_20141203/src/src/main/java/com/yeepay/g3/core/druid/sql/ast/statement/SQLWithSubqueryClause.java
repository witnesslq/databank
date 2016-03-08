package com.yeepay.g3.core.druid.sql.ast.statement;

import com.yeepay.g3.core.druid.sql.ast.SQLName;
import com.yeepay.g3.core.druid.sql.ast.SQLObjectImpl;
import com.yeepay.g3.core.druid.sql.ast.expr.SQLIdentifierExpr;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

import java.util.ArrayList;
import java.util.List;

public class SQLWithSubqueryClause extends SQLObjectImpl {

	private static final long serialVersionUID = 1L;

	private Boolean recursive;
	private final List<Entry> entries = new ArrayList<Entry>();

	public List<Entry> getEntries() {
		return entries;
	}

	public Boolean getRecursive() {
		return recursive;
	}

	public void setRecursive(Boolean recursive) {
		this.recursive = recursive;
	}

	@Override
	protected void accept0(SQLASTVisitor visitor) {
		if (visitor.visit(this)) {
			acceptChild(visitor, entries);
		}
		visitor.endVisit(this);
	}

	public static class Entry extends SQLObjectImpl {

		private static final long serialVersionUID = 1L;
		protected SQLIdentifierExpr name;
		protected final List<SQLName> columns = new ArrayList<SQLName>();
		protected SQLSelect subQuery;

		@Override
		protected void accept0(SQLASTVisitor visitor) {
			if (visitor.visit(this)) {
				acceptChild(visitor, name);
				acceptChild(visitor, columns);
				acceptChild(visitor, subQuery);
			}
			visitor.endVisit(this);
		}

		public SQLIdentifierExpr getName() {
			return name;
		}

		public void setName(SQLIdentifierExpr name) {
			this.name = name;
		}

		public SQLSelect getSubQuery() {
			return subQuery;
		}

		public void setSubQuery(SQLSelect subQuery) {
			this.subQuery = subQuery;
		}

		public List<SQLName> getColumns() {
			return columns;
		}

	}
}

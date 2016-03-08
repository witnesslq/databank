package com.yeepay.g3.core.druid.sql.ast.statement;

import com.yeepay.g3.core.druid.sql.ast.SQLExpr;
import com.yeepay.g3.core.druid.sql.ast.SQLObjectImpl;
import com.yeepay.g3.core.druid.sql.ast.SQLStatement;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

import java.util.ArrayList;
import java.util.List;

public class SQLInsertStatement extends SQLInsertInto implements SQLStatement {

	private static final long serialVersionUID = 1L;

	public SQLInsertStatement() {

	}

	@Override
	protected void accept0(SQLASTVisitor visitor) {
		if (visitor.visit(this)) {
			this.acceptChild(visitor, tableSource);
			this.acceptChild(visitor, columns);
			this.acceptChild(visitor, values);
			this.acceptChild(visitor, query);
		}

		visitor.endVisit(this);
	}

	public static class ValuesClause extends SQLObjectImpl {

		private static final long serialVersionUID = 1L;
		private final List<SQLExpr> values;

		public ValuesClause() {
			this(new ArrayList<SQLExpr>());
		}

		public ValuesClause(List<SQLExpr> values) {
			this.values = values;
			for (int i = 0; i < values.size(); ++i) {
				values.get(i).setParent(this);
			}
		}

		public List<SQLExpr> getValues() {
			return values;
		}

		public void output(StringBuffer buf) {
			buf.append(" VALUES (");
			for (int i = 0, size = values.size(); i < size; ++i) {
				if (i != 0) {
					buf.append(", ");
				}
				values.get(i).output(buf);
			}
			buf.append(")");
		}

		@Override
		protected void accept0(SQLASTVisitor visitor) {
			if (visitor.visit(this)) {
				this.acceptChild(visitor, values);
			}

			visitor.endVisit(this);
		}
	}
}

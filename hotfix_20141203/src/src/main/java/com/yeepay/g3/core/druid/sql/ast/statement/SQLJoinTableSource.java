package com.yeepay.g3.core.druid.sql.ast.statement;

import com.yeepay.g3.core.druid.sql.ast.SQLExpr;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

import java.util.ArrayList;
import java.util.List;

public class SQLJoinTableSource extends SQLTableSourceImpl {

	private static final long serialVersionUID = 1L;

	protected SQLTableSource left;
	protected JoinType joinType;
	protected SQLTableSource right;
	protected SQLExpr condition;
	protected final List<SQLExpr> using = new ArrayList<SQLExpr>();

	public SQLJoinTableSource(String alias) {
		super(alias);
	}

	public SQLJoinTableSource() {

	}

	protected void accept0(SQLASTVisitor visitor) {
		if (visitor.visit(this)) {
			acceptChild(visitor, this.left);
			acceptChild(visitor, this.right);
			acceptChild(visitor, this.condition);
			acceptChild(visitor, this.using);
		}

		visitor.endVisit(this);
	}

	public JoinType getJoinType() {
		return this.joinType;
	}

	public void setJoinType(JoinType joinType) {
		this.joinType = joinType;
	}

	public SQLTableSource getLeft() {
		return this.left;
	}

	public void setLeft(SQLTableSource left) {
		if (left != null) {
			left.setParent(this);
		}
		this.left = left;
	}

	public SQLTableSource getRight() {
		return this.right;
	}

	public void setRight(SQLTableSource right) {
		if (right != null) {
			right.setParent(this);
		}
		this.right = right;
	}

	public SQLExpr getCondition() {
		return this.condition;
	}

	public void setCondition(SQLExpr condition) {
		if (condition != null) {
			condition.setParent(this);
		}
		this.condition = condition;
	}

	public List<SQLExpr> getUsing() {
		return this.using;
	}

	public void output(StringBuffer buf) {
		this.left.output(buf);
		buf.append(' ');
		buf.append(JoinType.toString(this.joinType));
		buf.append(' ');
		this.right.output(buf);

		if (this.condition != null) {
			buf.append(" ON ");
			this.condition.output(buf);
		}
	}

	public static enum JoinType {
		COMMA(","), //
		JOIN("JOIN"), //
		INNER_JOIN("INNER JOIN"), //
		CROSS_JOIN("CROSS JOIN"), //
		NATURAL_JOIN("NATURAL JOIN"), //
		NATURAL_INNER_JOIN("NATURAL INNER JOIN"), //
		LEFT_OUTER_JOIN("LEFT JOIN"), RIGHT_OUTER_JOIN("RIGHT JOIN"), FULL_OUTER_JOIN("FULL JOIN"),
		STRAIGHT_JOIN("STRAIGHT_JOIN");

		public final String name;

		JoinType(String name) {
			this.name = name;
		}

		public static String toString(JoinType joinType) {
			return joinType.name;
		}
	}
}

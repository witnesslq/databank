package com.yeepay.g3.core.druid.sql.ast.statement;

import com.yeepay.g3.core.druid.sql.ast.SQLOrderBy;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

public class SQLUnionQuery extends SQLSelectQuery {

	private static final long serialVersionUID = 1L;

	private SQLSelectQuery left;
	private SQLSelectQuery right;
	private SQLUnionOperator operator = SQLUnionOperator.UNION;
	private SQLOrderBy orderBy;

	public SQLUnionOperator getOperator() {
		return operator;
	}

	public void setOperator(SQLUnionOperator operator) {
		this.operator = operator;
	}

	public SQLUnionQuery() {

	}

	public SQLSelectQuery getLeft() {
		return left;
	}

	public void setLeft(SQLSelectQuery left) {
		if (left != null) {
			left.setParent(this);
		}
		this.left = left;
	}

	public SQLSelectQuery getRight() {
		return right;
	}

	public void setRight(SQLSelectQuery right) {
		if (right != null) {
			right.setParent(this);
		}
		this.right = right;
	}

	public SQLOrderBy getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(SQLOrderBy orderBy) {
		if (orderBy != null) {
			orderBy.setParent(this);
		}
		this.orderBy = orderBy;
	}

	@Override
	protected void accept0(SQLASTVisitor visitor) {
		if (visitor.visit(this)) {
			acceptChild(visitor, left);
			acceptChild(visitor, right);
			acceptChild(visitor, orderBy);
		}
		visitor.endVisit(this);
	}

}

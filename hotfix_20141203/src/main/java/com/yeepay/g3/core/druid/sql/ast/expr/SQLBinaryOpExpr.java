package com.yeepay.g3.core.druid.sql.ast.expr;

import com.yeepay.g3.core.druid.sql.ast.SQLExpr;
import com.yeepay.g3.core.druid.sql.ast.SQLExprImpl;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

import java.io.Serializable;

public class SQLBinaryOpExpr extends SQLExprImpl implements Serializable {

	private static final long serialVersionUID = 1L;
	private SQLExpr left;
	private SQLExpr right;
	private SQLBinaryOperator operator;

	public SQLBinaryOpExpr() {

	}

	public SQLBinaryOpExpr(SQLExpr left, SQLBinaryOperator operator, SQLExpr right) {

		setLeft(left);
		setRight(right);
		this.operator = operator;
	}

	public SQLBinaryOpExpr(SQLExpr left, SQLExpr right, SQLBinaryOperator operator) {

		setLeft(left);
		setRight(right);
		this.operator = operator;
	}

	public SQLExpr getLeft() {
		return this.left;
	}

	public void setLeft(SQLExpr left) {
		if (left != null) {
			left.setParent(this);
		}
		this.left = left;
	}

	public SQLExpr getRight() {
		return this.right;
	}

	public void setRight(SQLExpr right) {
		if (right != null) {
			right.setParent(this);
		}
		this.right = right;
	}

	public SQLBinaryOperator getOperator() {
		return this.operator;
	}

	public void setOperator(SQLBinaryOperator operator) {
		this.operator = operator;
	}

	protected void accept0(SQLASTVisitor visitor) {
		if (visitor.visit(this)) {
			acceptChild(visitor, this.left);
			acceptChild(visitor, this.right);
		}

		visitor.endVisit(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((left == null) ? 0 : left.hashCode());
		result = prime * result + ((operator == null) ? 0 : operator.hashCode());
		result = prime * result + ((right == null) ? 0 : right.hashCode());
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
		if (!(obj instanceof SQLBinaryOpExpr)) {
			return false;
		}
		SQLBinaryOpExpr other = (SQLBinaryOpExpr) obj;
		if (left == null) {
			if (other.left != null) {
				return false;
			}
		} else if (!left.equals(other.left)) {
			return false;
		}
		if (operator != other.operator) {
			return false;
		}
		if (right == null) {
			if (other.right != null) {
				return false;
			}
		} else if (!right.equals(other.right)) {
			return false;
		}
		return true;
	}

}

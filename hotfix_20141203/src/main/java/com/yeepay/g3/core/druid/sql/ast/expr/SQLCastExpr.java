package com.yeepay.g3.core.druid.sql.ast.expr;

import com.yeepay.g3.core.druid.sql.ast.SQLDataType;
import com.yeepay.g3.core.druid.sql.ast.SQLExpr;
import com.yeepay.g3.core.druid.sql.ast.SQLExprImpl;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

public class SQLCastExpr extends SQLExprImpl {

	private static final long serialVersionUID = 1L;

	private SQLExpr expr;
	private SQLDataType dataType;

	public SQLCastExpr() {

	}

	public SQLExpr getExpr() {
		return this.expr;
	}

	public void setExpr(SQLExpr expr) {
		if (expr != null) {
			expr.setParent(this);
		}
		this.expr = expr;
	}

	public SQLDataType getDataType() {
		return this.dataType;
	}

	public void setDataType(SQLDataType dataType) {
		this.dataType = dataType;
	}

	protected void accept0(SQLASTVisitor visitor) {
		if (visitor.visit(this)) {
			acceptChild(visitor, this.expr);
			acceptChild(visitor, this.dataType);
		}
		visitor.endVisit(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataType == null) ? 0 : dataType.hashCode());
		result = prime * result + ((expr == null) ? 0 : expr.hashCode());
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
		if (getClass() != obj.getClass()) {
			return false;
		}
		SQLCastExpr other = (SQLCastExpr) obj;
		if (dataType == null) {
			if (other.dataType != null) {
				return false;
			}
		} else if (!dataType.equals(other.dataType)) {
			return false;
		}
		if (expr == null) {
			if (other.expr != null) {
				return false;
			}
		} else if (!expr.equals(other.expr)) {
			return false;
		}
		return true;
	}

}

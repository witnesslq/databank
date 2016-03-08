package com.yeepay.g3.core.druid.sql.visitor;

import com.yeepay.g3.core.druid.sql.ast.expr.*;

public class ParameterizedOutputVisitor extends SQLASTOutputVisitor {
	public ParameterizedOutputVisitor() {
		this(new StringBuilder());
	}

	public ParameterizedOutputVisitor(Appendable appender) {
		super(appender);
	}

	public boolean visit(SQLInListExpr x) {
		return ParameterizedOutputVisitorUtils.visit(this, x);
	}

	public boolean visit(SQLBinaryOpExpr x) {
		x = ParameterizedOutputVisitorUtils.merge(x);

		return super.visit(x);
	}

	public boolean visit(SQLNullExpr x) {
		print('?');
		return false;
	}

	public boolean visit(SQLIntegerExpr x) {
		if (Boolean.TRUE.equals(x.getAttribute(ParameterizedOutputVisitorUtils.ATTR_PARAMS_SKIP))) {
			return super.visit(x);
		}

		print('?');
		return false;
	}

	public boolean visit(SQLNumberExpr x) {
		if (Boolean.TRUE.equals(x.getAttribute(ParameterizedOutputVisitorUtils.ATTR_PARAMS_SKIP))) {
			return super.visit(x);
		}

		print('?');
		return false;
	}

	public boolean visit(SQLCharExpr x) {
		if (Boolean.TRUE.equals(x.getAttribute(ParameterizedOutputVisitorUtils.ATTR_PARAMS_SKIP))) {
			return super.visit(x);
		}

		print('?');
		return false;
	}

	public boolean visit(SQLNCharExpr x) {
		if (Boolean.TRUE.equals(x.getAttribute(ParameterizedOutputVisitorUtils.ATTR_PARAMS_SKIP))) {
			return super.visit(x);
		}

		print('?');
		return false;
	}
}

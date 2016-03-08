package com.yeepay.g3.core.druid.sql.visitor.functions;

import com.yeepay.g3.core.druid.sql.ast.SQLExpr;
import com.yeepay.g3.core.druid.sql.ast.expr.SQLMethodInvokeExpr;
import com.yeepay.g3.core.druid.sql.visitor.SQLEvalVisitor;

import java.util.List;

import static com.yeepay.g3.core.druid.sql.visitor.SQLEvalVisitor.*;

public class Isnull implements Function {

	public static final Isnull instance = new Isnull();

	public Object eval(SQLEvalVisitor visitor, SQLMethodInvokeExpr x) {
		final List<SQLExpr> parameters = x.getParameters();
		if (parameters.size() == 0) {
			return EVAL_ERROR;
		}

		SQLExpr condition = parameters.get(0);
		condition.accept(visitor);
		Object itemValue = condition.getAttributes().get(EVAL_VALUE);
		if (itemValue == EVAL_VALUE_NULL) {
			return Boolean.TRUE;
		} else if (itemValue == null) {
			return null;
		} else {
			return Boolean.FALSE;
		}
	}
}

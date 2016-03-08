package com.yeepay.g3.core.druid.sql.visitor.functions;

import com.yeepay.g3.core.druid.sql.ast.SQLExpr;
import com.yeepay.g3.core.druid.sql.ast.expr.SQLMethodInvokeExpr;
import com.yeepay.g3.core.druid.sql.visitor.SQLEvalVisitor;

import static com.yeepay.g3.core.druid.sql.visitor.SQLEvalVisitor.EVAL_VALUE;

public class Elt implements Function {

	public static final Elt instance = new Elt();

	public Object eval(SQLEvalVisitor visitor, SQLMethodInvokeExpr x) {
		if (x.getParameters().size() <= 1) {
			return SQLEvalVisitor.EVAL_ERROR;
		}

		SQLExpr param0 = x.getParameters().get(0);
		param0.accept(visitor);

		Object param0Value = param0.getAttributes().get(EVAL_VALUE);
		int param0IntValue;
		if (!(param0Value instanceof Number)) {
			return SQLEvalVisitor.EVAL_ERROR;
		}
		param0IntValue = ((Number) param0Value).intValue();

		if (param0IntValue >= x.getParameters().size()) {
			return null;
		}

		SQLExpr item = x.getParameters().get(param0IntValue);
		item.accept(visitor);

		Object itemValue = item.getAttributes().get(EVAL_VALUE);
		return itemValue;
	}
}

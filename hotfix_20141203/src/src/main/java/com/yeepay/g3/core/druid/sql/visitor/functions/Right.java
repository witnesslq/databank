package com.yeepay.g3.core.druid.sql.visitor.functions;

import com.yeepay.g3.core.druid.sql.ast.SQLExpr;
import com.yeepay.g3.core.druid.sql.ast.expr.SQLMethodInvokeExpr;
import com.yeepay.g3.core.druid.sql.visitor.SQLEvalVisitor;
import com.yeepay.g3.core.druid.sql.visitor.SQLEvalVisitorUtils;

import static com.yeepay.g3.core.druid.sql.visitor.SQLEvalVisitor.EVAL_VALUE;

public class Right implements Function {

	public static final Right instance = new Right();

	public Object eval(SQLEvalVisitor visitor, SQLMethodInvokeExpr x) {
		if (x.getParameters().size() != 2) {
			return SQLEvalVisitor.EVAL_ERROR;
		}

		SQLExpr param0 = x.getParameters().get(0);
		SQLExpr param1 = x.getParameters().get(1);
		param0.accept(visitor);
		param1.accept(visitor);

		Object param0Value = param0.getAttributes().get(EVAL_VALUE);
		Object param1Value = param1.getAttributes().get(EVAL_VALUE);
		if (param0Value == null || param1Value == null) {
			return SQLEvalVisitor.EVAL_ERROR;
		}

		String strValue = param0Value.toString();
		int intValue = SQLEvalVisitorUtils.castToInteger(param1Value);

		int start = strValue.length() - intValue;
		if (start < 0) {
			start = 0;
		}
		String result = strValue.substring(start, strValue.length());
		return result;
	}
}

package com.yeepay.g3.core.druid.sql.visitor.functions;

import com.yeepay.g3.core.druid.sql.ast.SQLExpr;
import com.yeepay.g3.core.druid.sql.ast.expr.SQLMethodInvokeExpr;
import com.yeepay.g3.core.druid.sql.visitor.SQLEvalVisitor;

import static com.yeepay.g3.core.druid.sql.visitor.SQLEvalVisitor.EVAL_VALUE;

public class Ltrim implements Function {

	public static final Ltrim instance = new Ltrim();

	public Object eval(SQLEvalVisitor visitor, SQLMethodInvokeExpr x) {
		if (x.getParameters().size() != 1) {
			return SQLEvalVisitor.EVAL_ERROR;
		}

		SQLExpr param0 = x.getParameters().get(0);
		param0.accept(visitor);

		Object param0Value = param0.getAttributes().get(EVAL_VALUE);
		if (param0Value == null) {
			return SQLEvalVisitor.EVAL_ERROR;
		}

		String strValue = param0Value.toString();

		int index = -1;
		for (int i = 0; i < strValue.length(); ++i) {
			if (!Character.isWhitespace(strValue.charAt(i))) {
				index = i;
				break;
			}
		}

		if (index <= 0) {
			return strValue;
		} else {
			return strValue.substring(index);
		}
	}
}

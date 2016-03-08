package com.yeepay.g3.core.druid.sql.visitor.functions;

import com.yeepay.g3.core.druid.sql.ast.SQLExpr;
import com.yeepay.g3.core.druid.sql.ast.expr.SQLMethodInvokeExpr;
import com.yeepay.g3.core.druid.sql.visitor.SQLEvalVisitor;

import static com.yeepay.g3.core.druid.sql.visitor.SQLEvalVisitor.EVAL_VALUE;

public class Ascii implements Function {

	public static final Ascii instance = new Ascii();

	public Object eval(SQLEvalVisitor visitor, SQLMethodInvokeExpr x) {
		if (x.getParameters().size() == 0) {
			return SQLEvalVisitor.EVAL_ERROR;
		}
		SQLExpr param = x.getParameters().get(0);
		param.accept(visitor);

		Object paramValue = param.getAttributes().get(EVAL_VALUE);
		if (paramValue == null) {
			return SQLEvalVisitor.EVAL_ERROR;
		}

		String strValue = paramValue.toString();
		if (strValue.length() == 0) {
			return SQLEvalVisitor.EVAL_ERROR;
		}

		int ascii = strValue.charAt(0);
		return ascii;
	}
}

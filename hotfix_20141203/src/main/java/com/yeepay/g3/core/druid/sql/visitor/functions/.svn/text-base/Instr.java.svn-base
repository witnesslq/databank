package com.yeepay.g3.core.druid.sql.visitor.functions;

import com.yeepay.g3.core.druid.sql.ast.SQLExpr;
import com.yeepay.g3.core.druid.sql.ast.expr.SQLMethodInvokeExpr;
import com.yeepay.g3.core.druid.sql.visitor.SQLEvalVisitor;

import static com.yeepay.g3.core.druid.sql.visitor.SQLEvalVisitor.EVAL_VALUE;


public class Instr implements Function {
	public static final Instr instance = new Instr();

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

		String strValue0 = param0Value.toString();
		String strValue1 = param1Value.toString();

		int result = strValue0.indexOf(strValue1) + 1;
		return result;
	}
}

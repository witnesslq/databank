package com.yeepay.g3.core.druid.sql.visitor.functions;

import com.yeepay.g3.core.druid.sql.ast.SQLExpr;
import com.yeepay.g3.core.druid.sql.ast.expr.SQLMethodInvokeExpr;
import com.yeepay.g3.core.druid.sql.visitor.SQLEvalVisitor;

import java.util.List;

import static com.yeepay.g3.core.druid.sql.visitor.SQLEvalVisitor.EVAL_VALUE;

public class Locate implements Function {

	public static final Locate instance = new Locate();

	public Object eval(SQLEvalVisitor visitor, SQLMethodInvokeExpr x) {
		List<SQLExpr> params = x.getParameters();
		int paramSize = params.size();
		if (paramSize != 2 && paramSize != 3) {
			return SQLEvalVisitor.EVAL_ERROR;
		}

		SQLExpr param0 = params.get(0);
		SQLExpr param1 = params.get(1);
		SQLExpr param2 = null;

		param0.accept(visitor);
		param1.accept(visitor);
		if (paramSize == 3) {
			param2 = params.get(2);
			param2.accept(visitor);
		}

		Object param0Value = param0.getAttributes().get(EVAL_VALUE);
		Object param1Value = param1.getAttributes().get(EVAL_VALUE);
		if (param0Value == null || param1Value == null) {
			return SQLEvalVisitor.EVAL_ERROR;
		}

		String strValue0 = param0Value.toString();
		String strValue1 = param1Value.toString();

		if (paramSize == 2) {
			int result = strValue1.indexOf(strValue0) + 1;
			return result;
		}

		Object param2Value = param2.getAttributes().get(EVAL_VALUE);
		int start = ((Number) param2Value).intValue();

		int result = strValue1.indexOf(strValue0, start + 1) + 1;
		return result;
	}
}

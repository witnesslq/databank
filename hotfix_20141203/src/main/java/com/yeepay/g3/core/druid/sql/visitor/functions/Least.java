package com.yeepay.g3.core.druid.sql.visitor.functions;

import com.yeepay.g3.core.druid.sql.ast.SQLExpr;
import com.yeepay.g3.core.druid.sql.ast.expr.SQLMethodInvokeExpr;
import com.yeepay.g3.core.druid.sql.visitor.SQLEvalVisitor;
import com.yeepay.g3.core.druid.sql.visitor.SQLEvalVisitorUtils;

import static com.yeepay.g3.core.druid.sql.visitor.SQLEvalVisitor.EVAL_VALUE;

public class Least implements Function {

	public static final Least instance = new Least();

	public Object eval(SQLEvalVisitor visitor, SQLMethodInvokeExpr x) {
		Object result = null;
		for (SQLExpr item : x.getParameters()) {
			item.accept(visitor);

			Object itemValue = item.getAttributes().get(EVAL_VALUE);
			if (result == null) {
				result = itemValue;
			} else {
				if (SQLEvalVisitorUtils.lt(itemValue, result)) {
					result = itemValue;
				}
			}
		}

		return result;
	}
}

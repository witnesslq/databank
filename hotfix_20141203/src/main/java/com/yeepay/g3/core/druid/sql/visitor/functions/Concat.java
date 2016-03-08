package com.yeepay.g3.core.druid.sql.visitor.functions;

import com.yeepay.g3.core.druid.sql.ast.SQLExpr;
import com.yeepay.g3.core.druid.sql.ast.expr.SQLMethodInvokeExpr;
import com.yeepay.g3.core.druid.sql.visitor.SQLEvalVisitor;

import static com.yeepay.g3.core.druid.sql.visitor.SQLEvalVisitor.EVAL_VALUE;

public class Concat implements Function {

	public static final Concat instance = new Concat();

	public Object eval(SQLEvalVisitor visitor, SQLMethodInvokeExpr x) {
		StringBuilder buf = new StringBuilder();

		for (SQLExpr item : x.getParameters()) {
			item.accept(visitor);

			Object itemValue = item.getAttributes().get(EVAL_VALUE);
			if (itemValue == null) {
				return null;
			}
			buf.append(itemValue.toString());
		}

		return buf.toString();
	}
}

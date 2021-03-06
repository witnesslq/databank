package com.yeepay.g3.core.druid.sql.visitor.functions;

import com.yeepay.g3.core.druid.sql.ast.SQLExpr;
import com.yeepay.g3.core.druid.sql.ast.expr.SQLMethodInvokeExpr;
import com.yeepay.g3.core.druid.sql.visitor.SQLEvalVisitor;

import java.math.BigDecimal;

import static com.yeepay.g3.core.druid.sql.visitor.SQLEvalVisitor.EVAL_VALUE;

public class Char implements Function {

	public static final Char instance = new Char();

	public Object eval(SQLEvalVisitor visitor, SQLMethodInvokeExpr x) {
		if (x.getParameters().size() == 0) {
			return SQLEvalVisitor.EVAL_ERROR;
		}

		StringBuffer buf = new StringBuffer(x.getParameters().size());
		for (SQLExpr param : x.getParameters()) {
			param.accept(visitor);

			Object paramValue = param.getAttributes().get(EVAL_VALUE);

			if (paramValue instanceof Number) {
				int charCode = ((Number) paramValue).intValue();
				buf.append((char) charCode);
			} else if (paramValue instanceof String) {
				int charCode = new BigDecimal((String) paramValue).intValue();
				buf.append((char) charCode);
			} else {
				return SQLEvalVisitor.EVAL_ERROR;
			}
		}

		return buf.toString();
	}
}

package com.yeepay.g3.core.druid.sql.visitor.functions;

import com.yeepay.g3.core.druid.sql.ast.SQLExpr;
import com.yeepay.g3.core.druid.sql.ast.expr.SQLMethodInvokeExpr;
import com.yeepay.g3.core.druid.sql.visitor.SQLEvalVisitor;
import com.yeepay.g3.core.druid.sql.visitor.SQLEvalVisitorUtils;

import java.util.List;

import static com.yeepay.g3.core.druid.sql.visitor.SQLEvalVisitor.EVAL_ERROR;
import static com.yeepay.g3.core.druid.sql.visitor.SQLEvalVisitor.EVAL_VALUE;

public class If implements Function {

	public static final If instance = new If();

	public Object eval(SQLEvalVisitor visitor, SQLMethodInvokeExpr x) {
		final List<SQLExpr> parameters = x.getParameters();
		if (parameters.size() == 0) {
			return EVAL_ERROR;
		}

		SQLExpr condition = parameters.get(0);
		condition.accept(visitor);
		Object itemValue = condition.getAttributes().get(EVAL_VALUE);
		if (Boolean.TRUE == itemValue || !SQLEvalVisitorUtils.eq(itemValue, 0)) {
			SQLExpr trueExpr = parameters.get(1);
			trueExpr.accept(visitor);
			return trueExpr.getAttributes().get(EVAL_VALUE);
		} else {
			SQLExpr falseExpr = parameters.get(2);
			falseExpr.accept(visitor);
			return falseExpr.getAttributes().get(EVAL_VALUE);
		}
	}
}

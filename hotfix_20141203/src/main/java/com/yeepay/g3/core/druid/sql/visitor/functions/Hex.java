package com.yeepay.g3.core.druid.sql.visitor.functions;

import com.yeepay.g3.core.druid.sql.ast.SQLExpr;
import com.yeepay.g3.core.druid.sql.ast.expr.SQLMethodInvokeExpr;
import com.yeepay.g3.core.druid.sql.parser.ParserException;
import com.yeepay.g3.core.druid.sql.visitor.SQLEvalVisitor;
import com.yeepay.g3.core.druid.util.HexBin;

import static com.yeepay.g3.core.druid.sql.visitor.SQLEvalVisitor.EVAL_VALUE;

public class Hex implements Function {

	public static final Hex instance = new Hex();

	public Object eval(SQLEvalVisitor visitor, SQLMethodInvokeExpr x) {
		if (x.getParameters().size() != 1) {
			throw new ParserException("argument's != 1, " + x.getParameters().size());
		}

		SQLExpr param0 = x.getParameters().get(0);
		param0.accept(visitor);

		Object param0Value = param0.getAttributes().get(EVAL_VALUE);
		if (param0Value == null) {
			return SQLEvalVisitor.EVAL_ERROR;
		}

		if (param0Value instanceof String) {
			byte[] bytes = ((String) param0Value).getBytes();
			String result = HexBin.encode(bytes);
			return result;
		}

		if (param0Value instanceof Number) {
			long value = ((Number) param0Value).longValue();
			String result = Long.toHexString(value).toUpperCase();
			return result;
		}

		return SQLEvalVisitor.EVAL_ERROR;
	}
}

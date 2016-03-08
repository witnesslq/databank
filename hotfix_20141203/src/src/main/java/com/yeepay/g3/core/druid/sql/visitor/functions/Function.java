package com.yeepay.g3.core.druid.sql.visitor.functions;

import com.yeepay.g3.core.druid.sql.ast.expr.SQLMethodInvokeExpr;
import com.yeepay.g3.core.druid.sql.visitor.SQLEvalVisitor;


public interface Function {
	Object eval(SQLEvalVisitor visitor, SQLMethodInvokeExpr x);
}

package com.yeepay.g3.core.druid.sql.visitor.functions;

import com.yeepay.g3.core.druid.sql.ast.expr.SQLMethodInvokeExpr;
import com.yeepay.g3.core.druid.sql.visitor.SQLEvalVisitor;

import java.util.Date;


public class Now implements Function {
	public static final Now instance = new Now();

	public Object eval(SQLEvalVisitor visitor, SQLMethodInvokeExpr x) {
		return new Date();
	}
}

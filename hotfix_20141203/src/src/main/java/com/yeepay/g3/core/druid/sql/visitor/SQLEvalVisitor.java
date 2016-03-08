package com.yeepay.g3.core.druid.sql.visitor;

import com.yeepay.g3.core.druid.sql.visitor.functions.Function;

import java.util.List;

public interface SQLEvalVisitor extends SQLASTVisitor {

	public static final String EVAL_VALUE = "eval.value";
	public static final String EVAL_EXPR = "eval.expr";
	public static final Object EVAL_ERROR = new Object();
	public static final Object EVAL_VALUE_COUNT = new Object();
	public static final Object EVAL_VALUE_NULL = new Object();

	Function getFunction(String funcName);

	void registerFunction(String funcName, Function function);

	List<Object> getParameters();

	void setParameters(List<Object> parameters);

	int incrementAndGetVariantIndex();

	boolean isMarkVariantIndex();

	void setMarkVariantIndex(boolean markVariantIndex);
}

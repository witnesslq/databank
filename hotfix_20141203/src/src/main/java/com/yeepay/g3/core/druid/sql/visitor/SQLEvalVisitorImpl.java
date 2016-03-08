package com.yeepay.g3.core.druid.sql.visitor;

import com.yeepay.g3.core.druid.sql.ast.expr.*;
import com.yeepay.g3.core.druid.sql.visitor.functions.Function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQLEvalVisitorImpl extends SQLASTVisitorAdapter implements SQLEvalVisitor {

	private List<Object> parameters = new ArrayList<Object>();

	private Map<String, Function> functions = new HashMap<String, Function>();

	private int variantIndex = -1;

	private boolean markVariantIndex = true;

	public SQLEvalVisitorImpl() {
		this(new ArrayList<Object>(1));
	}

	public SQLEvalVisitorImpl(List<Object> parameters) {
		this.parameters = parameters;
	}

	public List<Object> getParameters() {
		return parameters;
	}

	public void setParameters(List<Object> parameters) {
		this.parameters = parameters;
	}

	public boolean visit(SQLCharExpr x) {
		return SQLEvalVisitorUtils.visit(this, x);
	}

	public int incrementAndGetVariantIndex() {
		return ++variantIndex;
	}

	public int getVariantIndex() {
		return variantIndex;
	}

	public boolean visit(SQLVariantRefExpr x) {
		return SQLEvalVisitorUtils.visit(this, x);
	}

	public boolean visit(SQLBinaryOpExpr x) {
		return SQLEvalVisitorUtils.visit(this, x);
	}

	public boolean visit(SQLIntegerExpr x) {
		return SQLEvalVisitorUtils.visit(this, x);
	}

	public boolean visit(SQLNumberExpr x) {
		return SQLEvalVisitorUtils.visit(this, x);
	}

	@Override
	public boolean visit(SQLCaseExpr x) {
		return SQLEvalVisitorUtils.visit(this, x);
	}

	@Override
	public boolean visit(SQLInListExpr x) {
		return SQLEvalVisitorUtils.visit(this, x);
	}

	@Override
	public boolean visit(SQLNullExpr x) {
		return SQLEvalVisitorUtils.visit(this, x);
	}

	@Override
	public boolean visit(SQLMethodInvokeExpr x) {
		return SQLEvalVisitorUtils.visit(this, x);
	}

	@Override
	public boolean visit(SQLQueryExpr x) {
		return SQLEvalVisitorUtils.visit(this, x);
	}

	public boolean isMarkVariantIndex() {
		return markVariantIndex;
	}

	public void setMarkVariantIndex(boolean markVariantIndex) {
		this.markVariantIndex = markVariantIndex;
	}

	@Override
	public Function getFunction(String funcName) {
		return functions.get(funcName);
	}

	@Override
	public void registerFunction(String funcName, Function function) {
		functions.put(funcName, function);
	}

	public boolean visit(SQLIdentifierExpr x) {
		return SQLEvalVisitorUtils.visit(this, x);
	}
}

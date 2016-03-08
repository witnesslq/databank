package com.yeepay.g3.core.druid.sql.ast.expr;

public enum SQLUnaryOperator {
	Plus("+"), //
	Negative("-"), //
	Not("!"), //
	Compl("~"), //
	Prior("PRIOR"), //
	ConnectByRoot("CONNECT BY"), //
	BINARY("BINARY"), //
	RAW("RAW"), //
	NOT("NOT");

	public final String name;

	SQLUnaryOperator(String name) {
		this.name = name;
	}
}

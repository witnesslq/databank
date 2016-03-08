package com.yeepay.g3.core.druid.sql.ast.statement;

public enum SQLUnionOperator {
	UNION("UNION"), UNION_ALL("UNION ALL"), MINUS("MINUS"), INTERSECT("INTERSECT"), DISTINCT("UNION DISTINCT");

	public final String name;

	private SQLUnionOperator(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}

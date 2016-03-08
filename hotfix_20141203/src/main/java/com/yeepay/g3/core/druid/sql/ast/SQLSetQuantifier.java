package com.yeepay.g3.core.druid.sql.ast;

public interface SQLSetQuantifier {

	// SQL 92
	public static final int ALL = 1;
	public static final int DISTINCT = 2;

	public static final int UNIQUE = 3;
	public static final int DISTINCTROW = 4;

	// <SetQuantifier>
}

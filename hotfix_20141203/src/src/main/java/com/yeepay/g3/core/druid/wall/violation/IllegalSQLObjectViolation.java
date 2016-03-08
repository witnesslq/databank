package com.yeepay.g3.core.druid.wall.violation;

import com.yeepay.g3.core.druid.wall.Violation;

public class IllegalSQLObjectViolation implements Violation {

	private final String message;
	private final String sqlPart;
	private final int errorCode;

	public IllegalSQLObjectViolation(int errorCode, String message, String condition) {
		this.errorCode = errorCode;
		this.message = message;
		this.sqlPart = condition;
	}

	public String getSqlPart() {
		return sqlPart;
	}

	public String toString() {
		return this.sqlPart;
	}


	public String getMessage() {
		return message;
	}

	@Override
	public int getErrorCode() {
		return errorCode;
	}


}

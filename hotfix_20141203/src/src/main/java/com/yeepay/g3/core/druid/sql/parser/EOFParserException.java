package com.yeepay.g3.core.druid.sql.parser;

@SuppressWarnings("serial")
public class EOFParserException extends ParserException {

	public EOFParserException() {
		super("发现异常结束符，请检查SQL语句是否完整");
	}
}

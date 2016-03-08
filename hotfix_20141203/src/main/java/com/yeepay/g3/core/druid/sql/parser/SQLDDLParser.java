package com.yeepay.g3.core.druid.sql.parser;

import com.yeepay.g3.core.druid.sql.ast.statement.SQLTableConstaint;

public class SQLDDLParser extends SQLStatementParser {

	public SQLDDLParser(String sql) {
		super(sql);
	}

	public SQLDDLParser(SQLExprParser exprParser) {
		super(exprParser);
	}

	protected SQLTableConstaint parseConstraint() {
		if (lexer.token() == Token.CONSTRAINT) {
			lexer.nextToken();
		}

		if (lexer.token() == Token.IDENTIFIER) {
			this.exprParser.name();
			throw new ParserException("TODO");
		}

		if (lexer.token() == Token.PRIMARY) {
			lexer.nextToken();
			accept(Token.KEY);

			throw new ParserException("TODO");
		}

		throw new ParserException("TODO");
	}
}

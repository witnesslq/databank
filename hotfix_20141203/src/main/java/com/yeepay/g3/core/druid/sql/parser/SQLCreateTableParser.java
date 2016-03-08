package com.yeepay.g3.core.druid.sql.parser;

import com.yeepay.g3.core.druid.sql.ast.statement.SQLColumnDefinition;
import com.yeepay.g3.core.druid.sql.ast.statement.SQLCreateTableStatement;
import com.yeepay.g3.core.druid.sql.ast.statement.SQLPrimaryKey;
import com.yeepay.g3.core.druid.sql.ast.statement.SQLUnique;

public class SQLCreateTableParser extends SQLDDLParser {

	public SQLCreateTableParser(String sql) {
		super(sql);
	}

	public SQLCreateTableParser(SQLExprParser exprParser) {
		super(exprParser);
	}

	public SQLCreateTableStatement parseCrateTable() {
		return parseCrateTable(true);
	}

	public SQLCreateTableStatement parseCrateTable(boolean acceptCreate) {
		if (acceptCreate) {
			accept(Token.CREATE);
		}

		SQLCreateTableStatement createTable = newCreateStatement();

		if (identifierEquals("GLOBAL")) {
			lexer.nextToken();

			if (identifierEquals("TEMPORARY")) {
				lexer.nextToken();
				createTable.setType(SQLCreateTableStatement.Type.GLOBAL_TEMPORARY);
			} else {
				throw new ParserException("syntax error " + lexer.token() + " " + lexer.stringVal());
			}
		} else if (lexer.token() == Token.IDENTIFIER && lexer.stringVal().equalsIgnoreCase("LOCAL")) {
			lexer.nextToken();
			if (lexer.token() == Token.IDENTIFIER && lexer.stringVal().equalsIgnoreCase("TEMPORAY")) {
				lexer.nextToken();
				createTable.setType(SQLCreateTableStatement.Type.LOCAL_TEMPORARY);
			} else {
				throw new ParserException("syntax error");
			}
		}

		accept(Token.TABLE);

		createTable.setName(this.exprParser.name());

		if (lexer.token() == Token.LPAREN) {
			lexer.nextToken();

			for (; ; ) {
				if (lexer.token() == Token.IDENTIFIER || lexer.token() == Token.LITERAL_ALIAS) {
					SQLColumnDefinition column = this.exprParser.parseColumn();
					createTable.getTableElementList().add(column);
				} else if (lexer.token() == Token.PRIMARY) {
					SQLPrimaryKey primaryKey = exprParser.parsePrimaryKey();
					createTable.getTableElementList().add(primaryKey);
				} else if (lexer.token == Token.UNIQUE) {
					SQLUnique unique = exprParser.parseUnique();
					createTable.getTableElementList().add(unique);
				} else {
					throw new ParserException("TODO " + lexer.token());
				}

				if (lexer.token() == Token.COMMA) {
					lexer.nextToken();
					continue;
				}

				break;
			}

			// while
			// (this.tokenList.current().equals(OracleToken.ConstraintToken)) {
			// parseConstaint(table.getConstaints());
			//
			// if (this.tokenList.current().equals(OracleToken.CommaToken))
			// ;
			// lexer.nextToken();
			// }

			accept(Token.RPAREN);

		}

		return createTable;
	}

	protected SQLCreateTableStatement newCreateStatement() {
		return new SQLCreateTableStatement();
	}
}

package com.yeepay.g3.core.druid.sql.parser;

public class SQLParser {

	protected final Lexer lexer;

	public SQLParser(String sql) {
		this(new Lexer(sql));
		this.lexer.nextToken();
	}

	public SQLParser(Lexer lexer) {
		this.lexer = lexer;
	}

	public final Lexer getLexer() {
		return lexer;
	}

	protected boolean identifierEquals(String text) {
		return lexer.token() == Token.IDENTIFIER && lexer.stringVal().equalsIgnoreCase(text);
	}

	protected void acceptIdentifier(String text) {
		if (identifierEquals(text)) {
			lexer.nextToken();
		} else {
			setErrorEndPos(lexer.pos());
			throw new ParserException("语法错误, 期望 " + text + ", 实际 " + lexer.token());
		}
	}

	protected String as() {
		String alias = null;

		if (lexer.token() == Token.AS) {
			lexer.nextToken();

			if (lexer.token() == Token.LITERAL_ALIAS) {
				alias = '"' + lexer.stringVal() + '"';
				lexer.nextToken();
			} else if (lexer.token() == Token.IDENTIFIER) {
				alias = lexer.stringVal();
				lexer.nextToken();
			} else if (lexer.token() == Token.LITERAL_CHARS) {
				alias = "'" + lexer.stringVal() + "'";
				lexer.nextToken();
			} else {
				switch (lexer.token()) {
					case KEY:
					case INDEX:
					case CASE:
						alias = lexer.token().name();
						lexer.nextToken();
						return alias;
					case QUES:
						alias = "?";
						lexer.nextToken();
					default:
						break;
				}
			}

			if (alias != null) {
				while (lexer.token() == Token.DOT) {
					lexer.nextToken();
					alias += ('.' + lexer.token().name());
					lexer.nextToken();
				}

				return alias;
			}

			if (lexer.token() == Token.LPAREN) {
				return null;
			}

			throw new ParserException("错误 : " + lexer.token());
		}

		if (lexer.token() == Token.LITERAL_ALIAS) {
			alias = '"' + lexer.stringVal() + '"';
			lexer.nextToken();
		} else if (lexer.token() == Token.IDENTIFIER) {
			alias = lexer.stringVal();
			lexer.nextToken();
		} else if (lexer.token() == Token.LITERAL_CHARS) {
			alias = "'" + lexer.stringVal() + "'";
			lexer.nextToken();
		} else if (lexer.token() == Token.CASE) {
			alias = lexer.token.name();
			lexer.nextToken();
		}

		switch (lexer.token()) {
			case KEY:
				alias = lexer.token().name();
				lexer.nextToken();
				return alias;
			default:
				break;
		}

		return alias;
	}

	public void accept(Token token) {
		if (lexer.token() == token) {
			lexer.nextToken();
		} else {
			setErrorEndPos(lexer.pos());
			throw new ParserException("语法错误, 期望 " + token + ", 实际 " + lexer.token() + " "
					+ lexer.stringVal() + ", pos " + this.lexer.pos());
		}
	}

	public void match(Token token) {
		if (lexer.token() != token) {
			throw new ParserException("语法错误, 期望 " + token + ", 实际 " + lexer.token() + " "
					+ lexer.stringVal());
		}
	}

	private int errorEndPos = -1;

	protected void setErrorEndPos(int errPos) {
		if (errPos > errorEndPos) {
			errorEndPos = errPos;
		}
	}

}

package com.yeepay.g3.core.druid.sql.parser;

public class CharTypes {

	private static final boolean[] hexFlags = new boolean[256];

	static {
		for (char c = 0; c < hexFlags.length; ++c) {
			if (c >= 'A' && c <= 'F') {
				hexFlags[c] = true;
			} else if (c >= 'a' && c <= 'f') {
				hexFlags[c] = true;
			} else if (c >= '0' && c <= '9') {
				hexFlags[c] = true;
			}
		}
	}

	public static boolean isHex(char c) {
		return c < 256 && hexFlags[c];
	}

	public static boolean isDigit(char c) {
		return c >= '0' && c <= '9';
	}

	private static final boolean[] firstIdentifierFlags = new boolean[256];

	static {
		for (char c = 0; c < firstIdentifierFlags.length; ++c) {
			if (c >= 'A' && c <= 'Z') {
				firstIdentifierFlags[c] = true;
			} else if (c >= 'a' && c <= 'z') {
				firstIdentifierFlags[c] = true;
			}
		}
		firstIdentifierFlags['`'] = true;
		firstIdentifierFlags['_'] = true;
		firstIdentifierFlags['$'] = true;
	}

	public static boolean isFirstIdentifierChar(char c) {
		return c > firstIdentifierFlags.length || firstIdentifierFlags[c];
	}

	private static final boolean[] identifierFlags = new boolean[256];

	static {
		for (char c = 0; c < identifierFlags.length; ++c) {
			if (c >= 'A' && c <= 'Z') {
				identifierFlags[c] = true;
			} else if (c >= 'a' && c <= 'z') {
				identifierFlags[c] = true;
			} else if (c >= '0' && c <= '9') {
				identifierFlags[c] = true;
			}
		}
		// identifierFlags['`'] = true;
		identifierFlags['_'] = true;
		identifierFlags['$'] = true;
		identifierFlags['#'] = true;
	}

	public static boolean isIdentifierChar(char c) {
		return c > identifierFlags.length || identifierFlags[c];
	}

	private static final boolean[] whitespaceFlags = new boolean[256];

	static {
		whitespaceFlags[' '] = true;
		whitespaceFlags['\n'] = true;
		whitespaceFlags['\r'] = true;
		whitespaceFlags['\t'] = true;
		whitespaceFlags['\f'] = true;
		whitespaceFlags['\b'] = true;
		whitespaceFlags[160] = true; // 特别处理
	}

	/**
	 * @return false if {@link LayoutCharacters#EOI}
	 */
	public static boolean isWhitespace(char c) {
		return c <= whitespaceFlags.length && whitespaceFlags[c];
	}

}

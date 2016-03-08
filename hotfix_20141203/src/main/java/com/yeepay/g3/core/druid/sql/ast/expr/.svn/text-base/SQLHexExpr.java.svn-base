package com.yeepay.g3.core.druid.sql.ast.expr;

import com.yeepay.g3.core.druid.sql.ast.SQLExprImpl;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;
import com.yeepay.g3.core.druid.util.HexBin;

public class SQLHexExpr extends SQLExprImpl implements SQLLiteralExpr {

	private static final long serialVersionUID = 1L;

	private final String hex;

	public SQLHexExpr(String hex) {
		this.hex = hex;
	}

	public String getHex() {
		return hex;
	}

	public void output(StringBuffer buf) {
		buf.append("0x");
		buf.append(this.hex);

		String charset = (String) getAttribute("USING");
		if (charset != null) {
			buf.append(" USING ");
			buf.append(charset);
		}
	}

	protected void accept0(SQLASTVisitor visitor) {
		visitor.visit(this);
		visitor.endVisit(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hex == null) ? 0 : hex.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		SQLHexExpr other = (SQLHexExpr) obj;
		if (hex == null) {
			if (other.hex != null) {
				return false;
			}
		} else if (!hex.equals(other.hex)) {
			return false;
		}
		return true;
	}

	public byte[] toBytes() {
		return HexBin.decode(this.hex);
	}
}

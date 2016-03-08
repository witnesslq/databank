package com.yeepay.g3.core.druid.sql.ast.statement;

import com.yeepay.g3.core.druid.sql.ast.SQLHint;
import com.yeepay.g3.core.druid.sql.ast.SQLObjectImpl;

import java.util.ArrayList;
import java.util.List;

public abstract class SQLTableSourceImpl extends SQLObjectImpl implements SQLTableSource {

	private static final long serialVersionUID = 1L;

	protected String alias;

	protected List<SQLHint> hints;

	public SQLTableSourceImpl() {

	}

	public SQLTableSourceImpl(String alias) {

		this.alias = alias;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public int getHintsSize() {
		if (hints == null) {
			return 0;
		}

		return hints.size();
	}

	public List<SQLHint> getHints() {
		if (hints == null) {
			hints = new ArrayList<SQLHint>(2);
		}
		return hints;
	}

	public void setHints(List<SQLHint> hints) {
		this.hints = hints;
	}
}

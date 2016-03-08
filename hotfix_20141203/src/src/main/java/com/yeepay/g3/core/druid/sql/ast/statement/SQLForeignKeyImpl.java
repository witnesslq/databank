package com.yeepay.g3.core.druid.sql.ast.statement;

import com.yeepay.g3.core.druid.sql.ast.SQLName;

import java.util.ArrayList;
import java.util.List;

public abstract class SQLForeignKeyImpl extends SQLConstaintImpl implements SQLForeignKeyConstraint {

	private static final long serialVersionUID = 1L;
	private SQLName referencedTableName;
	private List<SQLName> referencingColumns = new ArrayList<SQLName>();
	private List<SQLName> referencedColumns = new ArrayList<SQLName>();

	@Override
	public List<SQLName> getReferencingColumns() {
		return referencingColumns;
	}

	@Override
	public SQLName getReferencedTableName() {
		return referencedTableName;
	}

	@Override
	public void setReferencedTableName(SQLName value) {
		this.referencedTableName = value;
	}

	@Override
	public List<SQLName> getReferencedColumns() {
		return referencedColumns;
	}

}

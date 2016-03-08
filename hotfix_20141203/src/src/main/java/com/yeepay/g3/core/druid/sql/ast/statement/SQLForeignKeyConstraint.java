package com.yeepay.g3.core.druid.sql.ast.statement;

import com.yeepay.g3.core.druid.sql.ast.SQLName;

import java.util.List;

public interface SQLForeignKeyConstraint extends SQLConstaint, SQLTableElement {

	List<SQLName> getReferencingColumns();

	SQLName getReferencedTableName();

	void setReferencedTableName(SQLName value);

	List<SQLName> getReferencedColumns();
}

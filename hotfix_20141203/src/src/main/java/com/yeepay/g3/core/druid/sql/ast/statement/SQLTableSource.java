package com.yeepay.g3.core.druid.sql.ast.statement;

import com.yeepay.g3.core.druid.sql.ast.SQLHint;
import com.yeepay.g3.core.druid.sql.ast.SQLObject;

import java.util.List;

public interface SQLTableSource extends SQLObject {

	String getAlias();

	void setAlias(String alias);

	List<SQLHint> getHints();
}

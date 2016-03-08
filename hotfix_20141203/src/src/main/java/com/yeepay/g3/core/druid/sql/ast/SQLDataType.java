package com.yeepay.g3.core.druid.sql.ast;

import java.util.List;

public interface SQLDataType extends SQLObject {

	String getName();

	void setName(String name);

	List<SQLExpr> getArguments();
}

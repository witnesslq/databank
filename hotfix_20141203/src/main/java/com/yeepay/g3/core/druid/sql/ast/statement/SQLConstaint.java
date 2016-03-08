package com.yeepay.g3.core.druid.sql.ast.statement;

import com.yeepay.g3.core.druid.sql.ast.SQLName;
import com.yeepay.g3.core.druid.sql.ast.SQLObject;

public interface SQLConstaint extends SQLObject {

	SQLName getName();

	void setName(SQLName value);
}

package com.yeepay.g3.core.druid.sql.ast;

import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

import java.util.Map;

public interface SQLObject {

	void accept(SQLASTVisitor visitor);

	SQLObject getParent();

	void setParent(SQLObject parent);

	Map<String, Object> getAttributes();

	Object getAttribute(String name);

	void putAttribute(String name, Object value);

	Map<String, Object> getAttributesDirect();

	void output(StringBuffer buf);
}

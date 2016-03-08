package com.yeepay.g3.core.druid.wall;

import com.yeepay.g3.core.druid.sql.ast.SQLObject;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

import java.util.List;

public interface WallVisitor extends SQLASTVisitor {

	WallConfig getConfig();

	WallProvider getProvider();

	List<Violation> getViolations();

	void addViolation(Violation violation);

	boolean isDenyTable(String name);

	String toSQL(SQLObject obj);
}

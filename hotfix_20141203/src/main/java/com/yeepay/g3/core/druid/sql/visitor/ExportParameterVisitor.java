package com.yeepay.g3.core.druid.sql.visitor;

import java.util.List;


public interface ExportParameterVisitor extends SQLASTVisitor {
	List<Object> getParameters();
}

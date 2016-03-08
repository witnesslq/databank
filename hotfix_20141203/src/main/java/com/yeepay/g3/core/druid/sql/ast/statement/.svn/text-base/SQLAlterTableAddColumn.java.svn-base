package com.yeepay.g3.core.druid.sql.ast.statement;

import com.yeepay.g3.core.druid.sql.ast.SQLObjectImpl;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

import java.util.ArrayList;
import java.util.List;

public class SQLAlterTableAddColumn extends SQLObjectImpl implements SQLAlterTableItem {

	private static final long serialVersionUID = 1L;

	private List<SQLColumnDefinition> columns = new ArrayList<SQLColumnDefinition>();

	@Override
	protected void accept0(SQLASTVisitor visitor) {
		if (visitor.visit(this)) {
			acceptChild(visitor, columns);
		}
		visitor.endVisit(this);
	}

	public List<SQLColumnDefinition> getColumns() {
		return columns;
	}

	public void setColumns(List<SQLColumnDefinition> columns) {
		this.columns = columns;
	}

}

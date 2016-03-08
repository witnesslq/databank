package com.yeepay.g3.core.druid.sql.ast.statement;

import com.yeepay.g3.core.druid.sql.ast.SQLExpr;
import com.yeepay.g3.core.druid.sql.ast.SQLStatementImpl;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

public class SQLDropIndexStatement extends SQLStatementImpl implements SQLDDLStatement {

	private static final long serialVersionUID = 1L;

	private SQLExpr indexName;
	private SQLExprTableSource tableName;

	public SQLExpr getIndexName() {
		return indexName;
	}

	public void setIndexName(SQLExpr indexName) {
		this.indexName = indexName;
	}

	public SQLExprTableSource getTableName() {
		return tableName;
	}

	public void setTableName(SQLExpr tableName) {
		this.setTableName(new SQLExprTableSource(tableName));
	}

	public void setTableName(SQLExprTableSource tableName) {
		this.tableName = tableName;
	}

	@Override
	protected void accept0(SQLASTVisitor visitor) {
		if (visitor.visit(this)) {
			acceptChild(visitor, indexName);
			acceptChild(visitor, tableName);
		}
		visitor.endVisit(this);
	}
}

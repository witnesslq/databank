package com.yeepay.g3.core.druid.sql.ast.statement;

import com.yeepay.g3.core.druid.sql.ast.SQLName;
import com.yeepay.g3.core.druid.sql.ast.SQLObjectImpl;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

public class SQLAlterTableDropForeinKey extends SQLObjectImpl implements SQLAlterTableItem {

	private static final long serialVersionUID = 1L;

	private SQLName indexName;

	public SQLName getIndexName() {
		return indexName;
	}

	public void setIndexName(SQLName indexName) {
		this.indexName = indexName;
	}

	@Override
	protected void accept0(SQLASTVisitor visitor) {
		if (visitor.visit(this)) {
			acceptChild(visitor, indexName);
		}
		visitor.endVisit(this);
	}

}

package com.yeepay.g3.core.druid.sql.ast.statement;

import com.yeepay.g3.core.druid.sql.ast.SQLName;
import com.yeepay.g3.core.druid.sql.ast.SQLStatement;
import com.yeepay.g3.core.druid.sql.ast.SQLStatementImpl;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

public class SQLUseStatement extends SQLStatementImpl implements SQLStatement {

	private static final long serialVersionUID = 1L;
	private SQLName database;

	public SQLName getDatabase() {
		return database;
	}

	public void setDatabase(SQLName database) {
		this.database = database;
	}

	@Override
	public void accept0(SQLASTVisitor visitor) {
		if (visitor.visit(this)) {
			acceptChild(visitor, database);
		}
		visitor.endVisit(this);
	}

}

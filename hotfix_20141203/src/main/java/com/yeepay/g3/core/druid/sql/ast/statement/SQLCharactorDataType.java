package com.yeepay.g3.core.druid.sql.ast.statement;

import com.yeepay.g3.core.druid.sql.ast.SQLDataTypeImpl;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

@SuppressWarnings("serial")
public class SQLCharactorDataType extends SQLDataTypeImpl {

	private String charSetName;
	private String collate;

	private String charType;

	public static final String CHAR_TYPE_BYTE = "BYTE";
	public static final String CHAR_TYPE_CHAR = "CHAR";

	public SQLCharactorDataType(String name) {
		super(name);
	}

	public String getCharSetName() {
		return charSetName;
	}

	public void setCharSetName(String charSetName) {
		this.charSetName = charSetName;
	}

	public String getCollate() {
		return collate;
	}

	public void setCollate(String collate) {
		this.collate = collate;
	}

	public String getCharType() {
		return charType;
	}

	public void setCharType(String charType) {
		this.charType = charType;
	}

	@Override
	protected void accept0(SQLASTVisitor visitor) {
		if (visitor.visit(this)) {
			acceptChild(visitor, this.arguments);
		}

		visitor.endVisit(this);
	}
}

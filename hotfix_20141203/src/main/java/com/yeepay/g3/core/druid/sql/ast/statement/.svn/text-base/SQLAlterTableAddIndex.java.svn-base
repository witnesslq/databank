package com.yeepay.g3.core.druid.sql.ast.statement;

import com.yeepay.g3.core.druid.sql.ast.SQLName;
import com.yeepay.g3.core.druid.sql.ast.SQLObjectImpl;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

import java.util.ArrayList;
import java.util.List;

public class SQLAlterTableAddIndex extends SQLObjectImpl implements SQLAlterTableItem {

	private static final long serialVersionUID = 1L;

	private SQLName name;

	private List<SQLSelectOrderByItem> items = new ArrayList<SQLSelectOrderByItem>();

	private String type;

	@Override
	protected void accept0(SQLASTVisitor visitor) {

	}

	public List<SQLSelectOrderByItem> getItems() {
		return items;
	}

	public void setItems(List<SQLSelectOrderByItem> items) {
		this.items = items;
	}

	public SQLName getName() {
		return name;
	}

	public void setName(SQLName name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}

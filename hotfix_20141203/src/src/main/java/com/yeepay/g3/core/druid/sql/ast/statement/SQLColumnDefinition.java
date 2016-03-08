package com.yeepay.g3.core.druid.sql.ast.statement;

import com.yeepay.g3.core.druid.sql.ast.SQLDataType;
import com.yeepay.g3.core.druid.sql.ast.SQLExpr;
import com.yeepay.g3.core.druid.sql.ast.SQLName;
import com.yeepay.g3.core.druid.sql.ast.SQLObjectImpl;
import com.yeepay.g3.core.druid.sql.visitor.SQLASTVisitor;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class SQLColumnDefinition extends SQLObjectImpl implements SQLTableElement {

	private SQLName name;
	private SQLDataType dataType;
	private SQLExpr defaultExpr;
	private final List<SQLColumnConstraint> constaints = new ArrayList<SQLColumnConstraint>(0);
	private String comment;

	private Boolean enable;

	public SQLColumnDefinition() {

	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public SQLName getName() {
		return name;
	}

	public void setName(SQLName name) {
		this.name = name;
	}

	public SQLDataType getDataType() {
		return dataType;
	}

	public void setDataType(SQLDataType dataType) {
		this.dataType = dataType;
	}

	public SQLExpr getDefaultExpr() {
		return defaultExpr;
	}

	public void setDefaultExpr(SQLExpr defaultExpr) {
		this.defaultExpr = defaultExpr;
	}

	public List<SQLColumnConstraint> getConstaints() {
		return constaints;
	}

	@Override
	public void output(StringBuffer buf) {
		name.output(buf);
		buf.append(' ');
		this.dataType.output(buf);
		if (defaultExpr != null) {
			buf.append(" DEFAULT ");
			this.defaultExpr.output(buf);
		}
	}

	@Override
	protected void accept0(SQLASTVisitor visitor) {
		if (visitor.visit(this)) {
			this.acceptChild(visitor, name);
			this.acceptChild(visitor, dataType);
			this.acceptChild(visitor, defaultExpr);
			this.acceptChild(visitor, constaints);
		}
		visitor.endVisit(this);
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}

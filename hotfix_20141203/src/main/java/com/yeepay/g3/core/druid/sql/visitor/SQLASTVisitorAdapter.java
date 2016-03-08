package com.yeepay.g3.core.druid.sql.visitor;

import com.yeepay.g3.core.druid.sql.ast.*;
import com.yeepay.g3.core.druid.sql.ast.expr.*;
import com.yeepay.g3.core.druid.sql.ast.statement.*;
import com.yeepay.g3.core.druid.sql.ast.statement.SQLInsertStatement.ValuesClause;

public class SQLASTVisitorAdapter implements SQLASTVisitor {

	public void endVisit(SQLAllColumnExpr x) {
	}

	public void endVisit(SQLBetweenExpr x) {
	}

	public void endVisit(SQLBinaryOpExpr x) {
	}

	public void endVisit(SQLCaseExpr x) {
	}

	public void endVisit(SQLCaseExpr.Item x) {
	}

	public void endVisit(SQLCharExpr x) {
	}

	public void endVisit(SQLIdentifierExpr x) {
	}

	public void endVisit(SQLInListExpr x) {
	}

	public void endVisit(SQLIntegerExpr x) {
	}

	public void endVisit(SQLExistsExpr x) {
	}

	public void endVisit(SQLNCharExpr x) {
	}

	public void endVisit(SQLNotExpr x) {
	}

	public void endVisit(SQLNullExpr x) {
	}

	public void endVisit(SQLNumberExpr x) {
	}

	public void endVisit(SQLPropertyExpr x) {
	}

	public void endVisit(SQLSelectGroupByClause x) {
	}

	public void endVisit(SQLSelectItem x) {
	}

	public void endVisit(SQLSelectStatement selectStatement) {
	}

	public void postVisit(SQLObject astNode) {
	}

	public void preVisit(SQLObject astNode) {
	}

	public boolean visit(SQLAllColumnExpr x) {
		return true;
	}

	public boolean visit(SQLBetweenExpr x) {
		return true;
	}

	public boolean visit(SQLBinaryOpExpr x) {
		return true;
	}

	public boolean visit(SQLCaseExpr x) {
		return true;
	}

	public boolean visit(SQLCaseExpr.Item x) {
		return true;
	}

	public boolean visit(SQLCastExpr x) {
		return true;
	}

	public boolean visit(SQLCharExpr x) {
		return true;
	}

	public boolean visit(SQLExistsExpr x) {
		return true;
	}

	public boolean visit(SQLIdentifierExpr x) {
		return true;
	}

	public boolean visit(SQLInListExpr x) {
		return true;
	}

	public boolean visit(SQLIntegerExpr x) {
		return true;
	}

	public boolean visit(SQLNCharExpr x) {
		return true;
	}

	public boolean visit(SQLNotExpr x) {
		return true;
	}

	public boolean visit(SQLNullExpr x) {
		return true;
	}

	public boolean visit(SQLNumberExpr x) {
		return true;
	}

	public boolean visit(SQLPropertyExpr x) {
		return true;
	}

	public boolean visit(SQLSelectGroupByClause x) {
		return true;
	}

	public boolean visit(SQLSelectItem x) {
		return true;
	}

	public void endVisit(SQLCastExpr x) {
	}

	public boolean visit(SQLSelectStatement astNode) {
		return true;
	}

	public void endVisit(SQLAggregateExpr x) {
	}

	public boolean visit(SQLAggregateExpr x) {
		return true;
	}

	public boolean visit(SQLVariantRefExpr x) {
		return true;
	}

	public void endVisit(SQLVariantRefExpr x) {
	}

	public boolean visit(SQLQueryExpr x) {
		return true;
	}

	public void endVisit(SQLQueryExpr x) {
	}

	public boolean visit(SQLSelect x) {
		return true;
	}

	public void endVisit(SQLSelect select) {
	}

	public boolean visit(SQLSelectQueryBlock x) {
		return true;
	}

	public void endVisit(SQLSelectQueryBlock x) {
	}

	public boolean visit(SQLExprTableSource x) {
		return true;
	}

	public void endVisit(SQLExprTableSource x) {
	}

	public boolean visit(SQLOrderBy x) {
		return true;
	}

	public void endVisit(SQLOrderBy x) {
	}

	public boolean visit(SQLSelectOrderByItem x) {
		return true;
	}

	public void endVisit(SQLSelectOrderByItem x) {
	}

	public boolean visit(SQLDropTableStatement x) {
		return true;
	}

	public void endVisit(SQLDropTableStatement x) {
	}

	public boolean visit(SQLCreateTableStatement x) {
		return true;
	}

	public void endVisit(SQLCreateTableStatement x) {
	}

	public boolean visit(SQLColumnDefinition x) {
		return true;
	}

	public void endVisit(SQLColumnDefinition x) {
	}

	public boolean visit(SQLDataType x) {
		return true;
	}

	public void endVisit(SQLDataType x) {
	}

	public boolean visit(SQLDeleteStatement x) {
		return true;
	}

	public void endVisit(SQLDeleteStatement x) {
	}

	public boolean visit(SQLCurrentOfCursorExpr x) {
		return true;
	}

	public void endVisit(SQLCurrentOfCursorExpr x) {
	}

	public boolean visit(SQLInsertStatement x) {
		return true;
	}

	public void endVisit(SQLInsertStatement x) {
	}

	public boolean visit(SQLUpdateSetItem x) {
		return true;
	}

	public void endVisit(SQLUpdateSetItem x) {
	}

	public boolean visit(SQLUpdateStatement x) {
		return true;
	}

	public void endVisit(SQLUpdateStatement x) {
	}

	public boolean visit(SQLCreateViewStatement x) {
		return true;
	}

	public void endVisit(SQLCreateViewStatement x) {
	}

	public boolean visit(NotNullConstraint x) {
		return true;
	}

	public void endVisit(NotNullConstraint x) {
	}

	@Override
	public void endVisit(SQLMethodInvokeExpr x) {

	}

	@Override
	public boolean visit(SQLMethodInvokeExpr x) {
		return true;
	}

	@Override
	public void endVisit(SQLUnionQuery x) {

	}

	@Override
	public boolean visit(SQLUnionQuery x) {
		return true;
	}

	@Override
	public boolean visit(SQLUnaryExpr x) {
		return true;
	}

	@Override
	public void endVisit(SQLUnaryExpr x) {

	}

	@Override
	public boolean visit(SQLHexExpr x) {
		return false;
	}

	@Override
	public void endVisit(SQLHexExpr x) {

	}

	@Override
	public void endVisit(SQLSetStatement x) {

	}

	@Override
	public boolean visit(SQLSetStatement x) {
		return true;
	}

	@Override
	public void endVisit(SQLAssignItem x) {

	}

	@Override
	public boolean visit(SQLAssignItem x) {
		return true;
	}

	@Override
	public void endVisit(SQLCallStatement x) {

	}

	@Override
	public boolean visit(SQLCallStatement x) {
		return true;
	}

	@Override
	public void endVisit(SQLJoinTableSource x) {

	}

	@Override
	public boolean visit(SQLJoinTableSource x) {
		return true;
	}

	@Override
	public boolean visit(ValuesClause x) {
		return true;
	}

	@Override
	public void endVisit(ValuesClause x) {

	}

	@Override
	public void endVisit(SQLSomeExpr x) {

	}

	@Override
	public boolean visit(SQLSomeExpr x) {
		return true;
	}

	@Override
	public void endVisit(SQLAnyExpr x) {

	}

	@Override
	public boolean visit(SQLAnyExpr x) {
		return true;
	}

	@Override
	public void endVisit(SQLAllExpr x) {

	}

	@Override
	public boolean visit(SQLAllExpr x) {
		return true;
	}

	@Override
	public void endVisit(SQLInSubQueryExpr x) {

	}

	@Override
	public boolean visit(SQLInSubQueryExpr x) {
		return true;
	}

	@Override
	public void endVisit(SQLListExpr x) {

	}

	@Override
	public boolean visit(SQLListExpr x) {
		return true;
	}

	@Override
	public void endVisit(SQLSubqueryTableSource x) {

	}

	@Override
	public boolean visit(SQLSubqueryTableSource x) {
		return true;
	}

	@Override
	public void endVisit(SQLTruncateStatement x) {

	}

	@Override
	public boolean visit(SQLTruncateStatement x) {
		return true;
	}

	@Override
	public void endVisit(SQLDefaultExpr x) {

	}

	@Override
	public boolean visit(SQLDefaultExpr x) {
		return true;
	}

	@Override
	public void endVisit(SQLCommentStatement x) {

	}

	@Override
	public boolean visit(SQLCommentStatement x) {
		return true;
	}

	@Override
	public void endVisit(SQLUseStatement x) {

	}

	@Override
	public boolean visit(SQLUseStatement x) {
		return true;
	}

	@Override
	public boolean visit(SQLAlterTableAddColumn x) {
		return true;
	}

	@Override
	public void endVisit(SQLAlterTableAddColumn x) {

	}

	@Override
	public boolean visit(SQLAlterTableDropColumnItem x) {
		return true;
	}

	@Override
	public void endVisit(SQLAlterTableDropColumnItem x) {

	}

	@Override
	public boolean visit(SQLDropIndexStatement x) {
		return true;
	}

	@Override
	public void endVisit(SQLDropIndexStatement x) {

	}

	@Override
	public boolean visit(SQLDropViewStatement x) {
		return true;
	}

	@Override
	public void endVisit(SQLDropViewStatement x) {

	}

	@Override
	public boolean visit(SQLSavePointStatement x) {
		return true;
	}

	@Override
	public void endVisit(SQLSavePointStatement x) {

	}

	@Override
	public boolean visit(SQLRollbackStatement x) {
		return true;
	}

	@Override
	public void endVisit(SQLRollbackStatement x) {

	}

	@Override
	public boolean visit(SQLReleaseSavePointStatement x) {
		return true;
	}

	@Override
	public void endVisit(SQLReleaseSavePointStatement x) {
	}

	@Override
	public boolean visit(SQLCommentHint x) {
		return true;
	}

	@Override
	public void endVisit(SQLCommentHint x) {

	}

	@Override
	public void endVisit(SQLCreateDatabaseStatement x) {

	}

	@Override
	public boolean visit(SQLCreateDatabaseStatement x) {
		return true;
	}

	@Override
	public boolean visit(SQLAlterTableDropIndex x) {
		return true;
	}

	@Override
	public void endVisit(SQLAlterTableDropIndex x) {

	}

	@Override
	public boolean visit(SQLAlterTableAddPrimaryKey x) {
		return true;
	}

	@Override
	public void endVisit(SQLAlterTableAddPrimaryKey x) {

	}

	@Override
	public void endVisit(SQLOver x) {
	}

	@Override
	public boolean visit(SQLOver x) {
		return true;
	}

	@Override
	public void endVisit(SQLColumnPrimaryKey x) {

	}

	@Override
	public boolean visit(SQLColumnPrimaryKey x) {
		return true;
	}

	@Override
	public void endVisit(SQLWithSubqueryClause x) {
	}

	@Override
	public boolean visit(SQLWithSubqueryClause x) {
		return true;
	}

	@Override
	public void endVisit(SQLWithSubqueryClause.Entry x) {
	}

	@Override
	public boolean visit(SQLWithSubqueryClause.Entry x) {
		return true;
	}

	@Override
	public boolean visit(SQLCharactorDataType x) {
		return true;
	}

	@Override
	public void endVisit(SQLCharactorDataType x) {

	}

	@Override
	public void endVisit(SQLAlterTableAlterColumn x) {

	}

	@Override
	public boolean visit(SQLAlterTableAlterColumn x) {
		return true;
	}

	@Override
	public boolean visit(SQLCheck x) {
		return true;
	}

	@Override
	public void endVisit(SQLCheck x) {

	}

	@Override
	public boolean visit(SQLAlterTableDropForeinKey x) {
		return true;
	}

	@Override
	public void endVisit(SQLAlterTableDropForeinKey x) {

	}

	@Override
	public boolean visit(SQLAlterTableDropPrimaryKey x) {
		return true;
	}

	@Override
	public void endVisit(SQLAlterTableDropPrimaryKey x) {

	}

	@Override
	public boolean visit(SQLAlterTableDisableKeys x) {
		return true;
	}

	@Override
	public void endVisit(SQLAlterTableDisableKeys x) {

	}

	@Override
	public boolean visit(SQLAlterTableEnableKeys x) {
		return true;
	}

	@Override
	public void endVisit(SQLAlterTableEnableKeys x) {

	}

	@Override
	public boolean visit(SQLAlterTableStatement x) {
		return true;
	}

	@Override
	public void endVisit(SQLAlterTableStatement x) {

	}

	@Override
	public boolean visit(SQLAlterTableAddForeignKey x) {
		return true;
	}

	@Override
	public void endVisit(SQLAlterTableAddForeignKey x) {

	}

	@Override
	public boolean visit(SQLAlterTableDisableConstraint x) {
		return true;
	}

	@Override
	public void endVisit(SQLAlterTableDisableConstraint x) {

	}

	@Override
	public boolean visit(SQLAlterTableEnableConstraint x) {
		return true;
	}

	@Override
	public void endVisit(SQLAlterTableEnableConstraint x) {

	}

	@Override
	public boolean visit(SQLColumnCheck x) {
		return true;
	}

	@Override
	public void endVisit(SQLColumnCheck x) {

	}

	@Override
	public boolean visit(SQLExprHint x) {
		return true;
	}

	@Override
	public void endVisit(SQLExprHint x) {

	}

	@Override
	public boolean visit(SQLAlterTableDropConstraint x) {
		return true;
	}

	@Override
	public void endVisit(SQLAlterTableDropConstraint x) {

	}

	@Override
	public boolean visit(SQLUnique x) {
		return true;
	}

	@Override
	public void endVisit(SQLUnique x) {

	}
}

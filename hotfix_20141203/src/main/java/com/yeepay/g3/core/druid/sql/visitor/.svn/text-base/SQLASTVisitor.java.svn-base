package com.yeepay.g3.core.druid.sql.visitor;

import com.yeepay.g3.core.druid.sql.ast.*;
import com.yeepay.g3.core.druid.sql.ast.expr.*;
import com.yeepay.g3.core.druid.sql.ast.statement.*;

public interface SQLASTVisitor {

	void endVisit(SQLAllColumnExpr x);

	void endVisit(SQLBetweenExpr x);

	void endVisit(SQLBinaryOpExpr x);

	void endVisit(SQLCaseExpr x);

	void endVisit(SQLCaseExpr.Item x);

	void endVisit(SQLCharExpr x);

	void endVisit(SQLIdentifierExpr x);

	void endVisit(SQLInListExpr x);

	void endVisit(SQLIntegerExpr x);

	void endVisit(SQLExistsExpr x);

	void endVisit(SQLNCharExpr x);

	void endVisit(SQLNotExpr x);

	void endVisit(SQLNullExpr x);

	void endVisit(SQLNumberExpr x);

	void endVisit(SQLPropertyExpr x);

	void endVisit(SQLSelectGroupByClause x);

	void endVisit(SQLSelectItem x);

	void endVisit(SQLSelectStatement selectStatement);

	void postVisit(SQLObject astNode);

	void preVisit(SQLObject astNode);

	boolean visit(SQLAllColumnExpr x);

	boolean visit(SQLBetweenExpr x);

	boolean visit(SQLBinaryOpExpr x);

	boolean visit(SQLCaseExpr x);

	boolean visit(SQLCaseExpr.Item x);

	boolean visit(SQLCastExpr x);

	boolean visit(SQLCharExpr x);

	boolean visit(SQLExistsExpr x);

	boolean visit(SQLIdentifierExpr x);

	boolean visit(SQLInListExpr x);

	boolean visit(SQLIntegerExpr x);

	boolean visit(SQLNCharExpr x);

	boolean visit(SQLNotExpr x);

	boolean visit(SQLNullExpr x);

	boolean visit(SQLNumberExpr x);

	boolean visit(SQLPropertyExpr x);

	boolean visit(SQLSelectGroupByClause x);

	boolean visit(SQLSelectItem x);

	void endVisit(SQLCastExpr x);

	boolean visit(SQLSelectStatement astNode);

	void endVisit(SQLAggregateExpr astNode);

	boolean visit(SQLAggregateExpr astNode);

	boolean visit(SQLVariantRefExpr x);

	void endVisit(SQLVariantRefExpr x);

	boolean visit(SQLQueryExpr x);

	void endVisit(SQLQueryExpr x);

	boolean visit(SQLUnaryExpr x);

	void endVisit(SQLUnaryExpr x);

	boolean visit(SQLHexExpr x);

	void endVisit(SQLHexExpr x);

	boolean visit(SQLSelect x);

	void endVisit(SQLSelect select);

	boolean visit(SQLSelectQueryBlock x);

	void endVisit(SQLSelectQueryBlock x);

	boolean visit(SQLExprTableSource x);

	void endVisit(SQLExprTableSource x);

	boolean visit(SQLOrderBy x);

	void endVisit(SQLOrderBy x);

	boolean visit(SQLSelectOrderByItem x);

	void endVisit(SQLSelectOrderByItem x);

	boolean visit(SQLDropTableStatement x);

	void endVisit(SQLDropTableStatement x);

	boolean visit(SQLCreateTableStatement x);

	void endVisit(SQLCreateTableStatement x);

	boolean visit(SQLColumnDefinition x);

	void endVisit(SQLColumnDefinition x);

	boolean visit(SQLDataType x);

	void endVisit(SQLDataType x);

	boolean visit(SQLCharactorDataType x);

	void endVisit(SQLCharactorDataType x);

	boolean visit(SQLDeleteStatement x);

	void endVisit(SQLDeleteStatement x);

	boolean visit(SQLCurrentOfCursorExpr x);

	void endVisit(SQLCurrentOfCursorExpr x);

	boolean visit(SQLInsertStatement x);

	void endVisit(SQLInsertStatement x);

	boolean visit(SQLInsertStatement.ValuesClause x);

	void endVisit(SQLInsertStatement.ValuesClause x);

	boolean visit(SQLUpdateSetItem x);

	void endVisit(SQLUpdateSetItem x);

	boolean visit(SQLUpdateStatement x);

	void endVisit(SQLUpdateStatement x);

	boolean visit(SQLCreateViewStatement x);

	void endVisit(SQLCreateViewStatement x);

	boolean visit(NotNullConstraint x);

	void endVisit(NotNullConstraint x);

	void endVisit(SQLMethodInvokeExpr x);

	boolean visit(SQLMethodInvokeExpr x);

	void endVisit(SQLUnionQuery x);

	boolean visit(SQLUnionQuery x);

	void endVisit(SQLSetStatement x);

	boolean visit(SQLSetStatement x);

	void endVisit(SQLAssignItem x);

	boolean visit(SQLAssignItem x);

	void endVisit(SQLCallStatement x);

	boolean visit(SQLCallStatement x);

	void endVisit(SQLJoinTableSource x);

	boolean visit(SQLJoinTableSource x);

	void endVisit(SQLSomeExpr x);

	boolean visit(SQLSomeExpr x);

	void endVisit(SQLAnyExpr x);

	boolean visit(SQLAnyExpr x);

	void endVisit(SQLAllExpr x);

	boolean visit(SQLAllExpr x);

	void endVisit(SQLInSubQueryExpr x);

	boolean visit(SQLInSubQueryExpr x);

	void endVisit(SQLListExpr x);

	boolean visit(SQLListExpr x);

	void endVisit(SQLSubqueryTableSource x);

	boolean visit(SQLSubqueryTableSource x);

	void endVisit(SQLTruncateStatement x);

	boolean visit(SQLTruncateStatement x);

	void endVisit(SQLDefaultExpr x);

	boolean visit(SQLDefaultExpr x);

	void endVisit(SQLCommentStatement x);

	boolean visit(SQLCommentStatement x);

	void endVisit(SQLUseStatement x);

	boolean visit(SQLUseStatement x);

	boolean visit(SQLAlterTableAddColumn x);

	void endVisit(SQLAlterTableAddColumn x);

	boolean visit(SQLAlterTableDropColumnItem x);

	void endVisit(SQLAlterTableDropColumnItem x);

	boolean visit(SQLAlterTableDropIndex x);

	void endVisit(SQLAlterTableDropIndex x);

	boolean visit(SQLAlterTableAddPrimaryKey x);

	void endVisit(SQLAlterTableAddPrimaryKey x);

	boolean visit(SQLDropIndexStatement x);

	void endVisit(SQLDropIndexStatement x);

	boolean visit(SQLDropViewStatement x);

	void endVisit(SQLDropViewStatement x);

	boolean visit(SQLSavePointStatement x);

	void endVisit(SQLSavePointStatement x);

	boolean visit(SQLRollbackStatement x);

	void endVisit(SQLRollbackStatement x);

	boolean visit(SQLReleaseSavePointStatement x);

	void endVisit(SQLReleaseSavePointStatement x);

	void endVisit(SQLCommentHint x);

	boolean visit(SQLCommentHint x);

	void endVisit(SQLCreateDatabaseStatement x);

	boolean visit(SQLCreateDatabaseStatement x);

	void endVisit(SQLOver x);

	boolean visit(SQLOver x);

	void endVisit(SQLColumnPrimaryKey x);

	boolean visit(SQLColumnPrimaryKey x);

	void endVisit(SQLWithSubqueryClause x);

	boolean visit(SQLWithSubqueryClause x);

	void endVisit(SQLWithSubqueryClause.Entry x);

	boolean visit(SQLWithSubqueryClause.Entry x);

	void endVisit(SQLAlterTableAlterColumn x);

	boolean visit(SQLAlterTableAlterColumn x);

	boolean visit(SQLCheck x);

	void endVisit(SQLCheck x);

	boolean visit(SQLAlterTableDropForeinKey x);

	void endVisit(SQLAlterTableDropForeinKey x);

	boolean visit(SQLAlterTableDropPrimaryKey x);

	void endVisit(SQLAlterTableDropPrimaryKey x);

	boolean visit(SQLAlterTableDisableKeys x);

	void endVisit(SQLAlterTableDisableKeys x);

	boolean visit(SQLAlterTableEnableKeys x);

	void endVisit(SQLAlterTableEnableKeys x);

	boolean visit(SQLAlterTableStatement x);

	void endVisit(SQLAlterTableStatement x);

	boolean visit(SQLAlterTableAddForeignKey x);

	void endVisit(SQLAlterTableAddForeignKey x);

	boolean visit(SQLAlterTableDisableConstraint x);

	void endVisit(SQLAlterTableDisableConstraint x);

	boolean visit(SQLAlterTableEnableConstraint x);

	void endVisit(SQLAlterTableEnableConstraint x);

	boolean visit(SQLColumnCheck x);

	void endVisit(SQLColumnCheck x);

	boolean visit(SQLExprHint x);

	void endVisit(SQLExprHint x);

	boolean visit(SQLAlterTableDropConstraint x);

	void endVisit(SQLAlterTableDropConstraint x);

	boolean visit(SQLUnique x);

	void endVisit(SQLUnique x);
}

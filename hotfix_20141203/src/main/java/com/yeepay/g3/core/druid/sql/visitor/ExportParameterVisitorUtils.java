package com.yeepay.g3.core.druid.sql.visitor;

import com.yeepay.g3.core.druid.sql.ast.SQLExpr;
import com.yeepay.g3.core.druid.sql.ast.expr.*;

import java.util.List;

public class ExportParameterVisitorUtils {

	public static boolean exportParamterAndAccept(final List<Object> parameters, List<SQLExpr> list) {
		for (int i = 0, size = list.size(); i < size; ++i) {
			SQLExpr param = list.get(i);

			SQLExpr result = exportParameter(parameters, param);
			if (result != param) {
				list.set(i, result);
			}
		}

		return false;
	}

	public static SQLExpr exportParameter(final List<Object> parameters, final SQLExpr param) {
		if (param instanceof SQLCharExpr) {
			Object value = ((SQLCharExpr) param).getText();
			parameters.add(value);
			return new SQLVariantRefExpr("?");
		}

		if (param instanceof SQLNumericLiteralExpr) {
			Object value = ((SQLNumericLiteralExpr) param).getNumber();
			parameters.add(value);
			return new SQLVariantRefExpr("?");
		}

		return param;
	}

	public static void exportParameter(final List<Object> parameters, SQLBinaryOpExpr x) {
		if (x.getLeft() instanceof SQLLiteralExpr && x.getRight() instanceof SQLLiteralExpr && x.getOperator().isRelational()) {
			return;
		}

		{
			SQLExpr leftResult = ExportParameterVisitorUtils.exportParameter(parameters, x.getLeft());
			if (leftResult != x.getLeft()) {
				x.setLeft(leftResult);
			}
		}

		{
			SQLExpr rightResult = exportParameter(parameters, x.getRight());
			if (rightResult != x.getRight()) {
				x.setRight(rightResult);
			}
		}
	}

	public static void exportParameter(final List<Object> parameters, SQLBetweenExpr x) {
		{
			SQLExpr result = exportParameter(parameters, x.getBeginExpr());
			if (result != x.getBeginExpr()) {
				x.setBeginExpr(result);
			}
		}

		{
			SQLExpr result = exportParameter(parameters, x.getEndExpr());
			if (result != x.getBeginExpr()) {
				x.setEndExpr(result);
			}
		}

	}
}

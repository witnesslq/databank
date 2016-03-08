<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>

<%@ taglib prefix="sitemesh-page" uri="http://www.opensymphony.com/sitemesh/page" %>
<!DOCTYPE html>
<html>
<head>
    <title>查询列表</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="span12">
            <table class="table table-hover table-striped table-condensed">
                <tr>
                    <th>#</th>
                    <th>查询名称</th>
                    <th>产品线</th>
                    <th>维护人</th>
                    <th>可用操作</th>
                </tr>
                <tr>
                    <td>1</td>
                    <td>检查未审核纸质签约商户</td>
                    <td>用户中心</td>
                    <td>纪柏涛(baitao.ji)</td>
                    <td>
                        <button class="btn btn-mini btn-success" type="button">执行</button>
                        <button class="btn btn-mini btn-primary" type="button">编辑</button>
                        <button class="btn btn-mini" type="button" onclick="deleteConfirm(1);">删除</button>
                    </td>
                </tr>
            </table>
            <%--<tags:pagination page="${tasks}" paginationSize="10"/>--%>
        </div>
    </div>
</div>
</body>
</html>
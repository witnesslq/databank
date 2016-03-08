﻿
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>

<%@ taglib prefix="e" uri="/emvc-tags" %>
<%@ taglib prefix="q" uri="/queryui-tags" %>
<html>
<head>
    <title>数据源列表</title>
</head>
<body>
<div class="Container">
    <div class="Content fontText">
        <form action="" id="form" method="get">
            <div class="information">
                <div class="search_tit">
                    <h1 class="fw fleft f14">数据源列表</h1>
                    <a href="<%=request.getContextPath()%>/datasource/create.action"
                       style="margin-right:20px;float:right">[新建数据源]</a>
                </div>
                <div class="clearer"></div>
                <div class="search_con">
                    <ul class="fix">
                        <li>
                            <label class="text_tit">数据库类型：</label>
                            <e:select class="select" name="dbType" headerKey="" headerValue="全部" list="@dsTypeList"/>
                        </li>
                        <li>
                            <label class="text_tit">数据源名：</label>
                            <e:textfield name="dsName" class="input_text"/>
                        </li>
                        <li>
                            <label class="text_tit">用户名：</label>
                            <e:textfield name="dsUserName" class="input_text"/>
                        </li>
                    </ul>
                    <div class="clearer"></div>
                    <div class="btn">
                        <input type="submit" class="btn_sure fw" value="查 询"/>
                        <input type="button" class="btn_cancel fw" value="清 空" onclick="clearAll();"/>
                    </div>
                    <div class="clearer"></div>
                </div>
            </div>
        </form>
        <!--search End-->
        <div class="clearer"></div>
        <div class="">
            <q:querytable queryKey="queryDataSource" class="list" formId="form">
                <q:nodata>无数据</q:nodata>
                <%--<q:column title="编号" value="@ds_id" width="60px" id="@ds_id"/>--%>
                <q:column title="数据库类型" value="@db_type" width="60px"/>
                <q:column title="数据源名称" value="@ds_name" width="120px"/>
                <q:column title="连接字符串" value="@ds_conn_str"/>
                <q:column title="用户名" value="@ds_username" width="120px"/>
                <q:column title="可用操作" width="100px">
                    <a href="javascript:void(0)" onclick="update(<e:property value="@ds_id"/>, 'edit');">编辑</a>
                    <a href="javascript:void(0)"
                       onclick="deleteConfirm(<e:property value="@ds_id"/>, '数据源', 'datasource');">删除</a>
                </q:column>
            </q:querytable>
        </div>
    </div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/databank.min.js?v=20140116"></script>
<script>
    function update(id) {
        window.location.href = "<%=request.getContextPath()%>/datasource/update.action?id=" + id;
    }
</script>
</body>
</html>
﻿
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib prefix="e" uri="/emvc-tags" %>
<%@ taglib prefix="q" uri="/queryui-tags" %>
<html>
<head>
    <title>申请数据源列表</title>
</head>
<body>
<div class="Container">
    <div class="Content fontText">
        <form action="" id="form" method="get">
            <div class="information">
                <div class="search_tit">
                    <h1 class="fw fleft f14">申请列表</h1>
                </div>
                <div class="clearer"></div>
                <div class="search_con">
                    <ul class="fix">
                        <input type="hidden" name="userId" id="userId" value="<e:property value="@userId"/>">
                        <li>
                            <label class="text_tit">数据库类型：</label>
                            <e:select class="select" name="dbType" headerKey="" headerValue="全部" list="@dsTypeList"/>
                        </li>
                        <li>
                            <label class="text_tit">数据源名：</label>
                            <e:textfield name="dsName" class="input_text"/>
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
            <q:querytable queryKey="queryUserApply" class="list" formId="form">
                <q:nodata>无数据</q:nodata>
                <q:column title="数据源类型" value="@db_type" width="100px"/>
                <q:column title="数据源名" value="@ds_name" width="100px"/>
                <q:column title="申请时间" value="@created_datetime" width="230px"/>
                <q:column title="使用时间" value="@allow_use_datetime" width="200px"/>
                <q:column title="审核人" value="@review_name" width="200px"/>
                <q:column title="审核状态" width="200px">
                    <e:if test="@apply_status == 'APPLY_REVIEW'">
                        审核中
                    </e:if>
                    <e:if test="@apply_status == 'APPLY_SUCCESS' ">
                        申请成功
                    </e:if>
                    <e:if test="@apply_status == 'APPLY_FAILURE'">
                        申请失败
                    </e:if>
                </q:column>
                <q:column title="可用操作" width="100px">
                    <a href="javascript:void(0)" onclick="check(<e:property value="@id"/>);">查看</a>
                </q:column>
            </q:querytable>
        </div>
    </div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/databank.min.js?v=20140116"></script>
<script>
    $(function () {
        $("#selectAll").click(function () {
            $('input[type="checkbox"]').attr("checked", this.checked);
        });
    });
    function check(id) {
        window.location.href = "<%=request.getContextPath()%>/application/viewDetail.action?id=" + id;
    }
</script>
</body>
</html>
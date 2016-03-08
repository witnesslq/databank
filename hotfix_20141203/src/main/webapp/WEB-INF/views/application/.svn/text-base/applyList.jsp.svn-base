﻿
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>

<%@ taglib prefix="e" uri="/emvc-tags" %>
<%@ taglib prefix="q" uri="/queryui-tags" %>
<html>
<head>
    <title>审核数据源</title>
</head>
<body>
<div class="Container">
    <div class="Content fontText">
        <form action="" id="form" method="get">
            <div class="information">
                <div class="search_tit">
                    <h1 class="fw fleft f14">申请列表</h1>
                    <a href="<%=request.getContextPath()%>/application/checkReview.action?reviewName=<e:property value='@reviewName'/>"
                       style="margin-right:20px;float:right">[查看审核记录]</a>
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
                            <label class="text_tit">申请人：</label>
                            <e:textfield name="applyName" class="input_text"/>
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
            <q:querytable queryKey="queryApply" class="list" formId="form">
                <q:nodata>无数据</q:nodata>
                <q:column title="数据源类型" value="@db_type" width="120px"/>
                <q:column title="数据源名" value="@ds_name" width="100px"/>
                <q:column title="申请人姓名" value="@login_name" width="100px"/>
                <q:column title="申请理由" value="@apply_reason" width="800px"/>
                <q:column title="可用操作" width="100px">
                    <a href="javascript:void(0)" onclick="review(<e:property value="@id"/>);">审核</a>
                </q:column>
            </q:querytable>
        </div>
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
    function review(id) {
        window.location.href = "<%=request.getContextPath()%>/application/updateApply.action?id=" + id;
    }
</script>
</body>
</html>
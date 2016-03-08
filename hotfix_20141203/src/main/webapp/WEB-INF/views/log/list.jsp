<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>

<%@ taglib prefix="e" uri="/emvc-tags" %>
<%@ taglib prefix="q" uri="/queryui-tags" %>
<html>
<head>
    <title>日志列表</title>
</head>
<body>
<div class="Container">
    <div class="Content fontText">
        <form action="" id="form" method="get">
            <div class="information">
                <div class="search_tit">
                    <h1 class="fw fleft f14">查询日志</h1>
                </div>
                <div class="clearer"></div>
                <div class="search_con">
                    <ul class="fix">
                        <li>
                            <label class="text_tit">日志级别：</label>
                            <e:select class="select" name="logLevel" headerKey="" headerValue="全部" list="@logLevels"/>
                        </li>
                        <li>
                            <label class="text_tit">数据源：</label>
                            <e:select class="select" name="logDsId" headerKey="" headerValue="全部" listKey="id"
                                      listValue="name"
                                      list="@allDataSource"/>
                        </li>
                        <li>
                            <label class="text_tit">Schema：</label>
                            <e:textfield name="schema" class="input_text"/>
                        </li>
                        <li>
                            <label class="text_tit">执行人：</label>
                            <e:textfield name="logExecutor" class="input_text"/>
                        </li>
                        <li>
                            <label class="text_tit">出错码：</label>
                            <e:textfield name="sqlStatus" class="input_text"/>
                        </li>
                        <li class="between">
                            <label class="text_tit">分数范围：</label>
                            <e:textfield name="startScore" class="input_text"/> <span>至</span> <e:textfield
                                name="endScore"
                                class="input_text"/>
                        </li>
                        <li class="between">
                            <label class="text_tit">时间范围：</label>
                            <e:textfield name="startTime" id="startTime" class="input_text"/> <span>至</span>
                            <e:textfield
                                    name="endTime" id="endTime" class="input_text"/>
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
            <q:querytable queryKey="queryOperationLog" class="list" formId="form" pageSize="50">
                <q:nodata>无数据</q:nodata>
                <%--<q:column title="编号" value="@log_id" width="60px"/>--%>
                <q:column title="日志级别" value="@log_level" width="80px"/>
                <q:column title="数据源/Schema" width="120px">
                    <e:property value="@ds_name"/>/<e:property value="@log_schema"/>
                </q:column>
                <q:column title="查询语句" value="@log_query"/>
                <q:column title="执行人" width="80px">
                    <a href="<%=request.getContextPath()%>/stat/list.action?dataType=user&loginName=<e:property value="@log_executor"/>"><e:property
                            value="@log_executor"/></a>
                </q:column>
                <q:column title="状态码" value="@log_sql_status" width="50px"/>
                <q:column title="行数" value="@log_num" width="60px"/>
                <q:column title="用时(ms)" value="@log_used_millisecond" width="65px"/>
                <q:column title="评分" value="@log_score" width="65px"/>
                <q:column title="执行时间" value="@_formater.formatDate(log_start_datetime)" width="150px"/>
            </q:querytable>
        </div>
    </div>
</div>
<script type="text/javascript" src="/databank-boss/static/js/databank.min.js?v=20141106"></script>
<script>
    $(function () {
        DatePickerExt.between("startTime", "endTime", {maxDate: 0, showButtonPanel: true});
        if ($("#startTime").val() == "") {
            $("#startTime").datepicker("setDate", "-7");
            $("#endTime").datepicker("setDate", "0");
        }
    });
</script>
</body>
</html>
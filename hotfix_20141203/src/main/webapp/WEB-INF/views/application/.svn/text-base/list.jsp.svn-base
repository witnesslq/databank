﻿
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>

<%@ taglib prefix="e" uri="/emvc-tags" %>
<%@ taglib prefix="q" uri="/queryui-tags" %>
<html>
<head>
    <title>申请数据源</title>
    <link href="<%=request.getContextPath()%>/static/js/chosen.css?v=20141106" rel="stylesheet"
          type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/common.js?v=20141106"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/static/js/jquery-2.0.3.min.js?v=20141106"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/static/js/jquery-migrate-1.2.1.min.js?v=20141106"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/static/js/chosen.jquery.min.js?v=20141106"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-ui.js?v=20141106"></script>
    <style type="text/css">
        .ui-dialog .ui-dialog-titlebar-close span {
            display: block;
            margin: -8px 0 0 -8px !important;
        }
    </style>
</head>
<body>
<div class="Container">
    <div class="Content fontText">
        <form action="" id="form" method="get">
            <div class="information">
                <div class="search_tit">
                    <h1 class="fw fleft f14">数据源列表</h1>
                    <a href="<%=request.getContextPath()%>/application/check.action?userId=<e:property value='@userId'/>"
                       style="margin-right:20px;float:right">[查看申请进度]</a>
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

        <div class="ui-widget-overlay ui-front" id="fullDiv" style="z-index:300;visibility: hidden"></div>
        <form id="applyForm" method="POST">


            <div id="addDataSourceDiv"
                 class="ui-dialog ui-widget ui-widget-content ui-corner-all "
                 style="display: block; z-index: 1002; outline: 0px none; height:auto; width: 650px; top: 120px; left: 524px;z-index:321;visibility: hidden;"
                 tabindex="-1" role="dialog" aria-labelledby="ui-dialog-title-1">
                <div class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix">
                    <span id="ui-dialog-title-1" class="ui-dialog-title">提示: 请填写申请信息</span>
                    <a class="ui-dialog-titlebar-close ui-corner-all" href="#" id="close" role="button"
                       onclick="closes(this.name)">
                        <span class="ui-icon ui-icon-closethick">close</span>
                    </a>
                </div>
                <div style="margin-top: 5px">
                    <table style="width: 600px;height: auto; margin-left: 15px;" cellspacing="7" cellpadding="7">
                        <input type="hidden" name="apply.dsId" id="dsId"/>
                        <tr style="margin-top: 10px">
                            <td style="text-align: right;font-size: medium">数据源名称：</td>
                            <td style="text-align: left" id="dataName"></td>
                        </tr>
                        <tr>
                            <td style="text-align: right;font-size: medium">数据源类型：</td>
                            <td style="text-align: left" id="dataType"></td>
                        </tr>
                        <tr>
                            <td style="text-align: right;font-size: medium">使用时间：</td>
                            <td><input type="text" name="apply.applyUseDateTime" id="applyUseDateTime"
                                       class="input_text "
                                       readonly="readonly">
                                <script>
                                    $(function () {
                                        $("#applyUseDateTime").datepicker({dateFormat: 'yy-mm-dd', minDate: 1})
                                    });
                                </script>

                                <span style="color: red;visibility: hidden" id="checkTime"> 是必填项</span>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right;font-size: medium">
                                允许表:
                            </td>
                            <td style="direction: ltr;text-align: left">
                                <select id="allow" name="allow"
                                        class="chosen"
                                        data-placeholder="只允许查询的表权限" style="width:100%;"
                                        multiple
                                        onchange="onSelectedLoad(this.id)"
                                        ></select>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right;font-size: medium">
                                允许列:
                            </td>
                            <td style="direction: ltr;text-align: left">
                                <select id="allowColumn"
                                        name="allowColumn"
                                        class="chosen"
                                        data-placeholder="只允许查询的列权限" style="width:100%;"
                                        multiple>

                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right;font-size: medium">
                                禁止表:
                            </td>
                            <td style="direction: ltr;text-align: left">
                                <select id="notAllow"
                                        name="notAllow"
                                        class="chosen"
                                        data-placeholder="禁止查询的表权限" style="width:100%;"
                                        onchange="onSelectedNotAllowTableLoad(this.id)"
                                        multiple></select>
                            </td>
                        <tr>
                        <tr>
                            <td style="text-align: right;font-size: medium">
                                禁止列:
                            </td>
                            <td style="direction: ltr;text-align: left">
                                <select id="notAllowColumn"
                                        name="notAllowColumn"
                                        class="chosen"
                                        data-placeholder="禁止查询的列权限" style="width:100%;"
                                        multiple></select>
                            </td>
                        <tr>
                        <tr style="margin-top: 0px">
                            <td style="text-align: right;font-size: medium">申请理由：</td>
                            <td> <textarea id="applyReason" name="apply.applyReason" class="textfield"
                                           maxlength="500" onchange="this.value=this.value.substring(0, 500)"
                                           onkeydown="this.value=this.value.substring(0, 500)"
                                           onkeyup="this.value=this.value.substring(0, 500)"
                                           style="width: 400px;height: 90px" rows="12"></textarea></td>
                            <td><span style="color: red;visibility: hidden;" id="checkReason"> 是必填项</span></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <button type="button" class="btn_sure fw" onclick="submitApply()">申请</button>
                                <button type="button" class="btn_cancel fw" onclick="resets()">重 置</button>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </form>
        <!--search End-->
        <div class="clearer"></div>
        <div class="">
            <q:querytable queryKey="queryDataSource" class="list" formId="form">
                <q:nodata>无数据</q:nodata>
                <q:column title="数据源类型" value="@db_type" width="120px"/>
                <q:column title="数据源名" value="@ds_name" width="100px"/>
                <q:column title="连接字符串" value="@ds_conn_str" width="300px"/>
                <q:column title="用户名" value="@ds_username" width="400px"/>
                <q:column title="可用操作" width="100px">
                    <a href="javascript:void(0)" onclick="application('<e:property value="@ds_id"/>','<e:property
                            value='@ds_name'/>','<e:property
                            value='@db_type'/>')" )>申请</a>
                </q:column>
            </q:querytable>
        </div>
    </div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/databank.min.js?v=20140116"></script>
<script>

    function loadTable(dsId) {
        $("#allow_" + dsId).find("option").remove();
        $("#notAllow_" + dsId).find("option").remove();
        $("#notAllowColumn_" + dsId).find("option").remove();
        $("#allowColumn_" + dsId).find("option").remove();
        $(".chosen").trigger("chosen:updated");
        MessageBoxExt.ajax({
            type: "post",
            url: GV.ctxPath + "/datasource/loadTable.action",
            data: {"id": dsId},
            style: "NONE",
            checkSuccess: function (data) {
                if (data.status != "error") {
                    $(data).each(function (index, element) {
                        var item = "<option value='" + element + "'";
                        item += ">" + element + "</option>";
                        $("#allow_" + dsId).append(item);
                        var item2 = "<option value='" + element + "'";
                        item2 += ">" + element + "</option>";
                        $("#notAllow_" + dsId).append(item2);
                    });
                    $(".chosen").trigger("chosen:updated");
                }
                return true;
            }
        });
    }


    //选择允许的表，加载表中的所有列
    function onSelectedLoad(name) {
        var temp = name;
        var id = null;
        if (null != temp) {
            id = temp.substring(temp.indexOf("_") + 1, temp.length);
        }
        //获取选中的列
        var columns = $("#allowColumn_" + id).val();
        var schemaAndTable = $("#allow_" + id).val();
        var dsId = id;
        //清除所有的option选项
        $("#allowColumn_" + id).find("option").remove();
        $(".chosen").trigger("chosen:updated");
        if (!(null == schemaAndTable) && schemaAndTable.length != 0) {
            MessageBoxExt.ajax({
                type: "post",
                url: GV.ctxPath + "/datasource/loadTableColumn.action",
                data: {"id": dsId, "schemaAndTable[]": schemaAndTable},
                style: "NONE",
                checkSuccess: function (data) {
                    if (data.status != "error") {
                        var allowColumnStr = $("#allowColumn_" + dsId).attr("data-rel");
                        $(data).each(function (index, element) {
                            var item = "<option value='" + element + "'";
                            if (!(null == columns) && columns.indexOf(element) >= 0) {
                                item += " selected";
                            }
                            if (!(null == allowColumnStr) && allowColumnStr.indexOf(element) >= 0) {
                                item += " selected";
                            }
                            item += ">" + element + "</option>";
                            $("#allowColumn_" + dsId).append(item);
                        });
                        $(".chosen").trigger("chosen:updated");
                    }
                    return true;
                }
            });
        }
    }

    //加载禁止 表的列
    function onSelectedNotAllowTableLoad(name) {
        var temp = name;
        var id = null;
        if (null != temp) {
            id = temp.substring(temp.indexOf("_") + 1, temp.length);
        }
        var dsId = id;
        //获取所有不允许的列
        var columns = $("#notAllowColumn_" + id).val();
        //清楚所有的option
        $("#notAllowColumn_" + id).find("option").remove();
        $(".chosen").trigger("chosen:updated");
        var schemaAndTable = $("#notAllow_" + id).val();
        //先判断是否已经选中允许的表
        if (schemaAndTable != null && schemaAndTable.length != 0) {
            MessageBoxExt.ajax({
                type: "post",
                url: GV.ctxPath + "/datasource/loadTableColumn.action",
                data: {"id": dsId, "schemaAndTable[]": schemaAndTable},
                style: "NONE",
                async: false,
                checkSuccess: function (data) {
                    if (data.status != "error") {
                        var notAllowStr = $("#notAllowColumn_" + dsId).attr("data-rel");
                        $(data).each(function (index, element) {
                            var item = "<option value='" + element + "'";
                            if (!(null == columns) && columns.indexOf(element) >= 0) {
                                item += " selected";
                            }
                            if (!(null == notAllowStr) && notAllowStr.indexOf(element) >= 0) {
                                item += " selected";
                            }
                            item += ">" + element + "</option>";
                            $("#notAllowColumn_" + dsId).append(item);
                        });
                        $(".chosen").trigger("chosen:updated");
                    }
                    return true;
                }
            });
        }
    }

    //申请数据源
    function application(id, name, type) {
        $("#addDataSourceDiv").css('visibility', 'visible');
        $("#fullDiv").css('visibility', 'visible');
        $("#dataName").text(name);
        $("#dataType").text(type);
        $("#dsId").val(id);
        $(".chosen").chosen();

        $("#close").attr("name", id);
        $("#allow").attr("id", "allow_" + id);
        $("#allowColumn").attr("id", "allowColumn_" + id);
        $("#notAllow").attr("id", "notAllow_" + id);
        $("#notAllowColumn").attr("id", "notAllowColumn_" + id);

        loadTable(id);
    }

    function closes(dsId) {
        $("#addDataSourceDiv").css('visibility', 'hidden');
        $("#fullDiv").css('visibility', 'hidden');
        $("#checkTime").css("visibility", "hidden");
        $("#checkReason").css("visibility", "hidden");

        $("#allow_" + dsId).attr("id", "allow");
        $("#allowColumn_" + dsId).attr("id", "allowColumn");
        $("#notAllow_" + dsId).attr("id", "notAllow");
        $("#notAllowColumn_" + dsId).attr("id", "notAllowColumn");
    }

    function resets() {
        $("#applyReason").attr("value", "");
        $("#applyUseDateTime").attr("value", "");
        $("select").val("");
        $("select").trigger("chosen:updated");
    }


    function submitApply() {
        //数据校验
        var userTime = $("#applyUseDateTime").val();
        var applyReason = $("#applyReason").val();
        if (null == userTime || "" == userTime) {
            $("#checkTime").css("visibility", "visible");
            return;
        }
        $("#checkTime").css("visibility", "hidden");
        if (null == applyReason || "" == applyReason) {
            $("#checkReason").css("visibility", "visible");
            return;
        }
        $("#checkReason").css("visibility", "hidden");
        var valList = {
            "applyUseDateTime": {
                req: true,
                label: "使用时间"
            },
            "applyReason": {
                req: true,
                label: "申请理由"
            }
        };
        ValidateExt.listen("userForm", valList);
        if (ValidateExt.val("applyForm")) {
            MessageBoxExt.ajax({
                type: "post",
                url: GV.ctxPath + "/application/apply.action",
                data: $("#applyForm").serialize(),
                style: "NONE",
                checkSuccess: function (data) {
                    if (data.status == "error") {
                        MessageBoxExt.alert(data.message);
                    }
                    else {
                        MessageBoxExt._styleRedirect(data.message,
                                GV.ctxPath + "/application/list.action");
                    }
                    return true;
                }
            });
            $("#addDataSourceDiv").css('visibility', 'hidden');
            $("#fullDiv").css('visibility', 'hidden');
        }
    }
</script>
</body>
</html>
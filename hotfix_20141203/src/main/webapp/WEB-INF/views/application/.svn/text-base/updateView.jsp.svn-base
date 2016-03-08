﻿
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>

<%@ taglib prefix="e" uri="/emvc-tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>编辑审核信息</title>
    <link href="<%=request.getContextPath()%>/static/js/chosen.css?v=20141106" rel="stylesheet"
          type="text/css"/>
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
        <div class="information">
            <h1 class="fw">编辑审核信息</h1>

            <div class="input_cont">
                <form id="myForm" method="POST">
                    <ul>
                        <li>
                            <label class="text_tit">数据库类型：</label>

                            <p>
                                <e:property value='@ds.dbType'/>
                            </p>
                        </li>
                        <li>
                            <label class="text_tit">数据源名称：</label>
                            <e:if test="@applyEntity.dsId != null">
                                <input type="hidden" name="apply.dsId" id="dsId"
                                       value="<e:property value='@applyEntity.dsId'/>">
                            </e:if>
                            <p>
                                <e:property value='@ds.name'/>
                            </p>
                        </li>
                        <li>
                            <label class="text_tit">申请人：</label>
                            <e:if test="@applyEntity.userId != null">
                                <input type="hidden" name="apply.userId" id="userId"
                                       value="<e:property value='@applyEntity.userId'/>">
                            </e:if>
                            <p>
                                <e:property value='@user.loginName'/>
                            </p>
                        </li>
                        <li>
                            <label class="text_tit">申请理由：</label>

                            <p><e:property
                                    value='@applyEntity.applyReason'/>
                            </p>
                        </li>
                        <li>
                            <label class="text_tit">允许使用时间：</label>

                            <p><input type="text" id="allowUseDateTime" name="apply.allowUseDateTime"
                                      class="input_text"
                                      rows="6" value="<e:property value='@applyTime'/>">
                                <script>
                                    $("#allowUseDateTime").datepicker({dateFormat: 'yy-mm-dd', minDate: 0});
                                </script>
                            </p>
                        </li>
                        <e:assign name="dsId" value="@ds.id"/>

                        <p style="height: 10px;"></p>
                        <label style="width:auto;min-width:0px ;margin-left: 10%;font-size: 14px">允许表:</label>
                        &nbsp;
                        <select id="allow-<e:property value='@applyEntity.dsId'/>"
                                name="allow-<e:property value='@applyEntity.dsId'/>"
                                class="chosen"
                                data-placeholder="只允许查询的表权限" style="width:49%;"
                                data-rel="<e:property value="@tablePermission.get('allow')"/>"
                                multiple onchange="onSelectedLoad(<e:property value='@applyEntity.dsId'/>)"
                                >
                            ></select>

                        <p style="height: 10px;"></p>

                        <label style="width:auto;min-width:0px;margin-left: 10%;font-size: 14px">允许列:</label>&nbsp;&nbsp;
                        <select id="allowColumn-<e:property value='@applyEntity.dsId'/>"
                                name="allowColumn-<e:property value='@applyEntity.dsId'/>"
                                class="chosen"
                                data-rel="<e:property value="@columnPermission.get('allowColumn')"/>"
                                data-placeholder="只允许查询的列权限" style="width:49%;"
                                multiple></select>

                        <p style="height: 10px;"></p>

                        <label style="width:auto;min-width:0px;margin-left: 10%;font-size: 14px">禁止表:</label>&nbsp;&nbsp;
                        <select id="notAllow-<e:property value='@applyEntity.dsId'/>"
                                name="notAllow-<e:property value='@applyEntity.dsId'/>"
                                class="chosen"
                                data-placeholder="禁止查询的表权限" style="width:49%;"
                                multiple
                                data-rel="<e:property value="@tablePermission.get('notAllow')"/>"
                                onchange="onSelectedNotAllowTableLoad(<e:property
                                        value='@applyEntity.dsId'/>)"></select>

                        <p style="height: 10px;"></p>
                        <label style="width:auto;min-width:0px;margin-left: 10%;font-size: 14px">禁止列:</label>&nbsp;&nbsp;
                        <select id="notAllowColumn-<e:property value='@applyEntity.dsId'/>"
                                name="notAllowColumn-<e:property value='@applyEntity.dsId'/>"
                                class="chosen"
                                data-rel="<e:property value="@columnPermission.get('notAllowColumn')"/>"
                                data-placeholder="禁止查询的列权限" style="width:49%;"
                                multiple
                                ></select>

                        <p style="height: 5px;"></p>
                        <li>
                            <label class="text_tit">审核理由：</label>
                            <textarea id="reviewMessage" name="apply.reviewMessage" class="textfield"
                                      maxlength="500" onchange="this.value=this.value.substring(0, 500)"
                                      onkeydown="this.value=this.value.substring(0, 500)"
                                      onkeyup="this.value=this.value.substring(0, 500)"
                                      rows="6" style="width: 41%"><e:property
                                    value='@applyEntity.reviewMessage'/></textarea>
                        </li>
                        <e:if test="@applyEntity.id != null">
                            <input type="hidden" name="apply.id" id="id"
                                   value="<e:property value='@applyEntity.id'/>">
                        </e:if>
                        <li>
                            <label class="text_tit">&nbsp;</label>
                            <button type="button" class="btn_sure fw" onclick="agreeApply('1')">通过
                            </button>
                            <button type="button" class="btn_sure fw" onclick="agreeApply('2')">不通过
                            </button>
                            <button type="button" class="btn_cancel fw" onclick="history.go(-1)">返 回</button>
                        </li>
                    </ul>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/common.js?v=20141106"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-2.0.3.min.js?v=20141106"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/static/js/jquery-migrate-1.2.1.min.js?v=20141106"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/chosen.jquery.min.js?v=20141106"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-ui.js?v=20141106"></script>
<script>
    var valList = {
        "allowUseDateTime": {
            req: true,
            label: "使用时间"
        },
        "reviewMessage": {
            req: true,
            label: "审核理由"
        }
    };
    $(function () {
        $(".chosen").chosen();
        $(".chosen-container").css("width", "600px");
        $(".chosen").trigger("chosen:updated");
        var dsId = <e:property value='@applyEntity.dsId'/>;
        //ajax
        var tables = "";
        var notTables = "";
        MessageBoxExt.ajax({
            type: "post",
            url: GV.ctxPath + "/datasource/loadTable.action",
            data: {"id": dsId},
            style: "NONE",
            checkSuccess: function (data) {
                if (data.status != "error") {
                    var allowStr = $("#allow-" + dsId).attr("data-rel");
                    var notAllowStr = $("#notAllow-" + dsId).attr("data-rel");
                    $(data).each(function (index, element) {
                        var item = "<option value='" + element + "'";
                        if (allowStr.indexOf(element) >= 0) {
                            item += " selected";
                            tables = tables + element + ",";
                        }
                        item += ">" + element + "</option>";
                        $("#allow-" + dsId).append(item);
                        var item2 = "<option value='" + element + "'";
                        if (notAllowStr.indexOf(element) >= 0) {
                            item2 += " selected";
                            notTables = notTables + element + ",";
                        }
                        item2 += ">" + element + "</option>";
                        $("#notAllow-" + dsId).append(item2);
                    });
                    //去除最后一个自符
                    var table = tables.substring(0, tables.length - 1);
                    var notTable = notTables.substring(0, notTables.length - 1);
                    //加载表中允许的列
                    onSelectedLoad(dsId, table);
                    //加载表中禁止的列
                    onSelectedNotAllowTableLoad(dsId, notTable);
                    $(".chosen").trigger("chosen:updated");
                }
                return true;
            }
        });
    });

    function agreeApply(flag) {
        ValidateExt.listen("myForm", valList);
        var dateTime = $("#allowUseDateTime").val();
        var message = $("#reviewMessage").val();
        if (ValidateInfo(dateTime, '1') && ValidateInfo(message)) {
            MessageBoxExt.ajax({
                type: "post",
                url: GV.ctxPath + "/application/updateApply.action?flag=" + flag,
                data: $("#myForm").serialize(),
                style: "NONE",
                checkSuccess: function (data) {
                    if (data.status == "error") {
                        MessageBoxExt.alert(data.message);
                    }
                    else {
                        MessageBoxExt._styleRedirect("操作成功, 点击确定跳转到审核数据源列表",
                                GV.ctxPath + "/application/checkReview.action?reviewName=<e:property value='@applyEntity.reviewName'/>"
                        );
                    }
                    return true;
                }
            });
        }
    }
    function ValidateInfo(info, flag) {
        if (null == info || info.length == 0) {
            if (null != flag) {
                MessageBoxExt.alert("允许使用时间不能为空！");
            }
            else {
                MessageBoxExt.alert("审核理由不能为空！");
            }
            return false;
        }
        return true;
    }
    //选择允许的表，加载表中的所有列
    function onSelectedLoad(id, tables) {
        //获取选中的列
        var columns = $("#allowColumn-" + id).val();
        var schemaAndTable;
        if (null != tables) {
            schemaAndTable = tables.split(",");
        }
        else {
            schemaAndTable = $("#allow-" + id).val();
        }
        var dsId = id;
        //清除所有的option选项
        $("#allowColumn-" + id).find("option").remove();
        $(".chosen").trigger("chosen:updated");
        if (!(null == schemaAndTable) && schemaAndTable.length != 0) {
            MessageBoxExt.ajax({
                type: "post",
                url: GV.ctxPath + "/datasource/loadTableColumn.action",
                data: {"id": dsId, "schemaAndTable[]": schemaAndTable},
                style: "NONE",
                checkSuccess: function (data) {
                    if (data.status != "error") {
                        var allowColumnStr = $("#allowColumn-" + dsId).attr("data-rel");
                        $(data).each(function (index, element) {
                            var item = "<option value='" + element + "'";
                            if (!(null == columns) && columns.indexOf(element) >= 0) {
                                item += " selected";
                            }
                            if (!(null == allowColumnStr) && allowColumnStr.indexOf(element) >= 0) {
                                item += " selected";
                            }
                            item += ">" + element + "</option>";
                            $("#allowColumn-" + dsId).append(item);
                        });
                        $(".chosen").trigger("chosen:updated");
                    }
                    return true;
                }
            });
        }
    }

    //加载禁止 表的列
    //加载禁止 表的列
    function onSelectedNotAllowTableLoad(id, tables) {
        var dsId = id;
        //获取所有不允许的列
        var columns = $("#notAllowColumn-" + id).val();
        //清楚所有的option
        $("#notAllowColumn-" + id).find("option").remove();
        $(".chosen").trigger("chosen:updated");
        var schemaAndTable;
        if (!null == tables) {
            schemaAndTable = tables.split(",");
        }
        else {
            schemaAndTable = $("#notAllow-" + id).val();
        }
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
                        var notAllowStr = $("#notAllowColumn-" + dsId).attr("data-rel");
                        $(data).each(function (index, element) {
                            var item = "<option value='" + element + "'";
                            if (!(null == columns) && columns.indexOf(element) >= 0) {
                                item += " selected";
                            }
                            if (!(null == notAllowStr) && notAllowStr.indexOf(element) >= 0) {
                                item += " selected";
                            }
                            item += ">" + element + "</option>";
                            $("#notAllowColumn-" + dsId).append(item);
                        });
                        $(".chosen").trigger("chosen:updated");
                    }
                    return true;
                }
            });
        }
    }
</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>

<%@ taglib prefix="e" uri="/emvc-tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>角色管理</title>
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
            <h1 class="fw">角色管理</h1>

            <div class="ui-widget-overlay ui-front" id="fullDiv" style="visibility: hidden;z-index:300;"></div>
            <div id="addDataSourceDiv"
                 class="ui-dialog ui-widget ui-widget-content ui-corner-all "
                 style="display: block; z-index: 1002; outline: 0px none; height: auto; width: 392px; top: 275px; left: 524px;z-index:321;visibility: hidden;"
                 tabindex="-1" role="dialog" aria-labelledby="ui-dialog-title-1">
                <div class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix">
                    <span id="ui-dialog-title-1" class="ui-dialog-title">提示: 请先选择数据源</span>
                    <a class="ui-dialog-titlebar-close ui-corner-all" href="#" role="button" onclick="cancle()">
                        <span class="ui-icon ui-icon-closethick">close</span>
                    </a>
                </div>
                <div class="alert ui-dialog-content ui-widget-content"
                     style="width: auto; min-height: 19px; height: auto;">
                    <e:assign name="permissions" value="@role.permissions+','"/>
                    <select style="margin-left: 28px;width: 160px;height: 30px" id="selectDataSource">
                        <option selected="selected">请选择数据源</option>
                        <e:iterator list="@allDataSource">
                            <e:if test="@permissions.contains(_this.id+',')">
                            </e:if>
                            <e:else>
                                <option id="option-<e:property value='@_status.index'/>"
                                        name="permissionList" value="<e:property value='@_this.id'/>"
                                        class="ownerPermissionList">
                                    <e:property value='@_this.name'/>
                                </option>
                            </e:else>
                        </e:iterator>
                    </select>
                </div>
                <div class="ui-dialog-buttonpane ui-widget-content ui-helper-clearfix">
                    <div class="ui-dialog-buttonset">
                        <button class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only"
                                type="button" role="button" aria-disabled="false" onclick="appendToDiv()">
                            <span class="ui-button-text">确定</span>
                        </button>
                    </div>
                </div>
            </div>
            <div class="input_cont">
                <form id="userForm" method="POST">
                    <ul>
                        <li>
                            <label class="text_tit">角色名：</label>

                            <p><e:if test="@role.id != null"><input type="hidden" name="role.id"
                                                                    value="<e:property value="@role.id"/>"
                                                                    class="required"/></e:if>
                                <input type="text" name="role.name" id="inputRoleName" class="input_text"
                                       value="<e:property value='@role.name'/>" placeholder="Role Name"></p
                        </li>
                        <input type="button" value="新增数据源" style="height: 25px;" id="addDataSource"
                               onclick="loadDataSource()">
                        <li style="overflow:visible;">
                            <label class="text_tit">数据源权限：</label>

                            <div style="width:86%;margin-left:192px" id="totalDiv"><span style="color:red;width:100%">1.先选择数据源，再设定表级权限以及列级权限 2.禁止权限优先生效 3.按 Ctrl 或 Command 键可以连续选择 4.仅支持前缀搜索</span>
                                <e:assign name="permissions" value="@role.permissions+','"/>
                                <e:iterator list="@dataSourcePermission">
                                    <div style="margin-bottom:10px">
                                        <label for="permissionList-<e:property value='@_status.index'/>"
                                               class="row_x"
                                               style="text-align:left; width: 15%;margin-bottom:15px">
                                            <input type="checkbox"
                                                   id="permissionList-<e:property value='@_this.id'/>"
                                                   name="permissionList" value="<e:property value='@_this.id'/>"
                                                   class="permissionList"
                                            <e:if test="@permissions.contains(_this.id+',')">
                                                   checked="true"</e:if> ><e:property value='@_this.name'/>
                                        </label>
                                        <table>
                                            <tr>
                                                <td>
                                                    <label style="width:auto;min-width:0px">允许表:</label></td>
                                                <td>
                                                    <select id="allow-<e:property value='@_this.id'/>"
                                                            name="allow-<e:property value='@_this.id'/>"
                                                            class="chosen"
                                                            data-placeholder="只允许查询的表权限" style="width:49%;"
                                                            multiple
                                                            data-rel="<e:property value="@tablePermission.get(_this.id+'').get('allow')"/>"
                                                            onchange="onSelectedLoad(<e:property
                                                                    value='@_this.id'/>)"
                                                            ></select>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label style="width:auto;min-width:0px">允许列:</label></td>
                                                <td>
                                                    <select id="allowColumn-<e:property value='@_this.id'/>"
                                                            name="allowColumn-<e:property value='@_this.id'/>"
                                                            class="chosen"
                                                            data-placeholder="只允许查询的列权限" style="width:49%;"
                                                            multiple
                                                            data-rel="<e:property value="@permissionColumn.get(_this.id+'').get('allowColumn')"/>"></select>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <label style="width:auto;min-width:0px">禁止表:</label></td>
                                                <td>
                                                    <select id="notAllow-<e:property value='@_this.id'/>"
                                                            name="notAllow-<e:property value='@_this.id'/>"
                                                            class="chosen"
                                                            data-placeholder="禁止查询的表权限" style="width:49%;"
                                                            multiple
                                                            data-rel="<e:property value="@tablePermission.get(_this.id+'').get('notAllow')"/>"
                                                            onchange="onSelectedNotAllowTableLoad(<e:property
                                                                    value='@_this.id'/>)"></select>
                                                </td>
                                            <tr>
                                            <tr>
                                                <td>
                                                    <label style="width:auto;min-width:0px">禁止列:</label></td>
                                                <td>
                                                    <select id="notAllowColumn-<e:property value='@_this.id'/>"
                                                            name="notAllowColumn-<e:property value='@_this.id'/>"
                                                            class="chosen"
                                                            data-placeholder="禁止查询的列权限" style="width:49%;"
                                                            multiple
                                                            data-rel="<e:property value="@permissionColumn.get(_this.id+'').get('notAllowColumn')"/>"></select>
                                                </td>
                                            <tr>
                                        </table>
                                    </div>
                                </e:iterator>
                            </div>
                        </li>
                        <div style="margin: auto">
                            <li>
                                <label class="text_tit">&nbsp;</label>
                                <button type="button" class="btn_sure fw" id="submit">保 存</button>
                                <button type="button" class="btn_cancel fw" onclick="resetChosen()">重 置</button>
                                <button type="button" class="btn_cancel fw" onclick="history.go(-1)">返 回</button>
                            </li>
                        </div>
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
<script type="text/javascript">
    $(function () {
        var valList = {
            "role_name": {
                req: true,
                len: {min: 1, max: 160},
                label: "角色名"
            }
        };
        ValidateExt.listen("userForm", valList);
        $("#submit").click(function () {
            if (ValidateExt.val("userForm")) {
                MessageBoxExt.ajax({
                    type: "post",
                    url: GV.ctxPath + "/role/<e:property value="@method"/>.action",
                    data: $("#userForm").serialize(),
                    style: "NONE",
                    checkSuccess: function (data) {
                        if (data.status == "error") {
                            MessageBoxExt.alert(data.message);
                        }
                        else {
                            MessageBoxExt._styleRedirect(data.message,
                                    GV.ctxPath + "/role/list.action"
                            );
                        }
                        return true;
                    }
                });
            }
        });
        $(".chosen").chosen();
        $(".chosen-container").css("width", "600px");
        var loadTable = function () {
            var dsId = $(this).val();
            if (this.checked && $("#allow-" + dsId + " option").length == 0) {
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
            }
        }
        $(".permissionList").click(loadTable);
        $('input[type="checkbox"][name="permissionList"][checked]').each(loadTable);
    });

    //清空下拉框的所有选项
    function resetChosen() {
        $("select").val("");
        $("select").trigger("chosen:updated");
        $("#inputRoleName").attr("value", "");
    }
    //显示选择数据源 的界面
    function loadDataSource() {
        $("#addDataSourceDiv").css('visibility', 'visible');
        $("#fullDiv").css('visibility', 'visible');
    }
    //返回tr对象
    function returnTr(id, name, value, dataplaceholder) {
        var tr = $("<tr></tr>");
        var td = $("<td></td>");
        var lab = $("<lab style='width:auto;min-width:5px;font-size:15px'>" + value + "</lab>");
        td.append(lab);
        var td2 = $("<td></td>");

        var select = $("<select style='width:49%;' multiple onchange=dealChange(this.id); ></select>");
        select.addClass("chosen");
        select.attr("id", id);
        select.attr("name", name);
        select.attr("data-placeholder", dataplaceholder);
        td2.append(select);
        tr.append(td);
        tr.append(td2);
        return tr;
    }

    //获取lab对象
    function returnLab(id, choseName) {
        var lab = $('<label style="text-align:left; width: 15%;margin-bottom:15px"></label>');
        lab.addClass("row_x");
        var input = $('<input type="checkbox" checked="true" class="permissionList">' + choseName + '</input>');
        input.attr("id", "permissionList-" + id);
        input.attr("name", "permissionList");
        input.attr("value", id);
        lab.append(input);
        return lab;
    }

    //追加数据源信息
    function appendToDiv() {
        var id = $("#selectDataSource").val();
        var choseName = $("#selectDataSource").find("option:selected").text();
        //已经选择了的option 移除
        $("#selectDataSource  option[value='" + id + "']").remove();
        $("#addDataSourceDiv").css('visibility', 'hidden');
        //追加div
        var parentdiv = $('<div style="margin-bottom:10px;"></div>');
        var lab = returnLab(id, choseName);
        parentdiv.append(lab);
        var table = $("<table></table>");
        var allow = "allow-" + id;
        var allowColumn = "allowColumn-" + id;
        var notAllow = "notAllow-" + id;
        var notAllowColumn = "notAllowColumn-" + id;
        var tr = returnTr(allow, allow, "允许表: ", "只允许查询的表权限");
        var tr2 = returnTr(allowColumn, allowColumn, "允许列: ", "只允许查询的列权限");
        var tr3 = returnTr(notAllow, notAllow, "禁止表: ", "禁止查询的表权限");
        var tr4 = returnTr(notAllowColumn, notAllowColumn, "禁止列: ", "禁止许查询的列权限");
        table.append(tr);
        table.append(tr2);
        table.append(tr3);
        table.append(tr4);
        parentdiv.append(table);
        $("#totalDiv").append(parentdiv);
        $(".chosen").chosen();
        $(".chosen-container").css("width", "600px");
        $("#fullDiv").css('visibility', 'hidden');
        if (null != id) {
            MessageBoxExt.ajax({
                type: "post",
                url: GV.ctxPath + "/datasource/loadTable.action",
                data: {"id": id},
                style: "NONE",
                checkSuccess: function (data) {
                    if (data.status != "error") {
                        $(data).each(function (index, element) {
                            var item = "<option value='" + element + "'";
                            item += ">" + element + "</option>";
                            $("#allow-" + id).append(item);
                            var item2 = "<option value='" + element + "'";
                            item2 += ">" + element + "</option>";
                            $("#notAllow-" + id).append(item2);
                        });
                        $(".chosen").trigger("chosen:updated");
                    }
                    return true;
                }
            });
        }
    }

    //取消添加数据源
    function cancle() {
        $("#addDataSourceDiv").css('visibility', 'hidden');
        $("#fullDiv").css('visibility', 'hidden');
    }

    //处理追加select中的change事件
    function dealChange(id) {
        if (null != id) {
            if (id.indexOf("allow-") >= 0) {
                var arr = id.split("-");
                var dsId = arr[1];
                onSelectedLoad(dsId);
            }
            else if (id.indexOf("notAllow-") >= 0) {
                var arr = id.split("-");
                var dsId = arr[1];
                onSelectedNotAllowTableLoad(dsId)
            }
        }
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
<div>
</div>
</body>
</html>
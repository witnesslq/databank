<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>

<%@ taglib prefix="e" uri="/emvc-tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>角色管理</title>
    <link href="<%=request.getContextPath()%>/static/js/chosen.css?v=20141106" rel="stylesheet"
          type="text/css"/>
</head>
<body>
<div class="Container">
    <div class="Content fontText">
        <div class="information">
            <h1 class="fw">角色管理</h1>

            <div class="input_cont">
                <form id="userForm" method="POST">
                    <ul>
                        <li>
                            <label class="text_tit">角色名：</label>

                            <p><e:if test="@role.id != null"><input type="hidden" name="role.id"
                                                                    value="<e:property value="@role.id"/>"
                                                                    class="required"/></e:if>
                                <input type="text" name="role.name" id="inputRoleName" class="input_text"
                                       value="<e:property value='@role.name'/>" placeholder="Role Name"></p>
                        </li>
                        <li style="overflow:visible;">
                            <label class="text_tit">数据源权限：</label>

                            <div style="width:86%;margin-left:192px"><span style="color:red;width:100%">1.先勾选数据源，再设定表级权限 2.禁止权限优先生效 3.按 Ctrl 或 Command 键可以连续选择 4.仅支持前缀搜索</span>
                                <e:assign name="permissions" value="@role.permissions+','"/>
                                <e:iterator list="@allDataSource">
                                    <div style="margin-bottom:10px">
                                        <label for="permissionList-<e:property value='@_status.index'/>" class="row_x"
                                               style="text-align:left; width: 15%;margin-bottom:15px">
                                            <input type="checkbox"
                                                   id="permissionList-<e:property value='@_status.index'/>"
                                                   name="permissionList" value="<e:property value='@_this.id'/>"
                                                   class="permissionList"
                                            <e:if test="@permissions.contains(_this.id+',')">
                                                   checked="true"</e:if> ><e:property value='@_this.name'/>
                                        </label>

                                        <div style="margin-left:200px">
                                            <label style="width:auto;min-width:0px">允许:</label>
                                            <select id="allow-<e:property value='@_this.id'/>"
                                                    name="allow-<e:property value='@_this.id'/>" class="chosen"
                                                    data-placeholder="只允许查询的表权限" style="width:49%;" multiple
                                                    data-rel="<e:property value="@permission.get(_this.id+'').get('allow')"/>"></select><br/>
                                            <label style="width:auto;min-width:0px">禁止:</label>
                                            <select id="notAllow-<e:property value='@_this.id'/>"
                                                    name="notAllow-<e:property value='@_this.id'/>" class="chosen"
                                                    data-placeholder="禁止查询的表权限" style="width:49%;" multiple
                                                    data-rel="<e:property value="@permission.get(_this.id+'').get('notAllow')"/>"></select>
                                        </div>
                                    </div>
                                </e:iterator>
                            </div>
                        </li>
                        <li>
                            <label class="text_tit">&nbsp;</label>
                            <button type="button" class="btn_sure fw" id="submit">保 存</button>
                            <button type="reset" class="btn_cancel fw">重 置</button>
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
<script type="text/javascript">
    $(function () {
//        $("label.row_x").css("text-align", "left").css("width", "86%");
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
                            MessageBoxExt._styleRedirect("操作成功, 点击确定跳转到角色列表",
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
                                }
                                item += ">" + element + "</option>";
                                $("#allow-" + dsId).append(item);

                                var item2 = "<option value='" + element + "'";
                                if (notAllowStr.indexOf(element) >= 0) {
                                    item2 += " selected";
                                }
                                item2 += ">" + element + "</option>";
                                $("#notAllow-" + dsId).append(item2);
                            });
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
</script>
<div>

</div>
</body>
</html>
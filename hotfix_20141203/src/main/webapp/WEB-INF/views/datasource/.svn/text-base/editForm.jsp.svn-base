﻿
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>

<%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head>
    <title>数据源管理</title>
</head>
<body>
<div class="Container">
    <div class="Content fontText">
        <div class="information">
            <h1 class="fw">数据源管理</h1>

            <div class="input_cont">
                <form id="myForm" method="POST">
                    <ul>
                        <li>
                            <label class="text_tit">数据源名称：</label>

                            <p><e:if test="@ds.id != null">
                                <input type="hidden" name="ds.id" id="dsId" value="<e:property value='@ds.id'/>">
                            </e:if>
                                <input type="text" name="ds.name" id="inputDSName" class="input_text"
                                       value="<e:property value='@ds.name'/>" placeholder="Name"></p>
                        </li>
                        <li>
                            <label class="text_tit">数据库类型：</label>
                            <select id="dbType" name="ds.dbType" class="select" onchange="onDbTypeChange()">
                                <option value="DB2">DB2</option>
                                <option value="MYSQL">MySQL</option>
                                <option value="H2">H2</option>
                            </select>
                        </li>
                        <li>
                            <label class="text_tit">连接字符串：</label>

                            <p><input type="text" name="ds.connStr" id="inputConnStr" class="input_text"
                                      style="width:500px"
                            <e:if test="@ds != null">
                                      value="<e:property value='@ds.connStr'/>">
                                </e:if>
                                <e:else>
                                    value="jdbc:db2://<host>:<port>/<database_name>">
                                </e:else></p>
                        </li>
                        <li>
                            <label class="text_tit">用户名：</label>

                            <p><input type="text" name="ds.username" id="inputUserName" class="input_text"
                                      value="<e:property value='@ds.username'/>" placeholder="Username"></p>
                        </li>
                        <li>
                            <label class="text_tit">密码：</label>

                            <p><input type="password" name="ds.password" id="inputPassword" class="input_text"
                                      placeholder="不修改密码请留空"></p>
                        </li>
                        <li>
                            <label class="text_tit">描述信息：</label>
                            <textarea id="inputDescription" name="ds.description" class="textfield" rows="8"><e:property
                                    value='@ds.description'/></textarea>
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
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/databank.min.js?v=20140116"></script>
<script>
    $(function () {
        var valList = {
            "ds.name": {
                req: true,
                len: {min: 1, max: 160},
                label: "数据源名称"
            },
            "ds.connStr": {
                req: true,
                len: {min: 1, max: 80},
                label: "链接字符串"
            },
            "ds.username": {
                req: true,
                len: {min: 1, max: 20},
                label: "用户名"
            }
        };
        ValidateExt.listen("myForm", valList);
        $("#submit").click(function () {
            if (ValidateExt.val("myForm")) {
                MessageBoxExt.ajax({
                    type: "post",
                    url: GV.ctxPath + "/datasource/<e:property value="@method"/>.action",
                    data: $("#myForm").serialize(),
                    style: "NONE",
                    checkSuccess: function (data) {
                        if (data.status == "error") {
                            MessageBoxExt.alert(data.message);
                        }
                        else {
                            MessageBoxExt._styleRedirect("操作成功, 点击确定跳转到数据源列表",
                                            GV.ctxPath + "/datasource/list.action"
                            );
                        }
                        return true;
                    }
                });
            }
        });
    });
</script>
</body>
</html>
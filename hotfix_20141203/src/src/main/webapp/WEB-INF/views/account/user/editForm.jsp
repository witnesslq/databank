﻿
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>

<%@ taglib prefix="e" uri="/emvc-tags" %>
<html>
<head>
  <title>创建/修改用户</title>
</head>
<body>
<div class="Container">
  <div class="Content fontText">
    <div class="information">
      <h1 class="fw">用户管理</h1>

      <div class="input_cont">
        <form id="roleForm" method="POST">
          <ul>
            <li>
              <label class="text_tit">登录帐户：</label>
              <e:if test="@method == 'update'">
                <p><input type="hidden" id="userId" name="user.id" value="<e:property value="@user.id"/>"/>
                  <input type="text" name="user.loginName" id="inputLoginName" class="input_text"
                         value="<e:property value='@user.loginName'/>" disabled="disabled"></p>
              </e:if>
              <e:else>
                <p><input type="text" name="user.loginName" id="inputLoginName" class="input_text"
                          placeholder="LoginName"/></p>
                <%--<e:select name="departmentId" list="@departmentList" listKey="departmentId"--%>
                <%--listValue="departmentName" id="departmentId" onchange="loadUserList();"/>--%>
                <%--<select id="loginName" name="user.id" class="span2"></select>--%>
              </e:else>
            </li>
            <li>
              <label class="text_tit">角色组：</label>

              <p><e:checkboxlabellist name="roleList" value="@user.roleList" list="@allRoles" listKey="id"
                                      listValue="name" labelClass="row_x"/></p>
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
<script type="text/javascript">
  $(function () {
    $("label.row_x").css("text-align", "left").css("width", "20%");
    var valList = {
      "user.loginName": {
        req: true,
        datatype: "^\\w+([-+.]\\w+)*$",
        len: {min: 1, max: 40},
        label: "登录名"
      }
    };
    ValidateExt.listen("roleForm", valList);
    $("#submit").click(function () {
      if (ValidateExt.val("roleForm")) {
        <e:if test="@method == 'create'">
        MessageBoxExt.ajax({
          url: GV.ctxPath + "/user/userInfo.action",
          type: 'POST',
          data: {
            "loginName": function () {
              return $("#inputLoginName").val()
            }
          },
          style: "NONE",
          checkSuccess: function (data) {
            if (data.status == "OK") {
              </e:if>
              MessageBoxExt.ajax({
                type: "post",
                url: GV.ctxPath + "/user/<e:property value="@method"/>.action",
                data: $("#roleForm").serialize(),
                style: "NONE",
                checkSuccess: function (data) {
                  if (data.status == "error") {
                    MessageBoxExt.alert(data.message);
                  }
                  else {
                    MessageBoxExt._styleRedirect("操作成功, 点击确定跳转到用户列表",
                            GV.ctxPath + "/user/list.action"
                    );
                  }
                  return true;
                }
              });
              <e:if test="@method == 'create'">
              return true;
            }
            else {
              MessageBoxExt.alert(data.message);
              return true;
            }
          }
        });
        </e:if>
      }
    });
  });
  function loadUserList() {
    var departmentId = $.trim(("#departmentId").val());
    $("#loginName").empty();
    $.ajax({
      url: "<%=request.getContextPath()%>/user/userList.action",
      method: "POST",
      data: {
        "departmentId": departmentId
      },
    }).success(function (data) {
              // 重新封装 select 下拉菜单
              $(data).each(function (index, element) {
                $("#loginName").append("<option value='" + element.id + "'>" + element.loginName + "</option>");
              });
            });
  }
</script>
</body>
</html>
﻿
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>

<%@ taglib prefix="e" uri="/emvc-tags" %>
<%@ taglib prefix="q" uri="/queryui-tags" %>
<html>
<head>
  <title>用户列表</title>
</head>
<body>
<div class="Container">
  <div class="Content fontText">
    <form action="" id="form" method="get">
      <div class="information">
        <div class="search_tit">
          <h1 class="fw fleft f14">用户列表</h1>
          <a href="<%=request.getContextPath()%>/user/create.action" style="margin-right:20px;float:right">[导入用户]</a>
        </div>
        <div class="clearer"></div>
        <div class="search_con">
          <ul class="fix">
            <li>
              <label class="text_tit">登录名：</label>
              <e:textfield name="loginName" class="input_text"/>
            </li>
            <li>
              <label class="text_tit">角色组：</label>
              <e:select class="select" name="roleId" headerKey="" headerValue="全部" listKey="id" listValue="name"
                        list="@roleList"/>
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
      <q:querytable queryKey="queryUser" class="list" formId="form">
        <q:nodata>无数据</q:nodata>
        <q:column title="" escape="false" id="forSelectAll" width="30px">
          <input type="checkbox" name="userIds" value="<e:property value="@user_id"/>"/>
        </q:column>
        <%--<q:column title="编号" value="@user_id" width="60px" id="@user_id"/>--%>
        <q:column title="用户名" width="120px">
          <a href="<%=request.getContextPath()%>/stat/list.action?dataType=user&loginName=<e:property value="@login_name"/>"><e:property
                  value="@login_name"/></a>
        </q:column>
        <q:column title="用户组" value="@role_name"/>
        <q:column title="最后使用的数据源" value="@ds_name" width="120px"/>
        <q:column title="可用操作" width="100px">
          <a href="javascript:void(0)" onclick="update(<e:property value="@user_id"/>, 'edit');">编辑</a>
          <a href="javascript:void(0)" onclick="deleteConfirm(<e:property value="@user_id"/>, '用户', 'user');">删除</a>
        </q:column>
      </q:querytable>
    </div>
  </div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/databank.min.js?v=20140116"></script>
<script>
  $(function () {
    $("#forSelectAll").html("<input type='checkbox' id='selectAll' name='userId'/>");
    $("#selectAll").click(function () {
      $('input[type="checkbox"][name="userIds"]').attr("checked", this.checked);
    });
  });
  function update(id) {
    window.location.href = "<%=request.getContextPath()%>/user/update.action?id=" + id;
  }
</script>
</body>
</html>
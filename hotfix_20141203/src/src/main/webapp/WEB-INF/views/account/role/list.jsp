﻿
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>

<%@ taglib prefix="e" uri="/emvc-tags" %>
<%@ taglib prefix="q" uri="/queryui-tags" %>
<html>
<head>
  <title>角色列表</title>
</head>
<body>
<div class="Container">
  <div class="Content fontText">
    <form action="" id="form" method="get">
      <div class="information">
        <div class="search_tit">
          <h1 class="fw fleft f14">角色列表</h1>
          <a href="<%=request.getContextPath()%>/role/create.action" style="margin-right:20px;float:right">[新建角色]</a>
        </div>
        <div class="clearer"></div>
        <div class="search_con">
          <ul class="fix">
            <li>
              <label class="text_tit">角色名：</label>
              <e:textfield name="roleName" class="input_text"/>
            </li>
            <li>
              <label class="text_tit">数据源：</label>
              <e:select class="select" name="dsId" headerKey="" headerValue="全部" listKey="id" listValue="name"
                        list="@dsList"/>
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
      <q:querytable queryKey="queryRole" class="list" formId="form">
        <q:nodata>无数据</q:nodata>
        <q:column title="" escape="false" id="forSelectAll" width="30px">
          <input type="checkbox" name="roleIds" value="<e:property value="@role_id"/>"/>
        </q:column>
        <%--<q:column title="编号" value="@role_id" width="60px" id="@role_id"/>--%>
        <q:column title="角色名" value="@role_name" width="120px"/>
        <q:column title="有权限的数据源ID" value="@permissions"/>
        <q:column title="可用操作" width="100px">
          <a href="javascript:void(0)" onclick="update(<e:property value="@role_id"/>, 'edit');">编辑</a>
          <a href="javascript:void(0)" onclick="deleteConfirm(<e:property value="@role_id"/>, '角色', 'role');">删除</a>
        </q:column>
      </q:querytable>
    </div>
  </div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/databank.min.js?v=20140116"></script>
<script>
  $(function () {
    $("#forSelectAll").html("<input type='checkbox' id='selectAll' name='roleId'/>");
    $("#selectAll").click(function () {
      $('input[type="checkbox"][name="roleIds"]').attr("checked", this.checked);
    });
  });
  function update(id) {
    window.location.href = "<%=request.getContextPath()%>/role/update.action?id=" + id;
  }
</script>
</body>
</html>
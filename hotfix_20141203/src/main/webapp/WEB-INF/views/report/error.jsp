<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>

<%@ taglib prefix="e" uri="/emvc-tags" %>
<%@ taglib prefix="q" uri="/queryui-tags" %>
<html>
<head>
  <title>出错码查询</title>
</head>
<body>
<div class="Container">
  <div class="Content fontText">
    <form action="" id="form" method="get">
      <div class="information">
        <div class="search_tit">
          <h1 class="fw fleft f14">出错码查询</h1>
        </div>
        <div class="clearer"></div>
        <div class="search_con">
          <ul class="fix">
            <li>
              <label class="text_tit">出错码：</label>
              <e:textfield class="input_text" name="sqlState"/>
            </li>
            <li>比如：SQLCODE=-104, SQLSTATE=42601</li>
            <li>请输入42601进行查询</li>
          </ul>
          <div class="clearer"></div>
          <div class="btn">
            <input type="submit" class="btn_sure fw" value="查 询"/>
            <input type="button"  class="btn_cancel fw" value="清 空" onclick="clearAll();"/>
          </div>
          <div class="clearer"></div>
        </div>
      </div>
    </form>
    <!--search End-->
    <div class="clearer"></div>
    <div class="">
      <q:querytable queryKey="queryError" class="list" formId="form">
        <q:nodata>无数据</q:nodata>
        <q:column title="出错码" value="@sqlstate" width="100px"/>
        <q:column title="解释" value="@explanation"/>
      </q:querytable>
    </div>
  </div>
</div>
</body>
</html>
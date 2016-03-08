<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>

<%@ taglib prefix="e" uri="/emvc-tags" %>
<%@ taglib prefix="q" uri="/queryui-tags" %>
<html>
<head>
  <title>笔记列表</title>
</head>
<body>
<div class="Container">
  <div class="Content fontText">
    <form action="" id="form" method="get">
      <div class="information">
        <div class="search_tit">
          <h1 class="fw fleft f14">笔记列表</h1>
        </div>
        <div class="clearer"></div>
        <div class="search_con">
          <ul class="fix">
            <li>
              <label class="text_tit">数据源：</label>
              <e:select class="select" name="dsId" headerKey="" headerValue="全部" listKey="id" listValue="name"
                        list="@dsList"/>
            </li>
            <li>
              <label class="text_tit">笔记名称：</label>
              <e:textfield name="noteName" class="input_text"/>
            </li>
            <li>
              <label class="text_tit">查询语句：</label>
              <e:textfield name="query" class="input_text"/>
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
      <q:querytable queryKey="queryNote" class="list" formId="form">
        <q:nodata>无数据</q:nodata>
        <q:param name="userId" value="@userId"/>
        <%--<q:column title="" escape="false" id="forSelectAll" width="30px">--%>
        <%--<input type="checkbox" name="noteIds" value="<e:property value="@note_id"/>"/>--%>
        <%--</q:column>--%>
        <%--<q:column title="编号" value="@note_id" width="60px" id="@note_id"/>--%>
        <q:column title="笔记名称" value="@note_name" width="220px"/>
        <q:column title="数据源" value="@ds_name" width="140px"/>
        <q:column title="Schema" value="@note_schema" width="100px"/>
        <q:column title="查询语句" value="@note_query"/>
        <q:column title="次数" value="@note_used_times" width="30px"/>
        <q:column title="可用操作" width="230px">
          <a href="javascript:void(0)" onclick="update(<e:property value="@note_id"/>, 'query');">执行</a>
          <a href="javascript:void(0)" onclick="update(<e:property value="@note_id"/>, 'excel');">下载Excel</a>
          <a href="javascript:void(0)" onclick="update(<e:property value="@note_id"/>, 'csv');">下载CSV</a>
          <a href="javascript:void(0)" onclick="update(<e:property value="@note_id"/>, 'edit');">编辑</a>
          <a href="javascript:void(0)" onclick="deleteConfirm(<e:property value="@note_id"/>, '查询笔记', 'note');">删除</a>
        </q:column>
      </q:querytable>
    </div>
  </div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/databank.min.js?v=20140116"></script>
<script>
  $(function () {
    //$("#forSelectAll").html("<input type='checkbox' id='selectAll' name='noteId'/>");
//		$("#selectAll").click(function() {
//			$('input[type="checkbox"][name="noteIds"]').attr("checked",this.checked);
//		});
  });
  function update(noteId, type) {
    var url = GV.ctxPath + "/note/update.action?noteId=" + noteId + "&type=" + type;
    window.location.href = url;
  }
</script>
</body>
</html>